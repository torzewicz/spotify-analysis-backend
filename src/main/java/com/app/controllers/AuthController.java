package com.app.controllers;

import com.app.models.user.*;
import com.app.security.SecurityConstants;
import com.app.security.filters.JwtUtils;
import com.app.services.EmailService;
import com.app.services.UserService;
import com.app.utils.LogUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping("/auth")
@Log4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        log.info(loginRequest.getUsername() + " is logging now, from: " + getIp(request));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User userByUsername = userService.getUserByUsername(loginRequest.getUsername());
        if (userByUsername != null) {
            if (userByUsername.isEnabled()) {
                if (userService.checkVerificationByUsername(loginRequest.getUsername())) {
                    log.info(LogUtils.getLogMessageWithUsername(userByUsername, "User verified"));


                    return ResponseEntity.ok(new JwtResponse(jwt, new Date((new Date()).getTime() + SecurityConstants.EXPIRATION_TIME).getTime()));
                } else {
                    log.info(LogUtils.getLogMessageWithUsername(userByUsername, "User not verified"));
                    return ResponseEntity
                            .ok()
                            .body(new Response("Verify your email address", ""));
                }
            } else {
                return ResponseEntity
                        .ok()
                        .body(new Response("Your account is disabled", ""));
            }
        } else {
            return null;
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<Object> verifyUser(@Valid @RequestBody VerifyRequest verifyRequest, HttpServletRequest request) {
        log.info(verifyRequest.getEmail() + " is verifying now, from: " + getIp(request));
        if (userService.checkCodeByEmail(verifyRequest.getEmail(), verifyRequest.getCode())) {
            userService.verifyUserByEmail(verifyRequest.getEmail());
            return ResponseEntity
                    .ok()
                    .body(new Response("Verification success!", ""));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new Response("Verification failed!", ""));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody @Valid User signUpRequest, HttpServletRequest request) {
        log.info("Signup request from: " + signUpRequest.getUsername() + " " + " " + signUpRequest.getEmail() + " ip: " + getIp(request));
        if (userService.checkExistenceByUsername(signUpRequest.getUsername())) {
            log.warn("User with username: " + signUpRequest.getUsername() + " already exists.");
            return ResponseEntity
                    .badRequest()
                    .body(new Response("This username is taken!", ""));
        }
        if (userService.checkExistenceByEmail(signUpRequest.getEmail())) {
            log.warn("User with email: " + signUpRequest.getEmail() + " already exists.");
            return ResponseEntity
                    .badRequest()
                    .body(new Response("This email is taken!", ""));
        }

        Random rnd = new Random();
        Integer code = rnd.nextInt(999999);
        String verificationCode = String.format("%06d", code);

        try {
            emailService.sendEmail(signUpRequest.getEmail(), verificationCode);
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Response("Cannot verify email address!", ""));
        }

        signUpRequest.setRole(RoleType.USER);
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        signUpRequest.setVerificationCode(verificationCode);

        User user = userService.saveUser(signUpRequest);
        user.setPassword(null);
        user.setConnectedToSpotify(false);
        user.setEnabled(true);
        return ResponseEntity.ok(user);
    }

    public static String getIp(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-FORWARDED-FOR");
        if (remoteAddr != null && !remoteAddr.equals("")) {
            return remoteAddr;
        }
        return request.getRemoteAddr();
    }
}

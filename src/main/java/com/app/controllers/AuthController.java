package com.app.controllers;

import com.app.models.user.*;
import com.app.repositories.user.UserRepository;
import com.app.security.SecurityConstants;
import com.app.security.filters.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/auth")
@Log4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        log.info(loginRequest.getUsername() + " is logging now, from: " + getIp(request));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt, new Date((new Date()).getTime() + SecurityConstants.EXPIRATION_TIME).getTime()));
    }


    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody @Valid User signUpRequest, HttpServletRequest request) {
        log.info("Signup request from: " + signUpRequest.getUsername() + " " + " " + signUpRequest.getEmail() + " ip: " + getIp(request));
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            log.warn("User with username: " + signUpRequest.getUsername() + " already exists.");
            return ResponseEntity
                    .badRequest()
                    .body(new Response("This username is taken!", ""));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            log.warn("User with email: " + signUpRequest.getEmail() + " already exists.");
            return ResponseEntity
                    .badRequest()
                    .body(new Response("This email is taken!", ""));
        }


        signUpRequest.setRole(RoleType.USER);
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        User user = userRepository.save(signUpRequest);
        user.setPassword(null);
        user.setConnectedToSpotify(false);
        return ResponseEntity.ok(user);
    }

    public static String getIp(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-FORWARDED-FOR");
        if (remoteAddr != null  && !remoteAddr.equals("")) {
            return  remoteAddr;
        }

        return request.getRemoteAddr();
    }
}

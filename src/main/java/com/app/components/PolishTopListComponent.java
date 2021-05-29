package com.app.components;

import com.app.models.spotify.TopListEntry;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
public class PolishTopListComponent {

    private static final List<TopListEntry> topListEntryList = Arrays.asList(
            new TopListEntry("7eBYMVok43QesT0KnuIkjn", "BUBBLETEA", 1),
            new TopListEntry("0VjIjW4GlUZAMYd2vXMi3b", "Blinding Lights", 2),
            new TopListEntry("2Y1Nd54Jrgciu2HtpP5ymM", "Impreza", 3),
            new TopListEntry("0vIdj6ZxZrHhHjkbsLHap8", "Polskie Tango", 4),
            new TopListEntry("78L2RUFISfqkmZMTCLYQJC", "100 dni do matury", 5),
            new TopListEntry("24Yi9hE78yPEbZ4kxyoXAI", "Roses - Imanbek Remix", 6),
            new TopListEntry("0xwpK0H2aL5YxREvcNw4Pe", "Szampan", 7),
            new TopListEntry("3ZjytV5BojgvkPnXdY5zV2", "Patointeligencja", 8),
            new TopListEntry("0XthAWvbDoOunjDceV6Jq2", "VOGUE", 9),
            new TopListEntry("2yjSMIxii1fcnrf1R9tU4g", "TOKYO2020", 10),
            new TopListEntry("2sTuKKRZI1ciK5tflR00YE", "GOMBAO 33", 11),
            new TopListEntry("2pIZ5W6m2fBqWwnTEGx5mK", "Niedostępny (prod. by Olek) (feat. DMN)", 12),
            new TopListEntry("2qX1iJIRJDlVsBQbQkz6L0", "Billy Kid", 13),
            new TopListEntry("6UelLqGlWMcVH1E5c4H7lY", "Watermelon Sugar", 14),
            new TopListEntry("2kYgryHD0pSjhorV2DOdxc", "Drift", 15),
            new TopListEntry("2XU0oxnq2qxCpomAAuJY8K", "Dance Monkey", 16),
            new TopListEntry("0vPVqOKo8LWVganQgBZS5y", "Schodki", 17),
            new TopListEntry("3xlk3SVStmEGEWx5FtqpkW", "Melodia", 18),
            new TopListEntry("3IYs5Krfc09hLqpGfT1DF6", "Rainman", 19),
            new TopListEntry("3PfIrDoz19wz7qK7tYeu62", "Don't Start Now", 20),
            new TopListEntry("55sNf76dIok3ddZtFiNaaf", "Jesteś ładniejsza niż na zdjęciach (na zawsze)", 21),
            new TopListEntry("2Gge7Q5ixaEuZFBMlu7cQW", "Anioły i Demony", 22),
            new TopListEntry("2fuA2bAFbVidQUdCiLjuG7", "A nie pamiętasz jak?", 23),
            new TopListEntry("30OnKqCINi9fRJyQTEZeDz", "Będzie Lepiej", 24),
            new TopListEntry("4mW3jXs0vwWVAXzrkWXx2Y", "Język ciała", 25),
            new TopListEntry("2ELxOAAcgZO6dqMPiDMjVV", "SZUBIENICAPESTYCYDYBROŃ", 26),
            new TopListEntry("4bpShq8coki3AlMsXuFCDa", "W PIĄTKI LEŻĘ W WANNIE", 27),
            new TopListEntry("5wOAQXIydVWJNatT6nKwhz", "Floyd Mayweather", 28),
            new TopListEntry("38r67NnLgSMEGhilk9QhF4", "Mogę Dziś Umierać", 29),
            new TopListEntry("7BGYwpS2RJ9Cp6YB6H0Xgd", "Biblioteka Trap", 30),
            new TopListEntry("5lhg8Z5fEnGmY1le7SeHwU", "Surfer", 31),
            new TopListEntry("3AzjcOeAmA57TIOr9zF1ZW", "Physical", 32),
            new TopListEntry("7eJMfftS33KTjuF7lTsMCx", "death bed (coffee for your head)", 33),
            new TopListEntry("2nFaT0t36H6UX3wN3Vxvjm", "California", 34),
            new TopListEntry("2KpCXX2rQUYgoaEXsxEG6H", "Poza kontrolą", 35),
            new TopListEntry("4pzRQIRRHXYsAryBKXALCF", "hot coffee", 36),
            new TopListEntry("3gG4bxTKqZgX199TF22Cko", "Sexoholik", 37),
            new TopListEntry("1p0GVV1UIllj5zJz2Z0Hzs", "#Hot16Challenge2", 38),
            new TopListEntry("19yD0Yreis6m4FFdfvCSJZ", "Morgan", 39),
            new TopListEntry("4fsZcOEWUxFyDe1ENTbvLM", "@ (trailer)", 40),
            new TopListEntry("017PF4Q3l4DBUiWoXk4OWT", "Break My Heart", 41),
            new TopListEntry("7BF6s1W1W9filnjQkNYasL", "Wschód (lubię zapierdalać)", 42),
            new TopListEntry("5vUCXkWBLzqwtzN5YJotGS", "JESIEŃ", 43),
            new TopListEntry("07Agqdd4SD83JRgN19OjdW", "Magenta (prod. Auer)", 44),
            new TopListEntry("6STE4z6PBDVmCsIHejY66L", "Brzydkie Rzeczy", 45),
            new TopListEntry("0biUEKHyKAPBDLcrjCUFsu", "Opowieści z Doliny Smoków", 46),
            new TopListEntry("4DVPJXPqKLIon9BHklap1w", "LECI NOWY FUTURE", 47),
            new TopListEntry("3KeWngnWN1REq9d26ZanKj", "Wybory", 48),
            new TopListEntry("6qa5AvDGpHe9j8EczP8Xe9", "Bali", 49),
            new TopListEntry("3H7ihDc1dqLriiWXwsc2po", "Breaking Me", 50),
            new TopListEntry("6KMvWbvvvD205ZhvXmjxVr", "Skrable", 51),
            new TopListEntry("0zCRgUgbdPY8GReA4mi7T8", "Hotel Maffija", 52),
            new TopListEntry("2dvxMlIfGY90Wvw8OSgZoF", "1998 (mam to we krwi)", 53),
            new TopListEntry("0nbXyq5TXYPCO7pr3N8S4I", "The Box", 54),
            new TopListEntry("4ocfGuRDytBDjTeJLDIDjL", "Ona By Tak Chciała", 55),
            new TopListEntry("7yvfHQ12Q0sFPLST4M0x7Z", "Bestia", 56),
            new TopListEntry("2rRJrJEo19S2J82BDsQ3F7", "Falling", 57),
            new TopListEntry("5iyZwawawLjHYpX4MxUKVF", "Salt", 58),
            new TopListEntry("3Dv1eDb0MEgF93GpLXlucZ", "Say So", 59),
            new TopListEntry("4glBRF3uJCf3bTG4hhIhpn", "Chłopaki nie płaczą", 60),
            new TopListEntry("0C1LsduE10DnOsZNiJxqD9", "Młody Boss", 61),
            new TopListEntry("4seR2zKmDX8LLSFEyum42k", "ROMANTICPSYCHO", 62),
            new TopListEntry("62aP9fBQKYKxi7PDXwcUAS", "ily (i love you baby) (feat. Emilee)", 63),
            new TopListEntry("2Fxmhks0bxGSBdJ92vM42m", "bad guy", 64),
            new TopListEntry("7BXn0iiLErvA2k1IIIweNO", "Never Let Me Down", 65),
            new TopListEntry("47vY0q2TEkkPsGNWtccNlZ", "NICKI", 66),
            new TopListEntry("3jjujdWJ72nww5eGnfs2E7", "Adore You", 67),
            new TopListEntry("7a53HqqArd4b9NF4XAmlbI", "Kings & Queens", 68),
            new TopListEntry("4SDdeq8HUaKPUGiE4WMASH", "Outside", 69),
            new TopListEntry("5lzHp4xQ5kioRYu1jG3Q54", "PDW", 70),
            new TopListEntry("4yP4P2pHqtejjQ1l12J8bA", "Testarossa", 71),
            new TopListEntry("3A2kl8G8oRUbyXceaEInjD", "HOOD ANGEL", 72),
            new TopListEntry("5s6jooT9ZcUjjD5qVx9B5K", "8 kobiet - Remix", 73),
            new TopListEntry("2tnVG71enUj33Ic2nFN6kZ", "Ride It", 74),
            new TopListEntry("7ju97lgwC2rKQ6wwsf9no9", "Rain On Me (with Ariana Grande)", 75),
            new TopListEntry("7szuecWAPwGoV1e5vGu8tl", "In Your Eyes", 76),
            new TopListEntry("25zU3Mo74b4uzIqop5Hc4T", "ASPARTAM", 77),
            new TopListEntry("1xQ6trAsedVPCdbtDAmk0c", "Savage Love (Laxed - Siren Beat)", 78),
            new TopListEntry("7ki8ooqJM5zV39pBe9qlp4", "Gubię kroki", 79),
            new TopListEntry("0GZfZOUpFBfArX6F50LXyf", "Żółte flamastry i grube katechetki", 80),
            new TopListEntry("5gOnvkwR6gTiSsfU7I2IbB", "Lucky Punch", 81),
            new TopListEntry("7ytR5pFWmSjzHJIeQkgog4", "ROCKSTAR (feat. Roddy Ricch)", 82),
            new TopListEntry("5VfxZFMY8Y5v47iZQMcIFI", "NIEPŁACZĘPONOTREDAME", 83),
            new TopListEntry("7qEHsqek33rTcFNT9PFqLf", "Someone You Loved", 84),
            new TopListEntry("0KNdXptuZDtiU28PpalmVZ", "Milion", 85),
            new TopListEntry("6v3KW9xbzN5yKLt9YKDYA2", "Señorita", 86),
            new TopListEntry("6Gn7Tpem8ydQpS5aIZWV57", "Mata Montana", 87),
            new TopListEntry("0gwmifwzIBArZYYwUNCuJb", "BLUE", 88),
            new TopListEntry("21jGcNKet2qwijlDFuPiPb", "Circles", 89),
            new TopListEntry("0xH6vEdMC6IltXVSK1mPst", "Kosmita", 90),
            new TopListEntry("3ZCTVFBt2Brf31RLEnCkWJ", "everything i wanted", 91),
            new TopListEntry("78qd8dvwea0Gosb6Fe6j3k", "Boss Bitch", 92),
            new TopListEntry("2gMXnyrvIjhVBUZwvLZDMP", "Before You Go", 93),
            new TopListEntry("3kWxHkGNqsToeOpCHZvkwk", "FPS", 94),
            new TopListEntry("24XLOVBFwlv8bR1eLFeUik", "Płuca zlepione topami", 95),
            new TopListEntry("3GZoWLVbmxcBys6g0DLFLf", "Supalonely (feat. Gus Dapperton)", 96),
            new TopListEntry("2EaWqg4KUDVx6gVbgQ4Lyw", "Przebój nocy", 97),
            new TopListEntry("5Zc1iY6yaGrNIgSzCgca8H", "jolka (trailer)", 98),
            new TopListEntry("35UvOH0tRlSCdtWtaXuiWh", "Najnowszy Klip", 99),
            new TopListEntry("7CHi4DtfK4heMlQaudCuHK", "Lose Control", 100)
    );

    public Integer getPlaceById(String id) {
        Optional<TopListEntry> listEntry = topListEntryList.stream().filter(topListEntry -> topListEntry.getId().equals(id)).findAny();
        return listEntry.isPresent() ? listEntry.get().getTopScore() : null;
    }


}

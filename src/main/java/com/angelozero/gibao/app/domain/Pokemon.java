package com.angelozero.gibao.app.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

    private Sprites sprites;

    @ToString
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sprites {

        private Other other;
    }

    @ToString
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Other {

        @JsonProperty("official-artwork")
        private OfficialArtWork officialArtWork;
    }

    @ToString
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OfficialArtWork {

        @JsonProperty("front_default")
        private String frontDefault;
    }
}

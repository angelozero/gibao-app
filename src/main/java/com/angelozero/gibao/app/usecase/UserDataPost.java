package com.angelozero.gibao.app.usecase;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum UserDataPost {

    BATS("bats"),
    FLOG("flog"),
    LORANN("lorann");

    private final String secretUser;

    UserDataPost(String secretUser) {
        this.secretUser = secretUser;
    }

    public static boolean contains(String user) {
        return Arrays.stream(UserDataPost.values())
                .anyMatch(secretUser -> secretUser.getSecretUser().equals(user.toLowerCase()));
    }
}

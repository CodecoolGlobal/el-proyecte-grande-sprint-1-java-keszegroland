package com.codecool.backend.model.payload;

import java.util.Set;

public record JwtResponse(String jwt, String username, Set<String> roles) {
}


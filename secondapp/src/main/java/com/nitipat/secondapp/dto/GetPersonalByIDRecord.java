package com.nitipat.secondapp.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GetPersonalByIDRecord(
        @NotNull
        UUID id
) {
}

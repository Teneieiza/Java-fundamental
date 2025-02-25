package com.nitipat.secondapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PostPersonalRecord(
        @NotNull
        @Size(min = 1, max = 12)
        String firstname,
        @NotNull
        @Size(min = 1, max = 12)
        String lastname
) {
}

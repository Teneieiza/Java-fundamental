package com.nitipat.secondapp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PostTenRecord(
        @NotNull
        @Size(min = 2, max = 10)
        String name,
        @NotNull
        @Min(1)
        @Max(99)
        Integer age,
        @NotNull
        @Size(min = 2, max = 10)
        String pet
) {
}

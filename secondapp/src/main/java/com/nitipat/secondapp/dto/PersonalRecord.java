package com.nitipat.secondapp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.ZonedDateTime;

public record PersonalRecord(

        @NotNull
        @Size(min = 1, max = 20)
        String firstname,

        @NotNull
        @Size(min = 1, max = 20)
        String lastname,

        @NotNull
        @Size(min = 1, max = 10)
        String nickname,

        @NotNull
        @Min(1)
        @Max(99)
        Integer age

) {
}

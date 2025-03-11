package com.nitipat.secondapp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PutPersonalRecord(

        String firstname,


        String lastname,


        String nickname,


        Integer age

) {
}

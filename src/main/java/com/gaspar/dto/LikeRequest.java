package com.gaspar.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LikeRequest {
    @NotNull
    @NotEmpty
    String customerEmail;

    @NotNull
    Integer bookId;
}

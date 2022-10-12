package com.example.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

    private String status;

    private String code;

    private T data;
}

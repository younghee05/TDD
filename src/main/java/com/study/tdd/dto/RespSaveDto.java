package com.study.tdd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RespSaveDto {
    private Boolean isSaveSuccess;
    private String message;
}

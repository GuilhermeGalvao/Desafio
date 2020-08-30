package com.example.Desafio.controller.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UniqueTypeResponse {

    private Long id;
    private String n;
    private Integer k;
    private Integer result;
}

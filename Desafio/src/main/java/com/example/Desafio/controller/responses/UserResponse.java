package com.example.Desafio.controller.responses;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
@Builder
public class UserResponse {

    private String id;
    private String name;
    private String email;
    private List<UniqueTypeResponse> uniqueTypes;
}

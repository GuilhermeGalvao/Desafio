package com.example.Desafio.controller;

import com.example.Desafio.controller.responses.UniqueTypeResponse;
import com.example.Desafio.service.UniqueTypeService;
import com.example.Desafio.entities.UniqueType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "UniqueTypeController", description = "REST APIs related to Unique Type Entity")
@RestController
@RequestMapping("/uniqueType")
@Slf4j
public class UniqueTypeController {

    @Autowired
    private UniqueTypeService uniqueTypeService;

    @ApiOperation(value = "Save Unique Type ", response = String.class, tags = "UniqueTypeController")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK")})
    @GetMapping(value="/save")
    public ResponseEntity save(@RequestParam("n") String n, @RequestParam("k") Integer k, @RequestParam(value = "user", required = false) String id){
        log.info("Save Unique Type: "+n + " " + k + " " + id);
        Integer uniqueType = uniqueTypeService.save(n, k, id);
        return  ResponseEntity.ok().body(uniqueType);
    }

    @ApiOperation(value = "Get list of Unique Types of User in the System ", response = Iterable.class, tags = "UniqueTypeController")
    @GetMapping(value="/get/{id}")
    public ResponseEntity get(@PathVariable("id") String id){
        log.info("Get list of unique types related to user: " +  id);
        List<UniqueTypeResponse> uniqueTypeResponseList = new ArrayList<>();
        if(uniqueTypeService.getAllUniqueTypesOfUser(id) != null){
            for(UniqueType uniqueType: uniqueTypeService.getAllUniqueTypesOfUser(id)){
                UniqueTypeResponse uniqueTypeResponse = UniqueTypeResponse.builder()
                        .id(uniqueType.getId())
                        .k(uniqueType.getK())
                        .n(uniqueType.getN())
                        .result(uniqueType.getResult()).build();
                uniqueTypeResponseList.add(uniqueTypeResponse);
            }
        }

        return  ResponseEntity.ok().body(uniqueTypeResponseList);
    }



}

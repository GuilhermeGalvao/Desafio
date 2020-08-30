package com.example.Desafio.controller;

import com.example.Desafio.controller.responses.UniqueTypeResponse;
import com.example.Desafio.controller.responses.UserResponse;
import com.example.Desafio.entities.UniqueType;
import com.example.Desafio.entities.User;
import com.example.Desafio.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

@Api(value = "UserController", description = "REST APIs related to User Entity")
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get User in the System ", response = UserResponse.class, tags = "UserController")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 400, message = "Bad Request!")})
    @GetMapping(value = "/get/{id_user}")
    public ResponseEntity<Object> get(@PathVariable("id_user") String id_user) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidKeyException, InvalidKeySpecException {
        log.info("Get User: " +  id_user);
        User requested = userService.get(id_user);
        if(requested != null){
            List<UniqueTypeResponse> uniqueTypeResponseList = new ArrayList<>();
            for(UniqueType uniqueType: requested.getUniqueTypes()){
                UniqueTypeResponse uniqueTypeResponse = UniqueTypeResponse.builder()
                        .id(uniqueType.getId())
                        .k(uniqueType.getK())
                        .n(uniqueType.getN())
                        .result(uniqueType.getResult()).build();
                uniqueTypeResponseList.add(uniqueTypeResponse);
            }
            UserResponse userResponse = UserResponse.builder()
                                    .id(requested.getId())
                                    .email(requested.getEmail())
                                    .name(requested.getName())
                                    .uniqueTypes(uniqueTypeResponseList).build();
            return ResponseEntity.ok().body(userResponse);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor inserir chave publica");
    }

    @ApiOperation(value = "Add new User in the System ", response = UserResponse.class, tags = "UserController")
    @PostMapping(value = "/addNew", consumes="application/json" )
    public UserResponse addNew(@RequestBody @ApiParam User data){
        log.info("Add new user: " +  data.getName() + " " + data.getEmail());
        User newUser = userService.save(data);
        UserResponse userResponse = UserResponse.builder()
                .id(newUser.getId())
                .email(newUser.getEmail())
                .name(newUser.getName())
                .uniqueTypes(null).build();
        return userResponse;
    }
    @ApiOperation(value = "Update a User in the System ", response = UserResponse.class, tags = "UserController")
    @PutMapping(value="/update/{id}", consumes = "application/json")
    public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody User data){
        log.info("Update user: " +  id );
        User userRequested = userService.update(id, data);
        if (userRequested == null)
            return ResponseEntity.notFound().build();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Insert a Public key of User in the System ", response = UserResponse.class, tags = "UserController")
    @PutMapping(value="/insertPublicKey/{id}", consumes = "text/plain")
    public ResponseEntity<Object> insertPublicKey(@PathVariable("id") String id, @RequestBody String publicKey){
        log.info("Insert Public Key of User: " +  id + " " + publicKey);
        try{
            userService.insertPublicKey(id, publicKey);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            log.info("ERR USER NOT FOUND: " + e.getStackTrace());
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Delete a User in the System ", response = UserResponse.class, tags = "UserController")
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") String id){
        log.info("Delete User: " +  id);
        try{
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            log.info("ERR: " + e.getStackTrace());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

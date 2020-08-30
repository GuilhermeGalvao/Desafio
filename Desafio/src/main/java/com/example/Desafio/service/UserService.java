package com.example.Desafio.service;

import com.example.Desafio.entities.User;
import com.example.Desafio.exceptions.UserNotFoundException;
import com.example.Desafio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

public class UserService {

    @Autowired
    private UserRepository userRepositorie;

    @Autowired
    private EncryptService encryptService;

    public UserService(){

    }

    public List<User> findAll() {
        return userRepositorie.findAll();
    }

    public User getUser(String request_id){
        Optional<User> user = userRepositorie.findById(request_id);
        if(user.isPresent() ){
            return user.get();
        }
        return null;
    }




    public User get(String request_id) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException,
            InvalidKeyException, InvalidKeySpecException {
        Optional<User> user = userRepositorie.findById(request_id);
        if(user.isPresent() && user.get().getPublicKey() != null){
            String encryptEmail = encryptService.encript(user.get().getEmail(), user.get().getPublicKey());
            user.get().setEmail(encryptEmail);
            String encryptName = encryptService.encript(user.get().getName(), user.get().getPublicKey());
            user.get().setName(encryptName);
            return user.get();
        }
        return null;
    }


    public User save(User data) {
        return userRepositorie.saveAndFlush(data);
    }

    public User update(String id, User user){
        Optional<User> userRequested = userRepositorie.findById(id);
        if (!userRequested.isPresent())
            return null;
        user.setId(id);
        userRepositorie.save(user);
        return user;
    }

    public String insertPublicKey(String id, String publicKey){
        Optional<User> user = userRepositorie.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("User Not Found");
        user.get().setPublicKey(publicKey);
        userRepositorie.save(user.get());
        return "SAVED";
    }

    public String delete(String id){
        try{
            userRepositorie.deleteById(id);
            return "Deleted";
        }catch (Exception e) {
            throw new UserNotFoundException("USER NOT FOUND");
        }
    }
}

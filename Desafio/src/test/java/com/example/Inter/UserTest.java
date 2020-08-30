package com.example.Inter;

import com.example.Desafio.entities.User;
import com.example.Desafio.repositories.UserRepository;
import com.example.Desafio.service.EncryptService;
import com.example.Desafio.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserTest {

    @InjectMocks
    private  UserService userService;

    @Mock
    private UserRepository userRepositorie;

    @Mock
    private EncryptService encryptService;

    @Before
    public void setup(){
        User user = new User();
        user.setId("1");
        user.setName("Guilherme Galvao");
        user.setEmail("guigalv@hotmail.com");
        user.setPublicKey("publicKey");
        user.setUniqueTypes(new ArrayList<>());
        Mockito.when(userRepositorie.findById("1")).thenReturn(Optional.of(user));

    }

    @Test
    public void testGetUser() throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidKeyException, InvalidKeySpecException {

        Mockito.when(encryptService.encript(Mockito.any(), Mockito.any())).thenReturn("encripted");

        User newUser = userService.get("1");
        assertTrue(newUser.getName() != null );
        assertEquals( newUser.getName(),"encripted");
        assertTrue(newUser.getEmail() != null );
        assertEquals(newUser.getEmail(), "encripted");
    }

    @Test
    public void insertNewUser(){
        User user = new User();
        user.setId("1");
        user.setName("Guilherme Galvao");
        user.setEmail("guigalv@hotmail.com");
        user.setPublicKey("publicKey");
        user.setUniqueTypes(new ArrayList<>());
        Mockito.when(userRepositorie.saveAndFlush(user)).thenReturn(user);
        User userResult = userService.save(user);
        assertTrue(userResult.getId() != null);
    }

    @Test
    public void updateUser(){

        User updatedUser = new User();
        updatedUser.setId("1");
        updatedUser.setName("Guilherme Galvao Teste");
        updatedUser.setEmail("guigalv@hotmail.com");
        updatedUser.setPublicKey("publicKey");
        updatedUser.setUniqueTypes(new ArrayList<>());


        User newUserUpdated = userService.update("1", updatedUser);
        assertTrue(newUserUpdated.getId() != null );
        assertTrue(newUserUpdated.getName() != null );
        assertEquals( "Guilherme Galvao Teste" , newUserUpdated.getName());
        assertTrue(newUserUpdated.getEmail() != null );
        assertEquals("guigalv@hotmail.com", newUserUpdated.getEmail());
        assertTrue(newUserUpdated.getPublicKey() != null );
        assertEquals("publicKey", newUserUpdated.getPublicKey());

    }

    @Test
    public void deleteUser(){
        Mockito.doNothing().when(userRepositorie).deleteById("1");
        assertEquals("Deleted", userService.delete("1"));
        Mockito.verify(userRepositorie).deleteById("1");

    }
}

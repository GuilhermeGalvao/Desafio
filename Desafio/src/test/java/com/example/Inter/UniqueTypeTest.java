package com.example.Inter;

import com.example.Desafio.entities.UniqueType;
import com.example.Desafio.entities.User;
import com.example.Desafio.repositories.UniqueTypeRepository;
import com.example.Desafio.service.UniqueTypeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UniqueTypeTest {

    @InjectMocks
    private UniqueTypeService uniqueTypeService;

    @Mock
    private UniqueTypeRepository uniqueTypeRepository;

    @Test
    public void testUniqueTypeCalculation(){
        Integer result;
        result = uniqueTypeService.uniqueType("1", 1 );
        Assertions.assertEquals(1, result);
        result = uniqueTypeService.uniqueType("1", 0);
        Assertions.assertEquals(1, result);
        result = uniqueTypeService.uniqueType("9875", 4 );
        Assertions.assertEquals(8, result);
    }

    @Test
    public void getUniqueDigitsByUserIdTest(){
        User user = new User();
        user.setId("1");
        user.setName("Guilherme Galvao");
        user.setEmail("guigalv@hotmail.com");
        user.setUniqueTypes(new ArrayList<>());

        UniqueType uniqueTypeResult1 = new UniqueType();
        uniqueTypeResult1.setId(1L);
        uniqueTypeResult1.setUser(user);
        uniqueTypeResult1.setN("9875");
        uniqueTypeResult1.setK(4);
        uniqueTypeResult1.setResult(8);

        UniqueType uniqueTypeResult2 = new UniqueType();
        uniqueTypeResult2.setId(2L);
        uniqueTypeResult2.setUser(user);
        uniqueTypeResult2.setN("1");
        uniqueTypeResult2.setK(1);
        uniqueTypeResult2.setResult(1);

        List<UniqueType> uniqueTypes = new ArrayList<>();
        uniqueTypes.add(uniqueTypeResult1);
        uniqueTypes.add(uniqueTypeResult2);

        Assertions.assertTrue(!uniqueTypes.isEmpty());
        when(uniqueTypeRepository.findByUserId("1")).thenReturn(uniqueTypes);
        List<UniqueType> result = uniqueTypeService.getAllUniqueTypesOfUser("1");
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(8, result.get(1).getResult() );
    }
}

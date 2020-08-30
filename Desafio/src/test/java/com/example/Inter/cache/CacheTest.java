package com.example.Inter.cache;

import com.example.Desafio.cache.Cache;
import com.example.Desafio.entities.UniqueType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class CacheTest {

    @Test
    public void uniqueInsertionCache(){
        Cache cache = new Cache();
        UniqueType uniqueType = new UniqueType();
        uniqueType.setId(1L);
        uniqueType.setN("9875");
        uniqueType.setK(4);
        uniqueType.setResult(8);

        cache.put(uniqueType);
        assertTrue(cache.get().getResult() == 8);
    }

    @Test
    public void cicleInsertionsCache(){
        Cache cache = new Cache();
        for(int i = 0;  i < 10; i ++){
            UniqueType uniqueType = new UniqueType();
            uniqueType.setId(1L);
            uniqueType.setN("9875");
            uniqueType.setK(4);
            uniqueType.setResult(i);
            cache.put(uniqueType);
        }
        assertTrue(cache.get().getResult() == 0);
        UniqueType newUniqueType = new UniqueType();
        newUniqueType.setId(1L);
        newUniqueType.setN("9875");
        newUniqueType.setK(4);
        newUniqueType.setResult(11);
        cache.put(newUniqueType);
        assertTrue(cache.get().getResult() == 11);

        UniqueType uniqueType = new UniqueType();
        uniqueType.setId(1L);
        uniqueType.setN("9875");
        uniqueType.setK(4);
        uniqueType.setResult(12);
        cache.put(uniqueType);
        assertTrue(cache.getCache().get(1).getResult() == 12);

    }

    @Test
    public void cacheSizeLessOrEqualsToTen(){
        Cache cache = new Cache();
        UniqueType newUniqueType = new UniqueType();
        newUniqueType.setId(1L);
        newUniqueType.setN("9875");
        newUniqueType.setK(4);
        newUniqueType.setResult(11);
        cache.put(newUniqueType);
        assertTrue(cache.getCache().size() <= 10);
        for(int i = 0;  i < 12; i ++){
            UniqueType uniqueType = new UniqueType();
            uniqueType.setId(1L);
            uniqueType.setN("9875");
            uniqueType.setK(4);
            uniqueType.setResult(i);
            cache.put(uniqueType);
        }
        assertTrue(cache.getCache().size() <= 10);

    }
}
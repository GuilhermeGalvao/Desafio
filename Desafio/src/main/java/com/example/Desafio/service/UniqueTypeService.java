package com.example.Desafio.service;

import com.example.Desafio.cache.Cache;
import com.example.Desafio.entities.UniqueType;
import com.example.Desafio.entities.User;
import com.example.Desafio.repositories.UniqueTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class UniqueTypeService {


    @Autowired
    private UniqueTypeRepository uniqueTypeRepository;

    @Autowired
    private UserService userService;


    public int uniqueType(String n, int k){
        String number = n;
        for(int i = 0; i < k-1; i ++){
            number += n ;
        }
        while(number.length() > 1){
            int result = 0;
            for(int i = 0; i < number.length(); i ++){
                result += Integer.parseInt(""+ number.charAt(i));
            }
            number = "" + result;
        }
        return Integer.parseInt(number);
    }


    public UniqueTypeService(){

    }

    public Integer save(String n, Integer k, String user_id){
        int result = 0;
        boolean cacheFound = false;
        /**
         * Verify cache
         */
        List<UniqueType> cache = Cache.getInstance().getCache();
        for(UniqueType uniqueType:cache){
            if(uniqueType.getK() == k && uniqueType.getN().equals(n)){
                result = uniqueType.getResult();
                log.info("Cache found: "+ n + " " + k);
                cacheFound = true;
                break;
            }
        }
        UniqueType uniqueTypeResult = new UniqueType();
        uniqueTypeResult.setK(k);
        uniqueTypeResult.setN(n);
        if(!cacheFound){
            result = uniqueType(n, k);
            Cache.getInstance().put(uniqueTypeResult);
        }
        if(user_id == null){
            return result;
        }
        uniqueTypeResult.setResult(result);
        User user = userService.getUser(user_id);
        UniqueType uniqueType = new UniqueType();
        uniqueType.setK(k);
        uniqueType.setN(n);
        uniqueType.setUser(user);
        uniqueType.setResult(result);
        uniqueTypeRepository.saveAndFlush(uniqueType);
        return result;
    }

    public List<UniqueType> getAllUniqueTypesOfUser(String id){
        List<UniqueType> result = uniqueTypeRepository.findByUserId(id);
        for(UniqueType uniqueType: result){
            uniqueType.setUser(null);
        }
        return result;
    }

}

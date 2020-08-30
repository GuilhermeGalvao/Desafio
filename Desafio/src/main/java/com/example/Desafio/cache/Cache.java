package com.example.Desafio.cache;

import com.example.Desafio.entities.UniqueType;

import java.util.ArrayList;
import java.util.List;

public class Cache {

    private List<UniqueType> cache;
    private int index;

    private static Cache _cache = new Cache();

    public static Cache getInstance()
    {
        return _cache;
    }

    Cache()
    {
        cache = new ArrayList<>();
        index = 0;
    }


    public void put(UniqueType value)
    {
        if(cache.size() >= 10){
            cache.set(index%10, value);
            index++;
        }else{
            cache.add(value);
        }
    }

    public UniqueType get()
    {
        return cache.get(0);
    }

    public int getSize()
    {
        return cache.size();
    }

    public List<UniqueType> getCache()
    {
        return cache;
    }


}

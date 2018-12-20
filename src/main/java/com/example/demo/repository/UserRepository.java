package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * user repository
 */
@Repository
public class UserRepository {
    /**
     * 采用内存型的存储 map
     * @param user
     * @return
     */
    private final ConcurrentHashMap<Integer, User> repository = new ConcurrentHashMap<>();

    private final static AtomicInteger idGenerator = new AtomicInteger();

    public boolean save(User user) {

        boolean success = false;
        //id from 1
        Integer id = idGenerator.incrementAndGet();
        user.setId(id);
        return repository.put(id,user)==null;

    }

    public Collection<User> findAll () {
        return repository.values();
    }

}

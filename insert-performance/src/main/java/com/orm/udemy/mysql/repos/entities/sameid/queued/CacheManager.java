package com.orm.udemy.mysql.repos.entities.sameid.queued;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CacheManager<T> {

    private HazelcastInstance hazelcastInstance;

    @PostConstruct
    public void init(){
        hazelcastInstance = Hazelcast.getHazelcastInstanceByName("hazelcast-instance");
    }

    public void save(T movie){
        hazelcastInstance.getList("regularly-changed-list").add(movie);
    }

    public List<T> getAll() {
        return hazelcastInstance.getList("regularly-changed-list");
    }
}

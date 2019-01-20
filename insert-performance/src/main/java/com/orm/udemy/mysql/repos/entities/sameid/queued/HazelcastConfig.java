package com.orm.udemy.mysql.repos.entities.sameid.queued;

import com.hazelcast.config.*;
import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    public Config hazelCastConfig(){
        return new Config()
                .setInstanceName("hazelcast-instance")
                .addMapConfig(
                        new MapConfig()
                                .setName("regularly-changed-value-cache")
                                .setMaxSizeConfig(
                                        new MaxSizeConfig(200000, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)
                                )
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(0)
                )
                .addListConfig(
                        new ListConfig()
                        .setName("regularly-changed-list")
                        .setMaxSize(1000000)
                )
                .setSerializationConfig(
                        new SerializationConfig()
                            .addDataSerializableFactoryClass(DataSerializableFactoryImpl.ID,
                                                             DataSerializableFactoryImpl.class)
                )
                ;

    }

    public static class DataSerializableFactoryImpl implements  DataSerializableFactory {

        static final int MOVIE_DETAILS_TYPE = 2;
        static final int ID = 1;
        static final int MOVIE_TYPE = 1;

        @Override
        public IdentifiedDataSerializable create(int typeId) {
            if (typeId == MOVIE_TYPE) {
                return new Movie();
            } else if (typeId == MOVIE_DETAILS_TYPE){
                return new MovieDetails();
            } else {
                return null;
            }
        }
    }

}
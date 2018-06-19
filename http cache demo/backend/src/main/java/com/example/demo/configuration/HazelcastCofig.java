package com.example.demo.configuration;

import com.example.demo.service.HardToComputeStringService;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import com.hazelcast.config.Config;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class HazelcastCofig {

    @Autowired
    HardToComputeStringService hardToComputeStringService;

    @Bean
    public Config hazelCastConfig(){
        return new Config()
                .setInstanceName("hazelcast-instance")
                .addMapConfig(
                        new MapConfig()
                                .setName("regularly-changed-value-cache")
                                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(0))
                .addMapConfig(
                        new MapConfig()
                                .setName("irregularly-changed-value-cache")
                                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(0))

                ;

    }

    @Scheduled(fixedRate = 10000)
    public void clearCache() {
        hardToComputeStringService.clearCache();
    }

}

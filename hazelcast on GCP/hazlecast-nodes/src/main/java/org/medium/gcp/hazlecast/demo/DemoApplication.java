package org.medium.gcp.hazlecast.demo;

		import com.google.gson.Gson;
        import com.google.gson.GsonBuilder;
		import com.hazelcast.core.Hazelcast;
		import com.hazelcast.core.HazelcastInstance;
		import com.hazelcast.core.IMap;
		import org.springframework.boot.CommandLineRunner;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.context.ApplicationContext;
		import org.springframework.context.annotation.Bean;

		import java.awt.event.InvocationEvent;
        import java.io.BufferedReader;
		import java.io.IOException;
		import java.io.InputStreamReader;
		import java.io.OutputStream;
		import java.lang.reflect.Array;
        import java.lang.reflect.InvocationTargetException;
        import java.net.HttpURLConnection;
		import java.net.MalformedURLException;
		import java.net.ProtocolException;
		import java.net.URL;
        import java.util.*;
        import java.util.logging.Level;
		import java.util.logging.Logger;
		import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
            final Set<HazelcastInstance> allHazelcastInstances = Hazelcast.getAllHazelcastInstances();
            final HazelcastInstance hazelcastInstance = (HazelcastInstance)allHazelcastInstances.toArray()[0];
			final IMap<String, Integer> map = hazelcastInstance.getMap("example-map");
			map.put("one", 1);
            map.put("two", 2);
            map.put("three", 3);
            map.put("four", 4);
            map.put("five", 5);
        };
	}
}

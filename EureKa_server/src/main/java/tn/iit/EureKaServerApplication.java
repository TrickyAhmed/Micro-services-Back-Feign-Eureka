package tn.iit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EureKaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EureKaServerApplication.class, args);
	}

}

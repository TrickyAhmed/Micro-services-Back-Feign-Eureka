package tn.micro.service.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import tn.micro.service.cloud.entity.Address;
import tn.micro.service.cloud.repository.AddressRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class UniversityMonolithicApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityMonolithicApplication.class, args);
	}
}
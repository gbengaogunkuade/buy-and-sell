package com.ogunkuade.microservicesmanager;

import com.ogunkuade.microservicesmanager.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
@EnableFeignClients		//ENABLE SCANNING OF ALL FEIGN CLIENTS
public class MicroservicesManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesManagerApplication.class, args);
	}

}

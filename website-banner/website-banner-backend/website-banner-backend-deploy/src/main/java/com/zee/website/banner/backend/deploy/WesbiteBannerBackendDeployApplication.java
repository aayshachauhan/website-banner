package com.zee.website.banner.backend.deploy;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.zee.website.banner.backend.*"})
public class WesbiteBannerBackendDeployApplication {

	public static void main(String[] args) {
		SpringApplication.run(WesbiteBannerBackendDeployApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
		
	}
}

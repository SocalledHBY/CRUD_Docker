package com.hby.crudbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hby.crudbackend.mapper")
public class CrudBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudBackendApplication.class, args);
	}

}

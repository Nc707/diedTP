package com.deso.etapa_final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.deso.etapa_final.repositories")
public class EtapaFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtapaFinalApplication.class, args);
    }
}

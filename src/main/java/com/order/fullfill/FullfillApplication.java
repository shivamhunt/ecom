package com.order.fullfill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.order.fullfill")
@EnableJpaRepositories(basePackages = "com.order.fullfill.database.repository")
@EntityScan(basePackages = "com.order.fullfill.database.entity")
//@ComponentScan(basePackages = "com.order.fullfill.database.repository")
public class FullfillApplication {

    public static void main(String[] args) {
        SpringApplication.run(FullfillApplication.class, args);
    }

}

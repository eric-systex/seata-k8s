package io.seata.samples.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = "io.seata.samples", exclude = DataSourceAutoConfiguration.class)
@ImportResource("classpath:db-bean/from-datasource-bean.xml")
public class SpringbootMybatisStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisStorageApplication.class, args);
    }

}

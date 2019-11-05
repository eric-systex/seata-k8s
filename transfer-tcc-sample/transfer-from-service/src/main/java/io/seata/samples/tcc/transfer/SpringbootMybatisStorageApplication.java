package io.seata.samples.tcc.transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.remoting.caucho.HessianServiceExporter;

import io.seata.samples.tcc.transfer.service.FirstTccService;

@SpringBootApplication(scanBasePackages = "io.seata.samples", exclude = DataSourceAutoConfiguration.class)
@ImportResource({"classpath:spring/*.xml", "classpath:db-bean/*.xml"})
public class SpringbootMybatisStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisStorageApplication.class, args);
    }
    
    @Autowired
    private FirstTccService firstService;

    @Bean(name = "/api/from")
    public HessianServiceExporter accountService(){
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(firstService);
        exporter.setServiceInterface(FirstTccService.class);
        return exporter;
    }

}

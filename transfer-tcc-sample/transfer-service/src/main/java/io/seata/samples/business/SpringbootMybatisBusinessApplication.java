package io.seata.samples.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import io.seata.samples.tcc.transfer.service.FirstTccService;
import io.seata.samples.tcc.transfer.service.SecondTccService;

@SpringBootApplication(scanBasePackages = "io.seata.samples", exclude = DataSourceAutoConfiguration.class)
@ImportResource({"classpath:spring/*.xml"})
public class SpringbootMybatisBusinessApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisBusinessApplication.class, args);
	}

	@Bean
    public HessianProxyFactoryBean fromClient(){
        HessianProxyFactoryBean factoryBean = new HessianProxyFactoryBean();
        factoryBean.setServiceUrl("http://api-transfer-from:8080/api/from");
        factoryBean.setServiceInterface(FirstTccService.class);
        return factoryBean;
    }
	
	@Bean
    public HessianProxyFactoryBean toClient(){
        HessianProxyFactoryBean factoryBean = new HessianProxyFactoryBean();
        factoryBean.setServiceUrl("http://api-transfer-to:8080/api/to");
        factoryBean.setServiceInterface(SecondTccService.class);
        return factoryBean;
    }
}

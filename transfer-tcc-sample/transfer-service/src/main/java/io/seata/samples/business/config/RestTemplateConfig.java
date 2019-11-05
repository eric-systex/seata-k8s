package io.seata.samples.business.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import io.seata.samples.business.interceptor.SeataRestTemplateInterceptor;

@Configuration
public class RestTemplateConfig {

//	@Autowired
//    private SeataRestTemplateInterceptor seataRestTemplateInterceptor;
	
	@Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
//        List<ClientHttpRequestInterceptor> interceptors = new ArrayList(restTemplate.getInterceptors());
//        interceptors.add(this.seataRestTemplateInterceptor);
//        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
}

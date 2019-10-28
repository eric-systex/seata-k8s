package io.seata.samples.business.client;

import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class StorageClient {

    @Autowired
    private RestTemplate restTemplate;

    public void deduct(String commodityCode, int orderCount) {
        System.out.println("business to storage " + RootContext.getXID());
        String url = "http://sbm-storage-service:8080/api/storage/deduct?commodityCode=" + commodityCode + "&count=" + orderCount;
        try {
            restTemplate.getForEntity(url, Void.class);
        } catch (Exception e) {
            System.err.println("deduct url {} ,error:");
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}

package io.seata.samples.business.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class TransferToClient {

    @Autowired
    private RestTemplate restTemplate;

    public void add(final String accountNo, final double amount) {
        String url = "http://api-transfer-to:8080/api/transfer-to/add?accountNo=" + accountNo + "&amount=" + amount;
        try {
            restTemplate.getForEntity(url, Void.class);
        } catch (Exception e) {
            log.error("add url {} ,error:", url);
            throw new RuntimeException();
        }
    }
}

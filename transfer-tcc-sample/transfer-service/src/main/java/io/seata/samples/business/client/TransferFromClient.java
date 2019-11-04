package io.seata.samples.business.client;

import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class TransferFromClient {

    @Autowired
    private RestTemplate restTemplate;

    public void minus(final String accountNo, final double amount) {
        System.out.println("transfer to transfer-from " + RootContext.getXID());
        String url = "http://api-transfer-from:8080/api/transfer-from/minus?accountNo=" + accountNo + "&amount=" + amount;
        try {
            restTemplate.getForEntity(url, Void.class);
        } catch (Exception e) {
            log.error("minus url {} ,error:", url, e);
            throw new RuntimeException();
        }
    }
}

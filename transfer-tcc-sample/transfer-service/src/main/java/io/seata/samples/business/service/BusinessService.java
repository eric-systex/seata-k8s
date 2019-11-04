package io.seata.samples.business.service;

import io.seata.core.context.RootContext;
import io.seata.samples.business.client.TransferToClient;
import io.seata.samples.business.client.TransferFromClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessService.class);

    @Autowired
    private TransferFromClient fromClient;
    @Autowired
    private TransferToClient toClient;

    /**
     * 减库存，下订单
     *
     * @param userId
     * @param commodityCode
     * @param orderCount
     */
    @GlobalTransactional
    public void transfer(String from, String to, double amount) {
        LOGGER.info("transfer begin ... xid: " + RootContext.getXID());

        //扣钱参与者，一阶段执行
        fromClient.minus(from, amount);

        // if(!ret){
        //     //扣钱参与者，一阶段失败; 回滚本地事务和分布式事务
        //     throw new RuntimeException("账号:["+from+"] 预扣款失败");
        // }

        //加钱参与者，一阶段执行
        toClient.add(to, amount);

        // if(!ret){
        //     throw new RuntimeException("账号:["+to+"] 预收款失败");
        // }

        System.out.println(String.format("transfer amount[%s] from [%s] to [%s] finish.", String.valueOf(amount), from, to));
        // return true;

    }
}

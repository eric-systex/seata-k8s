package io.seata.samples.business.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.seata.core.context.RootContext;
import io.seata.samples.tcc.transfer.service.FirstTccService;
import io.seata.samples.tcc.transfer.service.SecondTccService;
import io.seata.spring.annotation.GlobalTransactional;

@Service
public class BusinessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessService.class);

    @Autowired
    FirstTccService fromService;
    
    @Autowired
    SecondTccService toService;
    
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
        fromService.prepareMinus(null, from, amount);

        // if(!ret){
        //     //扣钱参与者，一阶段失败; 回滚本地事务和分布式事务
        //     throw new RuntimeException("账号:["+from+"] 预扣款失败");
        // }

        //加钱参与者，一阶段执行
        toService.prepareAdd(null, to, amount);

        // if(!ret){
        //     throw new RuntimeException("账号:["+to+"] 预收款失败");
        // }

        System.out.println(String.format("transfer amount[%s] from [%s] to [%s] finish.", String.valueOf(amount), from, to));
        // return true;

    }
}

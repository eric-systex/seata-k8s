package io.seata.samples.business.controller;

import io.seata.samples.business.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/transfer")
@RestController
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    /**
     * 购买下单，模拟全局事务提交
     *
     * @return
     */
    @RequestMapping("/commit")
    public Boolean purchaseCommit(HttpServletRequest request) {
        businessService.transfer("A", "C", 10);
        return true;
    }

    /**
     * 购买下单，模拟全局事务回滚
     *
     * @return
     */
    @RequestMapping("/rollback")
    public Boolean purchaseRollback() {
        try {
            businessService.transfer("B", "XXX", 10);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}

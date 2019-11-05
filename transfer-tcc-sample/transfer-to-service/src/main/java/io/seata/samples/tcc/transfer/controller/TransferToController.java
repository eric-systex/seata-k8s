package io.seata.samples.tcc.transfer.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.seata.core.context.RootContext;
import io.seata.samples.tcc.transfer.service.SecondTccService;

@RequestMapping("/api/transfer-to")
@RestController
public class TransferToController {

    @Autowired
    SecondTccService secondService;

    @GetMapping(value = "/add")
    public void deduct(@RequestParam String accountNo, @RequestParam Double amount) throws SQLException {
        System.out.println("to XID " + RootContext.getXID());
        secondService.prepareAdd(null, accountNo, amount);
    }

    @GetMapping(value = "/commit")
    public void commit() throws SQLException {
        System.out.println("to XID " + RootContext.getXID());
        secondService.commit(null);
    }

    @GetMapping(value = "/rollback")
    public void rollback() throws SQLException {
        System.out.println("to XID " + RootContext.getXID());
        secondService.rollback(null);
    }
}

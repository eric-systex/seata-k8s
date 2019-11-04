package io.seata.samples.tcc.transfer.controller;

import io.seata.samples.tcc.transfer.action.FirstTccAction;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RequestMapping("/api/transfer-from")
@RestController
public class TransferFromController {

    @Autowired
    FirstTccAction firstTccAction;

    @GetMapping(value = "/minus")
    public void minus(@RequestParam String accountNo, @RequestParam Double amount) throws SQLException {
        System.out.println("transfer-from XID " + RootContext.getXID());
        firstTccAction.prepareMinus(null, accountNo, amount);
    }

    // public void setFirstTccAction(FirstTccAction firstTccAction) {
    //     this.firstTccAction = firstTccAction;
    // }

}

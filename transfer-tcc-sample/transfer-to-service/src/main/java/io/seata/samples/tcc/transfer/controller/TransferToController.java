package io.seata.samples.tcc.transfer.controller;

import io.seata.samples.tcc.transfer.action.SecondTccAction;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RequestMapping("/api/transfer-to")
@RestController
public class TransferToController {

    private SecondTccAction secondTccAction;

    @GetMapping(value = "/add")
    public void add(@RequestParam String accountNo, @RequestParam Integer amount) throws SQLException {
        System.out.println("transfer-to XID " + RootContext.getXID());
        secondTccAction.prepareAdd(null, accountNo, amount);
    }

    public void setSecondTccAction(SecondTccAction secondTccAction) {
        this.secondTccAction = secondTccAction;
    }
}

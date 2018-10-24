package com.oerlemans.siemiatycze.balance.controller;

import com.oerlemans.siemiatycze.balance.domain.Weight;
import com.oerlemans.siemiatycze.balance.service.BalanceService;
import com.oerlemans.siemiatycze.balance.util.BalanceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    BalanceService balanceService;

    @RequestMapping(method = RequestMethod.GET , value ="/")
    public Weight getScaleData() {
        Weight weight = new Weight();
        weight.setTimeStamp(BalanceUtils.nowToString());
        weight.setBalanceResult(balanceService.getBalanceValue());
        return weight;
    }
}

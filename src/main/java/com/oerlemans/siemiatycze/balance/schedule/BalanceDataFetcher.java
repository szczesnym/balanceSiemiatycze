package com.oerlemans.siemiatycze.balance.schedule;

import com.oerlemans.siemiatycze.balance.service.BalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BalanceDataFetcher {
    @Autowired
    BalanceService balanceService;

    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceService.class);

    //@Scheduled(cron = "0 0 10 * * *")
    @Scheduled(fixedRate = 5000)
    public void fetchBalanceData() {
        balanceService.updateBalanceValue();
        LOGGER.info("Data fetched:" + balanceService.getBalanceValue());
        //balanceService.updateBalanceValue();
    }
}

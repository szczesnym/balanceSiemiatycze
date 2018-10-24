package com.oerlemans.siemiatycze.balance.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BalanceService {
    private double balanceValue;
    private static BalanceService instance = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceService.class);

    private BalanceService() {
        this.balanceValue = 0.0;
    }

    @Bean
    public static BalanceService getInstance() {

        synchronized (BalanceService.class) {
            if (instance == null) {
                instance = new BalanceService();
            }
            return instance;
        }
    }

    public double getBalanceValue() {
        //readScaleData();
        return balanceValue;
    }

    public void updateBalanceValue() {
        readScaleData();
    }

    private void readScaleData() {
        this.balanceValue =(double) new Double(mockScaleData() * 100 ).intValue() / 100;
    }

    private double mockScaleData() {
        Random generator = new Random();
        double tempValue = generator.nextDouble() * 10000;
        LOGGER.info("Scale data Mocked:" + tempValue);
        return tempValue;
    }
}

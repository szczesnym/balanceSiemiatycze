package com.oerlemans.siemiatycze.balance.service;

import com.oerlemans.siemiatycze.balance.physical.SerialController;
import com.oerlemans.siemiatycze.balance.util.BalanceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BalanceService {
    @Value("${application.testing}")
    private int TESTING;

    private double balanceValue;
    private static BalanceService instance = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceService.class);

    @Autowired
    SerialController serialController;

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

        //REAL
        if(TESTING == 0) {
            String serialStringValue = serialController.getAssciBuffer();
            balanceValue = BalanceUtils.scaleResultAsdouble(serialStringValue);
            LOGGER.info("BalanceService.readScaleData->serial data:" + serialStringValue);
        }
        //TESTIN
        if(TESTING == 1) {
            balanceValue = mockScaleData();
            LOGGER.info("BalanceService.readScaleData->serial data:" + balanceValue);
        }
    }

    private double mockScaleData() {
        Random generator = new Random();
        double tempValue = generator.nextDouble() * 10000;
        LOGGER.info("Scale data Mocked:" + tempValue);
        return (double)Math.round(tempValue * 100) / 100;
    }


}

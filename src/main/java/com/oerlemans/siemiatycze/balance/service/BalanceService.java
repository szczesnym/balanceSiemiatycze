package com.oerlemans.siemiatycze.balance.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

import com.oerlemans.siemiatycze.balance.physical.SerialController;

@Service
public class BalanceService {
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
        String serialStringValue = serialController.getAssciBuffer();
        LOGGER.info("serial data:" + serialStringValue);
        /*if(!serialStringValue.isEmpty()) {
            try{
                this.balanceValue =(double) new Double(new Double(serialStringValue) * 100 ).intValue() / 100;
            } catch(NumberFormatException nEx) {
                nEx.printStackTrace();
                System.out.println("Balance conversion error:" + nEx.getMessage());
                this.balanceValue =0.0;           
            }
        } else
        {
            this.balanceValue = 0.0;
        }*/
        
        //this.balanceValue =(double) new Double(mockScaleData() * 100 ).intValue() / 100;
    }

    private double mockScaleData() {
        Random generator = new Random();
        double tempValue = generator.nextDouble() * 10000;
        LOGGER.info("Scale data Mocked:" + tempValue);
        return tempValue;
    }
}

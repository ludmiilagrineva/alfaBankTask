package com.example.alfaBankTask.service;


import com.example.alfaBankTask.client.FeignOpenExchangeRatesClient;
import com.example.alfaBankTask.entity.Rates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class RatesServiceImpl implements RatesService{

    private Rates currentRates;
    private Rates yesterdayRates;
    private FeignOpenExchangeRatesClient feignOpenExchangeRates;
    @Value("${openexchangerates.app.id}")
    private String appId;


    @Autowired
    public RatesServiceImpl(FeignOpenExchangeRatesClient feignOpenExchangeRates) {
        this.feignOpenExchangeRates = feignOpenExchangeRates;
    }

    @Override
    public int getKeyForMarker(String currencyCode) {
        this.getRates();
        int result=Double.compare(this.yesterdayRates.getRates().get(currencyCode), this.currentRates.getRates().get(currencyCode));

        return result;
    }

    public void getRates() {
        this.currentRates = feignOpenExchangeRates.getLatestRatest(this.appId);
        Calendar yesterdayDate = Calendar.getInstance();
        yesterdayDate.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String newYesterdayDate = formatter.format(yesterdayDate.getTime());
        this.yesterdayRates=feignOpenExchangeRates.getHistoricalRates(newYesterdayDate, appId);
        }

}


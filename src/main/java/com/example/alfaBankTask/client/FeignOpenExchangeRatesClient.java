package com.example.alfaBankTask.client;


import com.example.alfaBankTask.entity.Rates;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OERClient", url = "${openexchangerates.url}")
public interface FeignOpenExchangeRatesClient {

    @GetMapping("/latest.json")
    Rates getLatestRatest(@RequestParam("app_id") String appId);

    @GetMapping("/historical/{date}.json")
    Rates getHistoricalRates(@PathVariable String date, @RequestParam("app_id") String appId);

}

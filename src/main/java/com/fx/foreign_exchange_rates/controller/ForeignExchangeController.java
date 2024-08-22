package com.fx.foreign_exchange_rates.controller;

import com.fx.foreign_exchange_rates.model.CURRENCY;
import com.fx.foreign_exchange_rates.model.ExchangeCurrency;
import com.fx.foreign_exchange_rates.model.ExchangeCurrencyTimeSeriesResponse;
import com.fx.foreign_exchange_rates.service.ForeignExchangeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ForeignExchangeController {

    @Autowired
    private ForeignExchangeService foreignExchangeService;


    @GetMapping("/fx")
    public List<ExchangeCurrency> getExchangeRates(@RequestParam(value = "targetCurrency", required = false) String targetCurrency){
        if(StringUtils.isNotEmpty(targetCurrency)){
            try {
                targetCurrency = CURRENCY.valueOf(targetCurrency).name();
            }catch (Exception exception){
                throw new IllegalArgumentException("targetCurency is not a valid one");
            }
        }
        return foreignExchangeService.getExchangeRates(targetCurrency);
    }

    @GetMapping("/fx/{targetCurrency}")
    public ExchangeCurrencyTimeSeriesResponse getExchangeRatesInTimeSeries(@PathVariable("targetCurrency") String targetCurrency){
        try {
            targetCurrency = CURRENCY.valueOf(targetCurrency).name();
        }catch (Exception exception){
            throw new IllegalArgumentException("targetCurency is not a valid one");
        }
        return foreignExchangeService.getExchangeRatesInTimeSeries(targetCurrency);
    }
}

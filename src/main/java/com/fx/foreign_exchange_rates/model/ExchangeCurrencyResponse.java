package com.fx.foreign_exchange_rates.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Data
public class ExchangeCurrencyResponse {

    private double amount;
    private String base;
    private LocalDate date;
    private Map<String, Double> rates;

    public List<ExchangeCurrency> getExchangeCurrencies() {
        List<ExchangeCurrency> exchangeCurrencies = new ArrayList<>();
        for(Map.Entry<String, Double> rateEntrySet : rates.entrySet()){
            String targetCurrency = rateEntrySet.getKey();
            Double exchangeRate = rateEntrySet.getValue();
            if(Arrays.stream(CURRENCY.values()).anyMatch(currency -> currency.name().equalsIgnoreCase(targetCurrency))){
                ExchangeCurrency exchangeCurrency = new ExchangeCurrency();
                exchangeCurrency.setConversionDate(date);
                exchangeCurrency.setSourceCurrency(base);
                exchangeCurrency.setTargetCurrency(targetCurrency);
                exchangeCurrency.setExchangeRate(exchangeRate);
                exchangeCurrencies.add(exchangeCurrency);
            }
        }
        return exchangeCurrencies;
    }
}

package com.fx.foreign_exchange_rates.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Data
public class ExchangeCurrencyTimeSeriesResponse {

    private double amount;
    private String base;

    @JsonProperty("start_date")
    private LocalDate startDate;
    @JsonProperty("end_date")
    private LocalDate endDate;
    private Map<String, Map<String, Double>> rates;

    /*public List<ExchangeCurrency> getExchangeCurrencies() {
        List<ExchangeCurrency> exchangeCurrencies = new ArrayList<>();
        for(Map.Entry<String, Map<String, Double>> timeSeriesEntrySet : rates.entrySet()){
            String conversionDate = timeSeriesEntrySet.getKey();
            Map<String, Double> exchangeRate = timeSeriesEntrySet.getValue();
            for(Map.Entry<String, Double> exchangeRateSet : exchangeRate.entrySet()){
                if(Arrays.stream(CURRENCY.values()).anyMatch(currency -> currency.name().equalsIgnoreCase(targetCurrency))){
                    ExchangeCurrency exchangeCurrency = new ExchangeCurrency();
                    exchangeCurrency.setConversionDate(date);
                    exchangeCurrency.setSourceCurrency(base);
                    exchangeCurrency.setTargetCurrency(targetCurrency);
                    exchangeCurrency.setExchangeRate(exchangeRate);
                    exchangeCurrencies.add(exchangeCurrency);
                }
            }
        }
        return exchangeCurrencies;
    }*/
}

package com.fx.foreign_exchange_rates.service.impl;

import com.fx.foreign_exchange_rates.model.CURRENCY;
import com.fx.foreign_exchange_rates.model.ExchangeCurrency;
import com.fx.foreign_exchange_rates.model.ExchangeCurrencyResponse;
import com.fx.foreign_exchange_rates.model.ExchangeCurrencyTimeSeriesResponse;
import com.fx.foreign_exchange_rates.repository.ForeignExchangeRepository;
import com.fx.foreign_exchange_rates.service.ForeignExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ForeignExchangeServiceImpl implements ForeignExchangeService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ForeignExchangeRepository foreignExchangeRepository;

    @Value("${foreign-exchange-api.url}")
    private String foreignExchangeBaseURL;

    @Override
    public List<ExchangeCurrency> getExchangeRates(String targetCurrency) {
        ExchangeCurrencyResponse exchangeCurrencyResponse = restTemplate.getForObject(getFXUrl(targetCurrency), ExchangeCurrencyResponse.class);
        List<ExchangeCurrency> exchangeCurrencies = exchangeCurrencyResponse.getExchangeCurrencies();
        foreignExchangeRepository.saveAll(exchangeCurrencies);
        return exchangeCurrencies;
    }

    @Override
    public ExchangeCurrencyTimeSeriesResponse getExchangeRatesInTimeSeries(String targetCurrency) {
        return restTemplate.getForObject(getFXUrlForTimeSeries(targetCurrency), ExchangeCurrencyTimeSeriesResponse.class);
    }

    private String getFXUrl(String targetCurrency){
        if(StringUtils.isNotEmpty(targetCurrency)){
            return String.format("%s/latest?from=%s&to=%s", foreignExchangeBaseURL, CURRENCY.USD.name(), targetCurrency);
        }
        return String.format("%s/latest?from=%s", foreignExchangeBaseURL, CURRENCY.USD.name());
    }

    private String getFXUrlForTimeSeries(String targetCurrency){
        LocalDate currDate = LocalDate.now();
        LocalDate threeDaysInPast = currDate.minusDays(3);
        return String.format("%s/%s..?from=%s&to=%s", foreignExchangeBaseURL, threeDaysInPast, CURRENCY.USD.name(), targetCurrency);
    }
}

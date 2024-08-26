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
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "latest-fx", key = "{#sourceCurrency, #currDate, #targetCurrency}")
    @Override
    public List<ExchangeCurrency> getExchangeRates(String sourceCurrency, LocalDate currDate, String targetCurrency) {
        ExchangeCurrencyResponse exchangeCurrencyResponse = restTemplate.getForObject(getFXUrl(sourceCurrency, currDate, targetCurrency), ExchangeCurrencyResponse.class);
        List<ExchangeCurrency> exchangeCurrencies = exchangeCurrencyResponse.getExchangeCurrencies();
        foreignExchangeRepository.saveAll(exchangeCurrencies);
        return exchangeCurrencies;
    }

    @Cacheable(value = "history-fx", key = "{#sourceCurrency, #currDate, #targetCurrency}")
    @Override
    public ExchangeCurrencyTimeSeriesResponse getExchangeRatesInTimeSeries(String sourceCurrency, LocalDate pastDate, String targetCurrency) {
        return restTemplate.getForObject(getFXUrlForTimeSeries(sourceCurrency, pastDate, targetCurrency), ExchangeCurrencyTimeSeriesResponse.class);
    }

    private String getFXUrl(String sourceCurrency, LocalDate currDate, String targetCurrency){
        if(StringUtils.isNotEmpty(targetCurrency)){
            return String.format("%s/%s?from=%s&to=%s", foreignExchangeBaseURL, currDate, sourceCurrency, targetCurrency);
        }
        return String.format("%s/%s?from=%s", foreignExchangeBaseURL, currDate, sourceCurrency);
    }

    private String getFXUrlForTimeSeries(String sourceCurrency, LocalDate pastDate, String targetCurrency){
        return String.format("%s/%s..?from=%s&to=%s", foreignExchangeBaseURL, pastDate, sourceCurrency, targetCurrency);
    }
}

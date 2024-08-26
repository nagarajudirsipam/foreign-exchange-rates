package com.fx.foreign_exchange_rates.service;

import com.fx.foreign_exchange_rates.model.ExchangeCurrency;
import com.fx.foreign_exchange_rates.model.ExchangeCurrencyTimeSeriesResponse;

import java.time.LocalDate;
import java.util.List;

public interface ForeignExchangeService {
    List<ExchangeCurrency> getExchangeRates(String sourceCurrency, LocalDate currDate, String targetCurrency);

    ExchangeCurrencyTimeSeriesResponse getExchangeRatesInTimeSeries(String sourceCurrency, LocalDate pastDate, String targetCurrency);
}

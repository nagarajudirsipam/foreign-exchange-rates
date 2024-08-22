package com.fx.foreign_exchange_rates.service;

import com.fx.foreign_exchange_rates.model.ExchangeCurrency;
import com.fx.foreign_exchange_rates.model.ExchangeCurrencyTimeSeriesResponse;

import java.util.List;

public interface ForeignExchangeService {
    List<ExchangeCurrency> getExchangeRates(String targetCurrency);

    ExchangeCurrencyTimeSeriesResponse getExchangeRatesInTimeSeries(String targetCurrency);
}

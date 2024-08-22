package com.fx.foreign_exchange_rates.model;

import java.io.Serializable;
import java.time.LocalDate;

public class ExchangeCurrencyKey implements Serializable {

    private LocalDate conversionDate;
    private String sourceCurrency;
    private String targetCurrency;

}

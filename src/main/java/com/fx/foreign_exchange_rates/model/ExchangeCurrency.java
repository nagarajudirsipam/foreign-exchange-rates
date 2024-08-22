package com.fx.foreign_exchange_rates.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@IdClass(ExchangeCurrencyKey.class)
public class ExchangeCurrency implements Serializable {

    @Id
    private LocalDate conversionDate;
    @Id
    private String sourceCurrency;
    @Id
    private String targetCurrency;
    private Double exchangeRate;

}

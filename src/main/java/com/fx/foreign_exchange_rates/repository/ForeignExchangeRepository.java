package com.fx.foreign_exchange_rates.repository;

import com.fx.foreign_exchange_rates.model.ExchangeCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForeignExchangeRepository extends JpaRepository<ExchangeCurrency, String> {
}

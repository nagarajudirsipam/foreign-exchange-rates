package com.fx.foreign_exchange_rates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ForeignExchangeRatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForeignExchangeRatesApplication.class, args);
	}

}

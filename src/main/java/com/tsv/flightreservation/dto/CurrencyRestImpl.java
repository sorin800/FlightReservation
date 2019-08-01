package com.tsv.flightreservation.dto;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyRestImpl {

    public CurrencyDao getCurrencies() {
//        Map<String, String> currenciesMap = new HashMap();

        RestTemplate restTemplate = new RestTemplate();
        CurrencyDao currencies = restTemplate.getForObject("https://api.exchangeratesapi.io/latest",
                CurrencyDao.class);

        return currencies;
    }
}

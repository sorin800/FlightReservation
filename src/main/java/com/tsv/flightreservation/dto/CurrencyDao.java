package com.tsv.flightreservation.dto;

import java.util.HashMap;

public class CurrencyDao {

    private HashMap<String, String> rates;

    private String base;

    private String date;

    public CurrencyDao() {
    }

    public void setRates(HashMap<String, String> rates) {
        this.rates = rates;
    }

    public HashMap<String, String> getRates() {
        return rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CurrencyDao{" +
                "rates=" + rates +
                ", base='" + base + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

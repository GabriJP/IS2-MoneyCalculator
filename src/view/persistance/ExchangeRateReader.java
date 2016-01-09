package view.persistance;

import model.Currency;
import model.ExchangeRate;

public interface ExchangeRateReader {
    ExchangeRate get(Currency from, Currency to);
}
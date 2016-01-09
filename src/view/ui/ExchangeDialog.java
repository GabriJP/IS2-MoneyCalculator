package view.ui;

import model.Currency;
import model.Money;

public interface ExchangeDialog {
    Money getInputMoney();

    Currency getOutputCurrency();
}
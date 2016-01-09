package control;

import application.swing.Application;
import model.ExchangeRate;
import model.Money;
import view.persistance.ExchangeRateReader;
import view.ui.ExchangeDialog;
import view.ui.ExchangeDisplay;

public class ExchangeCommand implements Command {
    private final ExchangeDialog exchangeDialog;
    private final ExchangeDisplay exchangeDisplay;
    private final ExchangeRateReader exchangeRateReader;
    private final Application application;

    public ExchangeCommand(ExchangeDialog exchangeDialog, ExchangeDisplay exchangeDisplay, ExchangeRateReader exchangeRateLoader, Application application) {
        this.exchangeDialog = exchangeDialog;
        this.exchangeDisplay = exchangeDisplay;
        this.exchangeRateReader = exchangeRateLoader;
        this.application = application;
    }

    public void execute() {
        application.disableCalculate();
        Money money = this.exchangeDialog.getInputMoney();
        ExchangeRate exchangeRate = this.exchangeRateReader.get(money.getCurrency(), this.exchangeDialog.getOutputCurrency());
        this.exchangeDisplay.show(exchange(money, exchangeRate));
    }

    public Money exchange(Money money, ExchangeRate exchangeRate) {
        double amount = money.getAmount() * exchangeRate.getRate();
        return new Money(amount, exchangeRate.getTo());
    }
}
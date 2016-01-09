package control;

import application.swing.Application;
import view.ui.ExchangeDisplay;

public class ClearCommand implements Command {
    private final ExchangeDisplay exchangeDisplay;
    private final Application application;

    public ClearCommand(ExchangeDisplay exchangeDisplay, Application application) {
        this.exchangeDisplay = exchangeDisplay;
        this.application = application;
    }

    public void execute() {
        this.exchangeDisplay.show(null);
        application.enableCalculate();
    }
}
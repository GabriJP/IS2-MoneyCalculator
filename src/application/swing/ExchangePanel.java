package application.swing;

import application.SQLiteCurrencySetLoader;
import model.Currency;
import model.Money;
import view.persistance.CurrencySetLoader;
import view.ui.ExchangeDialog;
import view.ui.ExchangeDisplay;

import javax.swing.*;
import java.util.Vector;

public class ExchangePanel extends JPanel implements ExchangeDialog, ExchangeDisplay {
    private final JComboBox<Currency> from;
    private final JComboBox<Currency> to;
    private final JTextField input;

    public ExchangePanel() {
        CurrencySetLoader loader = new SQLiteCurrencySetLoader();
        Vector<Currency> currencies = loader.load().toArray();
        this.from = new JComboBox<>(currencies);
        this.to = new JComboBox<>(currencies);
        this.input = new JTextField("1,0");
        this.deployComponents();
    }

    public void show(Money money) {
        if (money == null) {
            this.input.setText("1,0");
            this.input.setEditable(true);
        } else {
            this.input.setText(String.format("%.2f ", this.getInputAmount()) + this.getCurrency(this.from).getCode() + " = " + String.format("%.5f ", money.getAmount()) + money.getCurrency().getCode());
            this.input.setEditable(false);
        }
    }

    public Money getInputMoney() {
        return new Money(this.getInputAmount(), this.getCurrency(this.from));
    }

    public Currency getOutputCurrency() {
        return this.getCurrency(this.to);
    }

    private void deployComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(this.input);
        this.add(this.from);
        this.add(this.to);
    }

    private double getInputAmount() {
        try {
            return Double.parseDouble(this.input.getText().replaceAll(",", "."));
        } catch (Exception e) {
            this.input.setText("");
            return Double.parseDouble(this.input.getText().replaceAll(",", "."));
        }
    }

    private Currency getCurrency(JComboBox<Currency> comboBox) {
        return (Currency) comboBox.getSelectedItem();
    }
}

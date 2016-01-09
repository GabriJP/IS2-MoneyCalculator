package application.swing;

import application.SQLiteExchangeRateReader;
import control.ClearCommand;
import control.Command;
import control.ExchangeCommand;
import view.ui.ExchangeDialog;
import view.ui.ExchangeDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Application extends JFrame {
    private final Map<String, Command> commands;
    private ExchangeDialog exchangeDialog;
    private ExchangeDisplay exchangeDisplay;
    private JButton calculateButton;

    public static void main(String[] args) {
        new Application().setVisible(true);
    }

    public Application() {
        commands = new HashMap<>();
        this.deployComponents();
        this.createCommands();
    }

    private void deployComponents() {
        this.setTitle("Money Calculator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(400, 145));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(this.exchangePanel(), "Center");
        this.getContentPane().add(this.toolbar(), "South");
    }

    private void createCommands() {
        this.commands.put("exchange", new ExchangeCommand(this.exchangeDialog, this.exchangeDisplay, new SQLiteExchangeRateReader(), this));
        this.commands.put("clear", new ClearCommand(this.exchangeDisplay, this));
    }

    private JPanel exchangePanel() {
        ExchangePanel panel = new ExchangePanel();
        this.exchangeDialog = panel;
        this.exchangeDisplay = panel;
        return panel;
    }

    private JPanel toolbar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(this.calculateButton());
        panel.add(this.clearButton());
        return panel;
    }

    private JButton calculateButton() {
        JButton button = new JButton("Calculate");
        calculateButton = button;
        button.addActionListener(this.doCommand("exchange"));
        return button;
    }

    private JButton clearButton() {
        JButton button = new JButton("Clear");
        button.addActionListener(this.doCommand("clear"));
        return button;
    }

    public void enableCalculate(){
        this.calculateButton.setEnabled(true);
    }

    public void disableCalculate(){
        this.calculateButton.setEnabled(false);
    }

    private ActionListener doCommand(String operation) {
        return (event) -> this.commands.get(operation).execute();
    }
}

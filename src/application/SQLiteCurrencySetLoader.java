package application;

import model.Currency;
import model.CurrencySet;
import view.persistance.CurrencySetLoader;

import java.sql.*;

public class SQLiteCurrencySetLoader implements CurrencySetLoader {

    private Connection connection;

    public SQLiteCurrencySetLoader() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:data/CurrenciesAndRates.sqlite");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SQLiteCurrencySetLoader(Connection connection) {
        this.connection = connection;
    }

    public CurrencySet load() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CURRENCIES");
            CurrencySet currencySet = new CurrencySet();
            while (resultSet.next()) {
                currencySet.add(new Currency(resultSet.getString("code"), resultSet.getString("name"), resultSet.getString("symbol")));
            }
            resultSet.close();
            statement.close();

            return currencySet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

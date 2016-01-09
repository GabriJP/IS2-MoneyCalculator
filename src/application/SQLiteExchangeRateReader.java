package application;

import model.Currency;
import model.ExchangeRate;
import view.persistance.ExchangeRateReader;

import java.sql.*;

public class SQLiteExchangeRateReader implements ExchangeRateReader {

    private Connection connection;

    public SQLiteExchangeRateReader() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:data/CurrenciesAndRates.sqlite");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SQLiteExchangeRateReader(Connection connection) {
        this.connection = connection;
    }

    public ExchangeRate get(Currency from, Currency to) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EXCHANGE_RATES WHERE currency_from=? AND currency_to=?");
            preparedStatement.setString(1, from.getCode());
            preparedStatement.setString(2, to.getCode());
            ResultSet resultSet = preparedStatement.executeQuery();
            ExchangeRate result = resultSet.next() ? new ExchangeRate(from, to, resultSet.getDouble("exchange_rate")) : null;
            resultSet.close();
            preparedStatement.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
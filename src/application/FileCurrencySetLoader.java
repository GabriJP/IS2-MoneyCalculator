package application;

import model.Currency;
import model.CurrencySet;
import view.persistance.CurrencySetLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileCurrencySetLoader implements CurrencySetLoader {

    private final String path;

    public FileCurrencySetLoader(String path) {
        this.path = path;
    }

    @Override
    public CurrencySet load() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            CurrencySet currencySet = new CurrencySet();
            String read;
            while ((read = reader.readLine()) != null) {
                String[] split = read.split(";");
                currencySet.add(new Currency(split[0].trim(), split[1].trim(), split[2].trim()));
            }
            return currencySet;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

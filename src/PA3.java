import java.io.*;

/**
 * Created by Gabriel on 19/10/2015.
 */
public class PA3 {
    public static void main(String[] args) throws IOException {
        CurrencySet set = new CurrenctSetFileReader().read("./data/currencies.csv");
        Currency currency = set.get("USD");
        System.out.println(currency.getSymbol());
    }
}

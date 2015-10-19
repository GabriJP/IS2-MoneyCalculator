import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel on 19/10/2015.
 */
public class CurrencySet {
    private List<Currency> list = new ArrayList<>();

    public void add(Currency currency){
        list.add(currency);
    }

    public Currency get (String text){
        for(Currency currency : list){
            if(currency.getCode().equalsIgnoreCase(text)
                    || currency.getName().contains(text)
                    || currency.getSymbol().equals(text)){
                return currency;
            }
        }
        return null;
    }
}

package model;

import java.util.Vector;

public class CurrencySet {
    private final Vector<Currency> list;

    public CurrencySet() {
        list = new Vector<>();
    }

    public void add(Currency currency) {
        this.list.add(currency);
    }

    public Vector<Currency> toArray() {
        return this.list;
    }
}

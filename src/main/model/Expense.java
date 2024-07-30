package model;

import org.json.JSONObject;
import persistence.Writable;

public class Expense extends AbstractMoney {
    public Expense(String name, Integer amount) {
        super(name, amount);
    }
}

package model;

import org.json.JSONObject;
import persistence.Writable;

public class Income extends AbstractMoney {
    public Income(String name, Integer amount) {
        super(name, amount);
    }
}

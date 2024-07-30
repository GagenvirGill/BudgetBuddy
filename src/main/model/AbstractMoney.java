package model;

import org.json.JSONObject;
import persistence.Writable;

public abstract class AbstractMoney implements Writable {
    // Represents an abstract money Source, either income or expense

    private String name;
    private int amount;

    // Requires: amount > 0
    // Effects: Creates an abstractMoney type with a name and amount
    public AbstractMoney(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    // Modifies: this
    // Effects: Changes the name of the abstractMoney source to newName
    public void changeName(String newName) {
        name = newName;
    }

    // Requires: newAmount > 0
    // Modifies: this
    // Effects: Changes the amount of the abstractMoney source to newAmount
    public void changeAmount(int newAmount) {
        amount = newAmount;
    }

    // Requires: incByAmount > 0
    // Modifies: this
    // Effects: Increases the amount of the abstractMoney source by incByAmount
    public void increaseAmountBy(int incByAmount) {
        amount = amount + incByAmount;
    }

    // Requires: decByAmount > 0 and decByAmount < this.getAmount();
    // Modifies: this
    // Effects: Decreases the amount of the abstractMoney source by decByAmount
    public void decreaseAmountBy(int decByAmount) {
        amount = amount - decByAmount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("amount", amount);
        return json;
    }

}

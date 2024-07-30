package model;

import org.json.JSONObject;
import org.json.JSONArray;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Budget implements Writable {
    // Represents a budget given a users Incomes and Expenses

    private final List<Income> budgetsIncomes;
    private final List<Expense> budgetsExpenses;

    // Effects: Constructs a Budget with a name, and an empty list of incomes and expenses
    public Budget() {
        budgetsIncomes = new ArrayList<>();
        budgetsExpenses = new ArrayList<>();
    }

    // Modifies: this
    // Effects: Adds a new income to the list of incomes
    public void addIncome(Income newIncome) {
        budgetsIncomes.add(newIncome);
        EventLog.getInstance().logEvent(new Event("Income called "
                + newIncome.getName() + " added"));
    }

    // Modifies: this
    // Effects: Adds a new expense to the list of expenses
    public void addExpense(Expense newExpense) {
        budgetsExpenses.add(newExpense);
        EventLog.getInstance().logEvent(new Event("Expense called "
                + newExpense.getName() + " added"));
    }

    // Effects: Produces a list of all income sources names as strings
    public List<String> allIncomeNames() {
        List<String> incomeNames = new ArrayList<>();

        for (Income next : budgetsIncomes) {
            incomeNames.add(next.getName());
        }

        return incomeNames;
    }

    // Effects: Produces a list of all expenses names as strings
    public List<String> allExpenseNames() {
        List<String> expenseNames = new ArrayList<>();

        for (Expense next : budgetsExpenses) {
            expenseNames.add(next.getName());
        }

        return expenseNames;
    }

    // Effects: Adds up all income sources monthly values and produces their total
    public int totalIncomeAmount() {
        Integer total = 0;

        for (Income next : budgetsIncomes) {
            total = total + next.getAmount();
        }
        EventLog.getInstance().logEvent(new Event("Total Income amount at $"
                + total.toString() + " viewed"));

        return total;
    }

    // Effects: Adds up all expenses monthly cost and produces their total
    public int totalExpenseAmount() {
        Integer total = 0;

        for (Expense next : budgetsExpenses) {
            total = total + next.getAmount();
        }
        EventLog.getInstance().logEvent(new Event("Total Expense amount at $"
                + total.toString() + " viewed"));

        return total;
    }

    // Effects: Produces true if totalIncomeAmount() > totalExpenseAmount()
    public boolean canUserSaveMoney() {
        return totalExpenseAmount() < totalIncomeAmount();
    }

    // Effects: Produces the difference between totalIncomeAmount() and totalExpenseAmount()
    public int howMuchMoneyCanUserSave() {
        Integer totalExpense = 0;
        for (Expense next : budgetsExpenses) {
            totalExpense = totalExpense + next.getAmount();
        }

        Integer totalIncome = 0;
        for (Income next : budgetsIncomes) {
            totalIncome = totalIncome + next.getAmount();
        }

        Integer total = totalIncome - totalExpense;

        EventLog.getInstance().logEvent(new Event("Total Net amount at $"
                + total.toString() + " viewed"));

        return total;
    }

    public List<Income> getIncomes() {
        return budgetsIncomes;
    }

    public List<Expense> getExpenses() {
        return budgetsExpenses;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("incomes", incomesToJson());
        json.put("expenses", expensesToJson());
        EventLog.getInstance().logEvent(new Event("Saved Current Data"));
        return json;
    }

    // Effects: returns incomes in this budget as a JSON array
    private JSONArray incomesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Income i : budgetsIncomes) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }

    // Effects: returns expenses in this budget as a JSON array
    private JSONArray expensesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Expense e : budgetsExpenses) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }
}


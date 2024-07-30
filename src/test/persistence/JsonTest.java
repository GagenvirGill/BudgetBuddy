package persistence;

import model.Expense;
import model.Income;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    // NOTE: Code in this file is based on the JSONSerializationDemo file

    protected void checkIncome(String name, Integer amount, Income income) {
        assertEquals(name, income.getName());
        assertEquals(amount, income.getAmount());
    }

    protected void checkExpense(String name, Integer amount, Expense expense) {
        assertEquals(name, expense.getName());
        assertEquals(amount, expense.getAmount());
    }
}

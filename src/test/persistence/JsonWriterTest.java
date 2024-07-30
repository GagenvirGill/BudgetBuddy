package persistence;

import model.Income;
import model.Expense;
import model.Budget;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{
    // NOTE: Code in this file is based on the JSONSerializationDemo file

    @Test
    void testWriterInvalidFile() {
        try {
            Budget b = new Budget();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterEmptyBudget() {
        try {
            Budget b = new Budget();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBudget.json");
            writer.open();
            writer.write(b);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBudget.json");
            b = reader.read();
            assertEquals(0, b.totalIncomeAmount());
            assertEquals(0, b.totalExpenseAmount());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBudget() {
        try {
            Budget b = new Budget();
            b.addIncome(new Income("ihelp", 10));
            b.addIncome(new Income("ihelp2", 20));
            b.addExpense(new Expense("ehelp", 12));
            b.addExpense(new Expense("ehelp2", 16));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBudget.json");
            writer.open();
            writer.write(b);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBudget.json");
            b = reader.read();
            List<Income> incomes = b.getIncomes();
            List<Expense> expenses = b.getExpenses();
            assertEquals(2, incomes.size());
            assertEquals(2, expenses.size());
            checkIncome("ihelp", 10, incomes.get(0));
            checkIncome("ihelp2", 20, incomes.get(1));
            checkExpense("ehelp", 12, expenses.get(0));
            checkExpense("ehelp2", 16, expenses.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }
}

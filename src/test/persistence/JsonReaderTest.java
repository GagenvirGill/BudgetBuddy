package persistence;

import model.Income;
import model.Expense;
import model.Budget;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{
    // NOTE: Code in this file is based on the JSONSerializationDemo file

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Budget b = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testReaderEmptyBudget() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBudget.json");
        try {
            Budget b = reader.read();
            assertEquals(0, b.totalExpenseAmount());
            assertEquals(0, b.totalIncomeAmount());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBudget() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBudget.json");
        try {
            Budget b = reader.read();
            List<Income> incomes = b.getIncomes();
            List<Expense> expenses = b.getExpenses();
            assertEquals(2, incomes.size());
            assertEquals(2, expenses.size());
            checkIncome("ihelp", 10, incomes.get(0));
            checkIncome("ihelp2", 20, incomes.get(1));
            checkExpense("ehelp", 12, expenses.get(0));
            checkExpense("ehelp2", 16, expenses.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}

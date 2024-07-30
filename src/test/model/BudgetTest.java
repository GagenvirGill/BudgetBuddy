package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetTest {
    private Budget testBudget;
    private Income testIncome;
    private Income testIncome2;
    private Expense testExpense;
    private Expense testExpense2;

    private Income income1;
    private Income income2;
    private Expense expense1;
    private Expense expense2;

    @BeforeEach
    public void runBefore() {
        testBudget = new Budget();
        testIncome = new Income("test", 1000);
        testIncome2 = new Income("helps", 1);
        testExpense = new Expense("test", 1000);
        testExpense2 = new Expense("helps", 1);

        income1 = new Income("income1", 1);
        income2 = new Income("income2", 2);
        expense1 = new Expense("expense1", 1);
        expense2 = new Expense("expense2", 2);
    }

    @Test
    public void testConstructor() {
        List<Income> incomes = testBudget.getIncomes();
        List<Expense> expenses = testBudget.getExpenses();
        assertEquals(0, incomes.size());
        assertEquals(0, expenses.size());
    }

    @Test
    public void testAddOneIncome() {
        testBudget.addIncome(testIncome);
        List<Income> incomes = testBudget.getIncomes();
        List<Expense> expenses = testBudget.getExpenses();
        assertEquals(1, incomes.size());
        assertEquals(0, expenses.size());
        Income practiceIncome = incomes.get(0);
        assertEquals("test", practiceIncome.getName());
        assertEquals(1000, practiceIncome.getAmount());
    }

    @Test
    public void testAddTwoIncomes() {
        testBudget.addIncome(testIncome);
        testBudget.addIncome(testIncome2);
        List<Income> incomes = testBudget.getIncomes();
        List<Expense> expenses = testBudget.getExpenses();
        assertEquals(2, incomes.size());
        assertEquals(0, expenses.size());
        Income practiceIncome1 = incomes.get(0);
        Income practiceIncome2 = incomes.get(1);
        assertEquals("test", practiceIncome1.getName());
        assertEquals(1000, practiceIncome1.getAmount());
        assertEquals("helps", practiceIncome2.getName());
        assertEquals(1, practiceIncome2.getAmount());
    }

    @Test
    public void testAddOneExpense() {
        testBudget.addExpense(testExpense);
        List<Income> incomes = testBudget.getIncomes();
        List<Expense> expenses = testBudget.getExpenses();
        assertEquals(0, incomes.size());
        assertEquals(1, expenses.size());
        Expense practiceExpense = expenses.get(0);
        assertEquals("test", practiceExpense.getName());
        assertEquals(1000, practiceExpense.getAmount());
    }

    @Test
    public void testAddTwoExpense() {
        testBudget.addExpense(testExpense);
        testBudget.addExpense(testExpense2);
        List<Income> incomes = testBudget.getIncomes();
        List<Expense> expenses = testBudget.getExpenses();
        assertEquals(0, incomes.size());
        assertEquals(2, expenses.size());
        Expense practiceExpense1 = expenses.get(0);
        Expense practiceExpense2 = expenses.get(1);
        assertEquals("test", practiceExpense1.getName());
        assertEquals(1000, practiceExpense1.getAmount());
        assertEquals("helps", practiceExpense2.getName());
        assertEquals(1, practiceExpense2.getAmount());
    }

    @Test
    public void testAllIncomeNames() {
        testBudget.addIncome(testIncome);
        testBudget.addIncome(testIncome2);

        List<String> incomeNames = new ArrayList<>();
        incomeNames.add("test");
        incomeNames.add("helps");

        assertEquals(incomeNames, testBudget.allIncomeNames());
    }

    @Test
    public void testAllExpenseNames() {
        testBudget.addExpense(testExpense);
        testBudget.addExpense(testExpense2);

        List<String> expenseNames = new ArrayList<>();
        expenseNames.add("test");
        expenseNames.add("helps");

        assertEquals(expenseNames, testBudget.allExpenseNames());
    }

    @Test
    public void testTotalIncomeAmount() {
        testBudget.addIncome(testIncome);
        assertEquals(1000, testBudget.totalIncomeAmount());

        testBudget.addIncome(testIncome2);
        assertEquals(1001, testBudget.totalIncomeAmount());
    }

    @Test
    public void testTotalExpenseAmount() {
        testBudget.addExpense(testExpense);
        assertEquals(1000, testBudget.totalExpenseAmount());

        testBudget.addExpense(testExpense2);
        assertEquals(1001, testBudget.totalExpenseAmount());
    }

    @Test
    public void testCanUserSave() {
        testBudget.addIncome(income1);
        testBudget.addExpense(expense2);
        testBudget.addIncome(income2);
        testBudget.addExpense(expense2);
        assertFalse(testBudget.canUserSaveMoney());

        testBudget.addIncome(income1);
        assertFalse(testBudget.canUserSaveMoney());

        testBudget.addIncome(income2);
        testBudget.addExpense(expense1);
        assertTrue(testBudget.canUserSaveMoney());
    }

    @Test
    public void testHowMuchCanUserSave() {
        testBudget.addIncome(income1);
        testBudget.addExpense(expense2);
        testBudget.addIncome(income2);
        testBudget.addExpense(expense2);
        assertEquals(-1, testBudget.howMuchMoneyCanUserSave());

        testBudget.addIncome(income1);
        assertEquals(0, testBudget.howMuchMoneyCanUserSave());

        testBudget.addIncome(income2);
        testBudget.addExpense(expense1);
        assertEquals(1, testBudget.howMuchMoneyCanUserSave());

        testBudget.addIncome(testIncome);
        assertEquals(1001, testBudget.howMuchMoneyCanUserSave());
    }
}

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseTest {
    private Expense testExpense;
    private Expense testExpense2;

    @BeforeEach
    public void runBefore() {
        testExpense = new Expense("test", 1000);
        testExpense2 = new Expense("help", 1);
    }

    @Test
    public void testConstructor() {
        assertEquals("test", testExpense.getName());
        assertEquals(1000, testExpense.getAmount());
    }

    @Test
    public void testChangeName() {
        assertEquals("test", testExpense.getName());
        assertEquals(1000, testExpense.getAmount());

        testExpense.changeName("New Test");
        assertEquals("New Test", testExpense.getName());
        assertEquals(1000, testExpense.getAmount());

        testExpense.changeName("New Test2");
        assertEquals("New Test2", testExpense.getName());
        assertEquals(1000, testExpense.getAmount());
    }

    @Test
    public void testChangeAmount() {
        testExpense.changeAmount(2000);
        assertEquals("test", testExpense.getName());
        assertEquals(2000, testExpense.getAmount());

        testExpense.changeAmount(3000);
        assertEquals("test", testExpense.getName());
        assertEquals(3000, testExpense.getAmount());

        testExpense.changeAmount(1);
        assertEquals("test", testExpense.getName());
        assertEquals(1, testExpense.getAmount());
    }

    @Test
    public void testIncreaseAmountBy() {
        testExpense.increaseAmountBy(500);
        assertEquals("test", testExpense.getName());
        assertEquals(1500, testExpense.getAmount());

        testExpense.increaseAmountBy(600);
        assertEquals("test", testExpense.getName());
        assertEquals(2100, testExpense.getAmount());
    }

    @Test
    public void testDecreaseAmountBy() {
        testExpense.decreaseAmountBy(500);
        assertEquals("test", testExpense.getName());
        assertEquals(500, testExpense.getAmount());

        testExpense.decreaseAmountBy(499);
        assertEquals("test", testExpense.getName());
        assertEquals(1, testExpense.getAmount());
    }

    @Test
    public void testIncreaseAndDecrease() {
        testExpense2.increaseAmountBy(1);
        assertEquals("help", testExpense2.getName());
        assertEquals(2, testExpense2.getAmount());

        testExpense2.decreaseAmountBy(1);
        assertEquals("help", testExpense2.getName());
        assertEquals(1, testExpense2.getAmount());
    }
}
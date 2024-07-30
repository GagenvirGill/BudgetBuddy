package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncomeTest {
    private Income testIncome;
    private Income testIncome2;

    @BeforeEach
    public void runBefore() {
        testIncome = new Income("test", 1000);
        testIncome2 = new Income("help", 1);
    }

    @Test
    public void testConstructor() {
        assertEquals("test", testIncome.getName());
        assertEquals(1000, testIncome.getAmount());
    }

    @Test
    public void testChangeName() {
        assertEquals("test", testIncome.getName());
        assertEquals(1000, testIncome.getAmount());

        testIncome.changeName("New Test");
        assertEquals("New Test", testIncome.getName());
        assertEquals(1000, testIncome.getAmount());

        testIncome.changeName("New Test2");
        assertEquals("New Test2", testIncome.getName());
        assertEquals(1000, testIncome.getAmount());
    }

    @Test
    public void testChangeAmount() {
        testIncome.changeAmount(2000);
        assertEquals("test", testIncome.getName());
        assertEquals(2000, testIncome.getAmount());

        testIncome.changeAmount(3000);
        assertEquals("test", testIncome.getName());
        assertEquals(3000, testIncome.getAmount());

        testIncome.changeAmount(1);
        assertEquals("test", testIncome.getName());
        assertEquals(1, testIncome.getAmount());
    }

    @Test
    public void testIncreaseAmountBy() {
        testIncome.increaseAmountBy(500);
        assertEquals("test", testIncome.getName());
        assertEquals(1500, testIncome.getAmount());

        testIncome.increaseAmountBy(600);
        assertEquals("test", testIncome.getName());
        assertEquals(2100, testIncome.getAmount());
    }

    @Test
    public void testDecreaseAmountBy() {
        testIncome.decreaseAmountBy(500);
        assertEquals("test", testIncome.getName());
        assertEquals(500, testIncome.getAmount());

        testIncome.decreaseAmountBy(499);
        assertEquals("test", testIncome.getName());
        assertEquals(1, testIncome.getAmount());
    }

    @Test
    public void testIncreaseAndDecrease() {
        testIncome2.increaseAmountBy(1);
        assertEquals("help", testIncome2.getName());
        assertEquals(2, testIncome2.getAmount());

        testIncome2.decreaseAmountBy(1);
        assertEquals("help", testIncome2.getName());
        assertEquals(1, testIncome2.getAmount());
    }
}

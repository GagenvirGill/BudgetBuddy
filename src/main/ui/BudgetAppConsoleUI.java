package ui;

import model.Budget;
import model.Expense;
import model.Income;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Budget Application
public class BudgetAppConsoleUI {
    private static final String JSON_STORE = "./data/budget.json";
    private Budget budget;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Effects: Runs the budget application
    public BudgetAppConsoleUI() {
        runBudget();
    }

    // Modifies: this
    // Effects: Processes User input
    private void runBudget() {
        boolean keepGoing = true;
        String userInput = null;

        initalize();

        while (keepGoing) {
            optionsMenu();

            userInput = input.next();
            userInput = userInput.toLowerCase();

            if (userInput.equals("q")) {
                keepGoing = false;
            } else {
                processUserInput(userInput);
            }
        }

        System.out.println("\nGoodbye User");
    }

    // Modifies: this
    // Effects: Processes Users input
    private void processUserInput(String userInput) {
        if (userInput.equals("i")) {
            doAddIncome();
        } else if (userInput.equals("e")) {
            doAddExpense();
        } else if (userInput.equals("n")) {
            doIncomeNames();
        } else if (userInput.equals("m")) {
            doExpenseNames();
        } else if (userInput.equals("t")) {
            doTotalIncomeAmount();
        } else if (userInput.equals("x")) {
            doTotalExpenseAmount();
        } else if (userInput.equals("s")) {
            doHowMuchCanUserSave();
        } else if (userInput.equals("r")) {
            saveBudget();
        } else if (userInput.equals("p")) {
            loadBudget();
        } else {
            System.out.println("Invalid selection");
        }
    }

    // Modifies: this
    // Effects: initializes budget
    private void initalize() {
        budget = new Budget();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // Effects: Displays menu of users options
    private void optionsMenu() {
        System.out.println("\n Select from:");
        System.out.println("\ti -> add monthly income source");
        System.out.println("\te -> add monthly expense");
        System.out.println("\tn -> get all income source names");
        System.out.println("\tm -> get all expenses names");
        System.out.println("\tt -> get total monthly income");
        System.out.println("\tx -> get total monthly expenses");
        System.out.println("\ts -> check how much money you have leftover after expenses");
        System.out.println("\tr -> save budget to file");
        System.out.println("\tp -> load budget from file");

        System.out.println("\tq -> quit");
    }

    // Modifies: this
    // Effects: Adds an income source to our budget
    private void doAddIncome() {
        Income current = doCreateIncome();
        budget.addIncome(current);
    }

    // Effects: Creates an income source with the given name and the given amount by the user each given after
    // the user is asked  in the console.
    private Income doCreateIncome() {
        boolean keepGoing = true;
        String selectedName = "";

        while (keepGoing) {
            System.out.println("What should the incomes name be?");
            selectedName = input.next();
            selectedName = selectedName.toLowerCase();
            keepGoing = false;
        }

        keepGoing = true;
        int selectedAmount = 0;

        while (keepGoing) {
            System.out.println("What should the monthly amount be?");
            selectedAmount = input.nextInt();
            keepGoing = false;
        }

        return new Income(selectedName, selectedAmount);
    }

    // Modifies: this
    // Effects: Adds an expense to our budget
    private void doAddExpense() {
        Expense current = doCreateExpense();
        budget.addExpense(current);
    }

    // Effects: Creates an expense with the given name and the given amount by the user each given after
    // the user is asked  in the console.
    private Expense doCreateExpense() {
        boolean keepGoing = true;
        String selectedName = "";

        while (keepGoing) {
            System.out.println("What should the expenses name be?");
            selectedName = input.next();
            selectedName = selectedName.toLowerCase();
            keepGoing = false;
        }

        keepGoing = true;
        int selectedAmount = 0;

        while (keepGoing) {
            System.out.println("What should the monthly amount be?");
            selectedAmount = input.nextInt();
            keepGoing = false;
        }

        return new Expense(selectedName, selectedAmount);
    }

    // Effects: Prints the names of all the income sources in the budget to the console.
    private void doIncomeNames() {
        String masterString = "Income Sources Names:";
        List<Income> budgetIncomes = budget.getIncomes();

        for (Income next : budgetIncomes) {
            masterString = masterString + ", " + next.getName();
        }

        System.out.println(masterString);
    }

    // Effects: Prints the names of all the expenses in the budget to the console.
    private void doExpenseNames() {
        String masterString = "Expenses Names:";
        List<Expense> budgetExpenses = budget.getExpenses();

        for (Expense next : budgetExpenses) {
            masterString = masterString + ", " + next.getName();
        }

        System.out.println(masterString);
    }

    // Effects: Prints the total of all income sources amounts to the console.
    private void doTotalIncomeAmount() {
        System.out.printf("Your total income value for the month is: $"
                + Integer.toString(budget.totalIncomeAmount()));
    }

    // Effects: Prints the total of all expense amounts to the console.
    private void doTotalExpenseAmount() {
        System.out.printf("Your total expenses value for the month is: $"
                + Integer.toString(budget.totalExpenseAmount()));
    }

    // Effects: Prints the difference between the total budget income and total budget expenses
    // to provide the user with how much money they will have leftover to save.
    private void doHowMuchCanUserSave() {
        System.out.printf("Your leftover money after paying all of your expenses, for you to save is: $"
                + Integer.toString(budget.howMuchMoneyCanUserSave()));
    }

    // Effects: saves the budget to file
    private void saveBudget() {
        try {
            jsonWriter.open();
            jsonWriter.write(budget);
            jsonWriter.close();
            System.out.println("Saved budget plan to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Modifies: this
    // Effects: loads budget from file
    private void loadBudget() {
        try {
            budget = jsonReader.read();
            System.out.println("Loaded budget plan from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

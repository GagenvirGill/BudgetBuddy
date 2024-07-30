package ui;

import model.Budget;
import model.Event;
import model.EventLog;
import model.Expense;
import model.Income;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// Budget GUI Application
public class BudgetAppGUI extends JFrame implements ActionListener {
    private JLabel addNameLabel;
    private JLabel addAmountLabel;
    private JLabel userFeedbackLabel;
    private JLabel imageLabel;
    private JLabel incomeDataLabel;
    private JLabel expenseDataLabel;
    private JLabel totalAmountsLabel;
    private JTextArea listIncomes;
    private JTextArea listExpenses;
    private JTextField addNameField;
    private JTextField addAmountField;
    private JButton addIncomeButton;
    private JButton addExpenseButton;
    private JButton refreshButton;
    private JButton saveDataButton;
    private JButton loadDataButton;
    private JPanel addingPanel;
    private JPanel buttonsPanel;
    private JPanel addedIncomeDetails;
    private JPanel addedExpenseDetails;
    private JPanel userFeedbackPanel;
    private JPanel imagePanel;
    private JPanel generalInfoPanel;
    private JPanel emptyPanel;

    private static final String JSON_STORE = "./data/budget.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Budget budget;

    // Effects: Runs the budget gui application
    public BudgetAppGUI() {
        super("Budget App");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog(EventLog.getInstance());
                dispose();
            }
        });
        budget = new Budget();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 750));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));

        initializeGuiFields();
        initializeGuiButtons();
        initializeGuiPanels();
        initializeGuiImage();
        addPanelsToApp();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // Modifies: this
    // Effects: Initializes the GUI fields and labels
    public void initializeGuiFields() {
        addNameLabel = new JLabel("Name:");
        addAmountLabel = new JLabel("Amount:");
        addNameField = new JTextField(10);
        addAmountField = new JTextField(10);
        userFeedbackLabel = new JLabel("-----");
        incomeDataLabel = new JLabel("-----");
        expenseDataLabel = new JLabel("-----");
        totalAmountsLabel = new JLabel("-----");
        listIncomes = new JTextArea("Incomes: ", 12, 70);
        listExpenses = new JTextArea("Expenses: ", 12, 70);
    }

    // Modifies: this
    // Effects: Initializes the GUI panels
    public void initializeGuiPanels() {
        imagePanel = new JPanel();
        imagePanel.setBounds(0, 0, 1000, 120);
        imagePanel.setBackground(Color.LIGHT_GRAY);

        addingPanel = new JPanel();
        addingPanel.setBounds(0, 120, 1000, 50);
        addingPanel.setBackground(Color.LIGHT_GRAY);

        buttonsPanel = new JPanel();
        buttonsPanel.setBounds(0, 170, 1000, 50);
        buttonsPanel.setBackground(Color.LIGHT_GRAY);

        userFeedbackPanel = new JPanel();
        userFeedbackPanel.setBounds(0, 220, 1000, 50);
        userFeedbackPanel.setBackground(Color.LIGHT_GRAY);

        addedIncomeDetails = new JPanel();
        addedIncomeDetails.setBounds(0, 270, 1000, 200);
        addedIncomeDetails.setBackground(Color.LIGHT_GRAY);

        addedExpenseDetails = new JPanel();
        addedExpenseDetails.setBounds(0, 470, 1000, 200);
        addedExpenseDetails.setBackground(Color.LIGHT_GRAY);

        generalInfoPanel = new JPanel();
        generalInfoPanel.setBounds(0, 670, 1000, 60);
        generalInfoPanel.setBackground(Color.LIGHT_GRAY);

        emptyPanel = new JPanel();
    }

    // Modifies: this
    // Effects: Initializes the GUI buttons
    public void initializeGuiButtons() {
        addIncomeButton = new JButton("Add Income");
        addIncomeButton.setActionCommand("myIncome");
        addIncomeButton.addActionListener(this);

        addExpenseButton = new JButton("Add Expense");
        addExpenseButton.setActionCommand("myExpense");
        addExpenseButton.addActionListener(this);

        refreshButton = new JButton("Refresh");
        refreshButton.setActionCommand("myRefresh");
        refreshButton.addActionListener(this);

        saveDataButton = new JButton("Save Data");
        saveDataButton.setActionCommand("mySave");
        saveDataButton.addActionListener(this);

        loadDataButton = new JButton("Load Data");
        loadDataButton.setActionCommand("myLoad");
        loadDataButton.addActionListener(this);
    }

    // Modifies: this
    // Effects: Initializes the GUI image
    public void initializeGuiImage() {
        imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("./data/image.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(100,100, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        imageLabel.setIcon(imageIcon);
    }

    // Modifies: this
    // Effects: Adds all the panels to the app
    public void addPanelsToApp() {
        addToPanels();

        add(imagePanel);
        add(addingPanel);
        add(buttonsPanel);
        add(userFeedbackPanel);
        add(addedIncomeDetails);
        add(addedExpenseDetails);
        add(generalInfoPanel);
        add(emptyPanel);
    }

    // Modifies: this
    // Effects: Adds all the Buttons, Labels, and Images to the correct panels
    public void addToPanels() {
        imagePanel.add(imageLabel);

        addingPanel.add(addNameLabel);
        addingPanel.add(addNameField);
        addingPanel.add(addAmountLabel);
        addingPanel.add(addAmountField);

        buttonsPanel.add(addIncomeButton);
        buttonsPanel.add(addExpenseButton);
        buttonsPanel.add(refreshButton);
        buttonsPanel.add(saveDataButton);
        buttonsPanel.add(loadDataButton);

        userFeedbackPanel.add(userFeedbackLabel);

        addedIncomeDetails.add(listIncomes);

        addedExpenseDetails.add(listExpenses);
        generalInfoPanel.add(incomeDataLabel);
        generalInfoPanel.add(expenseDataLabel);
        generalInfoPanel.add(totalAmountsLabel);
    }

    // Modifies: this
    // Effects: Processes User Inputs into actions
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myIncome")) {
            budget.addIncome(new Income(addNameField.getText(), Integer.parseInt(addAmountField.getText())));
            userFeedbackLabel.setText("Added '" + addNameField.getText() + "' Income");
        } else if (e.getActionCommand().equals("myExpense")) {
            budget.addExpense(new Expense(addNameField.getText(), Integer.parseInt(addAmountField.getText())));
            userFeedbackLabel.setText("Added '" + addNameField.getText() + "' Expense");
        } else if (e.getActionCommand().equals("myRefresh")) {
            listIncomes.setText(incomesText());
            listExpenses.setText(expensesText());
            incomeDataLabel.setText(totalIncomeText());
            expenseDataLabel.setText(totalExpenseText());
            totalAmountsLabel.setText(totalAmountText());
        } else if (e.getActionCommand().equals("mySave")) {
            saveBudgetData();
            userFeedbackLabel.setText("Saved Data to Budget App");
        } else if (e.getActionCommand().equals("myLoad")) {
            loadBudgetData();
            userFeedbackLabel.setText("Loaded Data to Budget App");
        }
    }

    // Effects: Adds the names of all the budget incomes to a single string with it beginning with
    //          "Income: "
    //          If !(next == incomes.get(incomes.size() - 1)) adds ", " in between each name
    public String incomesText() {
        List<Income> incomes = budget.getIncomes();
        String text = "Incomes: ";

        for (Income next : incomes) {
            if (next == incomes.get(incomes.size() - 1)) {
                text = text + next.getName();
            } else {
                text = text + next.getName() + ", ";
            }
        }

        return text;
    }

    // Effects: Adds the names of all the budget expenses to a single string with it beginning with
    //          "Expense: "
    //          If !(next == expenses.get(expenses.size() - 1)) adds ", " in between each name
    public String expensesText() {
        List<Expense> expenses = budget.getExpenses();
        String text = "Expenses: ";

        for (Expense next : expenses) {
            if (next == expenses.get(expenses.size() - 1)) {
                text = text + next.getName();
            } else {
                text = text + next.getName() + ", ";
            }
        }

        return text;
    }

    // Effects: Turns the total income amount values into a string
    public String totalIncomeText() {
        Integer total = budget.totalIncomeAmount();
        return "Total Income Amount (in $): " + total.toString() + "!   ";
    }

    // Effects: Turns the total expense amount values into a string
    public String totalExpenseText() {
        Integer total = budget.totalExpenseAmount();
        return "Total Expense Amount (in $): " + total.toString() + "!   ";
    }

    // Effects: Turns the difference between the total income amount and the total expense amount into a string
    public String totalAmountText() {
        Integer total = budget.howMuchMoneyCanUserSave();
        return "Total Amount Leftover after Expenses (in $): " + total.toString() + "!";
    }

    // Effects: saves the budget to file
    public void saveBudgetData() {
        try {
            jsonWriter.open();
            jsonWriter.write(budget);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            userFeedbackLabel.setText("Failed to save data!");
        }
    }

    // Modifies: this
    // Effects: loads budget from file
    public void loadBudgetData() {
        try {
            budget = jsonReader.read();
        } catch (IOException e) {
            userFeedbackLabel.setText("Failed to load data!");
        }
    }

    // Effects: Prints the whole event log to the console
    public void printLog(EventLog e) {
        for (Event next : e) {
            System.out.println(next.toString());
        }
    }
}

# My Personal Project

### *What will this application do?*

My application will be a system for implementing a budget for multiple individuals.
A person will give the system their expenses and income of the past.
It will be able to provide statistics over periods of time.
Possible features being able to provide names of recurring and non-recurring expenses and to provide the balance of the
account as well.
(X could be an expense, or a total expense amount for a specific category)
(Y could be a list of expenses, a list of total expense amounts over the month, or year, etc)

### *Who will use this application?*

This application can be used by people who are looking to budget their current expenses.
This can be because they would like to save money or simply because they want to know how they spend their money 
throughout each year.

### *Why does this project interest me?*

This project/application interests me because I am not very good at keeping track of my money. 
This application could help me manage my expenses in real life better.
In addition to this, this application seems that it has a lot of areas to implement bigger and larger ideas for growth 
in Java and Computer science knowledge.

### *User Stories*

- As a user, I want to be able to add an expense/income and add it to list of current expenses/incomes.
- As a user, I want to be able to view my current expenses and whether it goes above my current income.
- As a user, I want to be able to restart/reset/quit my budget plan.
- As a user, I want to be able to know how much excess money I will have to save after all of my expenses.
- As a user, I want to be able to know what incomes/expense I have already added to my budget plan.
- As a user, I want to be able to increase/decrease my budget, income, and individual expense costs.

- As a user, I want to be able to save my budget plan to file if I want to.
- As a user, I want to be able to load my budget plan from file if I want to.


### *Instructions for Grader*
1. You can generate the first required action related to the user story "adding multiple Xs to a Y" by putting a name 
in the "name" box, a positive integer in the "amount" box and clicking either "add income" or "add expense". Whichever
is clicked will determine what is added, an income or an expense.
2. You can view all the incomes and expense added to the budget plan so far by clicking the refresh button. This will
add all the incomes and expenses added so far to the boxes labelled "Incomes:" and "Expenses:".
3. You have a few related actions at the button with values that refresh when teh refresh button is clicked. The
values represent the total income so far (in $), the total expenses so far (in $) and the difference between the 
total income and total expenses: aka how much is leftover to be saved.
4. You can locate my visual component by looking at the top middle of the application to see a piggy bank image.
5. You can save the state of the application by clicking the "save data" button.
6. You can reload the state of the application by clicking the "load data" button.


### *Phase 4: Task 2*

- Tue Nov 28 18:58:01 PST 2023 \
Income called TestIncome1 added
- Tue Nov 28 18:58:05 PST 2023 \
Income called TestIncome1 added
- Tue Nov 28 18:58:13 PST 2023 \
Expense called TestExpense1 added
- Tue Nov 28 18:58:15 PST 2023 \
Expense called TestExpense1 added
- Tue Nov 28 18:58:16 PST 2023 \
Total Income amount at $1500 viewed
- Tue Nov 28 18:58:16 PST 2023 \
Total Expense amount at $1200 viewed
- Tue Nov 28 18:58:16 PST 2023 \
Total Net amount at $300 viewed
- Tue Nov 28 18:58:19 PST 2023 \
Saved Current Data
- Tue Nov 28 18:58:20 PST 2023 \
Total Income amount at $1500 viewed
- Tue Nov 28 18:58:20 PST 2023 \
Total Expense amount at $1200 viewed
- Tue Nov 28 18:58:20 PST 2023 \
Total Net amount at $300 viewed
- Tue Nov 28 18:58:22 PST 2023 \
Income called TestIncome1 added
- Tue Nov 28 18:58:22 PST 2023 \
Income called TestIncome1 added
- Tue Nov 28 18:58:22 PST 2023 \
Expense called TestExpense1 added
- Tue Nov 28 18:58:22 PST 2023 \
Expense called TestExpense1 added
- Tue Nov 28 18:58:22 PST 2023 \
Loaded Saved Data



### *Phase 4: Task 3*
Something that I would consider refactoring in my project is implementing a type hierarchy for the Income and
Expense classes. The reason that I believe I should do this is because Income and Expense both have the exact same
implementation except for the fact that one is an income and the other is an expense. Therefore, I would refactor it to
have an abstract class titled Money or something of that nature. It would have fully implemented methods, and then 
I would have an Income and Expense class extending the Money class with the only method in those classes being the 
constructor.

Another change I would make to my project in order to increase performance would be to change the array lists of 
income and expenses in Budget to maps instead to improve access as in my project the order in which thngs are added is
not a crucial aspect of its functionality.
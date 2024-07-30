package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

public class JsonReader {
    // NOTE: Code in this file is based on the JSONSerializationDemo file

    private final String source;

    // Effects: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // Effects: reads budget from file and returns it, throws IOException if an error occurs reading data from file
    public Budget read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBudget(jsonObject);
    }

    // Effects: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // Effects: parses Budget from JSON object and returns it
    private Budget parseBudget(JSONObject jsonObject) {
        //***
        Budget b = new Budget();
        addIncomes(b, jsonObject);
        addExpenses(b, jsonObject);

        EventLog.getInstance().logEvent(new Event("Loaded Saved Data"));

        return b;
    }

    // Modifies: b
    // Effects: parses incomes from JSON object and adds them to Budget
    private void addIncomes(Budget b, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("incomes");
        for (Object json : jsonArray) {
            JSONObject nextIncome = (JSONObject) json;
            addIncome(b, nextIncome);
        }

    }

    // Modifies: b
    // Effects: parses income from JSON object and adds it to budget
    private void addIncome(Budget b, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int amount = jsonObject.getInt("amount");
        Income income = new Income(name, amount);
        b.addIncome(income);
    }

    // Modifies: b
    // Effects: parses expenses from JSON object and adds them to Budget
    private void addExpenses(Budget b, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            addExpense(b, nextExpense);
        }

    }

    // Modifies: b
    // Effects: parses expense from JSON object and adds it to budget
    private void addExpense(Budget b, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int amount = jsonObject.getInt("amount");
        Expense expense = new Expense(name, amount);
        b.addExpense(expense);
    }
}

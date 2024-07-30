package persistence;

import model.Budget;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representations of budget to file
public class JsonWriter {
    // NOTE: Code in this file is based on the JSONSerializationDemo file

    private static final int TAB = 4;
    private PrintWriter writer;
    private final String destination;

    // Effects: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // Modifies: this
    // Effects: opens writer, throws FileNoteFoundException if destination file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // Modifies: this
    // Effects: writes JSON representation of budget to file
    public void write(Budget b) {
        JSONObject json = b.toJson();
        saveToFile(json.toString(TAB));
    }

    // Modifies: this
    // Effects: closes writer
    public void close() {
        writer.close();
    }

    // Modifies: this
    // Effects: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}

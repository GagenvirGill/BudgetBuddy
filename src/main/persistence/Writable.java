package persistence;

import org.json.JSONObject;

public interface Writable {
    // NOTE: Code in this file is based on the JSONSerializationDemo file

    // Effects: Returns this as a JSON object
    JSONObject toJson();
}

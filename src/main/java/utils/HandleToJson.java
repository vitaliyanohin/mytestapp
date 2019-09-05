package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class HandleToJson {

  private static final Logger LOGGER = Logger.getLogger(HandleToJson.class);

  public static JsonArray convertAndHandleInputDataToJson(String inputData) {
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    JsonArray jsonArray = (JsonArray) new JsonParser().parse(inputData);
    JsonArray finalJsonArray = new JsonArray();
    for (int i = 0; i < jsonArray.size(); i++) {
      try {
        JsonObject tempJsonObject = new JsonObject();
        tempJsonObject.add("function", jsonArray.get(i));
        tempJsonObject.addProperty("result",
                engine.eval(jsonArray.get(i).toString().replace('"', ' ')).toString());
        finalJsonArray.add(tempJsonObject);
      } catch (ScriptException e) {
        LOGGER.log(Level.ERROR, "Failed to get connection: ", e);
      }
    }
    return finalJsonArray;
  }
}

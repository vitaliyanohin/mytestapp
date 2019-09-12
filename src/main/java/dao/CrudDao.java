package dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Optional;

public interface CrudDao {

  void add(JsonArray json, JsonObject data);

  Optional<JsonArray> getCalculation(String data);

  Optional<List<Object>> getAll();

  void delete(JsonArray json);

}

package service;

import com.google.gson.JsonArray;

import java.util.List;
import java.util.Optional;

public interface JsonCrudService {

  void prepareAndSaveCalculation(String inputData);

  Optional<JsonArray> getCalculation(String inputData);

  Optional<JsonArray> getAll();

  void delete(String inputData);

}

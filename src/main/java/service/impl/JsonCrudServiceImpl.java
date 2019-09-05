package service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.CrudDao;
import factory.CrudDaoFactory;
import service.JsonCrudService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class JsonCrudServiceImpl implements JsonCrudService {

  private static final CrudDao crudDao = CrudDaoFactory.getInstance();

  @Override
  public void prepareAndSaveCalculation(String inputData) {
    JsonArray jsonExpressions = (JsonArray) new JsonParser().parse(inputData);
    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime());
    JsonObject date = new JsonObject();
    date.addProperty("date_calculation", timeStamp);
    crudDao.add(jsonExpressions, date);
  }

  @Override
  public Optional<JsonArray> getCalculation(String inputData) {
    return crudDao.getCalculation(inputData);
  }

  @Override
  public Optional<JsonArray> getAll() {
    Optional<List<Object>> jsonList = crudDao.getAll();
    if (jsonList.isPresent()) {
      JsonParser jsonParser = new JsonParser();
      JsonArray jsonArray = (JsonArray) jsonParser.parse(String.valueOf(jsonList.get()));
      return Optional.of(jsonArray);
    }
    return Optional.empty();
  }

  @Override
  public void delete(String inputData) {
    JsonArray jsonArray = (JsonArray) new JsonParser().parse(inputData);
    crudDao.delete(jsonArray);
  }
}

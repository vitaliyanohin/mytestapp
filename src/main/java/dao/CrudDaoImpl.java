package dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import utils.GetSQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CrudDaoImpl implements CrudDao {

  private static final Logger LOGGER = Logger.getLogger(CrudDaoImpl.class);
  private Connection connection;
  private static final String INSERT_JSON = "INSERT INTO orders (calculation, date_calculation) " +
          "VALUES (to_json(?::json), to_json(?::json))";
  private static final String GET_ALL = "SELECT date_calculation FROM orders";
  private static final String GET_CALCULATION = "SELECT id, calculation FROM orders WHERE date_calculation ->> 'date_calculation' = ?";
  private static final String DELETE = "DELETE FROM orders where calculation::jsonb = ?::jsonb";

  public CrudDaoImpl() {
    connection = GetSQLConnection.getMysqlConnection();
  }

  public void add(JsonArray json, JsonObject data) {
    try (PreparedStatement statement = connection.prepareStatement(INSERT_JSON)) {
      statement.setString(1, json.toString());
      statement.setString(2, data.toString());
      statement.execute();
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to add json to DB: ", e);
    }
  }

  @Override
  public void delete(JsonArray json) {
    try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
      statement.setString(1, json.toString());
      statement.execute();
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to delete json: ", e);
    }
  }

  @Override
  public Optional<List<Object>> getAll() {
    try (PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
      ResultSet resultSet = statement.executeQuery();
      List<Object> allCalculations = new ArrayList<>();

      while (resultSet.next()) {
        allCalculations.add(resultSet.getObject("date_calculation"));
      }
      if (allCalculations.isEmpty()) {
        return Optional.empty();
      }
      return Optional.of(allCalculations);
    } catch (SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get All Calculation: ", e);
    }
    return Optional.empty();
  }

  @Override
  public Optional<JsonArray> getCalculation(String data) {
    try (PreparedStatement statement = connection.prepareStatement(GET_CALCULATION)) {
      statement.setString(1, data);
      ResultSet resultSet = statement.executeQuery();
      resultSet.next();
      JsonArray jsonArray = (JsonArray) new JsonParser().parse(resultSet.getString("calculation"));
      return Optional.of(jsonArray);
    } catch (SQLException e) {
      LOGGER.log(Level.ERROR, "Failed to get Calculation: ", e);
    }
    return Optional.empty();
  }
}

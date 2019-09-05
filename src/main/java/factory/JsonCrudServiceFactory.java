package factory;

import service.JsonCrudService;
import service.impl.JsonCrudServiceImpl;

public class JsonCrudServiceFactory {

  private static JsonCrudService userInstance;

  public static JsonCrudService getInstance() {
    if (userInstance == null) {
      userInstance = new JsonCrudServiceImpl();
    }
    return userInstance;
  }
}

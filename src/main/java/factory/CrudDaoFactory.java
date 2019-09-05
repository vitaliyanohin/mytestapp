package factory;

import dao.CrudDao;
import dao.CrudDaoImpl;

public class CrudDaoFactory {

  private static CrudDao userInstance;

  public static CrudDao getInstance() {
    if (userInstance == null) {
      userInstance = new CrudDaoImpl();
    }
    return userInstance;
  }
}

package controller;

import com.google.gson.JsonArray;
import dao.CrudDaoImpl;
import factory.JsonCrudServiceFactory;
import service.JsonCrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet(value = "/all")
@MultipartConfig()
public class GetAllCalculationsServlet extends HttpServlet {

  private final JsonCrudService jsonCrudService = JsonCrudServiceFactory.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Optional<JsonArray> jsonArray = jsonCrudService.getAll();
    if (jsonArray.isPresent()) {
      resp.setContentType("application/json");
      resp.setCharacterEncoding("UTF-8");
      resp.setStatus(HttpServletResponse.SC_OK);
      PrintWriter out = resp.getWriter();
      out.print(jsonArray.get());
      out.flush();
    } else {
      resp.setHeader("text", "DB is empty or else...");
      resp.sendError(HttpServletResponse.SC_NO_CONTENT);
    }
  }
}

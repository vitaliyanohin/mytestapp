package controller;

import com.google.gson.JsonArray;
import factory.JsonCrudServiceFactory;
import service.JsonCrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet(value = "/getCalculation")
@MultipartConfig()
public class GetCalculationServlet extends HttpServlet {

  private final JsonCrudService jsonCrudService = JsonCrudServiceFactory.getInstance();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Part part = req.getPart("formDataJson");
    InputStream fileInputStream = part.getInputStream();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, Charset.defaultCharset()));
    String inputData = bufferedReader.lines().collect(Collectors.joining());
    Optional<JsonArray> jsonArray = jsonCrudService.getCalculation(inputData);
    if (jsonArray.isPresent()) {
      resp.setContentType("application/json");
      resp.setCharacterEncoding("UTF-8");
      resp.setStatus(HttpServletResponse.SC_OK);
      PrintWriter out = resp.getWriter();
      out.print(jsonArray.get());
      out.flush();
    } else {
      resp.setHeader("text", "Data exist");
      resp.sendError(HttpServletResponse.SC_NO_CONTENT);
    }
  }
}
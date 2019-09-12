package controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import dao.CrudDaoImpl;
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
import java.util.stream.Collectors;

  @WebServlet(value = "/delete")
  @MultipartConfig()
  public class DeleteCalculationServlet extends HttpServlet {

    private final JsonCrudService jsonCrudService = JsonCrudServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Part part = req.getPart("formDataJson");
      InputStream fileInputStream = part.getInputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream, Charset.defaultCharset()));
      String inputData = br.lines().collect(Collectors.joining());
      jsonCrudService.delete(inputData);
      resp.setStatus(HttpServletResponse.SC_OK);
    }
  }

package controller;

import com.google.gson.JsonArray;
import utils.HandleToJson;

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

@WebServlet(value = "/calculate")
@MultipartConfig()
public class CalculateExpressionServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Part part = req.getPart("formDataJson");
    InputStream fileInputStream = part.getInputStream();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, Charset.defaultCharset()));
    String inputData = bufferedReader.lines().collect(Collectors.joining());
    JsonArray processedData = HandleToJson.convertAndHandleInputDataToJson(inputData);
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    resp.setStatus(HttpServletResponse.SC_OK);
    PrintWriter out = resp.getWriter();
    out.print(processedData);
    out.flush();
  }
}

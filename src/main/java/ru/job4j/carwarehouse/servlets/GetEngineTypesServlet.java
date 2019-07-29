package ru.job4j.carwarehouse.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.carwarehouse.store.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 25.07.2019
 */
public class GetEngineTypesServlet extends HttpServlet {

    private final EngineStore engineStore = EngineStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<String> engineNames = engineStore.getEngineTypes();
        ObjectMapper objectMapper = new ObjectMapper();
        String toJson = objectMapper.writeValueAsString(engineNames);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        System.out.println(toJson);
        writer.append(toJson);
        writer.flush();
    }
}
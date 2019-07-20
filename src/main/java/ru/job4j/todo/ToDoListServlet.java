package ru.job4j.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.todo.models.Item;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 20.07.2019
 */
public class ToDoListServlet extends HttpServlet {

    private ToDoListService service = new ToDoListService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Item> list = service.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        String toJson = objectMapper.writeValueAsString(list);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.print(toJson);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String desc = req.getParameter("description");
        Item item = new Item(desc, new Date(System.currentTimeMillis()), false);
        service.saveItem(item);
        resp.sendRedirect("/index.html");
    }
}
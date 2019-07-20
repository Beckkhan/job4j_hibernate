package ru.job4j.todo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 20.07.2019
 */
public class ItemStatusServlet extends HttpServlet {

    private ToDoListService service = new ToDoListService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("itemId"));
        boolean done = Boolean.valueOf(req.getParameter("itemStatus"));
        service.resetStatus(id, done);
    }
}
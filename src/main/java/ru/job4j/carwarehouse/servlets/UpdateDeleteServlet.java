package ru.job4j.carwarehouse.servlets;

import ru.job4j.carwarehouse.store.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 25.07.2019
 */
public class UpdateDeleteServlet extends HttpServlet {

    private final CarStore carStore = CarStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("carId"));
        boolean done = Boolean.valueOf(req.getParameter("sold"));
        carStore.statusChange(id, done);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.valueOf(req.getParameter("carId"));
        int result = carStore.delete(id);
        if (result > 0) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Cannot delete.");
            doGet(req, resp);

        }
    }
}
package ru.job4j.carwarehouse.servlets;

import ru.job4j.carwarehouse.entity.*;
import ru.job4j.carwarehouse.store.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 25.07.2019
 */
public class CarsServlet extends HttpServlet {

    private final CarStore carStore = CarStore.getInstance();

    private final PictureStore pictureStore = PictureStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<Car> list = carStore.getAll();
        List<Picture> images;
        for (Car car : list) {
            images  = pictureStore.getImagesByCarId(car.getId());
            car.setPictures(images);
        }
        req.setAttribute("cars", list);
        req.getRequestDispatcher("WEB-INF/views/Cars.jsp").forward(req, resp);
    }
}
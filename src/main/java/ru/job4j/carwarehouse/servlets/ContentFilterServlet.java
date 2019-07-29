package ru.job4j.carwarehouse.servlets;

import ru.job4j.carwarehouse.entity.*;
import ru.job4j.carwarehouse.store.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 25.07.2019
 */
public class ContentFilterServlet extends HttpServlet {

    private final CarStore carStore = CarStore.getInstance();

    private final PictureStore pictureStore = PictureStore.getInstance();

    private List<Car> list = null;

    private List<Picture> pictures;

    private List<Car> result = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String soldStatus = req.getParameter("filterSold");
        String withPicture = req.getParameter("filterImage");
        String name = req.getParameter("filterName");

        if (soldStatus != null && !name.equals("")) {
            list = carStore.filterCarsBySoldAndName(false, name);
        } else if (soldStatus != null) {
            list = carStore.filterCarsBySold(false);
        } else if (!name.equals("")) {
            list = carStore.filterCarsByName(name);
        } else {
            list = carStore.getAll();
        }

        for (Car car : list) {
            pictures  = pictureStore.getImagesByCarId(car.getId());
            car.setPictures(pictures);
        }

        if (withPicture != null) {
            for (Car c : list) {
                if (!c.getPictures().isEmpty()) {
                    result.add(c);
                }
            }
            list = result;
        }
        req.setAttribute("cars", list);
        req.getRequestDispatcher("WEB-INF/views/CarFilter.jsp").forward(req, resp);
    }
}
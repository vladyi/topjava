package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.CALORIES_PER_DAY;
import static ru.javawebinar.topjava.util.MealsUtil.filteredByStreams;

public class MealsServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(MealsServlet.class);
    private List<MealTo> meals;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (meals == null) {
            meals = filteredByStreams(MealsUtil.init(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
        }

        req.setAttribute("meals", meals);
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }
}

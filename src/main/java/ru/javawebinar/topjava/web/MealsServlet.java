package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.javawebinar.topjava.util.MealsUtil.CALORIES_PER_DAY;
import static ru.javawebinar.topjava.util.MealsUtil.getEntries;

@WebServlet(name = "MealsServlet", urlPatterns = {"/MealsServlet"})
public class MealsServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(MealsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("meals", getEntries(MealsUtil.init(), CALORIES_PER_DAY));
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }
}

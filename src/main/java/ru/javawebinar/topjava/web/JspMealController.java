package ru.javawebinar.topjava.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.AbstractMealRestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Controller
public class JspMealController extends AbstractMealRestController {

    public JspMealController(MealService service) {
        super(service);
    }

    @GetMapping("/meals")
    public String getAll(Model model) {
        model.addAttribute("users", super.getAll());
        return "meals";
    }

    @PostMapping("/meals")
    public String create(HttpServletRequest request) {
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        request.setAttribute("meal", meal);
        return "forward:meals";
    }

    @PutMapping("/meals")
    public String update(HttpServletRequest request) {
        final Meal meal = super.get(getId(request));
        request.setAttribute("meal", meal);
        return "forward:meals";
    }

    @DeleteMapping("/meals")
    public String delete(HttpServletRequest request) {
        int id = getId(request);
        super.delete(id);
        return "redirect:meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}

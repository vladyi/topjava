package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkAuthorizationRights;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;


@Service
public class MealService {

    private MealRepository repository;

    public MealService(@Autowired MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Meal get(int id) throws NotFoundException {
        Meal meal = checkNotFoundWithId(repository.get(id), id);
        checkAuthorizationRights(meal.getUserId());
        return meal;
    }

    public void update(Meal meal) {
        checkNotFoundWithId(repository.save(meal), meal.getId());
    }

    public Collection<Meal> getAll() {
        Collection<Meal> meals = repository.getAll();
        meals.stream().peek(val -> checkAuthorizationRights(val.getUserId()));
        return meals;
    }
}
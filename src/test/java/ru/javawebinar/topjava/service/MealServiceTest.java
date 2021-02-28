package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void delete() {
        service.delete(ADMIN_MEAL_ID, ADMIN_ID);
        assertThrows(NotFoundException.class, () -> service.delete(ADMIN_MEAL_ID, ADMIN_ID));
    }

    @Test
    public void deletedNotOwn() {
        assertThrows(NotFoundException.class, () -> service.delete(ADMIN_MEAL_ID, USER_ID));
    }

    @Test
    public void deletedNotExists() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_EXISTS_MEAL_ID, USER_ID));
    }

    @Test
    public void create() {
        Meal mealActual = service.create(getNew(), USER_ID);
        Meal mealExpected = getNew();
        int mealId = mealActual.getId();
        mealExpected.setId(mealActual.getId());
        MealTestData.assertMatch(mealActual, mealExpected);
        MealTestData.assertMatch(service.get(mealId, USER_ID), mealExpected);
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () -> service.create(getDuplicated(), USER_ID));
    }

    @Test
    public void get() {
        Meal mealActual = service.get(ADMIN_MEAL_ID, ADMIN_ID);
        MealTestData.assertMatch(mealActual, getExists());
    }

    @Test
    public void getNotExist() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_EXISTS_MEAL_ID, ADMIN_ID));
    }

    @Test
    public void getNotOwn() {
        assertThrows(NotFoundException.class, () -> service.get(ADMIN_MEAL_ID, USER_ID));
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, ADMIN_ID);
        MealTestData.assertMatch(service.get(ADMIN_MEAL_ID, ADMIN_ID), getUpdated());
    }

    @Test
    public void updateNotOwn() {
        assertThrows(NotFoundException.class, () -> service.update(service.get(ADMIN_MEAL_ID, ADMIN_ID), USER_ID));
    }

    @Test
    public void getAll() {
        MealTestData.assertMatch(service.getAll(USER_ID), meals);
    }

    @Test
    public void getBetweenInclusive() {
        MealTestData.assertMatch(service.getBetweenInclusive(
                LocalDate.of(2021, Month.FEBRUARY, 1),
                LocalDate.of(2021, Month.FEBRUARY, 1), USER_ID),
                meal3, meal2, meal1);
    }

    @Test
    public void getBetweenWithNullDates() {
        MealTestData.assertMatch(service.getBetweenInclusive(null, null, USER_ID), meals);
    }
}
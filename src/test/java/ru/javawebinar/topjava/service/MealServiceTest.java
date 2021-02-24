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

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.MealTestData.getDuplicated;
import static ru.javawebinar.topjava.MealTestData.getExists;
import static ru.javawebinar.topjava.MealTestData.getNew;
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
    MealService mealService;

    @Test
    public void create() {
        Meal mealActual = mealService.create(getNew(), USER_ID);
        Meal mealExpected = getNew();
        int mealId = mealActual.getId();
        mealExpected.setId(mealActual.getId());
        MealTestData.assertMatch(mealActual, mealExpected);
        MealTestData.assertMatch(mealService.get(mealId, USER_ID), mealExpected);
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () -> mealService.create(getDuplicated(), USER_ID));
    }

    @Test
    public void delete() {
        mealService.delete(ADMIN_MEAL_ID, ADMIN_ID);
        assertThrows(NotFoundException.class, () -> mealService.delete(ADMIN_MEAL_ID, ADMIN_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> mealService.delete(ADMIN_MEAL_ID, USER_ID));
    }

    @Test
    public void get() {
        Meal mealActual = mealService.get(ADMIN_MEAL_ID, ADMIN_ID);
        MealTestData.assertMatch(mealActual, getExists());
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> mealService.get(ADMIN_MEAL_ID, USER_ID));
    }

    @Test
    public void updateNotFound() {
        assertThrows(NotFoundException.class, () -> mealService.update(mealService.get(ADMIN_MEAL_ID, ADMIN_ID), USER_ID));
    }
}
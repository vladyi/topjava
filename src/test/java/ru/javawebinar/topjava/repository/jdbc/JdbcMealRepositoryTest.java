package ru.javawebinar.topjava.repository.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.MealTestData.getDuplicated;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JdbcMealRepositoryTest {

    @Autowired
    JdbcMealRepository mealRepository;

    @Test
    public void delete() {
        mealRepository.delete(ADMIN_MEAL_ID, ADMIN_ID);
        assertFalse(mealRepository.delete(ADMIN_MEAL_ID, ADMIN_ID));
    }

    @Test
    public void deletedNotFound() {
        assertFalse(mealRepository.delete(ADMIN_MEAL_ID, USER_ID));
    }

    @Test
    public void getNotFound() {
        assertNull(mealRepository.get(ADMIN_MEAL_ID, USER_ID));
    }

    @Test
    public void updateNotFound() {
        assertNull(mealRepository.save(mealRepository.get(ADMIN_MEAL_ID, ADMIN_ID), USER_ID));
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DuplicateKeyException.class, () -> mealRepository.save(getDuplicated(), USER_ID));
    }
}
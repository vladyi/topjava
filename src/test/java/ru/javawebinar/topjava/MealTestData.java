package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int ADMIN_MEAL_ID = START_SEQ + 5;
    public static final int NOT_EXISTS_MEAL_ID = START_SEQ + 100;

    public static final Meal duplicateUserMeal = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 3, 19, 14),
            "User Dinner", 786);

    public static final Meal newMeal = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 5, 19, 14),
            "User Dinner", 1435);

    public static final Meal existsMeal = new Meal(ADMIN_MEAL_ID, LocalDateTime.of(2021, Month.FEBRUARY, 2, 8, 37),
            "Admin breakfast", 537);

    public static Meal getDuplicated() {
        return new Meal(duplicateUserMeal.getDateTime(), duplicateUserMeal.getDescription(), duplicateUserMeal.getCalories());
    }

    public static Meal getNew() {
        return new Meal(newMeal.getDateTime(), newMeal.getDescription(), newMeal.getCalories());
    }

    public static Meal getExists() {
        return new Meal(existsMeal.getId(), existsMeal.getDateTime(), existsMeal.getDescription(), existsMeal.getCalories());
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}

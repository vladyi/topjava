package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int ADMIN_MEAL_ID = START_SEQ + 5;
    public static final int USER_MEAL_ID = START_SEQ + 12;
    public static final int NOT_EXISTS_MEAL_ID = START_SEQ + 100;

    public static final Meal duplicateUserMeal = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 3, 19, 14),
            "User Dinner", 786);

    public static final Meal newMeal = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 5, 19, 14),
            "User Dinner", 1435);

    public static final Meal existsMeal = new Meal(ADMIN_MEAL_ID, LocalDateTime.of(2021, Month.FEBRUARY, 2, 8, 37),
            "Admin breakfast", 537);

    public static final Meal meal1 = new Meal(USER_MEAL_ID, LocalDateTime.of(2021, Month.FEBRUARY, 1, 8, 11),
            "User breakfast", 412);

    public static final Meal meal2 = new Meal(USER_MEAL_ID + 1, LocalDateTime.of(2021, Month.FEBRUARY, 1, 12, 19),
            "User lunch", 1167);

    public static final Meal meal3 = new Meal(USER_MEAL_ID + 2, LocalDateTime.of(2021, Month.FEBRUARY, 1, 21, 0),
            "User dinner", 1731);

    public static final Meal meal4 = new Meal(USER_MEAL_ID + 3, LocalDateTime.of(2021, Month.FEBRUARY, 2, 8, 43),
            "User breakfast", 581);

    public static final Meal meal5 = new Meal(USER_MEAL_ID + 4, LocalDateTime.of(2021, Month.FEBRUARY, 2, 12, 32),
            "User lunch", 1245);

    public static final Meal meal6 = new Meal(USER_MEAL_ID + 5, LocalDateTime.of(2021, Month.FEBRUARY, 2, 18, 27),
            "User dinner", 1543);

    public static final Meal meal7 = new Meal(USER_MEAL_ID + 6, LocalDateTime.of(2021, Month.FEBRUARY, 3, 7, 53),
            "User breakfast", 654);

    public static final Meal meal8 = new Meal(USER_MEAL_ID + 7, LocalDateTime.of(2021, Month.FEBRUARY, 3, 13, 34),
            "User lunch", 1500);

    public static final Meal meal9 = new Meal(USER_MEAL_ID + 8, LocalDateTime.of(2021, Month.FEBRUARY, 3, 19, 14),
            "User dinner", 1718);

    public static final Meal meal10 = new Meal(USER_MEAL_ID + 9, LocalDateTime.of(2021, Month.JANUARY, 31, 0, 0),
            "User dinner", 1363);

    public static final Meal meal11 = new Meal(USER_MEAL_ID + 10, LocalDateTime.of(2021, Month.FEBRUARY, 4, 8, 3),
            "User breakfast", 451);

    public static final List<Meal> meals = Arrays.asList(meal11, meal9, meal8, meal7, meal6, meal5, meal4, meal3, meal2, meal1, meal10);

    public static Meal getDuplicated() {
        return new Meal(duplicateUserMeal.getDateTime(), duplicateUserMeal.getDescription(), duplicateUserMeal.getCalories());
    }

    public static Meal getNew() {
        return new Meal(newMeal.getDateTime(), newMeal.getDescription(), newMeal.getCalories());
    }

    public static Meal getExists() {
        return new Meal(existsMeal.getId(), existsMeal.getDateTime(), existsMeal.getDescription(), existsMeal.getCalories());
    }

    public static Meal getUpdated() {
        return new Meal(ADMIN_MEAL_ID, existsMeal.getDateTime().plus(2, ChronoUnit.MINUTES), "Обновленный завтрак", 200);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}

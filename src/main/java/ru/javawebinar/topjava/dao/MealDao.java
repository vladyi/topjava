package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MealDao {

    private Connection connection;

    public MealDao() {
        connection = DbUtil.getConnection();
    }

    public void addMeal(Meal meal) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into meals(date,description,calories) values (?, ?, ? )");

            preparedStatement.setDate(1, java.sql.Date.valueOf(meal.getDate()));
            preparedStatement.setString(2, meal.getDescription());
            preparedStatement.setInt(3, meal.getCalories());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            //TODO
        }
    }

    public void deleteMeal(int id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from meals where id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMeal(Meal meal) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update meals set date=?, description=?, calories=? where id=?");

            preparedStatement.setDate(1, java.sql.Date.valueOf(meal.getDate()));
            preparedStatement.setString(2, meal.getDescription());
            preparedStatement.setInt(3, meal.getCalories());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            //TODO
        }
    }

    public List<Meal> getAllUsers() {
        List<Meal> meals = new ArrayList<Meal>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from meals");
            while (rs.next()) {
                Meal meal = new Meal();
                meal.setId(rs.getInt("id"));
                meal.setDateTime(rs.getDate("date").toLocalDate());
                meal.setDescription(rs.getString("description"));
                meal.setCalories(rs.getInt("calories"));

                meals.add(meal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return meals;
    }

    public Meal getUserById(int id) {
        Meal meal = new Meal();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from meals where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                meal.setId(rs.getInt("id"));
                meal.setDateTime(rs.getDate("date").toLocalDate());
                meal.setDescription(rs.getString("description"));
                meal.setCalories(rs.getInt("calories"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return meal;
    }
}

package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Map;
import java.util.TreeMap;



public class DepartmentStoreManagementSystem extends Application {

    // TreeMap to store employees sorted by their ID
    private final Map<String, Employee> employees = new TreeMap<>();

    // Main method to launch the application
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Department Store Management System");

        // Create the main menu
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label menuLabel = new Label("Choose an option:");
        GridPane.setConstraints(menuLabel, 0, 0);
        grid.getChildren().add(menuLabel);

        Button addEmployeeButton = new Button("Add Employee");
        GridPane.setConstraints(addEmployeeButton, 0, 1);
        addEmployeeButton.setOnAction(e -> addEmployee());
        grid.getChildren().add(addEmployeeButton);

        Button removeEmployeeButton = new Button("Remove Employee");
        GridPane.setConstraints(removeEmployeeButton, 0, 2);
        removeEmployeeButton.setOnAction(e -> removeEmployee());
        grid.getChildren().add(removeEmployeeButton);

        Button updateEmployeeButton = new Button("Update Employee Info");
        GridPane.setConstraints(updateEmployeeButton, 0, 3);
        updateEmployeeButton.setOnAction(e -> updateEmployee());
        grid.getChildren().add(updateEmployeeButton);

        Button listEmployeesButton = new Button("List Employees");
        GridPane.setConstraints(listEmployeesButton, 0, 4);
        listEmployeesButton.setOnAction(e -> listEmployees());
        grid.getChildren().add(listEmployeesButton);

        Button findEmployeeByIdButton = new Button("Find Employee by ID");
        GridPane.setConstraints(findEmployeeByIdButton, 0, 5);
        findEmployeeByIdButton.setOnAction(e -> findEmployeeById());
        grid.getChildren().add(findEmployeeByIdButton);

        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Employee class to hold employee details
    static class Employee {
        String name;
        String position;
        double salary;

        Employee(String name, String position, double salary) {
            this.name = name;
            this.position = position;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Position: " + position + ", Salary: " + salary;
        }
    }

    // Method to add an employee
    private void addEmployee() {
        Stage stage = new Stage();
        stage.setTitle("Add Employee");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label idLabel = new Label("Employee ID:");
        GridPane.setConstraints(idLabel, 0, 0);
        TextField idInput = new TextField();
        GridPane.setConstraints(idInput, 1, 0);

        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 1);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 1);

        Label positionLabel = new Label("Position:");
        GridPane.setConstraints(positionLabel, 0, 2);
        TextField positionInput = new TextField();
        GridPane.setConstraints(positionInput, 1, 2);

        Label salaryLabel = new Label("Salary:");
        GridPane.setConstraints(salaryLabel, 0, 3);
        TextField salaryInput = new TextField();
        GridPane.setConstraints(salaryInput, 1, 3);

        Button addButton = new Button("Add");
        GridPane.setConstraints(addButton, 1, 4);
        addButton.setOnAction(e -> {
            String id = idInput.getText();
            String name = nameInput.getText();
            String position = positionInput.getText();
            double salary = Double.parseDouble(salaryInput.getText());
            employees.put(id, new Employee(name, position, salary));
            stage.close();
        });

        grid.getChildren().addAll(idLabel, idInput, nameLabel, nameInput, positionLabel, positionInput, salaryLabel, salaryInput, addButton);

        Scene scene = new Scene(grid, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    // Method to remove an employee
    private void removeEmployee() {
        Stage stage = new Stage();
        stage.setTitle("Remove Employee");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label idLabel = new Label("Employee ID:");
        GridPane.setConstraints(idLabel, 0, 0);
        TextField idInput = new TextField();
        GridPane.setConstraints(idInput, 1, 0);

        Button removeButton = new Button("Remove");
        GridPane.setConstraints(removeButton, 1, 1);
        removeButton.setOnAction(e -> {
            String id = idInput.getText();
            employees.remove(id);
            stage.close();
        });

        grid.getChildren().addAll(idLabel, idInput, removeButton);

        Scene scene = new Scene(grid, 300, 100);
        stage.setScene(scene);
        stage.show();
    }

    // Method to update employee information
    private void updateEmployee() {
        Stage stage = new Stage();
        stage.setTitle("Update Employee Info");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label idLabel = new Label("Employee ID:");
        GridPane.setConstraints(idLabel, 0, 0);
        TextField idInput = new TextField();
        GridPane.setConstraints(idInput, 1, 0);

        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 1);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 1);

        Label positionLabel = new Label("Position:");
        GridPane.setConstraints(positionLabel, 0, 2);
        TextField positionInput = new TextField();
        GridPane.setConstraints(positionInput, 1, 2);

        Label salaryLabel = new Label("Salary:");
        GridPane.setConstraints(salaryLabel, 0, 3);
        TextField salaryInput = new TextField();
        GridPane.setConstraints(salaryInput, 1, 3);

        Button updateButton = new Button("Update");
        GridPane.setConstraints(updateButton, 1, 4);
        updateButton.setOnAction(e -> {
            String id = idInput.getText();
            if (employees.containsKey(id)) {
                String name = nameInput.getText();
                String position = positionInput.getText();
                double salary = Double.parseDouble(salaryInput.getText());
                employees.put(id, new Employee(name, position, salary));
            }
            stage.close();
        });

        grid.getChildren().addAll(idLabel, idInput, nameLabel, nameInput, positionLabel, positionInput, salaryLabel, salaryInput, updateButton);

        Scene scene = new Scene(grid, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    // Method to list all employees
    private void listEmployees() {
        Stage stage = new Stage();
        stage.setTitle("List Employees");

        ListView<String> listView = new ListView<>();
        for (Map.Entry<String, Employee> entry : employees.entrySet()) {
            listView.getItems().add("ID: " + entry.getKey() + ", " + entry.getValue());
        }

        Scene scene = new Scene(listView, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    // Method to find an employee by ID
    private void findEmployeeById() {
        Stage stage = new Stage();
        stage.setTitle("Find Employee by ID");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label idLabel = new Label("Employee ID:");
        GridPane.setConstraints(idLabel, 0, 0);
        TextField idInput = new TextField();
        GridPane.setConstraints(idInput, 1, 0);

        Label resultLabel = new Label();
        GridPane.setConstraints(resultLabel, 1, 1);

        Button findButton = new Button("Find");
        GridPane.setConstraints(findButton, 1, 2);
        findButton.setOnAction(e -> {
            String id = idInput.getText();
            if (employees.containsKey(id)) {
                resultLabel.setText("Found: " + employees.get(id));
            } else {
                resultLabel.setText("Employee ID not found.");
            }
        });

        grid.getChildren().addAll(idLabel, idInput, resultLabel, findButton);

        Scene scene = new Scene(grid, 400, 200);
        stage.setScene(scene);
        stage.show();
    }
}
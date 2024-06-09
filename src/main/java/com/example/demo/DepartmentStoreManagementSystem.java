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
    // BST to store products sorted by their ID
    private final BST products = new BST();
    // BST to store customers sorted by their customer ID
    private final Map<String, Customer> customers = new TreeMap<>();
    // PriorityQueue to store orders sorted by their customer and order IDs
    PriorityQueue orders = new PriorityQueue();

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

        Button addProductButton = new Button("Add Product");
        GridPane.setConstraints(addProductButton, 0, 1);
        addProductButton.setOnAction(e -> addProduct());
        grid.getChildren().add(addProductButton);

        Button removeProductButton = new Button("Remove Product");
        GridPane.setConstraints(removeProductButton, 0, 2);
        removeProductButton.setOnAction(e -> removeProduct());
        grid.getChildren().add(removeProductButton);

        Button updateProductButton = new Button("Update Product Info");
        GridPane.setConstraints(updateProductButton, 0, 3);
        updateProductButton.setOnAction(e -> updateProduct());
        grid.getChildren().add(updateProductButton);

        Button listProductsButton = new Button("List Products");
        GridPane.setConstraints(listProductsButton, 0, 4);
        listProductsButton.setOnAction(e -> listProducts());
        grid.getChildren().add(listProductsButton);

        Button findProductByIdButton = new Button("Find Product by ID");
        GridPane.setConstraints(findProductByIdButton, 0, 5);
        findProductByIdButton.setOnAction(e -> findProductById());
        grid.getChildren().add(findProductByIdButton);

        Button addEmployeeButton = new Button("Add Employee");
        GridPane.setConstraints(addEmployeeButton, 1, 1);
        addEmployeeButton.setOnAction(e -> addEmployee());
        grid.getChildren().add(addEmployeeButton);

        Button removeEmployeeButton = new Button("Remove Employee");
        GridPane.setConstraints(removeEmployeeButton, 1, 2);
        removeEmployeeButton.setOnAction(e -> removeEmployee());
        grid.getChildren().add(removeEmployeeButton);

        Button updateEmployeeButton = new Button("Update Employee Info");
        GridPane.setConstraints(updateEmployeeButton, 1, 3);
        updateEmployeeButton.setOnAction(e -> updateEmployee());
        grid.getChildren().add(updateEmployeeButton);

        Button listEmployeesButton = new Button("List Employees");
        GridPane.setConstraints(listEmployeesButton, 1, 4);
        listEmployeesButton.setOnAction(e -> listEmployees());
        grid.getChildren().add(listEmployeesButton);

        Button findEmployeeByIdButton = new Button("Find Employee by ID");
        GridPane.setConstraints(findEmployeeByIdButton, 1, 5);
        findEmployeeByIdButton.setOnAction(e -> findEmployeeById());
        grid.getChildren().add(findEmployeeByIdButton);

        Button addCustomerButton = new Button("Add Customer");
        GridPane.setConstraints(addCustomerButton, 2, 1);
        addCustomerButton.setOnAction(e -> addCustomer());
        grid.getChildren().add(addCustomerButton);

        Button removeCustomerButton = new Button("Remove Customer");
        GridPane.setConstraints(removeCustomerButton, 2, 2);
        removeCustomerButton.setOnAction(e -> removeCustomer());
        grid.getChildren().add(removeCustomerButton);

        Button updateCustomerButton = new Button("Update Customer Info");
        GridPane.setConstraints(updateCustomerButton, 2, 3);
        updateCustomerButton.setOnAction(e -> updateCustomer());
        grid.getChildren().add(updateCustomerButton);

        Button listCustomerButton = new Button("List Customers");
        GridPane.setConstraints(listCustomerButton, 2, 4);
        listCustomerButton.setOnAction(e -> listCustomers());
        grid.getChildren().add(listCustomerButton);

        Button findCustomerByIdButton = new Button("Find Customer by ID");
        GridPane.setConstraints(findCustomerByIdButton, 2, 5);
        findCustomerByIdButton.setOnAction(e -> findCustomerById());
        grid.getChildren().add(findCustomerByIdButton);

        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Product class to hold product details
    static class Product {
        int productID;
        String productName;
        int inventoryAmount;

        Product(int productID, String productName, int inventoryAmount) {
            this.productID = productID;
            this.productName = productName;
            this.inventoryAmount = inventoryAmount;
        }

        @Override
        public String toString() {
            return "Product ID: " + productID + ", Product Name: " + productName + ", Inventory Amount: " + inventoryAmount;
        }
    }

    // Customer class to hold customer details
    static class Customer {
        String email;
        String customerName;
        String phoneNumber;
        String loyaltyNumber;

        Customer(String email, String customerName, String phoneNumber, String loyaltyNumber) {
            this.email = email;
            this.customerName = customerName;
            this.phoneNumber = phoneNumber;
            this.loyaltyNumber = loyaltyNumber;
        }

        @Override
        public String toString() {
            return "Customer Name: " + customerName + ", Email: " + email + ", Phone Number: " + phoneNumber + ", Loyalty Points: " + loyaltyNumber;
        }
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


    // Method to add a product
    private void addProduct() {
        Stage stage = new Stage();
        stage.setTitle("Add Product");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label idLabel = new Label("Product ID:");
        GridPane.setConstraints(idLabel, 0, 0);
        TextField idInput = new TextField();
        GridPane.setConstraints(idInput, 1, 0);

        Label nameLabel = new Label("Product Name:");
        GridPane.setConstraints(nameLabel, 0, 1);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 1);

        Label inventoryLabel = new Label("Inventory Amount:");
        GridPane.setConstraints(inventoryLabel, 0, 2);
        TextField inventoryInput = new TextField();
        GridPane.setConstraints(inventoryInput, 1, 2);

        Button addButton = new Button("Add");
        GridPane.setConstraints(addButton, 1, 3);
        addButton.setOnAction(e -> {
            String productId = idInput.getText();
            int productIdInt = Integer.parseInt(idInput.getText());
            String productName = nameInput.getText();
            int inventoryAmount = Integer.parseInt(inventoryInput.getText());
            products.root = products.insert(products.root, productIdInt, productName, inventoryAmount);
            stage.close();
        });

        grid.getChildren().addAll(idLabel, idInput, nameLabel, nameInput, inventoryLabel, inventoryInput, addButton);

        Scene scene = new Scene(grid, 300, 200);
        stage.setScene(scene);
        stage.show();
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

    private void removeProduct() {
        Stage stage = new Stage();
        stage.setTitle("Remove Product");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label idLabel = new Label("Product ID:");
        GridPane.setConstraints(idLabel, 0, 0);
        TextField idInput = new TextField();
        GridPane.setConstraints(idInput, 1, 0);

        Button removeButton = new Button("Remove");
        GridPane.setConstraints(removeButton, 1, 1);
        removeButton.setOnAction(e -> {
            int productIdInt = Integer.parseInt(idInput.getText());
            products.root = products.removeIterativeMethod(products.root, productIdInt);
            // products.remove(id);
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

    private void updateProduct() {
        Stage stage = new Stage();
        stage.setTitle("Update Product Info");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label idLabel = new Label("Product ID:");
        GridPane.setConstraints(idLabel, 0, 0);
        TextField idInput = new TextField();
        GridPane.setConstraints(idInput, 1, 0);

        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 1);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 1);

        Label inventoryLabel = new Label("Inventory Amount:");
        GridPane.setConstraints(inventoryLabel, 0, 2);
        TextField inventoryInput = new TextField();
        GridPane.setConstraints(inventoryInput, 1, 2);

        Button updateButton = new Button("Update");
        GridPane.setConstraints(updateButton, 1, 3);
        updateButton.setOnAction(e -> {
            int productIdInt = Integer.parseInt(idInput.getText());
            if (products.find(products.root, productIdInt) != -1) {
                // Retrieve the updated name and inventory amount from the input fields
                String name = nameInput.getText();
                int inventoryAmount = Integer.parseInt(inventoryInput.getText());

                // Update the product information by inserting a new node with the same id
                products.root = products.insert(products.root, productIdInt, name, inventoryAmount);
            }
            stage.close();
        });

        grid.getChildren().addAll(idLabel, idInput, nameLabel, nameInput, inventoryLabel, inventoryInput, updateButton);

        Scene scene = new Scene(grid, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    // Method to list all Products
    private void listProducts() {
        Stage stage = new Stage();
        stage.setTitle("List Products");

        ListView<String> listView = new ListView<>();
        products.traverseAndAddToList(products.root, listView);

        Scene scene = new Scene(listView, 400, 300);
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

    private void findProductById() {
        Stage stage = new Stage();
        stage.setTitle("Find Product by ID");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label idLabel = new Label("Product ID:");
        GridPane.setConstraints(idLabel, 0, 0);
        TextField idInput = new TextField();
        GridPane.setConstraints(idInput, 1, 0);

        Label resultLabel = new Label();
        GridPane.setConstraints(resultLabel, 1, 1);

        Button findButton = new Button("Find");
        GridPane.setConstraints(findButton, 1, 2);
        findButton.setOnAction(e -> {
            String id = idInput.getText();
            // Search for the product in the prducts BST
            int searchResult = products.find(products.root, Integer.parseInt(id));

            // Check if the porduct was found in the BST
            if (searchResult != -1) {
                // Product found, update the result as a print
                resultLabel.setText("Found: " + searchResult);
            } else {
                // Product ID not found, update the result as a print
                resultLabel.setText("Product ID not found.");
            }
        });

        grid.getChildren().addAll(idLabel, idInput, resultLabel, findButton);

        Scene scene = new Scene(grid, 400, 200);
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

    private void addCustomer() {
        Stage stage = new Stage();
        stage.setTitle("Add Customer");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label idLabel = new Label("Customer ID:");
        GridPane.setConstraints(idLabel, 0, 0);
        TextField idInput = new TextField();
        GridPane.setConstraints(idInput, 1, 0);

        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 1);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 1);

        Label emailLabel = new Label("Email:");
        GridPane.setConstraints(emailLabel, 0, 2);
        TextField emailInput = new TextField();
        GridPane.setConstraints(emailInput, 1, 2);

        Label phoneLabel = new Label("Phone Number:");
        GridPane.setConstraints(phoneLabel, 0, 3);
        TextField phoneInput = new TextField();
        GridPane.setConstraints(phoneInput, 1, 3);

        Label loyaltyLabel = new Label("Loyalty Number:");
        GridPane.setConstraints(loyaltyLabel, 0, 4);
        TextField loyaltyInput = new TextField();
        GridPane.setConstraints(loyaltyInput, 1, 4);

        Button addButton = new Button("Add");
        GridPane.setConstraints(addButton, 1, 5);
        addButton.setOnAction(e -> {
            String id = idInput.getText();
            String name = nameInput.getText();
            String email = emailInput.getText();
            String phone = phoneInput.getText();
            String loyalty = loyaltyInput.getText();
            customers.put(id, new Customer(email, name, phone, loyalty));
            stage.close();
        });

        grid.getChildren().addAll(idLabel, idInput, nameLabel, nameInput, emailLabel, emailInput, phoneLabel, phoneInput, loyaltyLabel, loyaltyInput, addButton);

        Scene scene = new Scene(grid, 300, 300);
        stage.setScene(scene);
        stage.show();
    }

    private void findCustomerById() {
        Stage stage = new Stage();
        stage.setTitle("Find Customer by ID");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label idLabel = new Label("Customer ID:");
        GridPane.setConstraints(idLabel, 0, 0);
        TextField idInput = new TextField();
        GridPane.setConstraints(idInput, 1, 0);

        Label resultLabel = new Label();
        GridPane.setConstraints(resultLabel, 1, 1);

        Button findButton = new Button("Find");
        GridPane.setConstraints(findButton, 1, 2);
        findButton.setOnAction(e -> {
            String id = idInput.getText();
            if (customers.containsKey(id)) {
                resultLabel.setText("Found: " + customers.get(id));
            } else {
                resultLabel.setText("Customer ID not found.");
            }
        });

        grid.getChildren().addAll(idLabel, idInput, resultLabel, findButton);

        Scene scene = new Scene(grid, 400, 200);
        stage.setScene(scene);
        stage.show();
    }

    private void listCustomers() {
        Stage stage = new Stage();
        stage.setTitle("List Customers");

        ListView<String> listView = new ListView<>();
        for (Map.Entry<String, Customer> entry : customers.entrySet()) {
            listView.getItems().add("ID: " + entry.getKey() + ", " + entry.getValue());
        }

        Scene scene = new Scene(listView, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    private void updateCustomer() {
        Stage stage = new Stage();
        stage.setTitle("Update Customer Info");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label idLabel = new Label("Customer ID:");
        GridPane.setConstraints(idLabel, 0, 0);
        TextField idInput = new TextField();
        GridPane.setConstraints(idInput, 1, 0);

        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 1);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 1);

        Label emailLabel = new Label("Email:");
        GridPane.setConstraints(emailLabel, 0, 2);
        TextField emailInput = new TextField();
        GridPane.setConstraints(emailInput, 1, 2);

        Label phoneLabel = new Label("Phone Number:");
        GridPane.setConstraints(phoneLabel, 0, 3);
        TextField phoneInput = new TextField();
        GridPane.setConstraints(phoneInput, 1, 3);

        Label loyaltyLabel = new Label("Loyalty Number:");
        GridPane.setConstraints(loyaltyLabel, 0, 4);
        TextField loyaltyInput = new TextField();
        GridPane.setConstraints(loyaltyInput, 1, 4);

        Button updateButton = new Button("Update");
        GridPane.setConstraints(updateButton, 1, 5);
        updateButton.setOnAction(e -> {
            String id = idInput.getText();
            if (customers.containsKey(id)) {
                String name = nameInput.getText();
                String email = emailInput.getText();
                String phone = phoneInput.getText();
                String loyalty = loyaltyInput.getText();
                customers.put(id, new Customer(email, name, phone, loyalty));
            }
            stage.close();
        });

        grid.getChildren().addAll(idLabel, idInput, nameLabel, nameInput, emailLabel, emailInput, phoneLabel, phoneInput, loyaltyLabel, loyaltyInput, updateButton);

        Scene scene = new Scene(grid, 300, 300);
        stage.setScene(scene);
        stage.show();
    }

    private void removeCustomer() {
        Stage stage = new Stage();
        stage.setTitle("Remove Customer");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label idLabel = new Label("Customer ID:");
        GridPane.setConstraints(idLabel, 0, 0);
        TextField idInput = new TextField();
        GridPane.setConstraints(idInput, 1, 0);

        Button removeButton = new Button("Remove");
        GridPane.setConstraints(removeButton, 1, 1);
        removeButton.setOnAction(e -> {
            String id = idInput.getText();
            if (customers.containsKey(id)) {
                customers.remove(id);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Removed");
                alert.setHeaderText(null);
                alert.setContentText("Customer with ID " + id + " has been removed.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Customer ID not found.");
                alert.showAndWait();
            }
            stage.close();
        });

        grid.getChildren().addAll(idLabel, idInput, removeButton);

        Scene scene = new Scene(grid, 300, 100);
        stage.setScene(scene);
        stage.show();
    }
}

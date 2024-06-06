package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue {
    // Order class is for the data that will be held in each node
    public static class Order {
        int productID;
        int customerID;
        int orderPriority; // priority 1, 2, or 3, with 1 being pick up orders, 2 being next-day orders, and 3 being normal orders

        public Order(int productID, int customerID, int orderPriority) {
            this.productID = productID;
            this.customerID = customerID;
            this.orderPriority = orderPriority;
        }

        // May need getters and setters
    }

    // Set an array to store the orders
    private List<Order> orders;

    public PriorityQueue() {
        orders = new ArrayList<>();
    }

    // Parent node index
    private int parent(int index) {
        return (index - 1) / 2;
    }

    // Left child node
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    // right child node
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    // When a swap is needed in priority, use the swap function
    private void swap(int i, int j) {
        Order temp = orders.get(i);
        orders.set(i, orders.get(j));
        orders.set(j, temp);
    }

    // Heapify up: for moving a node up
    private void heapifyUp(int index) {
        while (index > 0 && orders.get(parent(index)).orderPriority > orders.get(index).orderPriority) {
            swap(parent(index), index);
            index = parent(index);
        }
    }

    // Heapify down: for moving a node down
    private void heapifyDown(int index) {
        int minimumIndex = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < orders.size() && orders.get(left).orderPriority < orders.get(minimumIndex).orderPriority) {
            minimumIndex = left;
        }

        if (right < orders.size() && orders.get(right).orderPriority < orders.get(minimumIndex).orderPriority) {
            minimumIndex = right;
        }

        if (index != minimumIndex) {
            swap(index, minimumIndex);
            heapifyDown(minimumIndex);
        }
    }

    // Inserting an order into the priority queue function
    public void insert(int productID, int customerID, int orderPriority) {
        Order newOrder = new Order(productID, customerID, orderPriority);
        orders.add(newOrder);
        heapifyUp(orders.size() - 1);
    }

    // return the highest priority order after removal from the priority queue
    public Order remove() {
        if (orders.isEmpty()) {
            throw new IllegalStateException("Error: Priority queue is empty");
        }
        Order removedOrder = orders.get(0);
        orders.set(0, orders.get(orders.size() - 1));
        orders.remove(orders.size() - 1);
        heapifyDown(0);
        return removedOrder;
    }

    // View the highest priority order from the priority queue without removing it or deleting
    public Order peek() {
        if (orders.isEmpty()) {
            throw new IllegalStateException("Errro: Priority queue is empty");
        }
        return orders.get(0);
    }

    // Checking if the priority queue is empty
    public boolean isEmpty() {
        return orders.isEmpty();
    }

    // Getting the size of the priority queue
    public int size() {
        return orders.size();
    }
}


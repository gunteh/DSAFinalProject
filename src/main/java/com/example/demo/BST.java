package com.example.demo;

import javafx.scene.control.ListView;

import java.security.*;
import java.util.*;

/**
 *
 * @author gunterherd
 * @class CS244
 * @professor Radie
 * @Project Final
 * @quarter Spring 2024
 */

class Node {
    Node left;
    Node right;
    int productID;
    String productName;
    int inventoryAmount;

    // Constructor for the Node
    Node(int productID, String productName, int inventoryAmount) {
        this.productID = productID;
        this.productName = productName;
        this.inventoryAmount = inventoryAmount;
        left = null;
        right = null;
    }
}

public class BST {
        static final int COUNTS = 10;
        Node root; //generate Node variable root

        public void BinaryTree()
        {
            root = null; //generate a standard binary tree
        }

        public Node createNewNodes(int productID, String productName, int inventoryAmount) {
            Node a = new Node(productID, productName, inventoryAmount); //instantiate a new node
            a.productID = productID;
            a.productName = productName;
            a.inventoryAmount = inventoryAmount;
            a.left = null;
            a.right = null;

            return a;
        }

        public Node insert(Node node, int productID, String productName, int inventoryAmount) {
            if(node == null) {
                return createNewNodes(productID, productName, inventoryAmount); //if node is null, create a new node
            }

            if(productID < node.productID) { //if value is less than node, go to left child
                node.left = insert(node.left, productID, productName, inventoryAmount);
            } else if((productID > node.productID)) { //else go to right child if greater
                node.right = insert(node.right, productID, productName, inventoryAmount);
            }

            return node;
        }


        public Node removeIterativeMethod(Node node, int ID)
        {
            Node currentNode = node;
            Node previousNode = null;

            // Check to make sure current node is not the value and not null
            // traverse to the right or left node based on value comparison to
            // current node
            while (currentNode != null && currentNode.productID != ID) {
                previousNode = currentNode;
                if (ID < currentNode.productID)
                    currentNode = currentNode.left;
                else
                    currentNode = currentNode.right;
            }

            // if current node is null, return node
            if (currentNode == null) {
                return node;
            }

            // Once the node  is found, check the children nodes to find replacement
            // first possible condition: no children nodes
            if (currentNode.left == null || currentNode.right == null) {
                Node newCurrentNode;

                // second possible condition: right child
                // exists but the left child does not exist
                if (currentNode.left == null)
                    newCurrentNode = currentNode.right;
                else
                    newCurrentNode = currentNode.left;

                // third possible condition:
                // if previous node is root and null, return current node
                if (previousNode == null)
                    return newCurrentNode;

                // fourth possible condition:
                // if deleted node is previous left or right, replace with current
                if (currentNode == previousNode.left)
                    previousNode.left = newCurrentNode;
                else
                    previousNode.right = newCurrentNode;
            }

            // fifth possible condition: replaced node has two children
            else {
                Node successorNode = null;
                Node tempNode;

                // Find the successor node
                tempNode = currentNode.right;
                while (tempNode.left != null) {
                    successorNode = tempNode;
                    tempNode = tempNode.left;
                }

                // as long as successor node is not null, make the left equal
                // the right
                if (successorNode != null)
                    successorNode.left = tempNode.right;

                    // if the successor is the
                    // current node, make the right node equal to the
                    // node to be deleted
                else
                    currentNode.right = tempNode.right;

                currentNode.productID = tempNode.productID;
            }
            return node;
        }

        public int find(Node node, int value) {
            if(node == null) {
                return -1;
            }
            if(node.productID == value) {
                return node.productID; // if value is found in the current node, return
            }

            if(value < node.productID) {
                return find(node.left, value); //recurse through left node
            } else {
                return find(node.right, value); //recurse through right node
            }

        }

        static void printTree(Node node, int gap) {
            if (node == null) {
                return;
            }


            gap += COUNTS; // arbitrary gap increasing for the printTree function

            // traverse the entire tree
            printTree(node.right, gap); //go through right nodes

            System.out.print("\n"); //printing out the gaps and nodes iteratively
            for (int i = COUNTS; i < gap; i++)
                System.out.print(" ");
            System.out.print(node.productID + "\n");

            printTree(node.left, gap); //go through left nodes
        }

        // Wrapper on printTree()
        static void printingTree(Node node)
        {
            // set default gap to zero
            printTree(node, 0);
        }

        void printInorder(Node node)
        {
            if (node == null)
                return; //ensures that the process returns if null node is reached

            printInorder(node.left); //traverse the left node first
            System.out.print(node.productID + " "); //print node data
            printInorder(node.right); //then traverse the right node
        }

        void postOrderTraversal(Node node) { //emulated from lecture notes
            if(node == null) {
                return; //ensures that the process returns if null node is reached
            } else {
                postOrderTraversal(node.left); //traverse the left node first
                postOrderTraversal(node.right); //then traverse the right node
                System.out.print(node.productID + " "); //print node data
            }
        }

        void printPreorder(Node node)
        {
            if (node == null)
                return; //ensures that the process returns if null node is reached

            System.out.print(node.productID + " "); //print node data
            printPreorder(node.left); //traverse the left node first
            printPreorder(node.right); //then tranverse the right node
        }

    // Traversing the BST and adding each node's information to the list view
    void traverseAndAddToList(Node node, ListView<String> listView) {
        if (node != null) {
            // Visit the left subtree structure
            traverseAndAddToList(node.left, listView);

            // Add the current node's information to the list view as needed
            listView.getItems().add("Product ID: " + node.productID + ", Product Name: " + node.productName + ", Inventory Amount: " + node.inventoryAmount);

            // Visit the right subtree structure
            traverseAndAddToList(node.right, listView);
        }
    }

        void findMin(Node node) {
            //traverse the left node and find smallest node
            if (node == null) {
                return;
            }

            while (node.left != null) {
                node = node.left;
            }
            System.out.print(node.productID + " ");

        }

        void findMax(Node node) {
            //traverse the right node and find largest node
            if (node == null) {
                return;
            }

            while (node.right != null) {
                node = node.right;
            }
            System.out.print(node.productID + " ");

        }


        static Node previousNode;

        static Boolean validatingBST(Node root)
        {
            // Use inorder traverse, but also check that root is not null
            // if there is no left root, return false
            if (root != null) {
                if (!validatingBST(root.left))
                    return false;

                // check that previous node is not null and that root node is less
                // than or equal to previous node
                if (previousNode != null && root.productID <= previousNode.productID)
                    return false;

                previousNode = root;

                return validatingBST(root.right);
            }
            return true;
        }

        static Boolean isBooleanBST(Node root)
        {
            return validatingBST(root);
        }
}

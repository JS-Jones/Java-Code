/* Name: Joshau Jones
 * Email: joshua.jones@stonybrook.edu
 * ID: 114195108
 * Recitation: 01
 * Homework 4
*/

import java.util.Scanner;

// Creates the Tree
public class Tree {
    private TreeNode root;
    private NodeTree nodeList;

    // Constructor intializing a new nodeTree
    public Tree() {
        nodeList = new NodeTree();
    }

    // addNode takes in label, prompt, message, and parentLabel
    public boolean addNode(String label, String prompt, String message, String parentLabel) {
        // Creates a node and adds it to the nodeList
        TreeNode newNode = new TreeNode(label, prompt, message);
        nodeList.addNode(newNode);

        // Checks if root exists
        if (root == null) {
            System.out.println("Error: Root not found.");
            return false;
        }
        
        // References to the parent Node
        TreeNode parentNode = getNodeReference(parentLabel);

        // Checks if the parent exists
        if (parentNode == null) {
            System.out.println("Error: Parent not found.");
            return false;
        }

        // sets childen array to the parentNode's children list 
        TreeNode[] children = parentNode.getChildren();

        // Sets the array index and the parentNode children to the new node - first left, then middle, then right
        if (children[0] == null) {
            children[0] = newNode;
            parentNode.setLeft(newNode);
        } else if (children[1] == null) {
            children[1] = newNode;
            parentNode.setMiddle(newNode);
        } else if (children[2] == null) {
            children[2] = newNode;
            parentNode.setRight(newNode);
        } else {
            System.out.println("Error: Parent already has three children.");
            return false;
        }

        return true;
    }

    // Returns the Root
    public TreeNode getRoot() {
        return root;
    }

    // Sets the Root
    public void setRoot(TreeNode root) {
        this.root = root;
        nodeList.addNode(root);
    }

    // Finds the node being referenced
    public TreeNode getNodeReference(String label) {
        return nodeList.getNode(label);
    }

    // Code for traversal - using recurssion, prints all the nodes in the tree
    public void preOrder(TreeNode node) {
        if (node != null) {
            System.out.println("Label: " + node.getLabel());
            System.out.println("Prompt: " + node.getPrompt());
            System.out.println("Message: " + node.getMessage());
            System.out.println();

            preOrder(node.getLeft());
            preOrder(node.getMiddle());
            preOrder(node.getRight());
        }
    }

    // Begins the Help session
    public void beginSession() {
        // checks if roots exists
        if (root == null) {
            System.out.println("\nError: No tree set up.\n");
            return;
        } else{
            System.out.println("\nHelp Session Starting...");
        }
        
        TreeNode currentNode = root;
        Scanner scanner = new Scanner(System.in);
        
        // Loops until the node is a lead or 0 is entered
        while (true) {
            // checks if node is a lead
            if (currentNode.isLeaf()) {
                System.out.println(currentNode.getMessage());
                System.out.println("\nThank you for using this automated help service!\n");
                break;
            }
            
            // Prints node and its corresponding choices
            System.out.println(currentNode.getMessage());
            TreeNode[] children = currentNode.getChildren();
            for (int i = 0; i < children.length; i++) {
                TreeNode child = children[i];
                if (child != null) {
                    System.out.println((i + 1) + ") " + child.getPrompt());
                } 
            }
            
            System.out.println("0) Exit Session.");
            System.out.print("Choice> ");
            
            int choice;
            // Ensure the choice is number
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }
            
            System.out.println();

            // if the choice is valid, current node is changed
            if (choice == 0) {
                break;
            } else if (choice >= 1 && choice <= children.length) {
                currentNode = children[choice - 1];
            } else {
                System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }
    

    // NodeTree uses arrays to store all the nodes in this tree - helps find a specific node
    private class NodeTree {
        private TreeNode[] nodes;
        private int size;

        // Constructor, adjust the TreeNode size to number of possible entries
        public NodeTree() {
            nodes = new TreeNode[1000]; 
            size = 0;
        }

        // Adds a node to this list. Records size to know which index to add the new node.
        public void addNode(TreeNode node) {
            nodes[size] = node;
            size++;
        }

        // Gets the node by searching for a label (finding the parent node instance) through the whole list
        public TreeNode getNode(String label) {
            for (int i = 0; i < size; i++) {
                if (nodes[i] != null && nodes[i].getLabel().equals(label)) {
                    return nodes[i];
                }
            }
            return null;
        }
    }
}

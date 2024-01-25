/* Name: Joshau Jones
 * Email: joshua.jones@stonybrook.edu
 * ID: 114195108
 * Recitation: 01
 * Homework 4
*/

import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

// Main file for tree - shows options to choose
public class TreeDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tree tree = new Tree();
        
        // Loops until Q is entered
        while (true) {
            // Shows options
            System.out.println("L - Load a Tree.");
            System.out.println("H - Begin a Help Session.");
            System.out.println("T - Traverse the Tree in preorder.");
            System.out.println("Q - Quit");
            System.out.print("Choice> ");
            
            String choice = scanner.nextLine().trim().toUpperCase();
            
            // Using Switch case to determine what choice is selected
            switch (choice) {
                case "L":
                    // Creates a tree based on the file name entered
                    System.out.print("\nEnter the file name> ");
                    String fileName = scanner.nextLine();
                    if (loadTreeFromFile(tree, fileName)) {
                        System.out.println("\nTree created successfully!\n");
                    } else {
                        System.out.println("Error loading the tree from the file.\n");
                    }
                    break;
                case "H":
                    // Begins the Help session of a specific choice
                    tree.beginSession();
                    break;
                case "T":
                    // Traverses the tree
                    System.out.println("\nTraversing the tree in preorder:");
                    tree.preOrder(tree.getRoot());
                    break;
                case "Q":
                    // Quits the program
                    System.out.println("\nThank you for using our automated help services!");
                    scanner.close();
                    System.exit(0);
                default:
                    // Default case in case none of the above
                    System.out.println("Invalid choice. Please choose a valid option.\n");
                    break;
            }
        }
    }
    
    // Loads a tree from the input file entered
    private static boolean loadTreeFromFile(Tree tree, String fileName) {
        // Try/Catch the File and makes sure it exists
        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            TreeNode parent = null;
            int numChildren;
    
            while (scanner.hasNextLine()) {
                // Needed to trim every line to work
                String line = scanner.nextLine().trim();
    
                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }
                
                // Creates a node for the root here since its the first choice - avoid confusion when completely running Tree class
                if (line.equals("root")) {
                    String prompt = scanner.nextLine().trim();
                    String message = scanner.nextLine().trim();

                    // Creates a new node and sets it as the root for the overall tree
                    parent = new TreeNode(line, prompt, message);
                    tree.setRoot(parent);

                    // Splits the string to create an array for the number of children
                    String[] parts = scanner.nextLine().split(" ");
                    numChildren = Integer.parseInt(parts[1]);

                    // Loops based on the number of children and adds it to the tree
                    for (int i = 0; i < numChildren; i++) {
                        String childLabel = scanner.nextLine().trim();
                        String childPrompt = scanner.nextLine().trim();
                        String childMessage = scanner.nextLine().trim();
                        // addNode assigns the Root's children to these 
                        tree.addNode(childLabel, childPrompt, childMessage, line);
                    }

                } else {
                    // Repeat the above but for all other nodes
                    String[] parts = line.split(" ");
                    numChildren = Integer.parseInt(parts[1]);

                    for (int i = 0; i < numChildren; i++) {
                        String childLabel = scanner.nextLine().trim();
                        String childPrompt = scanner.nextLine().trim();
                        String childMessage = scanner.nextLine().trim();
                        tree.addNode(childLabel, childPrompt, childMessage, parts[0]);
                    }
                }

            }
            return true;
        } catch (IOException e) {
            System.out.println("\nNot a valid file. Try again.");
            return false;
        }
    }
}
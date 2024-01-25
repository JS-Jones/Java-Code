/* Name: Joshau Jones
 * Email: joshua.jones@stonybrook.edu
 * ID: 114195108
 * Recitation: 01
 * Homework 4
*/

// Creates and stores the data in each node
public class TreeNode {
    // References to the Tree Nodes
    private TreeNode left;
    private TreeNode middle;
    private TreeNode right;

    // Message variables move down the tree
    private String label;
    private String message;
    private String prompt;

    // Constructor Method
    public TreeNode(String label, String prompt, String message) {
        this.label = label;
        this.prompt = prompt;
        this.message = message;
    }

    // Returns the node label
    public String getLabel() {
        return label;
    }

    // Returns the node message
    public String getMessage() {
        return message;
    }

    // Returns the node propmt
    public String getPrompt() {
        return prompt;
    }

    // Returns the left node
    public TreeNode getLeft() {
        return left;
    }

    // Sets the left node
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    // Returns the middle node
    public TreeNode getMiddle() {
        return middle;
    }

    // Sets the middle Node
    public void setMiddle(TreeNode middle) {
        this.middle = middle;
    }

    // Reutns the right node
    public TreeNode getRight() {
        return right;
    }

    // Sets the right node
    public void setRight(TreeNode right) {
        this.right = right;
    }

    // Checks if the current node is a leaf --> no children
    public boolean isLeaf() {
        return (left == null && middle == null && right == null);
    }

    // Stores the children in an array - not necessary but I thought it helped with just getting the children later on
    public TreeNode[] getChildren() {
        TreeNode[] children = new TreeNode[3];
        children[0] = left;
        children[1] = middle;
        children[2] = right;
        return children;
    }
}

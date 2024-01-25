/* Name: Joshau Jones
 * Email: joshua.jones@stonybrook.edu
 * ID: 114195108
 * Recitation: 01
 * Homework 2
*/

public class ItemInfoNode {
    private ItemInfo info;
    private ItemInfoNode next;
    private ItemInfoNode prev;

    // ItemInfoNode Constructor
    public ItemInfoNode() {
        this.info = null;
        this.next = null;
        this.prev = null;
    }

    // Returns Info Object
    public ItemInfo getInfo() {
        return info;
    }

    // Set the Info Object
    public void setInfo(ItemInfo info) {
        this.info = info;
    }

    // Returns the reference to the next node
    public ItemInfoNode getNext() {
        return next;
    }

    // Sets the reference to the next node
    public void setNext(ItemInfoNode next) {
        this.next = next;
    }

    // Returns the reference to the previous node
    public ItemInfoNode getPrev() {
        return prev;
    }

    // Sets the reference to the previous node
    public void setPrev(ItemInfoNode prev) {
        this.prev = prev;
    }
}

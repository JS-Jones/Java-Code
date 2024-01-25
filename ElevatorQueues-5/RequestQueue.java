/* Name: Joshau Jones
 * Email: joshua.jones@stonybrook.edu
 * ID: 114195108
 * Recitation: 01
 * Homework 3
*/
 
import java.util.ArrayList;

// Queue to store elevator requests - Subclass of ArrayList
public class RequestQueue extends ArrayList<Request> {
    
    // Constructor
    public RequestQueue() {
    }

    // Enqueues - adds a request to the end of the queue
    public void enqueue(Request request) {
        this.add(request);
    }

    // Dequeues - removes and returns the first request from the queue.
    public Request dequeue() {
        // First checks if the list is empty and returns null if so
        if (!this.isEmpty()) {
            return this.remove(0);
        } else {
            return null;
        }
    }

    // size - returns the number of requests
    @Override
    public int size() {
        return super.size();
    }

    // isEmpty - checks if the queue list is empty
    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }
}
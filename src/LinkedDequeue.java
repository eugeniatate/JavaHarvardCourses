// File LinkedDequeue.java 
/**
 * This class represents a Dequeue datatype implemented using a singly-linked
 * list with appropriate operations.
 *
 * @author: Eugenia Tate
 * @version: Last modified on April 24, 2025
 *           Implements a Dequeue as a linked-list
 */

public class LinkedDequeue {
    QueueNode tail;
    QueueNode head;
    int count;

   /**
    * The QueueNode class is an inner class implemented to model a queue node;
    * it can contain an Object type of data, and also holds the link to the
    * next node in the queue. If there are no other nodes, the link will be null.
    */
   class QueueNode // an inner class
   {
       Object item;
       QueueNode link;
   }

   /**
    * This constructor for the class will set up the needed instance variables
    * which begin with no nodes present and thus are set to null.
    */
   public LinkedDequeue() {
      tail = head = null;
      count = 0;
   }

   /**
    * This method will construct a new QueueNode and add it onto the rear
    * of the dequeue. If it is the first node added into
    * the queue, both front and rear will reference it, otherwise it is added
    * using the tail variable. The node counter is also updated.
    *
    * @param x The Object to be added as part of a new QueueNode
    */
   public void tailAdd(Object o) {
      QueueNode temp = new QueueNode();
      temp.item = o;
      temp.link = null;

      if (tail == null)
         head = tail = temp;
      else {
         tail.link = temp;
         tail = temp;
      }
      count++;
   }

   /**
    * This method will construct a new QueueNode and add it onto the front
    * of the dequeue. If it is the first node added into
    * the queue, both front and rear will reference it, otherwise it is added
    * using the head variable. The node counter is also updated.
    *
    * @param x The Object to be added as part of a new QueueNode
    */
   public void headAdd(Object o) {
      QueueNode temp = new QueueNode();
      temp.item = o;
      temp.link = null;

      if (head == null)
         head = tail = temp;
      else {
         temp.link = head;
         head = temp;
      }
      count++;
   }

   /**
    * This method will peek at the head of the dequeue w/o removing the head element of the dequeue.
    * An exception is thrown if user tries to peek at the empty dequeue's head 
    *
    * @return  Object   - an object peeked at
    */
   public Object headPeek() throws DequeueUnderFlowException {
      if (head == null)
         throw new DequeueUnderFlowException("Deque is empty! Can't peek!");
      else {
         return head.item;
      }
   }

    /**
    * This method will peek at the tail of the dequeue w/o removing the tail element of the dequeue
    * An exception is thrown if user tries to peek at the empty dequeue's taail
    *
    * @return  Object   - an object peeked at
    */
   public Object tailPeek() throws DequeueUnderFlowException {
      if (tail == null)
         throw new DequeueUnderFlowException("Deque is empty! Can't peek!");
      else {
         return tail.item;
      }
   }

    /**
    * This method will remove the last node of the dequeue and return its value. 
    * In doing so, the dequeue variables are reset to detach the node,
    * and the Object which it contains is then returned. The dequeue
    * counter is also updated to reflect the removal.
    * An exception is thrown if user tries to remove from the empty dequeue. 
    *
    * @return  Object   - an object removed
    */
   public Object tailRemove() throws DequeueUnderFlowException {
      if (isEmpty())
         throw new DequeueUnderFlowException("Can't remove from empty dequeue!");

      else if (size() == 1) {
         Object tempItem = head.item;
         tail = head = null;
         return tempItem;

      } 
      else {
         QueueNode current = new QueueNode();
         current = head;
         while (current.link != tail) {
            current = current.link;
         }

         Object tempItem = tail.item;
         tail = current;
         current.link = null;
         count--;
         return tempItem;
      }
   }

   /**
    * This method will test for an empty queue and return a boolean result.
    *
    * @return true for an empty list; false if the queue contains QueueNodes.
    */
   public boolean isEmpty() {
      return (count == 0);
   }

   /**
    * This method will evaluate and return the current size of the queue.
    *
    * @return An int describing the current number of nodes in the queue
    */
   public int size() {
      return count;
   }

    /**
    * This method will remove the first node of the dequeue and return its value. 
    * In doing so, the dequeue variables are reset to detach the node,
    * and the Object which it contains is then returned. The dequeue
    * counter is also updated to reflect the removal.
    * An exception is thrown if user tries to remove from the empty dequeue. 
    *
    * @return  Object   - an object removed
    */
   public Object headRemove() throws DequeueUnderFlowException {
      if (isEmpty())
         throw new DequeueUnderFlowException("Can't remove from empty dequeue!");
      else {
         Object tempItem = head.item;
         head = head.link;
         if (head == null)
            tail = null;
         count--;
         return tempItem;
      }
   }

    /**
    * This method will print all elements of the dequeue each on a separate line
    *
    * @return  String   - string representation of the dequeue 
    */
   public String toString() {
      String dequeToString = "";
      if (head == null)
         dequeToString += "[]";
      else {
         dequeToString += "[\n";
         QueueNode current = new QueueNode();
         current = head;
         while (current != null) {
            dequeToString += current.item + "\n";
            current = current.link;
         }
         dequeToString += "]";
      }
      return dequeToString;
   }
}

// new custom Dequeue exception class 
class DequeueUnderFlowException extends Exception {
   public DequeueUnderFlowException(String errorMessage) {
      super(errorMessage);
   }
}
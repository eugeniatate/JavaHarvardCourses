/**
*  LinkedDequeueTests.java
* 
* Test file to test single-linked Dequeue data structure and its methods 
*
*  @version: Last Modified April 24, 2025
*  @author:  Eugenia Tate
*/

public class LinkedDequeueTests {
    public static void main(String[] args) {
        LinkedDequeue dequeue = new LinkedDequeue();
        dequeue.headAdd(1);
        dequeue.headAdd(0);
        dequeue.tailAdd(10);
        System.out.println("Expected dequeue: [1,0,10]; Actual dequeue: " + dequeue.toString());
        System.out.println("Expected dequeue size: 3, Actual dequeue size: " + dequeue.size());

        try {
            System.out.println("Expected head is 0, Actual head: " + dequeue.headPeek());
        }
        catch (DequeueUnderFlowException e) {
            System.out.println(e.getMessage());
        }

        try {
            dequeue.headRemove();
        }
        catch (DequeueUnderFlowException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Expected tail is 10, Actual head: "+ dequeue.tailPeek());
        }
        catch (DequeueUnderFlowException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("Expected dequeue: [1,10], Actual dequeue: " + dequeue.toString());
        System.out.println("Expected dequeue size: 2, Actual dequeue size: " + dequeue.size());
        System.out.println("Expected isEmpty(): false, Actual isEmpty: " + dequeue.isEmpty());
        
        try {
            dequeue.tailRemove();
        }
        catch (DequeueUnderFlowException e) {
            System.out.println( e.getMessage());
        }

        System.out.println("Expected dequeue: [1], Actual dequeue: " + dequeue.toString());
        System.out.println("Expected dequeue size: 1, Actual dequeue size: " + dequeue.size());

        try {
            dequeue.headRemove();
        }
        catch (DequeueUnderFlowException e) {
            System.out.println( e.getMessage());
        }

        System.out.println("Expected dequeue: [], Actual dequeue: " + dequeue.toString());
        System.out.println("Expected isEmpty(): true, Actual isEmpty: " + dequeue.isEmpty());
        System.out.println("Expected dequeue size: 0, Actual dequeue size: " + dequeue.size());

        try {
            System.out.println("Expected head is (Exception thrown), Actual head: " + dequeue.headPeek());
        }
        catch (DequeueUnderFlowException e) {
            System.out.println( e.getMessage());
        }

        try {
            dequeue.headRemove();
        }
        catch (DequeueUnderFlowException e) {
            System.out.println( e.getMessage());
        }
    }
}

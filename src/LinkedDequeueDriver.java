//@@ TO DO:
//@@     1. Make it work gracefully when no DequeueUnderFlowException class is there
//@@     2. Make it validate link integrity everytime it displays a LinkedDequeue and
//@@        warn when links are foobar'ed
//@@     3. When removing an item from the list display the removed item in the terminal.

// Exercises these LinkedDequeue methods:
//     1. public LinkedDequeue ()        // Constructor.
//     2. public void headAdd (Object x) // Add x to head of queue.
//     3. public void tailAdd (Object x) // Add x to tail of queue.
//     4. public boolean empty()         // Tests if queue is empty.
//     5. public int size()              // Returns # of items in queue.
//     6. public Object headRemove ()    // Deletes item at head of queue and returns it.
//     7. public Object tailRemove ()    // Deletes item at tail of queue and returns it.

// Makes these assumptions regarding LinkedDequeue.java:
//     1. The name of the "node" class is QueueNode.
//     2. QueueNode is an innerclass to LinkedDequeue.
//     3. QueueNode includes these two non-private instance variables:
//             Object    item;   // an item in the dequeue
//             QueueNode link;   // link to next node in the dequeue
//     4. LinkedDequeue includes these three non-private instance variables:
//             QueueNode head;   // Points to dequeue's head item
//             QueueNode tail;   // Points to dequeue's tail item
//             int       count;  // number of items in the dequeue
//
// For example:
//     class LinkedDequeue {
//         ...
//         class QueueNode {
//             ...
//             Object    item;   // an item in the dequeue
//             QueueNode link;   // link to next node in the dequeue
//             ...
//         }
//         ...
//         QueueNode head;       // Points to dequeue's head item
//         QueueNode tail;       // Points to dequeue's tail item
//         int count;            // number of items in the dequeue
//         ...
//     }

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LinkedDequeueDriver {
    public static void main(String [] args) {
        LinkedDequeueDriverFrame linkedDequeueDriverFrame = new LinkedDequeueDriverFrame();
        linkedDequeueDriverFrame.setVisible(true);
    }
}

class LinkedDequeueDriverFrame extends JFrame {

    private final int WIDTH = 2000, HEIGHT = 400, CELLMARGIN = 25;

    private LinkedDequeue           linkedDequeue;
    private LinkedDequeue.QueueNode tail, head, queueNode;
    private int                     count;

    private static int nextItem;

    private JPanel  linkedDequeuePanel, buttonPanel, labelPanel;

    private JButton constructButton, headAddButton, headRemoveButton, tailAddButton, tailRemoveButton;

    public LinkedDequeueDriverFrame() {
        nextItem         = 0;
        buttonPanel      = new JPanel();
        constructButton  = new JButton("Construct LinkedDequeue");
        headAddButton    = new JButton("Add Item To Head");
        headRemoveButton = new JButton("Delete Item From Head");
        tailAddButton    = new JButton("Add Item To Tail");
        tailRemoveButton = new JButton("Delete Item From Tail");

        headAddButton.setEnabled( false );
        headRemoveButton.setEnabled( false );
        tailAddButton.setEnabled( false );
        tailRemoveButton.setEnabled( false );

        linkedDequeuePanel = new JPanel();
        linkedDequeuePanel.setLayout( new BoxLayout( linkedDequeuePanel, BoxLayout.X_AXIS ) );

        labelPanel = new JPanel();
        labelPanel.setLayout( new BoxLayout( labelPanel, BoxLayout.Y_AXIS ) );
        labelPanel.add( new JLabel( "item: " ) );
        labelPanel.add( new JLabel( "link: " ) );
        labelPanel.add( new JLabel( "location: " ) );

        do_layout();
        do_plumbing();
    }

    private void do_layout() {
        buttonPanel.setLayout( new BoxLayout( buttonPanel, BoxLayout.X_AXIS ) );
        buttonPanel.add( constructButton );
        buttonPanel.add( headAddButton );
        buttonPanel.add( headRemoveButton );
        buttonPanel.add( tailAddButton );
        buttonPanel.add( tailRemoveButton );

        this.setLayout( new BorderLayout() );
        this.add( buttonPanel, BorderLayout.SOUTH );
        this.setSize( WIDTH, HEIGHT );
        this.setDefaultCloseOperation( EXIT_ON_CLOSE );
        this.setLocationRelativeTo( null );
        this.setTitle( "Linked Dequeue Driver" );
    }

    private void do_plumbing() {
        constructButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                constructButtonClicked();
            }
        } );

        headAddButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                headAddButtonClicked();
            }
        } );

        headRemoveButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                headRemoveButtonClicked();
            }
        } );

        tailAddButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                tailAddButtonClicked();
            }
        } );

        tailRemoveButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                tailRemoveButtonClicked();
            }
        } );
    }

    private void constructButtonClicked() {
        System.out.println( "Constructing new, empty LinkedDequeue" );
        linkedDequeue = new LinkedDequeue();
        System.out.println( "Constructed new, empty LinkedDequeue" );
        displayLinkedDequeue();
        headAddButton.setEnabled( true );
        headRemoveButton.setEnabled( true );
        tailAddButton.setEnabled( true );
        tailRemoveButton.setEnabled( true );
    }

    private void headAddButtonClicked() {
        String itemToAdd = ++nextItem + "";
        System.out.printf( "Adding \"%s\" to head of LinkedDequeue\n", itemToAdd);
        linkedDequeue.headAdd( itemToAdd );
        System.out.printf( "Added \"%s\" to head of LinkedDequeue\n", itemToAdd);
        displayLinkedDequeue();
    }

    private void headRemoveButtonClicked() {
        System.out.println( "Removing item from head of LinkedDequeue");
        boolean deleteSucceeded = true;
        try { linkedDequeue.headRemove(); }
        catch( DequeueUnderFlowException dufe ) {
            System.out.println( "Caught DequeueUnderFlowException" );
            deleteSucceeded = false;
        }
        if ( deleteSucceeded ) { System.out.println( "Removed item from head of LinkedDequeue"); }
        displayLinkedDequeue();
    }

    private void tailAddButtonClicked() {
        String itemToAdd = ++nextItem + "";
        System.out.printf( "Adding \"%s\" to tail of LinkedDequeue\n", itemToAdd);
        linkedDequeue.tailAdd( itemToAdd );
        System.out.printf( "Added \"%s\" to tail of LinkedDequeue\n", itemToAdd);
        displayLinkedDequeue();
    }

    private void tailRemoveButtonClicked() {
        System.out.println( "Removing item from tail of LinkedDequeue");
        boolean deleteSucceeded = true;
        try { linkedDequeue.tailRemove(); }
        catch( DequeueUnderFlowException dufe ) {
            System.out.println( "Caught DequeueUnderFlowException" );
            deleteSucceeded = false;
        }
        if ( deleteSucceeded ) { System.out.println( "Removed item from tail of LinkedDequeue"); }
        displayLinkedDequeue();
    }

    private void displayLinkedDequeue() {

        linkedDequeuePanel.removeAll();
        linkedDequeuePanel.revalidate();
        linkedDequeuePanel.repaint();
        linkedDequeuePanel.add( labelPanel );

        System.out.println( "Displaying LinkedDequeue" );
        try {
            count = linkedDequeue.count;
            tail  = linkedDequeue.tail;
            head = linkedDequeue.head;
            queueNode = head;
            linkedDequeuePanel.add( new QueueNodeJPanel( head, "head" ) );
            for ( int i=1; i<=count; i++ ) {
                linkedDequeuePanel.add( new QueueNodeJPanel( queueNode ) );
                queueNode = queueNode.link;
            }
        }
        catch( Exception e ) {
            System.out.println( "Caught exception while traversing LinkedDequeue!!!" );
            e.printStackTrace();
        }

        linkedDequeuePanel.add( new QueueNodeJPanel( tail, "tail" ) );

        System.out.println( "Displayed LinkedDequeue" );
        this.add( linkedDequeuePanel, BorderLayout.NORTH );
        linkedDequeuePanel.revalidate();
        linkedDequeuePanel.repaint();
    }

    class QueueNodeJPanel extends JPanel {

        // Constructors
        QueueNodeJPanel( LinkedDequeue.QueueNode queueNode ) {
            this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
            //System.out.printf( "Added JPanel for item between head and tail to QueueNodeJPanel\n");
            this.add( new JLabel( queueNode.item.toString() ) );       // item
            this.add( new JLabel( getHexAddress( queueNode.link ) ) ); // link
            this.add( new JLabel( getHexAddress( queueNode ) ) );      // queueNode's address
            this.setBorder(BorderFactory.createLineBorder( this.getBackground(), CELLMARGIN ));
        }

        QueueNodeJPanel( LinkedDequeue.QueueNode queueNode, String string ) {
            this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
            if ( string.equals( "head" ) ) {
                if ( head == null ) {
                    //System.out.printf( "Added JLabel for head to QueueNodeJPanel\n");
                    this.add( new JLabel( " " ) );                     // item
                    this.add( new JLabel( "null" ) );                  // link
                    this.add( new JLabel( "head" ) );                  // queueNode's address
                }
                else {
                    //System.out.printf( "Added JLabel for head ... to QueueNodeJPanel\n");
                    this.add( new JLabel( " " ) );                     // item
                    this.add( new JLabel( getHexAddress( head ) ) );  // link
                    this.add( new JLabel( "head" ) );                  // queueNode's address
                }
            }
            if ( string.equals( "tail" ) ) {
                if ( tail == null ) {
                    //System.out.printf( "Added JLabel for tail to QueueNodeJPanel\n");
                    this.add( new JLabel( " " ) );                     // item
                    this.add( new JLabel( "null" ) );                  // link
                    this.add( new JLabel( "tail" ) );                  // queueNode's address
                }
                else {
                    //System.out.printf( "Added JLabel for tail ... to QueueNodeJPanel\n");
                    this.add( new JLabel( " " ) );                     // item
                    this.add( new JLabel( getHexAddress( tail ) ) );   // link
                    this.add( new JLabel( "tail" ) );                  // queueNode's address
                }
            }
            this.setBorder(BorderFactory.createLineBorder( this.getBackground(), CELLMARGIN ));
        }

        private String getHexAddress( LinkedDequeue.QueueNode queueNode ) {
            //System.out.printf( "queueNode==null is %b\n", queueNode==null );
            if ( queueNode==null ) { return "null"; }
            else {
                String hexAddress = ((Object) queueNode).toString().split( "@" )[1];
                return hexAddress.toUpperCase();
            }
        }
    }
}


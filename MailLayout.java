import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * This program creates a window imitating email with all the standard email fields and message area. The Send button
 *  allows user to store the email contents in the output file.
 * @author Eugenia Tate
 * @version Last Modified 04/15/2025
 */

public class MailLayout {
    private static final String EMAIL_TITLE = "New Message";
    public static void main(String [] args) {
        // Construct the window
        MailWindow mailWindow = new MailWindow(EMAIL_TITLE);
        // Make it visible
        mailWindow.setVisible(true);
    }
}

class MailWindow extends JFrame {
    private static final int WIDTH=600, HEIGHT=500;
    private JTextField  toField = new JTextField(), 
                        ccField = new JTextField(), 
                        bccField = new JTextField(), 
                        subjectField = new JTextField();
    private JComboBox<String> fromComboBox = new JComboBox<>(new String[] {
             "billclinton@whitehouse.gov", "barackobama@gmail.com", "donaldjtrump@yahoo.com"});
    private JTextArea messageArea;
    private JButton sendButton;
    private JLabel  toJLabel = new JLabel("To:"),
                    ccJLabel = new JLabel("Cc:"),
                    bccJLabel = new JLabel("Bcc:"),
                    subjectJLabel = new JLabel("Subject:"),
                    fromJLabel = new JLabel("From:");

    // CONSTRUCTOR
    public MailWindow(String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.addComponents();
        this.addListeners();
    }

    // ADDING LISTENERS
    public void addListeners(){
        // Action listener to send email if the user clicks Send button
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                sendEmail();
            }
        });
        // Keyboard listener to update title if the user typed soemthing in the Subject field and released keyboard
        subjectField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateTitle();
            }
        });
    }

    // ADDING LAYOUT COMPONENTS
    public void addComponents () {
        // placing Send button at the top in the left corner
        JPanel sendButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        sendButton = new JButton("Send");
        sendButtonPanel.add(sendButton);
        this.add(sendButtonPanel, BorderLayout.NORTH);
        // creating main panel that will contain all other fields and message area
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    
        // creating panel to hold email fields
        // used texbook on Java Swing starting from page 350 on GridBagLayout
        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // creating GridBagConstraints to properly format the email fields and rows
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 5, 2, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // calling addEmailFieldRow method to add appropriate label and field for each row in the layout
        addEmailFieldRow(fieldsPanel, gbc, 0, toJLabel, toField);
        addEmailFieldRow(fieldsPanel, gbc, 1, ccJLabel, ccField);
        addEmailFieldRow(fieldsPanel, gbc, 2, bccJLabel, bccField);
        addEmailFieldRow(fieldsPanel, gbc, 3, subjectJLabel, subjectField);
        addEmailFieldRow(fieldsPanel, gbc, 4, fromJLabel, fromComboBox);
        // creating a message area panel
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBorder(BorderFactory.createEmptyBorder(2, 10, 10, 10));
        messageArea = new JTextArea();
        messageArea.setLineWrap(true);
        // adding a scrolling ability for the user to scroll message text 
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setPreferredSize(new Dimension(550, 200));
        messagePanel.add(scrollPane, BorderLayout.CENTER);

        // adding message area and all the field rows to the main panel
        mainPanel.add(fieldsPanel);
        mainPanel.add(messagePanel);
        this.add(mainPanel, BorderLayout.CENTER);
        mainPanel.setVisible(true);
    }

    /**
     * This method adds appropriate label anf field to the panel using specified constraints  
     * 
     * @param   panel   panel to add the row to
     * @param   gbc     GridBagConstarints object
     * @param   row     int indicating row number to add to
     * @param   label   JLabel to add to the row 
     * @param   field   JComponent represwnting a filed to add to a row 
     */
    private void addEmailFieldRow(JPanel panel, GridBagConstraints gbc, int row, JLabel label, JComponent field) {
        // label placement at position 0
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        panel.add(label, gbc);
        // field placement at position 1
        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(field, gbc);
    }

     /**
     * This method "sends" email by storing it in the output file
     * Result: outbox.txt
     */
    private void sendEmail() {
        String from = (String) fromComboBox.getSelectedItem();
        String to = toField.getText();
        String cc = ccField.getText();
        String bcc = bccField.getText();
        String subject = subjectField.getText();
        String message = messageArea.getText();

        try (PrintStream out = new PrintStream(new File("outbox.txt"))) {
            out.println("From: " + from);
            out.println("To: " + to);
            out.println("Cc: " + cc);
            out.println("Bcc: " + bcc);
            out.println("Subject: " + subject);
            out.println("Message:\n" + message);
        } 
        catch (IOException exception) {
            JOptionPane.showMessageDialog(this, "Error saving message.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // erase contents of the email it has been "sent", providing user with a clean slate to create another email
        toField.setText("");
        ccField.setText("");
        bccField.setText("");
        subjectField.setText("");
        messageArea.setText("");
        this.setTitle("New Message");
    }

    /**
     * This method updates title of the window to that of an email Subject
     * Result: outbox.txt
     */
    private void updateTitle() {
        String subject = subjectField.getText().trim();
        if (!subject.isEmpty()) {
            this.setTitle(subject);
        }
        else {
            this.setTitle("New Message");
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * This Java Swing application implements the classic 15-puzzle game, consisting of a 4x4 grid 
 * containing 15 numbered tiles and one empty space. The goal is for the player to slide tiles 
 * around until they are arranged in order from 1 to 15, with the empty space in the bottom-right corner.
 * 
* @author   Eugenia Tate
* @version  Last Modified 04/16/2025
 */

public class FifteenPuzzle {
    public static void main(String[] args) {
        MyFifteenPuzzle myFiftennPuzzle = new MyFifteenPuzzle();
        myFiftennPuzzle.setVisible(true);
    }
}

class MyFifteenPuzzle extends JFrame {
    private static final int SIZE = 4;
    private JButton[][] buttons = new JButton[SIZE][SIZE];
    private JPanel gridPanel;
    private JButton shuffleButton;
    private JSpinner difficultyLevel;
    private int emptyRow = SIZE - 1;
    private int emptyColumn = SIZE - 1;
    private final Random random = new Random();

    // CONSTRUCTOR
    public MyFifteenPuzzle() {
        this.setTitle("15-Puzzle Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 500);
        this.setLocationRelativeTo(null);
        this.addComponents();
    }

    // ADDING COMPONENTS
    public void addComponents(){
        gridPanel = new JPanel(new GridLayout(SIZE, SIZE));
        initializeGrid();

        // Difficulty level control spinner
        difficultyLevel = new JSpinner(new SpinnerNumberModel(100, 1, 1000, 10));

        shuffleButton = new JButton("Shuffle");
        // adding Action Listener for Shuffle button
        shuffleButton.addActionListener( new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                shuffle((int) difficultyLevel.getValue());
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JLabel("Difficulty Level:"));
        bottomPanel.add(difficultyLevel);
        bottomPanel.add(shuffleButton);

        add(gridPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Helper method to set up grid in its original ordered state 
    private void initializeGrid() {
        int count = 1;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JButton button = new JButton();
                if (count <= 15) {
                    button.setText(String.valueOf(count));
                } else {
                    button.setText("");
                }

                button.setFont(new Font("Arial", Font.BOLD, 24));
                // adding Action Listener to number buttons within the grid
                button.addActionListener( new ActionListener() {
                    public void actionPerformed ( ActionEvent ae) {
                        moveTile(button);
                    }
                });
                // adding button into the grid
                buttons[row][col] = button;
                gridPanel.add(button);
                count++;
            }
        }
    }

    // helper method for business logic to move tile for the given button parm
    private void moveTile(JButton button) {
        int row = -1, col = -1;
        // finding position of a clicked button parm
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // if button found in the grid store its row and column and stop iterating
                if (buttons[i][j] == button) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }
        // checks if the clicked button is next to the blank tile 
        if (isAdjacentTile(row, col, emptyRow, emptyColumn)) {
            // if adjacent - swap the tiles
            swapTiles(row, col, emptyRow, emptyColumn);
            // reset values for empty tile location 
            emptyRow = row;
            emptyColumn = col;
            // check correctness of the buttons in the grid after every move
            checkIfWin();  
        }
    }

    // helper method to determine of the user won the game by checking if all buttons are in correct positions on the grid
    // from 1 to 15 (for a 4x4 grid), and that the last tile is blank.
    private void checkIfWin() {
        int count = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                String text = buttons[i][j].getText();
                // if we are checking last tile [3][3] and it is not blank - user did not win
                if (i == SIZE - 1 && j == SIZE - 1) {
                    if (!text.equals("")) return; 
                } 
                // if the tile does not contain the expected number (count), then user did not win
                else {
                    if (!text.equals(String.valueOf(count))) return;
                    count++;
                }
            }
        }
        // if all above conditions were checked and passed then the user won - output the message 
        JOptionPane.showMessageDialog(this, "Congratulations, you won!", "Winner", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // helper method determining if a button is adjacent to an empty tile
    // takes 4 parameters representing button's row anc column and the empty tile's row and column
    private boolean isAdjacentTile(int row1, int col1, int row2, int col2) {
        // if column is same but row number differ by 1 OR row is the same but columns differ by 1 --> Return TRUE; else return FALSE
        return (Math.abs(row1 - row2) == 1 && col1 == col2) || (Math.abs(col1 - col2) == 1 && row1 == row2);
    }

    // helper method to swap tile values (move button value to an empty tile)
    private void swapTiles(int row1, int col1, int row2, int col2) {
        // saving value of the clicked button 
        String temp = buttons[row1][col1].getText();
        // set the value of the button to empty
        buttons[row1][col1].setText(buttons[row2][col2].getText());
        // set the previously empty tile to new value from the clicked button
        buttons[row2][col2].setText(temp);
    }
    
    // helper method to shuffle values on the grid to make solution more complicated 
    // depending on difficulty level user selected ( number of moves)
    private void shuffle(int moves) {
        // for every selected number of moves, swap tiles
        for (int i = 0; i < moves; i++) {
            // variable to hold the number of all legal mpves possible
            int legalMovesCount = 0;
            // arrays holding 4 possible positions to move to 
            int[] legalMoveRows = new int[4];
            int[] legalMoveColumns = new int[4];

            // if empty tile is not in row 0, then we know that cell in row above is where a legal move can be made
            if (emptyRow > 0) { 
                // storing legal move position and bumping legal move count
                legalMoveRows[legalMovesCount] = emptyRow - 1;
                legalMoveColumns[legalMovesCount] = emptyColumn;
                legalMovesCount++;
            }
            // if empty tile is not in row 3, then we know that cell in row below is where a legal move can be made
            if (emptyRow < SIZE - 1) { 
                legalMoveRows[legalMovesCount] = emptyRow + 1;
                legalMoveColumns[legalMovesCount] = emptyColumn;
                legalMovesCount++;
            }
            // if empty tile is not in column 0, then we know that cell in column to the left is where a legal move can be made
            if (emptyColumn > 0) { 
                legalMoveRows[legalMovesCount] = emptyRow;
                legalMoveColumns[legalMovesCount] = emptyColumn - 1;
                legalMovesCount++;
            }

            // if empty tile is not in column 3, then we know that cell in column to the right is where a legal move can be made
            if (emptyColumn < SIZE - 1) { 
                legalMoveRows[legalMovesCount] = emptyRow;
                legalMoveColumns[legalMovesCount] = emptyColumn + 1;
                legalMovesCount++;
            }

            // picks a random one out of all legal tiles 
            int randomLegalTile = random.nextInt(legalMovesCount);
            int newRow = legalMoveRows[randomLegalTile];
            int newCol = legalMoveColumns[randomLegalTile];

            // swaps the randomly selected tile with empty one 
            swapTiles(emptyRow, emptyColumn, newRow, newCol);
            // update empty tile position
            emptyRow = newRow;
            emptyColumn = newCol;
        }
    }
}

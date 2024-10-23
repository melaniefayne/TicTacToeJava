package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private TicTacToe game;  // game.TicTacToe game logic
    private final JButton[][] buttons;  // Buttons for the 3x3 grid
    private final JLabel statusLabel;  // Label to show game status (winner/tie/ongoing)
    private final JLabel scoreLabelX, scoreLabelO, scoreLabelTie;  // Labels for scoreboard
    private int xWins = 0, oWins = 0, ties = 0;  // Scores
    private final JButton replayButton;  // Button to replay the game

    public TicTacToeGUI() {
        // Initialize the game logic
        game = new TicTacToe();

        // Set up the GUI window (JFrame)
        setTitle("Tic-Tac-Toe");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a panel with a 3x3 grid for the buttons
        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];

        // Initialize the buttons and add them to the panel
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("_");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(this);  // Set button click listener
                gridPanel.add(buttons[row][col]);
            }
        }

        // Create a status label
        statusLabel = new JLabel("Player X's turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        // Create the scoreboard panel
        JPanel scorePanel = new JPanel(new GridLayout(1, 3));
        scoreLabelX = new JLabel("Player X: 0", SwingConstants.CENTER);
        scoreLabelO = new JLabel("Player O: 0", SwingConstants.CENTER);
        scoreLabelTie = new JLabel("Ties: 0", SwingConstants.CENTER);
        scorePanel.add(scoreLabelX);
        scorePanel.add(scoreLabelO);
        scorePanel.add(scoreLabelTie);

        // Create a replay button
        replayButton = new JButton("Play Again");
        replayButton.setFont(new Font("Arial", Font.PLAIN, 20));
        replayButton.addActionListener(e -> resetGame());
        replayButton.setEnabled(false);  // Disabled until the game ends

        // Add components to the window
        add(gridPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.NORTH);
        add(scorePanel, BorderLayout.SOUTH);
        add(replayButton, BorderLayout.EAST);

        // Make the window visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Find which button was clicked
        JButton clickedButton = (JButton) e.getSource();

        // Find the row and column of the clicked button
        int row = -1, col = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == clickedButton) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        // If the move is valid, update the game board and button text
        if (game.choose(row, col)) {
            clickedButton.setText(String.valueOf(game.getCurrentPlayer() == Player.X ? Player.O : Player.X));  // Show the previous player's mark
            clickedButton.setEnabled(false);  // Disable the button after it's clicked

            // Check if the game has ended
            if (game.didWin(Player.X)) {
                statusLabel.setText("Player X wins!");
                xWins++;  // Increment M's score
                scoreLabelX.setText("Player X: " + xWins);  // Update scoreboard
                disableAllButtons();
                replayButton.setEnabled(true);  // Enable replay
            } else if (game.didWin(Player.O)) {
                statusLabel.setText("Player O wins!");
                oWins++;  // Increment O's score
                scoreLabelO.setText("Player O: " + oWins);  // Update scoreboard
                disableAllButtons();
                replayButton.setEnabled(true);  // Enable replay
            } else if (game.didTie()) {
                statusLabel.setText("It's a tie!");
                ties++;  // Increment tie counter
                scoreLabelTie.setText("Ties: " + ties);  // Update scoreboard
                disableAllButtons();
                replayButton.setEnabled(true);  // Enable replay
            } else {
                // Update the status label for the next player
                statusLabel.setText("Player " + game.getCurrentPlayer() + "'s turn");
            }
        }
    }

    // Disable all buttons when the game ends
    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    // Reset the game for replay
    private void resetGame() {
        game = new TicTacToe();  // Reset the game logic
        statusLabel.setText("Player X's turn");  // Reset status label
        replayButton.setEnabled(false);  // Disable replay button

        // Reset all buttons
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("_");
                buttons[row][col].setEnabled(true);
            }
        }
    }

    public static void main(String[] args) {
        // Run the Tic-Tac-Toe GUI
        new TicTacToeGUI();
    }
}

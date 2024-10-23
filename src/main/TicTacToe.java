package main;

public class TicTacToe {
    // 2D array to store the game board
    private final Player[][] board;
    // Variable to track the current player
    private Player currentPlayer;

    // Constructor to initialize the board and set the first player
    public TicTacToe() {
        board = new Player[3][3]; // Create a 3x3 board
        currentPlayer = Player.X; // X always goes first
    }

    // Method to get the current player
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean choose(int row, int column) {
        // Check if row and column are within the valid range (0 to 2)
        if (row < 0 || row > 2 || column < 0 || column > 2) {
            System.out.println("Invalid move. Row and column must be between 0 and 2.");
            return false;
        }

        // Check if the selected cell is available
        if (board[row][column] != null) {
            System.out.println("This spot is already taken.");
            return false;
        }

        // Place the current player's mark on the board
        board[row][column] = currentPlayer;

        // Switch players after a successful move
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;

        return true;
    }

    public boolean didWin(Player player) {
        // Check rows for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }

        // Check columns for a win
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }

        // Check the two diagonals for a win
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    public boolean didTie() {
        // Check if there are any empty spots left
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    return false; // If there's an empty spot, it's not a tie
                }
            }
        }
        // If there are no empty spots and no player has won, it's a tie
        return !didWin(Player.X) && !didWin(Player.O);
    }

    public boolean notDone() {
        return !didWin(Player.X) && !didWin(Player.O) && !didTie();
    }

    // Test Method
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();

        // Test making some moves
        game.choose(0, 0); // X
        game.choose(1, 0); // O
        game.choose(1, 1); // X
        game.choose(2, 2); // O
        game.choose(0, 1); // X
        game.choose(0, 2); // O
        game.choose(2, 0); // X
        game.choose(2, 1); // O
        game.choose(1, 2); // X - should result in a tie
        System.out.println(game);

        // Check for a winner or a tie
        if (game.didWin(Player.X)) {
            System.out.println("X wins!");
        } else if (game.didWin(Player.O)) {
            System.out.println("O wins!");
        } else if (game.didTie()) {
            System.out.println("It's a tie!");
        } else {
            System.out.println("The game is still ongoing.");
        }
    }

    /// Util Methods
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j] == null ? '_' : board[i][j]).append(" "); // Use '_' for empty spots
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

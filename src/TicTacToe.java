public class TicTacToe {
    // 2D array to store the game board
    private final char[][] board;
    // Variable to track the current player ('X' or 'O')
    private char currentPlayer;

    // Constructor to initialize the board and set the first player
    public TicTacToe() {
        board = new char[3][3]; // Create a 3x3 board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '_'; // Initialize all spots to '_'
            }
        }
        currentPlayer = 'X'; // X always goes first
    }

    // Method to get the current player
    public char getNextPlayerChar() {
        return currentPlayer;
    }

    public boolean choose(int row, int column) {
        // Check if row and column are within the valid range (0 to 2)
        if (row < 0 || row > 2 || column < 0 || column > 2) {
            System.out.println("Invalid move. Row and column must be between 0 and 2.");
            return false;
        }

        // Check if the selected cell is available
        if (board[row][column] != '_') {
            System.out.println("This spot is already taken.");
            return false;
        }

        // Place the current player's mark on the board
        board[row][column] = currentPlayer;

        // Switch players after a successful move
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';

        return true;
    }

    public boolean didWin(char player) {
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
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        return board[0][2] == player && board[1][1] == player && board[2][0] == player;
    }

    public boolean didTie() {
        // Check if there are any empty spots left
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_') {
                    return false; // If there's an empty spot, it's not a tie
                }
            }
        }

        // If there are no empty spots and no player has won, it's a tie
        return !didWin('X') && !didWin('O');
    }

    public boolean notDone() {
        return !didWin('X') && !didWin('O') && !didTie();
    }

    // Test Method
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();

        // Test making some moves
        game.choose(0, 0); // X
        game.choose(1, 1); // O
        game.choose(0, 1); // X
        game.choose(2, 2); // O
        game.choose(0, 2); // X - this should make X win

        System.out.println(game);

        // Check if X won
        if (game.didWin('X')) {
            System.out.println("X wins!");
        } else if (game.didWin('O')) {
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
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}

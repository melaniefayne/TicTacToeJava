import java.util.Scanner;

public class TicTacToeMain {

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println(game);

        while (game.notDone()) {
            char currentPlayer = game.getNextPlayerChar();
            System.out.println("Player " + currentPlayer + ", enter your move (row and column):");

            int row = scanner.nextInt();
            int column = scanner.nextInt();

            // Attempt to place the player's mark and re-prompt if the move is invalid
            while (!game.choose(row, column)) {
                System.out.println("Invalid move. Please try again.");
                row = scanner.nextInt();
                column = scanner.nextInt();
            }

            // Display the board after the move
            System.out.println(game);

            // Check if the game has ended
            if (game.didWin('X')) {
                System.out.println("Player X wins!");
                break;
            } else if (game.didWin('O')) {
                System.out.println("Player O wins!");
                break;
            } else if (game.didTie()) {
                System.out.println("It's a tie!");
                break;
            }
        }

        System.out.println("Game over. Thanks for playing!");
        scanner.close();
    }
}

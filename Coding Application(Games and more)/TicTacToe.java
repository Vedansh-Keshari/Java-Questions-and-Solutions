package dev.amitwani;

/*
 *
 * A program for end user so that he/she can play tic-tac-toe game with computer
 * or game can be played by two end users.
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {

        System.out.print("!!Welcome to Tic-Tac-Toe!!\n" +
                "Decide your opponent:\n" +
                "1) Play with computer\n" +
                "2) Play with a friend\n" +
                "Enter your choice –");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        scanner.nextLine();

        String[] game = new String[]{"*", "*", "*", "*", "*", "*", "*", "*", "*"};
        String player1Mark = "X";
        String player2Mark = "O";
        boolean isGameOver = false;
        int nextTurn = 0;

        if (choice == 1) {

            System.out.print("\nEnter your name –");
            String playerName = scanner.nextLine();
            String computerName = "T-Bot";

            System.out.println("Hey " + playerName + ", I am Computer. My name is ‘" + computerName + "’.");

            System.out.print("\nWho’ll go first?\n" +
                    "1) T-Bot\n" +
                    "2) Me\n" +
                    "Enter your choice –");

            nextTurn = scanner.nextInt();

            printGameBoard(game);

            while (!isGameOver) {

                List<Integer> emptySpaces = findEmptySpaces(game);

                if (nextTurn == 1) {

                    System.out.print("T-Bot’s turn: ");

                    Random random = new Random();
                    int nextMove = emptySpaces.get(random.nextInt(emptySpaces.size()));

                    System.out.print(nextMove);

                    updateGameBoard(game, nextMove, player1Mark);

                    nextTurn = 2;
                } else {

                    System.out.print(playerName + "’s turn: ");

                    playerNextMove(scanner, game, player2Mark, playerName, emptySpaces);

                    nextTurn = 1;
                }

                printGameBoard(game);

                String result = checkIfGameOver(game);

                if (result != null) {
                    isGameOver = true;
                    String winner = result.equals(player1Mark) ? "T-Bot" : playerName;
                    System.out.println("RESULTS TIME:\n" + winner + " WON !!");
                }

            }

        } else if (choice == 2) {

            System.out.print("\nEnter your name of player 1 –");
            String player1Name = scanner.nextLine();
            System.out.print("\nEnter your name of player 2 –");
            String player2Name = scanner.nextLine();

            System.out.print("\nWho’ll go first?\n" +
                    "1) " + player1Name + "\n" +
                    "2) " + player2Name + "\n" +
                    "Enter your choice –");

            nextTurn = scanner.nextInt();

            printGameBoard(game);

            while (!isGameOver) {

                List<Integer> emptySpaces = findEmptySpaces(game);

                if (nextTurn == 1) {

                    System.out.print(player1Name + "’s turn: ");

                    playerNextMove(scanner, game, player1Mark, player1Name, emptySpaces);

                    nextTurn = 2;
                } else {

                    System.out.print(player2Name + "’s turn: ");

                    playerNextMove(scanner, game, player2Mark, player2Name, emptySpaces);

                    nextTurn = 1;
                }

                printGameBoard(game);

                String result = checkIfGameOver(game);

                if (result != null) {
                    isGameOver = true;
                    String winner = result.equals(player1Mark) ? player1Name : player2Name;
                    System.out.println("RESULTS TIME:\n" + winner + " WON !!");
                }

            }

        }

    }

    private static void playerNextMove(Scanner scanner, String[] game, String playerMark, String playerName, List<Integer> emptySpaces) {
        int nextMove = scanner.nextInt();
        boolean available = isSpaceEmpty(emptySpaces, nextMove);
        while (!available) {
            System.out.println("Invalid input. Space already filled.");
            System.out.print(playerName + "’s turn: ");

            nextMove = scanner.nextInt();
            available = isSpaceEmpty(emptySpaces, nextMove);
        }

        updateGameBoard(game, nextMove, playerMark);
    }

    private static boolean isSpaceEmpty(List<Integer> emptySpaces, int nextMove) {
        return emptySpaces.stream().anyMatch(x -> x == nextMove);
    }

    private static String checkIfGameOver(String[] game) {

        // check columns
        for (int i = 0; i < 3; i++) {
            if (game[i].equals(game[i + 3]) && game[i + 3].equals(game[i + 6]) && !game[i].equals("*")) {
                return game[i];
            }
        }

        // check rows
        for (int i = 0; i < 7; i += 3) {
            if (game[i].equals(game[i + 1]) && game[i + 1].equals(game[i + 2]) && !game[i].equals("*")) {
                return game[i];
            }
        }

        // check diagonal
        if (game[0].equals(game[4]) && game[4].equals(game[8]) && !game[0].equals("*")) {
            return game[0];
        }
        if (game[2].equals(game[4]) && game[4].equals(game[6]) && !game[2].equals("*")) {
            return game[2];
        }

        return null;
    }

    private static void updateGameBoard(String[] game, int nextMove, String playerMark) {
        game[nextMove - 1] = playerMark;
    }

    private static List<Integer> findEmptySpaces(String[] game) {

        List<Integer> emptySpaces = new ArrayList<>();

        for (int i = 1; i <= game.length; i++) {
            if (game[i - 1].equals("*")) {
                emptySpaces.add(i);
            }
        }

        return emptySpaces;
    }

    private static void printGameBoard(String[] game) {
        System.out.println();
        for (int i = 1; i < 10; i++) {
            System.out.print(game[i - 1] + "\t");
            if (i % 3 == 0)
                System.out.println();
        }
    }
}

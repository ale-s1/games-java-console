package org.example.ticTacToe;

import java.util.*;

public class TicTacToe {
    private final int ROWS = 3;
    private final  int COLS = 3;
    private final char[][] Board = new char[ROWS][COLS];
    private final char characterToFillBoard = ' ';

    public TicTacToe(){
        for (char[] val : Board) {
            Arrays.fill(val, characterToFillBoard);
        }
    }

    public char[][] getBoard() {
        return Board;
    }

    public void printBoard(){
        for (char[] row : Board) {
            for (char c : row) {
                System.out.print(" | ");
                System.out.print(c);
            }
            System.out.print(" | ");
            System.out.println(" ");
        }
    }

    public List<Integer> availableMoves(){
        List<Integer> availablePositions = new ArrayList<>();
        int position = 0;
        for (int row = 0; row < Board.length; row++) {
            for (int col = 0; col < Board[row].length; col++) {
                if(Board[row][col] == characterToFillBoard){
                    availablePositions.add(position);
                }
                position++;
            }
        }
        return availablePositions;
    }

    public boolean makeMove(int x, int y, char player){
        if(Board[x][y] == characterToFillBoard){
            Board[x][y] = player;
            return true;
        }
        return false;
    }

    public boolean isBoardFull(){
        for (int i = 0; i < Board.length; i++) {
            for (int j = 0; j < Board[i].length; j++) {
                if(Board[i][j] == characterToFillBoard){
                    return false;
                }
            }
        }
        return  true;
    }

    public Character checkWinner() {
        // Check rows
        for (char[] chars : Board) {
            if (chars[0] != characterToFillBoard && chars[0] == chars[1] && chars[1] == chars[2]) {
                return chars[0];
            }
        }

        // Check columns
        for (int col = 0; col < Board[0].length; col++) {
            if (Board[0][col] != characterToFillBoard && Board[0][col] == Board[1][col] && Board[1][col] == Board[2][col]) {
                return Board[0][col];
            }
        }

        // Check diagonals
        if (Board[0][0] != characterToFillBoard && Board[0][0] == Board[1][1] && Board[1][1] == Board[2][2]) {
            return Board[0][0];
        }
        if (Board[0][2] != characterToFillBoard && Board[0][2] == Board[1][1] && Board[1][1] == Board[2][0]) {
            return Board[0][2];
        }
        return characterToFillBoard;
    }


    public int minMax(int depth, boolean isMaximizing){
        char winner = checkWinner();
        if(winner == 'O'){
            return 1;
        }
        if(winner == 'X'){
            return -1;
        }
        if(isBoardFull()){
            return 0;
        }
        int bestScore = 0;
        if(isMaximizing){
            bestScore = Integer.MIN_VALUE;
            for(int move: availableMoves()){
                int row = move / ROWS;
                int col = move % COLS;
                Board[row][col] = 'O';
                int score = minMax(depth + 1, false);
                Board[row][col] = characterToFillBoard;
                bestScore = Math.max(score, bestScore);
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for(int move: availableMoves()){
                int row = move / ROWS;
                int col = move % COLS;
                Board[row][col] = 'X';
                int score = minMax(depth + 1, true);
                Board[row][col] = characterToFillBoard;
                bestScore = Math.min(score, bestScore);
            }
        }
        return bestScore;
    }

    public int getBestMove(){
        int bestScore = Integer.MIN_VALUE;
        int bestMove = 0;

        for(int move: availableMoves()){
            int row = move / ROWS;
            int col = move % COLS;
            Board[row][col] = 'O';
            int score = minMax(0, false);
            Board[row][col] = characterToFillBoard;
            if(score > bestScore){
                bestScore = score;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private void messageStartGame(){
        System.out.println("\n" +
            "\n" +
            " _____ _        _____            _____          \n" +
            "|_   _(_) ___  |_   _|_ _  ___  |_   _|__   ___ \n" +
            "  | | | |/ __|   | |/ _` |/ __|   | |/ _ \\ / _ \\\n" +
            "  | | | | (__    | | (_| | (__    | | (_) |  __/\n" +
            "  |_| |_|\\___|   |_|\\__,_|\\___|   |_|\\___/ \\___|\n" +
            "\n");
            System.out.println("You are X and the machine is O");
            System.out.println("Please enter a position in the grid as shown below:");
            System.out.println("Enter the row number (0-2) first, followed by the column number (0-2).");
            int col = Integer.MIN_VALUE;
            for (int moves: availableMoves()){
                int tempCol = moves / COLS;
                if(tempCol > col){
                    System.out.println();
                    col = tempCol;
                    System.out.print(moves);
                } else{
                    System.out.print("|" + moves);
                }
            }
    }

    public boolean gameOver(){
        return isBoardFull() || checkWinner() != characterToFillBoard;
    }


    public void start() throws  InterruptedException {
        messageStartGame();
        boolean machine = new Random().nextBoolean();
        System.out.println("\n The house is choosing who goes first \uD83C\uDFB2...");
        Thread.sleep(2000);
        System.out.println("The house chose:  " + (machine ? "Machine" : "User"));
        Scanner scanner = new Scanner(System.in);

        while (!gameOver()){
            System.out.println(machine ? "Machine's" : "User's" + " turn");
            if(machine){
                int move = getBestMove();
                int row = move / ROWS;
                int col = move % COLS;
                makeMove(row, col, 'O');
            } else{
                while (true){
                    try{
                        System.out.println("row: ");
                        int row = scanner.nextInt();

                        System.out.print("col: ");
                        int col= scanner.nextInt();

                        if(row >= 0 && row < ROWS && col >= 0 && col < COLS){
                            makeMove(row, col, 'X');
                            break;
                        } else{
                            System.out.println("Invalid move! Try again.");
                        }
                    }catch (Exception err){
                        if(err instanceof InputMismatchException){
                            System.out.println("Invalid input! Please enter valid numbers.");
                            scanner.nextLine();
                        } else{
                            System.out.println("Opps! something went wrong");
                            scanner.nextLine();
                        }
                    }
                }
            }
            machine = !machine;
            printBoard();
        }

        if(checkWinner() != characterToFillBoard){
            System.out.println("The winner is " + checkWinner());
        } else {
            System.out.println("It's a tie!");
        }
    }
}
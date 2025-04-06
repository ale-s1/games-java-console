package org.example.ticTacToe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {

    @Test
    void testRowWinner() {
        TicTacToe game = new TicTacToe();

        game.makeMove(0, 0, 'X'); // X at (0,0)
        game.makeMove(0, 1, 'X'); // X at (0,1)
        game.makeMove(0, 2, 'X'); // X at (0,2)

        assertEquals('X', game.checkWinner(), "X should win by completing the top row.");
    }

    @Test
    void testColumnWinner() {
        TicTacToe game = new TicTacToe();

        game.makeMove(0, 0, 'O'); // O at (0,0)
        game.makeMove(1, 0, 'O'); // O at (1,0)
        game.makeMove(2, 0, 'O'); // O at (2,0)

        assertEquals('O', game.checkWinner(), "O should win by completing the first column.");
    }

    @Test
    void testDiagonalWinner() {
        TicTacToe game = new TicTacToe();

        game.makeMove(0, 0, 'X'); // X at (0,0)
        game.makeMove(1, 1, 'X'); // X at (1,1)
        game.makeMove(2, 2, 'X'); // X at (2,2)
        assertEquals('X', game.checkWinner(), "X should win by completing the diagonal from top-left to bottom-right.");
    }

    @Test
    void testAntiDiagonalWinner() {
        TicTacToe game = new TicTacToe();

        game.makeMove(0, 2, 'O'); // O at (0,2)
        game.makeMove(1, 1, 'O'); // O at (1,1)
        game.makeMove(2, 0, 'O'); // O at (2,0)

        assertEquals('O', game.checkWinner(), "O should win by completing the diagonal from top-right to bottom-left.");
    }
}
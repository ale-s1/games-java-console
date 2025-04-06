# Overview ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

Tic-Tac-Toe console game that lets the player play against the machine. The machine uses the Minimax algorithm to make optimal moves.

## Features
- Player vs. Machine.

## Code Snippet
- Here’s a brief look at the Minimax algorithm used in the game.
- This is just for learning purposes it is not supposed to be great.
    
```java    
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


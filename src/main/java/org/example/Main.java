package org.example;

import org.example.ticTacToe.TicTacToe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TicTacToe game = new TicTacToe();
        game.start();
    }

}
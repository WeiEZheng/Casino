package com.github.zipcodewilmington.casino.games.TicTacToe;

import com.github.zipcodewilmington.casino.games.GameInterface.Games;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.*;

public class TicTacToeGame implements Games<TicTacToePlayer> {
    private final IOConsole console = new IOConsole(AnsiColor.BLUE);
    private boolean exitFlag = false;
    private Map<TicTacToePlayer, String> playerSymbol = new HashMap<>();
    private Map<String, TicTacToePlayer> playerSymbolReverse = new HashMap<>();
    private Board board = new Board();
    private String win="";
    private List<TicTacToePlayer> order = new ArrayList<>();

    public TicTacToeGame(){};

    @Override
    public void play() {
        Boolean invalidMoveFlag = true;
        Integer[] move;
        while (!exitFlag){
            Collections.shuffle(order);
            TicTacToePlayer player1 = order.get(0);
            TicTacToePlayer player2 = order.get(1);
            selectSymbol();
            while (win.equals("")){
                System.out.println(board.displayBoard());
                while (invalidMoveFlag) {
                    move = getMove(player1);
                    invalidMoveFlag = board.markBoard(playerSymbol.get(player1),
                            move[0]-1,move[1]-1);
                }
                win = getWinner(board.getBoard());
                System.out.println(board.displayBoard());
                if (!win.equals(""))
                    break;
                invalidMoveFlag = true;
                while (invalidMoveFlag) {
                    move = getMove(player2);
                    invalidMoveFlag = board.markBoard(playerSymbol.get(player2),
                            move[0]-1,move[1]-1);
                }
                win = getWinner(board.getBoard());
                invalidMoveFlag = true;
            }
            System.out.println("Winner is: " + playerSymbolReverse.get(win).getPerson().getName());
            exit();
        }
    }

    public Integer[] getMove(TicTacToePlayer player1) {
        Integer [] move = new Integer[2];
        move[0]= console.getIntegerInput(player1.getPerson().getName()+
                ", which row would you like to place your mark?");
        while (move[0]<1 || move[0]>3){
            move[0] = console.getIntegerInput(player1.getPerson().getName()+
                    "not valid row! Which row would you like to place your mark?");
        }
        move[1] = console.getIntegerInput(player1.getPerson().getName()+
                ", which column would you like to place your mark?");
        while (move[1]<1 || move[1]>3){
            move[1] = console.getIntegerInput(player1.getPerson().getName()+
                    "not valid column! Which row would you like to place your mark?");
        }
        return move;
    }

    public void selectSymbol(){
        String symbol = console.getStringInput(order.get(0).getPerson().getName()+", " +
                "do you want to be X or O?");
        while (!(symbol.equalsIgnoreCase("X") || symbol.equalsIgnoreCase("O"))){
            symbol = console.getStringInput(order.get(0).getPerson().getName()+", not a valid input." +
                    "Do you want to be X or O?");
        }
        symbol=symbol.toUpperCase();
        playerSymbol.put(order.get(0),symbol);
        playerSymbolReverse.put(symbol,order.get(0));
        if (symbol.equalsIgnoreCase("X"))
            symbol="O";
        else
            symbol="X";
        playerSymbol.put(order.get(1),symbol);
        playerSymbolReverse.put(symbol,order.get(1));
        System.out.println(order.get(1).getPerson().getName()+", you will be "+symbol);
    }

    @Override
    public void addPlayer(TicTacToePlayer player) {
        order.add(player);
    }

    public String getWinner(String[][] boardState){
        String winner="";
        if ((boardState[0][0] == boardState[1][1]) &&
                (boardState[0][0] == boardState[2][2])) {
            winner = boardState[0][0];
        }
        else if ((boardState[2][0] == boardState[0][2]) &&
                (boardState[2][0] == boardState[1][1])) {
            winner = boardState[1][1];
        }
        else{
            for (int i=0;i<3;i++) {
                if ((boardState[i][0] == boardState[i][1]) &&
                        (boardState[i][0] == boardState[i][2])){
                    winner = boardState[i][0];
                    break;
                }
                else if ((boardState[0][i] == boardState[1][i]) &&
                        (boardState[0][i] == boardState[2][i])){
                    winner = boardState[0][i];
                    break;
                }
            }
        }
        return winner;
    }

    @Override
    public void exit() {
        String input = console.getStringInput("Do you want to exit the game?");
        if (input.equalsIgnoreCase("Yes"))
            this.exitFlag=true;
        else {
            board = new Board();
        }
    }

    @Override
    public void winConditionCheck(TicTacToePlayer player) {
    }
    @Override
    public void setPlayerMax() {}
    @Override
    public void removePlayer(TicTacToePlayer player) {}
}

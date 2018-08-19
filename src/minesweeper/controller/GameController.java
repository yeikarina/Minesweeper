/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.controller;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import minesweeper.model.Board;
import java.util.Scanner;
/**
 *
 * @author Personal
 */
public class GameController {
    private boolean gameOver = false;
    private boolean gameWon = false;
    private Board board = new Board();
    private Board boardUpdated = new Board();

    public GameController(Board b) {
        this.board = b; 
        iniciarArray();
    }
    
    
    
    public Board getBoard() {
        return board;
    }
    
    public void setBoard(Board board) {
        this.board = board;
    }
   
    
    private void iniciarArray(){
        boardUpdated = new Board(board.getHeight(), board.getWidth(), board.getMines());
        
        for (int i = 0; i < boardUpdated.getHeight(); i++) {
            for (int j = 0; j < boardUpdated.getWidth(); j++) {
                boardUpdated.getCells()[i][j]= " . ";
            }
        }
        
       
        showArray();
    }
    
    public void movement(){
        ArrayList move = askForMovement();
        int indexI = (int) move.get(0);
        int indexJ = (int) move.get(1);
        String movement = (String) move.get(2);
        if(movement.equals("M") && boardUpdated.getCells()[indexI-1][indexJ-1].equals(" . ")){
            boardUpdated.getCells()[indexI-1][indexJ-1] = " P ";
            showArray();
        }else if(boardUpdated.getCells()[indexI-1][indexJ-1].equals(" . ") == false){
                    if(boardUpdated.getCells()[indexI-1][indexJ-1].equals(" P ")){
                    System.out.println("• Esta celda ya fue marcada");
                    }else{
                        System.out.println("• Esta celda ya fue descubierta");
                    }
        }else {
            if (board.getCells()[indexI-1][indexJ-1] == "*" && movement.equals("U")){
            gameOver = true;
            showArray();
            gameOver();
            }
            else if(movement.equals("U") && boardUpdated.getCells()[indexI-1][indexJ-1].equals(" . ")){
                if(board.getCells()[indexI-1][indexJ-1] == "-"){
                    int[][] emptyCells = new int[board.getHeight()*board.getWidth()][2];
                
                    int counter=0;
                 // buscando celdas vacias en el tablero
                    for (int i = 0; i < board.getHeight(); i++) {
                        for (int j = 0; j < board.getWidth(); j++) {
                            if(board.getCells()[i][j] == "-"){
                                 emptyCells[counter][0]=i;
                                 emptyCells[counter][1]=j;
                                 counter++;
                            }
                        }
                    }
                    //mostrar empty cell
                    int[][] cellsToReveal = new int[board.getHeight()*board.getWidth()][2];
                    cellsToReveal[0][0]=indexI-1;
                    cellsToReveal[0][1]=indexJ-1;
                    int counter2=1;
                    //buscando celdas a revelar
                  
                    for (int i = 0; i < counter2; i++) {
                        int[] position1 = new int[2];
                        position1[0]= cellsToReveal[i][0];
                        position1[1] = cellsToReveal[i][1];
                        for (int j = 0; j < counter; j++) {
                            int[] position2 = new int[2];
                            position2[0]=emptyCells[j][0];
                            position2[1] = emptyCells[j][1];
                            if(adjacency(position1, position2) && contains(cellsToReveal, counter2, position2) == false){
                                cellsToReveal[counter2][0] = position2[0];
                                cellsToReveal[counter2][1] = position2[1];
                                counter2++;
                            }
                        }
                    }
                    
                    updateBoard(cellsToReveal, counter2);
                    showArray();
                }else if(boardUpdated.getCells()[indexI-1][indexJ-1].equals(" . ")){
                    boardUpdated.getCells()[indexI- 1][indexJ-1] = " " + board.getCells()[indexI-1][indexJ-1] + " ";
                    showArray();
                } 

                
            } 
        }
        
        
        gameWon=gameWon();
    }
    
    public void showArray(){
            int counter=0;
            int minesToMark=0;
            System.out.println("");
            for (int i = 0; i < this.boardUpdated.getHeight(); i++) {
                for (int j = 0; j < this.boardUpdated.getWidth(); j++) {
                    System.out.print(boardUpdated.getCells()[i][j]);
                    if(boardUpdated.getCells()[i][j].equals(" P ")){
                        counter++;
                    }
                }System.out.println("");
            }
            minesToMark = boardUpdated.getMines() - counter;
            System.out.println("Minas por marcar = " + minesToMark);
        
    }
    private void updateBoard(int[][] cellsToReveal, int counter){
        for (int i = 0; i <counter; i++) {
                //boardUpdated.getCells()[cellsToReveal[i][0]][cellsToReveal[i][1]]= " - ";
                
                try {
                        if(board.getCells()[cellsToReveal[i][0]-1][cellsToReveal[i][1]-1]!="*"){
                            boardUpdated.getCells()[cellsToReveal[i][0]-1][cellsToReveal[i][1]-1]= " "+board.getCells()[cellsToReveal[i][0]-1][cellsToReveal[i][1]-1]+" ";
                        };
                    } catch (Exception e) {}
                    try {
                        if(board.getCells()[cellsToReveal[i][0]-1][cellsToReveal[i][1]]!="*"){
                            boardUpdated.getCells()[cellsToReveal[i][0]-1][cellsToReveal[i][1]]= " "+board.getCells()[cellsToReveal[i][0]-1][cellsToReveal[i][1]]+" ";
                        };
                    } catch (Exception e) {}
                    try {
                        if(board.getCells()[cellsToReveal[i][0]-1][cellsToReveal[i][1]+1]!="*"){
                            boardUpdated.getCells()[cellsToReveal[i][0]-1][cellsToReveal[i][1]+1]= " "+board.getCells()[cellsToReveal[i][0]-1][cellsToReveal[i][1]+1]+" ";
                        };
                    } catch (Exception e) {}
                    try {
                        if(board.getCells()[cellsToReveal[i][0]][cellsToReveal[i][1]-1]!="*"){
                            boardUpdated.getCells()[cellsToReveal[i][0]][cellsToReveal[i][1]-1]= " "+board.getCells()[cellsToReveal[i][0]][cellsToReveal[i][1]-1]+" ";
                        };
                    } catch (Exception e) {}
                    try {
                        if(board.getCells()[cellsToReveal[i][0]][cellsToReveal[i][1]+1]!="*"){
                            boardUpdated.getCells()[cellsToReveal[i][0]][cellsToReveal[i][1]+1]= " "+board.getCells()[cellsToReveal[i][0]][cellsToReveal[i][1]+1]+" ";
                        };
                    } catch (Exception e) {}
                    try {
                        if(board.getCells()[cellsToReveal[i][0]+1][cellsToReveal[i][1]-1]!="*"){
                            boardUpdated.getCells()[cellsToReveal[i][0]+1][cellsToReveal[i][1]-1]= " "+board.getCells()[cellsToReveal[i][0]+1][cellsToReveal[i][1]-1]+" ";
                        };
                    } catch (Exception e) {}
                    try {
                        if (board.getCells()[cellsToReveal[i][0]+1][cellsToReveal[i][1]]!="*"){
                            boardUpdated.getCells()[cellsToReveal[i][0]+1][cellsToReveal[i][1]]= " "+board.getCells()[cellsToReveal[i][0]+1][cellsToReveal[i][1]]+" ";
                        };
                    } catch (Exception e) {}
                    try {
                        if(board.getCells()[cellsToReveal[i][0]+1][cellsToReveal[i][1]+1]!="*"){
                            boardUpdated.getCells()[cellsToReveal[i][0]+1][cellsToReveal[i][1]+1]= " "+board.getCells()[cellsToReveal[i][0]+1][cellsToReveal[i][1]+1]+" ";
                        };
                    } catch (Exception e) {}
        }
    }
    private ArrayList askForMovement(){
        ArrayList move = new ArrayList();
        String cadena = "";
        String movement = "";
        int indexI=-1;
        int indexJ=-1;
        Scanner entrance = new Scanner(System.in);
        boolean flag;
        do {
            flag = true;
            System.out.println("Movimiento :");
            cadena = entrance.nextLine();
            String[] c = cadena.split(" ");
            try {
                indexI = Integer.parseInt(c[0]);
                if(indexI<= boardUpdated.getHeight() && indexI > 0){
                    
                }else{
                    flag = false;
                    System.out.println("• Digite una altura entre 1 y "+ boardUpdated.getHeight());
                }
            } catch (Exception e) {
                System.out.println("• Digite una altura numerica");
                flag = false;
                }
            try {
                indexJ = Integer.parseInt(c[1]);
                if(indexJ<= boardUpdated.getWidth() && indexJ > 0){
                   
                }else{
                    flag = false;
                    System.out.println("• Digite una anchura entre 1 y "+ boardUpdated.getWidth());
                }
            } catch (Exception e) {
                System.out.println("• Digite una anchura numerica");
                flag = false;
            }
            try {
                if(c[2].equals("U") || c[2].equals("M")){
                    movement = c[2];
                } else{
                    System.out.println("• Ingrese un movimiento válido");
                    flag = false;
                }

            } catch (Exception e) {
                System.out.println("• Por favor ingrese correctamente el movimiento a realizar");
                flag = false;
            }
            
                
        } while (!flag);
        
        
        move.add(indexI);
        move.add(indexJ);
        move.add(movement);
     
        
        return move;
    }
    
    private boolean adjacency(int[] position1, int[] position2){
        //position1 es la posicion marcada por el usuario
        //position2 es la posicion evaluada
        boolean flag=false;
        if(((position1[0]-1)== position2[0]) && ((position1[1]-1)==position2[1])){
            flag=true;
        }
        else if(((position1[0]-1)== position2[0]) && ((position1[1])==position2[1])){
            flag=true;
        }
        else if(((position1[0]-1)== position2[0]) && ((position1[1]+1)==position2[1])){
            flag=true;
        }
        else if(((position1[0]) == position2[0]) && ((position1[1]-1)==position2[1])){
            flag=true;
        }
        else if(((position1[0]) == position2[0]) && ((position1[1]+1)==position2[1])){
            flag=true;
        }
        else if(((position1[0]+1) == position2[0]) && ((position1[1]-1)==position2[1])){
            flag=true;
        }
        else if(((position1[0]+1)== position2[0]) && ((position1[1])==position2[1])){
            flag=true;
        }
        else if(((position1[0]+1)== position2[0]) && ((position1[1]+1)==position2[1])){
            flag=true;
        }
          
        return flag;
    }
    
    private boolean contains(int[][] cells,int counter, int[] position){
        boolean flag = false;
        for (int i = 0; i < counter; i++) {
            if(cells[i][0]==position[0] && cells[i][1]== position[1]){
                flag = true;
            }
        }
        return flag;
    }
    
    private Boolean gameWon(){
        int counter = 0;
        int minesMarked = 0;// cuenta las P que corresponden a Minas
        int minesMarked2 = 0;//cuenta las P independeintemente de las minas
        boolean flag= false;
        for (int i = 0; i < boardUpdated.getHeight(); i++) {
            for (int j = 0; j < boardUpdated.getWidth(); j++) {
                if(boardUpdated.getCells()[i][j].equals(" P ") && board.getCells()[i][j].equals("*")){
                    minesMarked++;
                }
                if(boardUpdated.getCells()[i][j].equals(" P ")){
                    minesMarked2++;
                }
            }
        }
        if(minesMarked== board.getMines() && minesMarked == minesMarked2){
            flag=true;System.out.println("\nHas ganado el juego :D");
        }
        
        return flag;
    }
    
    private void gameOver(){
        System.out.println("\nEl juego ha terminado");
        System.out.println("Has perdido :(");
        for (int i = 0; i < boardUpdated.getHeight(); i++) {
            for (int j = 0; j < boardUpdated.getWidth(); j++) {
                if(board.getCells()[i][j].equals("*")){
                    boardUpdated.getCells()[i][j]= " * ";
                }
            }
        }
    }
    
    public boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    
    public boolean getGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }
}

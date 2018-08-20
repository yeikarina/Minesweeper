/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import javax.swing.JOptionPane;
import minesweeper.controller.BoardController;
import minesweeper.controller.GameController;
import minesweeper.model.Board;
import java.util.Scanner;

/**
 *
 * @author Personal
 */
public class Minesweeper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       //iniciamos pidiendo las entradas
        int height=0;
        int width=0;
        int mines=0;
        String cadena;
        Scanner entrance = new Scanner(System.in);
        boolean flag = false;
        System.out.println("****BUSCA MINAS GAME****");
        System.out.println("Instrucciones:\n1. Para Iniciar: \nIngresar el alto, ancho y el numero de minas del tablero "
                + "                                          \nseparado por espacio, por ejemplo: 8 15 10"
                + "                       \n2. Para jugar:   \nDigitar altura, anchura y movimiento a realizar en la "
                + "                                          \ncelda separado por espacio, ejempo: 2 5 M donde M es "
                + "                                          \nmarcar bandera que en el tablero sera una P."
                + "                                          \nIMPORTANTE!!: Los únicos movimientos posibles son M(marcar) y U(descubrir)");
              
        do {
            flag = false;            
            System.out.println("\n***Ingrese debajo: Alto, Ancho y Numero de Minas***");
            cadena=entrance.nextLine();
        
            String[] c = cadena.split(" ");
            try {
                height = Integer.parseInt(c[0]);
                if (height < 1) {
                    System.out.println("• La altura no puede ser menor a 1 ");
                    flag = true;
                }
            } catch (Exception e) {
                System.out.println("• Por favor ingrese correctamente la altura");
                flag = true;
                }
            try {
                width = Integer.parseInt(c[1]);
                if (width < 8 ) {
                    System.out.println("• La anchura no puede ser menor a 8 reference http://minesweeperonline.com/#");
                    flag = true;
                }
            } catch (Exception e) {
                System.out.println("• Por favor ingrese correctamente la anchura");
                flag = true;
            }
            try {
                mines = Integer.parseInt(c[2]);
                if(mines > height*width-1 && flag != true){
                    System.out.println("• El numero de minas debe ser menor a " + (height*width));
                    flag = true;
                }
            } catch (Exception e) {
                System.out.println("• Por favor ingrese correctamente el numero de minas");
                flag = true;
            }
        } while (flag);
        
        //se ingresan los datos para crear el tablero
        if(height >=1 && width >= 8 && (mines>=0 && mines < height*width)){
            Board board = new Board(height, width, mines);
            BoardController BC = new BoardController(board);
            board = BC.fillMines();
            //BC.showBoard();
            BC.fillCells();
            GameController gc=new GameController(board);
            
            //empieza el juego 
            do {                
                gc.movement();
            } while (gc.getGameOver() == false && gc.getGameWon() == false);
        }
    }
}

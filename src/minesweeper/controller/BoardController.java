
package minesweeper.controller;
import minesweeper.model.Board;
/**
 *
 * @author Personal
 */
public class BoardController {
    Board b =new Board();
    
    public BoardController(Board board) {
        this.b = board;
    }
    
    public Board fillMines(){
        String [][] auxMat= b.getCells();
        //variables para crear la distribucion poison en la generacion de las minas aleatoriamente
        float m = b.getWidth() * b.getHeight(); 
        float y = (b.getMines()/m);
        
        int counter=0;//variable para validar el numero de minas.
        
        do{
            for(int i=0; i<b.getHeight(); i++){
                for(int j=0; j<b.getWidth(); j++){
                    double n=Math.random();//n es el numero aleatorio
                   // generacion de variables aleatorias con distribucion poison
                    if((n<= y && counter<b.getMines()) || auxMat[i][j]=="*"){
                        if(auxMat[i][j]!= "*" ){
                            auxMat[i][j]="*";counter++;
                        }
                    }else{
                        auxMat[i][j]="0";
                    }
                }
            }
        }while(counter< b.getMines());                
             
        this.b.setCells(auxMat);
        return b;
    }
    
    public void showBoard(){
        System.out.println("");
        for(int i=0; i<b.getHeight(); i++){
            for(int j=0; j<b.getWidth(); j++){
                System.out.print(b.getCells()[i][j]+" ");
            }
            System.out.println();
       }
    }
    
    public void fillCells(){
        String [][] auxMat= b.getCells();
        for(int i=0; i<b.getHeight(); i++){
            for(int j=0; j<b.getWidth(); j++){
                int counter=0;//contador de minas
                if(b.getCells()[i][j] != "*"){
                    try {
                        if(b.getCells()[i-1][j-1]=="*"){
                            counter++;auxMat[i][j]=counter+"";
                        };
                    } catch (Exception e) {}
                    try {
                        if(b.getCells()[i-1][j]=="*"){
                            counter++;auxMat[i][j]=counter+"";
                        };
                    } catch (Exception e) {}
                    try {
                        if(b.getCells()[i-1][j+1]=="*"){
                            counter++;auxMat[i][j]=counter+"";
                        };
                    } catch (Exception e) {}
                    try {
                        if(b.getCells()[i][j-1]=="*"){
                            counter++;auxMat[i][j]=counter+"";
                        };
                    } catch (Exception e) {}
                    try {
                        if(b.getCells()[i][j+1]=="*"){
                            counter++;auxMat[i][j]=counter+"";
                        };
                    } catch (Exception e) {}
                    try {
                        if(b.getCells()[i+1][j-1]=="*"){
                            counter++;auxMat[i][j]=counter+"";
                        };
                    } catch (Exception e) {}
                    try {
                        if (b.getCells()[i+1][j]=="*"){
                            counter++;auxMat[i][j]=counter+"";
                        };
                    } catch (Exception e) {}
                    try {
                        if(b.getCells()[i+1][j+1]=="*"){
                            counter++;auxMat[i][j]=counter+"";
                        };
                    } catch (Exception e) {}
                    if(counter==0){
                        auxMat[i][j]="-";
                    }
                }
            }
       }
    }
}

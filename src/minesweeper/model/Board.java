/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.model;

import javax.xml.bind.DatatypeConverter;


/**
 *
 * @author Personal
 */
public class Board {
    private int mines;
    private int height;
    private int width;
    private String[][] cells;

    public Board() {
    }
    
    public Board(int height, int width, int mines) {
        this.height = height;
        this.width = width;
        this.mines = mines;
        this.cells = new String[height][width];
    }

    public String[][] getCells() {
        return cells;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getMines() {
        return mines;
    }
    
    public void setCells(String[][] board) {
        this.cells = board;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }
    
}

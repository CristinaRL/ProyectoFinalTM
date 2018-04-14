/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

/**
 *
 * @author levanna
 */
public class Matriz {
    int[][] rmatrix;
    int[][] gmatrix;
    int[][] bmatrix;

    /**
     *
     * @param rmatrix
     * @param gmatrix
     * @param bmatrix
     */
    public Matriz(int[][] rmatrix, int[][] gmatrix, int[][] bmatrix){
        this.rmatrix = rmatrix;
        this.gmatrix = gmatrix;
        this.bmatrix = bmatrix;       
    }

    public int[][] getRmatrix() {
        return rmatrix;
    }

    public int[][] getGmatrix() {
        return gmatrix;
    }

    public int[][] getBmatrix() {
        return bmatrix;
    }
    
    
}

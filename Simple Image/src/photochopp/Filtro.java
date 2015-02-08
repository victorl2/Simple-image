package photochopp;
import java.awt.image.BufferedImage;

/**
 * @author Victor Ferreira Teixeira da Silva
 */
public class Filtro {
    private int lines;
    private int cols;
    protected double[][]vetor = new double[lines][cols];
    
    /**You can set the length of both dimentions of the matrix*/
    public Filtro(int lines,int cols){
        this.lines = lines;
        this.cols = cols;
        double[][]newVetor = new double[lines][cols];
        vetor = newVetor;
    }
    
    /**This will make the filter a square matrix*/
    public Filtro(int k){
        double[][]newVetor = new double[k][k];
        vetor = newVetor;
        this.lines = k;
        this.cols = k;
    }
    /**Return the length of lines*/
    public int getLines(){
        return this.lines;
    }
    /**Return the length of cols*/
    public int getCols(){
        return this.cols;
    }
    /**Return the length of lines - usefull if you have a square filter*/
    public int getSize(){
        return this.lines;
    }
    /**Set a value in the filter matrix*/
    public void setValue(int x,int y,double value){
        vetor[x][y] = value;
    }

    /**Check if the pixel is on the image */
    public boolean validPixel(int x,int y,BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
        if(x>=width || x<0 || y>=height || y<0 )
            return false;
        return true;
    }
    /**Check if a pixel is on the filter matrix*/
    public boolean validPixel(int x,int y){
        int width =  this.lines;
        int height = this.cols;
        if(x>=width || x<0 || y>=height || y<0 )
            return false;
        return true;
    }    
}
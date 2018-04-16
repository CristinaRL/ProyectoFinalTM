/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.awt.Color;
import java.awt.image.BufferedImage;
import static java.lang.Math.min;
import static java.lang.Math.max;
import java.util.Arrays;


/**
 *
 * @author levanna
 */
public class Filtros {
    
    /**
     *Clase que te permite aplicar filtros sobre una imagen
     */
    public Filtros(){
    }
    
    /**
     * Permite aplicar un filtro negativo a una imagen
     * @param img imagen
     * @return imagen con el filtro aplicado
     */
    public BufferedImage negative(BufferedImage img){
        Color color;
        int r,g,b;
        
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                
                color = new Color(img.getRGB(i, j));//Obtenemos el color del pixel
     
                r = 255-color.getRed();//Extraemos los valores RGB
                g = 255-color.getGreen();
                b = 255-color.getBlue();
               
                img.setRGB(i, j, new Color(r,g,b).getRGB());//Colocamos en el pixel imagen los valores invertidos                                                                    
          }
        }
        return img;        
    }
    
    /**
     * Permite binarizar una imagen con el valor lindar deseado
     * @param img imagen
     * @param threshold valor lindar del filtro
     * @return imagen con el filtro aplicado
     */
    public BufferedImage binarize(BufferedImage img, int threshold){
        Color color;
        int r,g,b;
        
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                
                color = new Color(img.getRGB(i, j)); //Obtenemos el color del pixel
               
                g = color.getGreen(); //Usamos solo el color de uno de los canales RGB, el verde da mejores resultados
                if(g>threshold){
                    g = 255;
                }else{
                    g = 0;
                }
                img.setRGB(i, j, new Color(g,g,g).getRGB()); //Guardamos el color binarizado en cada canal (el mismo)                                                                    
          }
        }
        return img;        
    }
    
    /**
     * Permite cambiar la tonalidad de la imagen a la deseada
     * @param img
     * @param hue
     * @return imagen con el filtro aplicado
     */
    public BufferedImage changeHue(BufferedImage img, float hue){
        Color color;
        int r,g,b;
        float[] hsb = new float[3];
        hue = hue/255;
        
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                
                color = new Color(img.getRGB(i, j));//Obtenemos el color del pixel

                r = color.getRed();//Extraemos los valores RGB
                g = color.getGreen();
                b = color.getBlue();
                Color.RGBtoHSB(r, g, b, hsb); //Guardamos en la lista hsb los valores RGB convertidos
                hsb[0] = hue;
                int rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
                color = new Color(rgb);
                img.setRGB(i, j, color.getRGB());                                                                    
          }
        }
        return img;     
    }
        
    /**
     * Permite cambiar la saturacion de la imagen
     * @param img
     * @param saturation
     * @return imagen con el filtro aplicado
     */
    public BufferedImage changeSaturation(BufferedImage img, float saturation){
        Color color;
        int r,g,b;
        float[] hsb = new float[3];
        saturation = saturation/255;
        
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){

                color = new Color(img.getRGB(i, j));//Obtenemos el color del pixel

                r = color.getRed();//Extraemos los valores RGB
                g = color.getGreen();
                b = color.getBlue();
                Color.RGBtoHSB(r, g, b, hsb); //Guardamos en la lista hsb los valores RGB convertidos
                hsb[1] = saturation;
                int rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
                color = new Color(rgb);
                img.setRGB(i, j, color.getRGB());                                                                    
          }
        }
        return img;     
    }
    
    /**
     * Permite cambiar el brillo de la imagen
     * @param img
     * @param brightness
     * @return imagen con el filtro aplicado
     */
    public BufferedImage changeBrightness(BufferedImage img, float brightness){
        Color color;
        int r,g,b;
        float[] hsb = new float[3];
        brightness = brightness/255;
        
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){

                color = new Color(img.getRGB(i, j));//Obtenemos el color del pixel

                r = color.getRed();//Extraemos los valores RGB
                g = color.getGreen();
                b = color.getBlue();
                Color.RGBtoHSB(r, g, b, hsb); //Guardamos en la lista hsb los valores RGB convertidos
                hsb[2] = brightness;

                int rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
                
                color = new Color(rgb);
                img.setRGB(i, j, color.getRGB());                                                                    
          }
        }
        return img;     
    }

    /**
     * Permite eliminar el ruido de la imagen
     * @param img imagen
     * @param m tamaño de la mascara
     * @return imagen con el filtro aplicado
     */
    public BufferedImage averaging(BufferedImage img, int m){
        int r,g,b;
        int mascara[][] = new int[m][m];
        for (int[] row: mascara)
            Arrays.fill(row, 1);
        
        Matriz matrix = this.convolucionar(img, mascara, 0);
        int rmatrix[][] = matrix.getRmatrix();
        int gmatrix[][] = matrix.getGmatrix();
        int bmatrix[][] = matrix.getBmatrix();
        
        BufferedImage img_aux = img;
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                r = rmatrix[i][j];//Extraemos los valores RGB
                g = gmatrix[i][j];
                b = bmatrix[i][j];

                img.setRGB(i, j, new Color(r,g,b).getRGB());   
            }
        }
        
        return img;
    }
    
    /**
     * Permite desenfocar la imagen
     * @param img imagen
     * @return imagen con el filtro aplicado
     */
    public BufferedImage gaussian(BufferedImage img){
        int r,g,b;
        int mascara[][] = {{1,2,1},{2,4,2},{1,2,1}};
        Matriz matrix = this.convolucionar(img, mascara, 0);
        int rmatrix[][] = matrix.getRmatrix();
        int gmatrix[][] = matrix.getGmatrix();
        int bmatrix[][] = matrix.getBmatrix();
        
        BufferedImage img_aux = img;
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                r = rmatrix[i][j];//Extraemos los valores RGB
                g = gmatrix[i][j];
                b = bmatrix[i][j];

                img.setRGB(i, j, new Color(r,g,b).getRGB());   
            }
        }
        
        return img;
    }
    
    /**
     * Permite detectar los bordes de la imagen en el eje de las X
     * @param img imagen
     * @return imagen con el filtro aplicado
     */
    public BufferedImage sobelX(BufferedImage img){
        int rgb;
        int m = 3;
        int mascara[][] = {{1, 2, 1}, {0, 0, 0}, {-1,-2,-1}};
        Matriz matrix = this.convolucionar(img, mascara, 0);
        int rmatrix[][] = matrix.getRmatrix();
        int gmatrix[][] = matrix.getGmatrix();
        int bmatrix[][] = matrix.getBmatrix();
        
        BufferedImage img_aux = img;
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                rgb = (rmatrix[i][j] + gmatrix[i][j] + bmatrix[i][j])/3;//Extraemos los valores RGB
                if(rgb>10){
                    rgb = 255;
                }else{
                    rgb = 0;
                }
                img.setRGB(i, j, new Color(rgb,rgb,rgb).getRGB());   
            }
        }
        return img;
    }
    
    /**
     * Permite detectar los bordes de la imagen tanto en el eje de las X como en el de las Y
     * @param img
     * @return imagen con el filtro aplicado
     */
    public BufferedImage laplaciana(BufferedImage img){
        int rgb;
        int m = 3;
        int mascara[][] = {{0,-1,0},{-1,4,-1},{0,-1,0}};
        Matriz matrix = this.convolucionar(img, mascara, 0);
        int rmatrix[][] = matrix.getRmatrix();
        int gmatrix[][] = matrix.getGmatrix();
        int bmatrix[][] = matrix.getBmatrix();
        
        BufferedImage img_aux = img;
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                rgb = (rmatrix[i][j] + gmatrix[i][j] + bmatrix[i][j])/3;//Extraemos los valores RGB
                if(rgb>10){
                    rgb = 255;
                }else{
                    rgb = 0;
                }
                img.setRGB(i, j, new Color(rgb,rgb,rgb).getRGB());   
            }
        }
        
        return img;
    }
    
    /**
     * Permite aplicar a la imagen un filtro que simula relieve
     * @param img imagen
     * @return imagen con el filtro aplicado
     */
    public BufferedImage emboss(BufferedImage img){
        int mascara[][] = {{-1,-1,0},{-1,0,1},{0,1,1}};
        int r,g,b;
        
        Matriz matrix = this.convolucionar(img, mascara, 128);
        int rmatrix[][] = matrix.getRmatrix();
        int gmatrix[][] = matrix.getGmatrix();
        int bmatrix[][] = matrix.getBmatrix();
        
        BufferedImage img_aux = img;
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                r = rmatrix[i][j];//Extraemos los valores RGB
                g = gmatrix[i][j];
                b = bmatrix[i][j];
                img.setRGB(i, j, new Color(r,g,b).getRGB());   
            }
        }
        
        return img;
    }
    
    /**
     * Permite aplicar a la imagen el efecto de bordes resplandecientes
     * @param img imagen
     * @return imagen con el filtro aplicado
     */
    public BufferedImage glowingEdges(BufferedImage img){
        int mascara[][] = {{-1,-1,0},{-1,0,1},{0,1,1}};
        int r,g,b;
        
        Matriz matrix = this.convolucionar(img, mascara, 0);
        int rmatrix[][] = matrix.getRmatrix();
        int gmatrix[][] = matrix.getGmatrix();
        int bmatrix[][] = matrix.getBmatrix();
        
        BufferedImage img_aux = img;
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                r = rmatrix[i][j];//Extraemos los valores RGB
                g = gmatrix[i][j];
                b = bmatrix[i][j];

                img.setRGB(i, j, new Color(r,g,b).getRGB());   
            }
        }
        
        return img;
    }
    
    /**
     * Permite convolucionar una imagen con la mascara indicada
     * @param img imagen
     * @param mascara mascara para convolucionar
     * @param bias valor para corregir el color en determinados filtros, como el emboss
     * @return matriz con la mascara aplicada (convolucion)
     */
    public Matriz convolucionar(BufferedImage img, int[][] mascara, int bias){
        Color color;
        int rgb;
        int m = mascara.length;
        int width = img.getWidth();
        int height = img.getHeight();
        int rmatrix[][] = new int[width][height];
        int gmatrix[][] = new int[width][height];
        int bmatrix[][] = new int[width][height];
        int r,g,b;
        BufferedImage img_aux = this.extenderImagen(img, m);
        System.out.println(img_aux.getWidth());
        int pesos = 0;
        for (int k=0; k<m; k++){
            for(int l=0; l<m; l++){
                pesos += mascara[k][l];
            }
        }
        if(pesos == 0){
            pesos = 1;
        }
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                r = 0;
                g = 0;
                b = 0;
                for (int k=0; k<m; k++){
                    for(int l=0; l<m; l++){
                        //se obtiene el color del pixel
                        color = new Color(img_aux.getRGB(i+k, j+l));
                        r += color.getRed()*mascara[k][l];
                        g += color.getGreen()*mascara[k][l];
                        b += color.getBlue()*mascara[k][l];
                    }
                }
                rmatrix[i][j]= min(max((int)(r/pesos) + bias, 0), 255);
                gmatrix[i][j]= min(max((int)(g/pesos) + bias, 0), 255);;
                bmatrix[i][j]= min(max((int)(b/pesos) + bias, 0), 255);;
            }
        }
        
        Matriz matrix = new Matriz(rmatrix, gmatrix, bmatrix);
       
        return matrix;
    }
    
    /**
     * Extiende la imagen pasada por parametro, con un marco del tamaño de la mascara
     * @param img imagen
     * @param m tamaño de la mascara
     * @return imagen extendida
     */
    public BufferedImage extenderImagen(BufferedImage img, int m){
        
        BufferedImage img_extendida = new BufferedImage(img.getWidth()+m, img.getHeight()+m, img.getType());

        for(int i=0;i<img_extendida.getWidth();i++){
            for(int j=0;j<img_extendida.getHeight();j++){
                if(i<img.getWidth() && j<img.getHeight()){
                    img_extendida.setRGB(i, j, img.getRGB(i, j));
                }else if(!(i<img.getWidth()) && !(j<img.getHeight())){
                    img_extendida.setRGB(i, j, img.getRGB(i-m, j-m));
                }else if(!(i<img.getWidth())){
                    img_extendida.setRGB(i, j, img.getRGB(i-m, j));
                }else{
                    img_extendida.setRGB(i, j, img.getRGB(i, j-m));
                }
            }
        }
        
        return img_extendida;
    }
}
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
    
    public Filtros(){
    }
    
    public BufferedImage negative(BufferedImage img){
        Color color;
        int r,g,b;
        
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                //se obtiene el color del pixel
                color = new Color(img.getRGB(i, j));
                //se extraen los valores RGB
                r = 255-color.getRed();
                g = 255-color.getGreen();
                b = 255-color.getBlue();
                //se coloca en la nueva imagen con los valores invertidos
                img.setRGB(i, j, new Color(r,g,b).getRGB());                                                                    
          }
        }
        return img;        
    }
    
//    public BufferedImage saturar(BufferedImage img, int threshold){
//        Color color;
//        int r,g,b;
//        
//        for(int i=0;i<img.getWidth();i++){
//            for(int j=0;j<img.getHeight();j++){
//                //se obtiene el color del pixel
//                color = new Color(img.getRGB(i, j));
//                //se extraen los valores RGB
//                r = color.getRed();
//                g = color.getGreen();
//                b = color.getBlue();
//                if(r>threshold){
//                    r = 255;
//                }else{
//                    r = 0;
//                }
//                if(g>threshold){
//                    g = 255;
//                }else{
//                    g = 0;
//                }
//                if(b>threshold){
//                    b = 255;
//                }else{
//                    b = 0;
//                }
//                //se coloca en la nueva imagen con los valores invertidos
//                img.setRGB(i, j, new Color(r,g,b).getRGB());                                                                    
//          }
//        }
//        return img;        
//    }
    
    public BufferedImage binarize(BufferedImage img, int threshold){
        Color color;
        int r,g,b;
        
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                //se obtiene el color del pixel
                color = new Color(img.getRGB(i, j));
                //usamos solo el color de uno de los canales RGB
                g = color.getGreen();
                if(g>threshold){
                    g = 255;
                }else{
                    g = 0;
                }
                //se coloca el color binarizado en cada canal (el mismo)
                img.setRGB(i, j, new Color(g,g,g).getRGB());                                                                    
          }
        }
        return img;        
    }
    
    public BufferedImage changeHue(BufferedImage img, float hue){
        Color color;
        int r,g,b;
        float[] hsb = new float[3];
        hue = hue/255;
        
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                //se obtiene el color del pixel
                color = new Color(img.getRGB(i, j));
                //se extraen los valores RGB
                r = color.getRed();
                g = color.getGreen();
                b = color.getBlue();
                Color.RGBtoHSB(r, g, b, hsb); //se guarda en la lista hsb los valores RGB convertidos
                hsb[0] = hue;
                int rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
                color = new Color(rgb);
                img.setRGB(i, j, color.getRGB());                                                                    
          }
        }
        return img;     
    }
        
    public BufferedImage changeSaturation(BufferedImage img, float saturation){
        Color color;
        int r,g,b;
        float[] hsb = new float[3];
        saturation = saturation/255;
        
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                //se obtiene el color del pixel
                color = new Color(img.getRGB(i, j));
                //se extraen los valores RGB
                r = color.getRed();
                g = color.getGreen();
                b = color.getBlue();
                Color.RGBtoHSB(r, g, b, hsb); //se guarda en la lista hsb los valores RGB convertidos
                hsb[1] = saturation;
                int rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
                color = new Color(rgb);
                img.setRGB(i, j, color.getRGB());                                                                    
          }
        }
        return img;     
    }
    
    public BufferedImage changeBrightness(BufferedImage img, float brightness){
        Color color;
        int r,g,b;
        float[] hsb = new float[3];
        brightness = brightness/255;
        
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                //se obtiene el color del pixel
                color = new Color(img.getRGB(i, j));
                //se extraen los valores RGB
                r = color.getRed();
                g = color.getGreen();
                b = color.getBlue();
                Color.RGBtoHSB(r, g, b, hsb); //se guarda en la lista hsb los valores RGB convertidos
                hsb[2] = brightness;

                int rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
                
                color = new Color(rgb);
                img.setRGB(i, j, color.getRGB());                                                                    
          }
        }
        return img;     
    }

    public BufferedImage averaging(BufferedImage img, int m){
        int r,g,b;
        int mascara[][] = new int[m][m];
        for (int[] row: mascara)
            Arrays.fill(row, 1);
        
        Matriz matrix = this.convolucionar(img, mascara);
        int rmatrix[][] = matrix.getRmatrix();
        int gmatrix[][] = matrix.getGmatrix();
        int bmatrix[][] = matrix.getBmatrix();
        
        BufferedImage img_aux = img;
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                r = rmatrix[i][j];
                g = gmatrix[i][j];
                b = bmatrix[i][j];

                img.setRGB(i, j, new Color(r,g,b).getRGB());   
            }
        }
        
        return img;
    }
    
    public BufferedImage gaussian(BufferedImage img){
        int r,g,b;
        int mascara[][] = {{1,2,1},{2,4,2},{1,2,1}};
        Matriz matrix = this.convolucionar(img, mascara);
        int rmatrix[][] = matrix.getRmatrix();
        int gmatrix[][] = matrix.getGmatrix();
        int bmatrix[][] = matrix.getBmatrix();
        
        BufferedImage img_aux = img;
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                r = rmatrix[i][j];
                g = gmatrix[i][j];
                b = bmatrix[i][j];

                img.setRGB(i, j, new Color(r,g,b).getRGB());   
            }
        }
        
        return img;
    }
    
    public BufferedImage sobelX(BufferedImage img){
        int rgb;
        int m = 3;
        int mascara[][] = {{1, 2, 1}, {0, 0, 0}, {-1,-2,-1}};
        Matriz matrix = this.convolucionar(img, mascara);
        int rmatrix[][] = matrix.getRmatrix();
        int gmatrix[][] = matrix.getGmatrix();
        int bmatrix[][] = matrix.getBmatrix();
        
        BufferedImage img_aux = img;
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                rgb = (rmatrix[i][j] + gmatrix[i][j] + bmatrix[i][j])/3;
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
    
    public BufferedImage laplaciana(BufferedImage img){
        int rgb;
        int m = 3;
        int mascara[][] = {{0,-1,0},{-1,4,-1},{0,-1,0}};
        Matriz matrix = this.convolucionar(img, mascara);
        int rmatrix[][] = matrix.getRmatrix();
        int gmatrix[][] = matrix.getGmatrix();
        int bmatrix[][] = matrix.getBmatrix();
        
        BufferedImage img_aux = img;
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                rgb = (rmatrix[i][j] + gmatrix[i][j] + bmatrix[i][j])/3;
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
                r = rmatrix[i][j];
                g = gmatrix[i][j];
                b = bmatrix[i][j];
                
                
                //System.out.println(r+","+g+","+b);
                img.setRGB(i, j, new Color(r,g,b).getRGB());   
            }
        }
        
        return img;
    }
    
    public BufferedImage glowingEdges(BufferedImage img){
        int mascara[][] = {{-1,-1,0},{-1,0,1},{0,1,1}};
        int r,g,b;
        
        Matriz matrix = this.convolucionar(img, mascara);
        int rmatrix[][] = matrix.getRmatrix();
        int gmatrix[][] = matrix.getGmatrix();
        int bmatrix[][] = matrix.getBmatrix();
        
        BufferedImage img_aux = img;
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                r = rmatrix[i][j];
                g = gmatrix[i][j];
                b = bmatrix[i][j];
                //System.out.println(r+","+g+","+b);
                img.setRGB(i, j, new Color(r,g,b).getRGB());   
            }
        }
        
        return img;
    }
    
    
    
    public Matriz convolucionar(BufferedImage img, int[][] mascara){
        Color color;
        int rgb;
        int m = mascara.length;
        int width = img.getWidth();
        int height = img.getHeight();
        int rmatrix[][] = new int[width][height];
        int gmatrix[][] = new int[width][height];
        int bmatrix[][] = new int[width][height];
        int r,g,b;
        BufferedImage img_aux = img;
        
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
                        if((i<img.getWidth()-m) && (j<img.getHeight()-m)){
                             color = new Color(img_aux.getRGB(i+k, j+l));
                        }
                        else if(!(i<img.getWidth()-m) && !(j<img.getHeight()-m)){
                            color = new Color(img_aux.getRGB(i-k, j-l));
                        }else if(!(i<img.getWidth()-m)){
                            color = new Color(img_aux.getRGB(i-k, j+l));
                        }else{
                            color = new Color(img_aux.getRGB(i+k, j-l));
                        }
                        
                        //se extraen los valores RGB
                        
                       r += color.getRed()*mascara[k][l];
                       g += color.getGreen()*mascara[k][l];
                       b += color.getBlue()*mascara[k][l];
                        
                    }
                }
                rmatrix[i][j]= min(max((int)(r/pesos), 0), 255);
                gmatrix[i][j]= min(max((int)(g/pesos), 0), 255);;
                bmatrix[i][j]= min(max((int)(b/pesos), 0), 255);;
            }
        }
        
        Matriz matrix = new Matriz(rmatrix, gmatrix, bmatrix);
       
        return matrix;
    }
    
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
        BufferedImage img_aux = img;
        
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
                        if((i<img.getWidth()-m) && (j<img.getHeight()-m)){
                             color = new Color(img_aux.getRGB(i+k, j+l));
                        }
                        else if(!(i<img.getWidth()-m) && !(j<img.getHeight()-m)){
                            color = new Color(img_aux.getRGB(i-k, j-l));
                        }else if(!(i<img.getWidth()-m)){
                            color = new Color(img_aux.getRGB(i-k, j+l));
                        }else{
                            color = new Color(img_aux.getRGB(i+k, j-l));
                        }
                        
                        //se extraen los valores RGB
                        
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
}
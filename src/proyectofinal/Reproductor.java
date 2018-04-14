/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author levanna
 */
public class Reproductor {
    public Reproductor(){
        
    }
    
    public BufferedImage cargarImagen(String ruta) throws IOException{
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(ruta));
        } catch (IOException e) {
            System.err.println(e);
        }
        return img;
    }
    
    public void guardarImagen(BufferedImage img) throws IOException{
        try {
            // retrieve image
            File outputfile = new File("new_lenna.png");
            ImageIO.write(img, "PNG", outputfile);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

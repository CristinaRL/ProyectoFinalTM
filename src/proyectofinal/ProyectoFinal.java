/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.awt.image.BufferedImage;
import java.io.IOException;



/**
 *
 * @author levanna
 */
public class ProyectoFinal {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException
	{
            Reproductor p = new Reproductor();
            BufferedImage img = null;
            Filtros filtros = new Filtros();
            try{
                img = p.cargarImagen("lenna.png");
            }
            catch(IOException e){
                 System.err.println(e);
            }
            
            //img = filtros.negative(img);
            //img = filtros.binarize(img,120);
            img = filtros.averaging(img, 6);
            //img = filtros.changeSaturation(img, 19);
            //img = filtros.changeBrightness(img, 255);
            //img = filtros.changeHue(img, 255);
            //img = filtros.laplaciana(img);
            //img = filtros.sobelX(img);
            //img = filtros.glowingEdges(img);
            //img = filtros.emboss(img);
            //img = filtros.gaussian(img);
            try{
                 p.guardarImagen(img);
            }
            catch(IOException e){
                 System.err.println(e);
            }
           
		
	}//FIN DEL MAIN
    
}

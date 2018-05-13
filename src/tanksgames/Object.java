/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
/**
 *
 * @author dmitr
 */
abstract public class Object{

    protected BufferedImage getImage(String namefile)
    {
        File file = new File(namefile);
        BufferedImage f = ImageIO.read(file);
        return f;
    }
    
    abstract BufferedImage PaintImage();
    
    Object()
    {
        
    }
}

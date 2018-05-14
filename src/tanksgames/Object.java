/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import javax.imageio.ImageIO;
/**
 *
 * @author dmitr
 */
abstract public class Object{

    protected BufferedImage getImage(String namefile)
    {
        try
        {
            return ImageIO.read(new File(namefile));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        return null;
    }
    
    abstract public BufferedImage PaintImage();
    
    Object()
    {
        
    }
}

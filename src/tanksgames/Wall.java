/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.ImageIcon;
/**
 *
 * @author dmitr
 */
public class Wall extends DestructibleObject{
    static String namefiles = "src/tanksgames/Img/Wall/1.png";
    
    public Wall()
    {
        super((new java.util.Random().nextInt(3))+1);
    }
    
    @Override
    BufferedImage PaintImage()
    {
        return super.getImage(namefiles);
    }
}

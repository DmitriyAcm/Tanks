/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import static tanksgames.Tank.namefiles;
import view.ColorObject;

/**
 *
 * @author dmitr
 */
public class Headquarters extends DestructibleObject {
    ColorObject _color;
    
    static String namefiles = "src/tanksgames/Img/Headquarters/1.png";
    
    public Headquarters(ColorObject col)
    {
        super(2);
        _color = col;
    }
    
    @Override
    public BufferedImage PaintImage()
    {
        BufferedImage curImage = super.getImage(namefiles);
        
        return ColorObject.PaintImage(curImage, _color);
    }
}

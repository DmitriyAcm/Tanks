/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;

import java.awt.image.BufferedImage;

/**
 *
 * @author dmitr
 */
public class Water extends NonDestructibleObject{
    static String namefiles = "src/tanksgames/Img/Water/1.png";
    
    @Override
    BufferedImage PaintImage()
    {
        return super.getImage(namefiles);
    }
    
    public Water()
    {
        super();
    }
}

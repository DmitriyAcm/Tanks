/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;

import javax.swing.ImageIcon;
import view.ColorObject;

/**
 *
 * @author dmitr
 */
public class Headquarters extends Object {
    ColorObject _color;
    
    public Headquarters(ColorObject col)
    {
        super(2);
        _color = col;
    }
    
    @Override
    public boolean AirBlocks()
    {
        return true;
    }
    
    @Override
    public boolean GroundBlocks()
    {
        return true;
    }
    
    @Override
    public void DamageObject()
    {
        super._PointsHealth--;
    }
    
    @Override
    public ImageIcon getImage()
    {
        return new ImageIcon("src/tanksgames/Img/Headquarters/2.png");
    }
}

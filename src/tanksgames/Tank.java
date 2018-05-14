/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import Coordination.*;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import view.ColorObject;

/**
 *
 * @author dmitr
 */
public class Tank extends DynamicObject{
    static String namefiles = "src/tanksgames/Img/Tank/1.png";
    
    private ColorObject _color;
    private int _cooldown = 0;
    
    public Tank(Direction dir, GameField field, ColorObject color)
    {
        super(dir,3,field);
        
        _color = color;
    }
    
    private boolean Recharge()
    {
        return _cooldown>0;
    }
    
    public boolean Rotate(Rotation rot)
    {
        _cooldown--;
        super._direct=super._direct.Rotate(rot);
        return true;
    }
    
    public boolean Move()
    {
        if(super.moveTo(super._direct))
        {
            _cooldown--;
            return true;
        }
        return false;
    }
    
    public boolean Fire(Bullet bull)
    {
        if(!Recharge())
        {
            bull.Fire();
            _cooldown=5;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public BufferedImage PaintImage()
    {
        return super.getImage(namefiles);
    }
}

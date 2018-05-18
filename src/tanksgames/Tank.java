/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import Coordination.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import static java.util.Collections.swap;
import javax.swing.ImageIcon;
import view.ColorObject;

/**
 *
 * @author dmitr
 */
public class Tank extends DynamicObject{
    static String namefiles = "src/tanksgames/Img/Tank/1.png";
    private final int COOLDOWN = 1;
    
    
    private ColorObject _color;
    public int _cooldown = 0;
    
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
            _field.FindCell(this).AddObject(bull);
            bull.Fire();
            _cooldown=COOLDOWN;
            return true;
        }
        else
        {
            _cooldown--;
            return false;
        }
    }
    
    @Override
    public BufferedImage PaintImage()
    {
        BufferedImage curImage = super.getImage(namefiles);
        
        return ColorObject.PaintImage(curImage, _color);
    }
}

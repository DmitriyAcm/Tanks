/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import Coordination.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import static java.util.Collections.swap;
import javax.swing.ImageIcon;
import view.ColorObject;

/**
 *
 * @author dmitr
 */
public class Tank extends DynamicObject{
    static String namefiles = folder + "Tank/1.png";
    private final int COOLDOWN = 2;
    
    public ColorObject _color;
    public int _cooldown = 0;
    
    private Player _curPlayer = null;
    
    public Tank(Direction dir, GameField field, ColorObject color, Player player)
    {
        super(dir,3,field);
        
        _color = color;
        _curPlayer=player;
    }
    
    public boolean Recharge()
    {
        return _cooldown>0;
    }
    
    public boolean Rotate(Rotation rot)
    {
        _cooldown--;
        super._direct=super._direct.Rotate(rot);
        ArrayList<Cell> list = new ArrayList<>();
        
        list.add(_field.FindCell(this));
        
        _field.InformAboutStep(list);
        
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
    public void DamageObject() {
        super.DamageObject(); //To change body of generated methods, choose Tools | Templates.
        _field.FindCell(this).DeleteObject(this);
        _curPlayer.SpawnTank();
    }
    
    @Override
    public BufferedImage PaintImage()
    {
        BufferedImage curImage = super.getImage(namefiles);
        
        return ColorObject.PaintImage(curImage, _color);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import Coordination.*;

/**
 *
 * @author dmitr
 */
public class Tank extends DynamicObject{
    
    private int _color;
    private int _cooldown = 0;
    
    public Tank(Direction dir, GameField field, int color)
    {
        super(dir,field);
        
        _color = color;
    }
    
    private boolean Recharge()
    {
        return _cooldown>0;
    }
    
    public void Rotate(Rotation rot)
    {
        super._direct=super._direct.Rotate(rot);
    }
    
    public boolean Move()
    {
        return super.moveTo(super._direct);
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
}

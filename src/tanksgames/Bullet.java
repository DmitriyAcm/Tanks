/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import java.util.ArrayList;
import Coordination.*;

/**
 *
 * @author dmitr
 */
public abstract class Bullet extends DynamicObject {
    protected int _LenghtFlight;
    private final int _Radius;
    
    Bullet(Direction dir, int lenFly, GameField field, int radius)
    {
        super(dir,field);
        _LenghtFlight = lenFly;
        _Radius = radius; 
    }
    
    public void Fire()
    {
        ArrayList<Vector> list = traectory();
        
        if(list.isEmpty())
        {
            throw new NullPointerException("Запуск снаряда по несуществующему пути");
        }
        
        for(Vector cur : list)
        {
            Direction dir1 = cur.next();
            while(dir1 != null)
            {
                if(!super.moveTo(dir1))
                {
                    break;
                }
                dir1 = cur.next();
            }
        }
        
        new ShockWave(_field.FindCell(this), _Radius);
    }
    
    public abstract ArrayList<Vector> traectory();
    
    @Override
    public boolean AirBlocks()
    {
        return true;
    }
    
    @Override
    public boolean GroundBlocks()
    {
        return false;
    }
    
    @Override
    public void DamageObject()
    {
    
    }
}

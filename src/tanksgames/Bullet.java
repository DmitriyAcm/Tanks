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
        super(dir,1,field);
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
        
        Cell position=null;
        
        for(Vector cur : list)
        {
            Direction dir1 = cur.next();
            while(dir1 != null)
            {
                super._direct=dir1;
                super.moveTo(dir1);
                Cell curCell = _field.FindCell(this);
                
                if(curCell._objects.size()>1 || (curCell._objects.size()>0) && !(curCell._objects.get(0) instanceof Water || curCell._objects.get(0) instanceof Bullet))
                {
                    position=_field.FindCell(this);
                    break;
                }
                dir1 = cur.next();
            }
        }
        
        if(position == null)
        {
            position = _field.FindCell(this);
        }
        
        new ShockWave(position, _Radius);
    }
    
    public abstract ArrayList<Vector> traectory();
}

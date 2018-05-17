/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import Coordination.Direction;
import Coordination.Rotation;
import java.util.ArrayList;
/**
 *
 * @author dmitr
 */
public class Cell {
    
    public ArrayList<Object> _objects = new ArrayList<Object>();
    
    private final Cell[] _NearbyCell = new Cell[Direction.NUMDIRECT];
    
    Cell()
    {
        
    }
    
    public void SetCell(Cell cell, Direction direct)
    {
        _NearbyCell[direct.direct()] = cell;
    }
    
    Cell nextCell(Direction dir)
    {
        return _NearbyCell[dir.direct()];
    }
    
    boolean CheckObject(Object obj)
    {
        return _objects.contains(obj);
    }
    
    public boolean AddObject(Object obj)
    {
        if(obj==null)
        {
            throw new NullPointerException("Object for add non valid");
        }
        
        if(obj instanceof Bullet || _objects.isEmpty())
        {
            _objects.add(obj);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean DeleteObject(Object obj)
    {
        if(obj==null)
        {
            throw new NullPointerException("Object for delete non valid");
        }

        return _objects.remove(obj);
    }
    
    public void DamageCell()
    {
        ArrayList<Object> curobjects = (ArrayList<Object>)_objects.clone();
        for(Object curObj : curobjects)
        {
            if(curObj instanceof DestructibleObject)
            {
                ((DestructibleObject)curObj).DamageObject();
                if(((DestructibleObject) curObj).Destroyed())
                {
                    DeleteObject(curObj);
                }
            }
        }
    }
}

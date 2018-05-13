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
    
    public ArrayList<Object> _objects;
    
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
    
    boolean AddObject(Object obj)
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
    
    boolean DeleteObject(Object obj)
    {
        if(obj==null)
        {
            throw new NullPointerException("Object for delete non valid");
        }

        return _objects.remove(obj);
    }
    
    void DamageCell()
    {
        for(Object curObj : _objects)
        {
            if(curObj instanceof DestructibleObject)
            {
                ((DestructibleObject)curObj).DamageObject();
            }
        }
    }
}

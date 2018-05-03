/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import Coordination.Direction;
/**
 *
 * @author dmitr
 */
public class Cell {
    
    Object _AirObj;
    Object _GroundObj;
    
    private final Cell[] _NearbyCell = new Cell[Direction.NUMDIRECT];
    
    Cell(Cell left, Cell up)
    {
        _NearbyCell[Direction.Left().direct()] = left;
        _NearbyCell[Direction.Up().direct()] = up;
    }
    
    void SetCell(Cell cell, Direction direct)
    {
        _NearbyCell[direct.direct()] = cell;
    }
    
    Cell nextCell(Direction dir)
    {
        return _NearbyCell[dir.direct()];
    }
    
    boolean CheckObject(Object obj)
    {
        return _AirObj == obj || _GroundObj == obj;
    }
    
    boolean AddObject(Object obj)
    {
        if(obj==null)
        {
            throw new NullPointerException("Object for add non valid");
        }
        
        if(obj.AirBlocks() && obj.GroundBlocks())
        {
            if(_AirObj == null && _GroundObj == null)
            {
                _AirObj = obj;
                _GroundObj = obj;
                return true;
            }
        }
        else if(obj.AirBlocks())
        {
            if(_AirObj == null)
            {
                _AirObj = obj;
                return true;
            }
        }
        else if(obj.GroundBlocks())
        {
            if(_GroundObj == null)
            {
                _GroundObj = obj;
                return true;
            }
        }
        return false;
    }
    
    boolean DeleteObject(Object obj)
    {
        if(obj==null)
        {
            throw new NullPointerException("Object for delete non valid");
        }
        
        if(_AirObj == obj)
        {
            _AirObj = null;
            return true;
        }
        
        if(_GroundObj == obj)
        {
            _GroundObj = null;
            return true;
        }
        
        return false;
    }
    
    void DamageCell()
    {
        _AirObj.DamageObject();
        _GroundObj.DamageObject();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import Coordination.Direction;
import Coordination.Rotation;
/**
 *
 * @author dmitr
 */
public class Cell {
    
    Object _AirObj;
    Object _GroundObj;
    
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
        
        boolean isDelete = false;
        
        if(_AirObj == obj)
        {
            _AirObj = null;
            isDelete = true;
        }
        
        if(_GroundObj == obj)
        {
            _GroundObj = null;
            isDelete = true;
        }
        
        return isDelete;
    }
    
    void DamageCell()
    {
        _AirObj.DamageObject();
        _GroundObj.DamageObject();
        
        if(_AirObj.Destroyed())
        {
            _AirObj=null;
        }
        if(_GroundObj.Destroyed())
        {
            _GroundObj=null;
        }
    }
}

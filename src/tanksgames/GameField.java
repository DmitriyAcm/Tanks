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
public class GameField {
    int _width;
    int _height;
    
    Cell _LeftUpCell;
    
    // TODO -- тесты
    public GameField(int X, int Y)
    {
        _width = X;
        _height = Y;
        
        _LeftUpCell = new Cell(null, null);
        Cell prevCell = _LeftUpCell;
        for(int i=1;i<_width;++i)
        {
            Cell newCell = new Cell(prevCell,null);
            prevCell.SetCell(newCell, Direction.Right());
            prevCell = newCell;
        }
        
        Cell UpCell = _LeftUpCell;
        for(int i=1;i<_height;++i)
        {
            Cell curLeftCell = new Cell(null,UpCell);
            Cell curUpCell = UpCell.nextCell(Direction.Right());
            
            for(int j=1;j<_width;++j)
            {
                Cell curCell = new Cell(curLeftCell, curUpCell);
                
                curUpCell.SetCell(curCell, Direction.Down());
                curLeftCell.SetCell(curCell, Direction.Right());
                
                curUpCell=curUpCell.nextCell(Direction.Right());
                curLeftCell=curLeftCell.nextCell(Direction.Right());
            }
            
            UpCell = UpCell.nextCell(Direction.Down());
        }
    }
    
    public Cell FindCell(Object obj)
    {
        Cell curLeftRightCell = _LeftUpCell;
        
        while(curLeftRightCell != null)
        {
            Cell curCell = curLeftRightCell;
            while(curCell != null)
            {
                if(curCell.CheckObject(obj))
                {
                    return curCell;
                }
                curCell = curCell.nextCell(Direction.Right());
            }
            curLeftRightCell = curLeftRightCell.nextCell(Direction.Down());
        }
        
        
        return null;
    }
    
    public boolean moveObjectTo(Object obj, Direction dir)
    {
        Cell curCell = FindCell(obj);
        if(curCell.CheckObject(obj) && curCell.nextCell(dir).AddObject(obj))
        {
            curCell.DeleteObject(obj);
            return true;
        }
        
        return false;
    }
    
    public Cell GetCell(int X, int Y)
    {
        if(X>=_width || Y>=_height)
        {
            return null;
        }
        
        Cell curCell = _LeftUpCell;
        while(X-->0)
        {
            curCell = curCell.nextCell(Direction.Right());
        }
        
        while(Y-->0)
        {
            curCell = curCell.nextCell(Direction.Down());
        }
        
        return curCell;
    }
}

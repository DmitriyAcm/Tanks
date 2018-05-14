/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;

import Coordination.Coordinate;
import Coordination.Direction;
import Coordination.Rotation;


/**
 *
 * @author dmitr
 */
public class GameField {
    private final int _width;
    private final int _height;
    
    private final Cell[][] _field;
    
    public GameField(int X, int Y)
    {
        _width = X;
        _height = Y;
        
        _field = new Cell[_height][_width];
        
        for(int i=0;i<_height;++i)
        {
            for(int j=0;j<_width;++j)
            {
                _field[i][j]=new Cell();
            }
        }
        for(int i=0;i<_height;++i)
        {
            for(int j=0;j<_width;++j)
            {
                int cnt = 0;
                Direction curDir = Direction.Up();
                do
                {
                    int nextDirX = j+Direction.x[cnt];
                    int nextDirY = i+Direction.y[cnt];
                    if(nextDirX >= 0 && nextDirX <_width && nextDirY >= 0 && nextDirY < _height)
                    {
                        _field[i][j].SetCell(_field[nextDirY][nextDirX], curDir);
                    }
                    curDir = curDir.Rotate(Rotation.Right());
                    ++cnt;
                }
                while(curDir.direct()!=Direction.Up().direct());
            }
        }
    }
    
    public int width()
    {
        return _width;
    }
    
    public int height()
    {
        return _height;
    }
    
    public Cell FindCell(Object obj)
    {      
        for(Cell[] i : _field)
        {
            Cell curCell = i[0];
            while(curCell != null)
            {
                if(curCell.CheckObject(obj))
                {
                    return curCell;
                }
                curCell = curCell.nextCell(Direction.Right());
            }
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
    
    public Cell GetCell(Coordinate coord)
    {
        if(coord.getX()>=_width || coord.getY()>=_height)
        {
            return null;
        }

        return _field[coord.getY()][coord.getX()];
    }
    
    public Coordinate FindCoord(Cell cell) 
    {
        for(int i=0;i<_height;++i)
        {
            for(int j=0;j<_width;++j)
            {
                if(cell == _field[i][j])
                {
                    return new Coordinate(j,i);
                }
            }  
        }
        return null;
    }
}

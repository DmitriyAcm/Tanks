/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import Coordination.Direction;
import Coordination.Rotation;
import view.Color;

/**
 *
 * @author dmitr
 */
public class Player {
    private final Color _color;
    private final Tank _tank;
    private final Headquarters _head;
    
    Player(Color color, GameField field)
    {
        _color = color;
        _head = new Headquarters(_color);
        _tank = new Tank(Direction.Down(),field,_color);
        
    }
    
    boolean addHead(Cell pos)
    {
        if(pos.AddObject(_head))
        {
            Direction curDir = Direction.Up();
            do
            {
                if(pos.nextCell(curDir).AddObject(_tank))
                {
                    break;
                }
                curDir = curDir.Rotate(Rotation.Right());
            }
            while(curDir.direct()!=Direction.Up().direct());
            return true;
        }
        
        return false;
    }
}

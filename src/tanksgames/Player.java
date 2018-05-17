/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import Coordination.Direction;
import Coordination.Rotation;
import view.ColorObject;

/**
 *
 * @author dmitr
 */
public class Player {
    private ColorObject _color;
    private final Tank _tank;
    private final Headquarters _head;
    public int _numStep = 0;
    private GameModel _model;
    
    public Tank tank()
    {
        return _tank;
    }
    
    public Headquarters head()
    {
        return _head;
    }
    
    public GameModel model()
    {
        return _model;
    }
    
    Player(GameField field, Cell pos, ColorObject color, GameModel model)
    {
        _color = color;
        _head = new Headquarters(_color);
        _tank = new Tank(Direction.Down(),field,_color);
           
        if(pos.AddObject(_head))
        {
            Direction curDir = Direction.Up();
            do
            {
                if(pos.nextCell(curDir)!=null && pos.nextCell(curDir).AddObject(_tank))
                {
                    break;
                }
                curDir = curDir.Rotate(Rotation.Right());
            }
            while(curDir.direct()!=Direction.Up().direct());
        }
        
        _model = model;
    }
    
    public boolean DecrementStep()
    {
        if(_numStep<=0)
        {
            return false;
        }
        
        _numStep--;
        if(_numStep<=0)
        {
            _model.ChangePlayer();
        }
        model().InformAboutStep();
        return true;
    }
}

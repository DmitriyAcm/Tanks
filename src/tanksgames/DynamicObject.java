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
abstract public class DynamicObject extends DestructibleObject{
    public Direction _direct;
    
    GameField _field;
    
    DynamicObject(Direction dir, int PointHealth, GameField field)
    {
        super(PointHealth);
        _direct = dir;
        _field = field;
    }
    
    boolean moveTo(Direction dir)
    {
        _direct = dir;
        return _field.moveObjectTo(this, dir);
    }
}

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
abstract public class DynamicObject extends Object{
    private Direction _direct;
    
    DynamicObject(Direction dir)
    {
        super();
        _direct = dir;
    }
    
    DynamicObject(Direction dir, int PointHealth)
    {
        super(PointHealth);
        _direct = dir;
    }
    
    boolean moveTo(Direction dir)
    {
        _direct = dir;
        
        ///////// --- Обработка через поле
        // Запросить клетку где находится объект
        // Удалить объект из клетки
        // Переместить объект на клетку
        
        return true;
    }
}

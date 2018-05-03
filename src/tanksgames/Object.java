/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;

/**
 *
 * @author dmitr
 */
abstract public class Object {
    protected int _PointsHealth;
    
    Object()
    {
        _PointsHealth = (int)1e9;
    }
    
    Object(int PointHealth)
    {
        _PointsHealth = PointHealth;
    }
    
    boolean Destroyed()
    {
        return _PointsHealth <= 0;
    }
    
    abstract void DamageObject();
    abstract boolean AirBlocks();
    abstract boolean GroundBlocks();      
}

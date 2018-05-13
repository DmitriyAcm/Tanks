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
abstract public class DestructibleObject extends Object {
    protected int _PointsHealth;
      
    DestructibleObject(int PointHealth)
    {
        super();
        _PointsHealth = PointHealth;
    }
    
    boolean Destroyed()
    {
        return _PointsHealth <= 0;
    }
    
    public void DamageObject()
    {
        _PointsHealth--;
    }
}

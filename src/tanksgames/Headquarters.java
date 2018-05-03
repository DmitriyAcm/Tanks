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
public class Headquarters extends Object {
    int _color;
    
    public Headquarters(int col)
    {
        _color = col;
    }
    
    @Override
    public boolean AirBlocks()
    {
        return false;
    }
    
    @Override
    public boolean GroundBlocks()
    {
        return true;
    }
    
    @Override
    public void DamageObject()
    {
        super._PointsHealth--;
    }
}

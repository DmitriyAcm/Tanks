/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;

import view.Color;

/**
 *
 * @author dmitr
 */
public class Headquarters extends Object {
    Color _color;
    
    public Headquarters(Color col)
    {
        super(2);
        _color = col;
    }
    
    @Override
    public boolean AirBlocks()
    {
        return true;
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

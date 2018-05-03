/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import java.util.Random;
/**
 *
 * @author dmitr
 */
public class Wall extends Object{
    public Wall()
    {
        super((new java.util.Random().nextInt(3))+1);
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

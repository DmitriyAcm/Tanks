/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import java.util.ArrayList;
import Coordination.*;

/**
 *
 * @author dmitr
 */
public abstract class Bullet extends DynamicObject {
    protected int _LenghtFlight;
    
    Bullet(Direction dir, int lenFly, GameField field, int radius)
    {
        super(dir,field);
        _LenghtFlight = lenFly;
        
        ArrayList<Vector> list = traectory();
        
        for(Vector cur : list)
        {
            Direction dir1 = cur.next();
            while(dir1 != null)
            {
                super.moveTo(dir1);
                dir1 = cur.next();
            }
        }
        
        new ShockWave(field.FindCell(this), radius);
    }
    
    public abstract ArrayList<Vector> traectory();
    
    @Override
    public boolean AirBlocks()
    {
        return true;
    }
    
    @Override
    public boolean GroundBlocks()
    {
        return false;
    }
    
    @Override
    public void DamageObject()
    {
    
    }
}

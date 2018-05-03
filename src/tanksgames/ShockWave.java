/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import java.util.TreeMap;
import java.util.LinkedList;
import Coordination.*;

/**
 *
 * @author dmitr
 */
public class ShockWave {
    
    //TODO тесты 
    ShockWave(Cell pos, int Radius)
    {
        LinkedList<Cell> q = new LinkedList<Cell>();
        
        TreeMap mp = new TreeMap<Cell,String>();
        
        q.add(pos);
        mp.put(pos,"1");
        pos.DamageCell();
        
        
        while(!q.isEmpty())
        {
            Cell curCell = q.pop();
            int curDist = (int)mp.get(curCell);
            
            if(curDist>=Radius)
            {
                continue;
            }
            
            Direction curDir = Direction.Up();
            do
            {
                Cell newCell = curCell.nextCell(curDir);
                
                if(mp.get(newCell)==null)
                {
                    q.add(newCell);
                    mp.put(newCell, curDist+1);
                    newCell.DamageCell();
                }
                
                curDir = curDir.Rotate(Rotation.Right());
            }
            while(curDir.direct()!=Direction.Up().direct());
        }
    }
    
}

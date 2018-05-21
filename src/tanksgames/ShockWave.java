/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import java.util.TreeMap;
import java.util.LinkedList;
import Coordination.*;
import Listeners.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author dmitr
 */
public class ShockWave {
    
    ArrayList<ArrayList<Cell>> waves = new ArrayList<ArrayList<Cell>>();
    ////////////////////////////////////////////

    //TODO тесты 
    ShockWave(Cell pos, int Radius)
    {
        LinkedList<Cell> q = new LinkedList<Cell>();
        
        HashMap mp = new HashMap<Cell,String>();
        
        q.add(pos);
        mp.put(pos,"1");
        pos.DamageCell();
        
        ArrayList<Cell> curFld = new ArrayList<Cell>();
        for(Cell cl : q)
        {
            curFld.add(cl);
        }
        waves.add(curFld);
        curFld = new ArrayList<Cell>();
        while(!q.isEmpty())
        {
            Cell curCell = q.pop();
            int curDist = Integer.parseInt((String)mp.get(curCell));
            
            if(curDist>=Radius)
            {
                continue;
            }
            
            Direction curDir = Direction.Up();
            do
            {
                Cell newCell = curCell.nextCell(curDir);
                
                if(newCell != null && mp.get(newCell)==null)
                {
                    q.add(newCell);
                    mp.put(newCell, Integer.toString(curDist+1));
                    newCell.DamageCell();
                }
                curDir = curDir.Rotate(Rotation.Right());
            }
            while(curDir.direct()!=Direction.Up().direct());
            
            String s1 = (String)mp.get(q.getFirst());
            String s2 = Integer.toString(curDist+1);
            if(s1.equals(s2))
            {
                for(Cell cl : q)
                {
                    curFld.add(cl);
                }
                waves.add(curFld);
                curFld = new ArrayList<Cell>();
            }
        }
    }
    
    ArrayList<Cell> GetFrontWave()
    {
        if(waves.isEmpty())
        {
            return null;
        }
        
        ArrayList<Cell> cur = waves.get(0);
        
        waves.remove(0);
        
        return cur;
    }
}

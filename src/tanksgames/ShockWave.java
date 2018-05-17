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
    
    static private ArrayList<ShockWaveListener> _listeners = new ArrayList<ShockWaveListener>();
    
    // -- обработка слушателей
    public static void AddListener(ShockWaveListener list)
    {
        _listeners.add(list);
    }
    
    public static void RemoveListener(ShockWaveListener list)
    {
        _listeners.remove(list);
    }
    
    private void InformListener(ArrayList<Cell> obl)
    {
        ShockWaveEvent event = new ShockWaveEvent(this,obl);
        for(ShockWaveListener i : _listeners)
        {
            i.ExplosiveBullet(event);
        }
    }
    
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
        InformListener(curFld);
        curFld.clear();
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
                InformListener(curFld);
                curFld.clear();
            }
        }
        
        
    }
    
}

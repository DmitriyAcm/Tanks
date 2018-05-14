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
        
        TreeMap mp = new TreeMap<Cell,String>();
        
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
                
                if(mp.get(newCell)==null)
                {
                    q.add(newCell);
                    mp.put(newCell, Integer.toString(curDist+1));
                    newCell.DamageCell();
                    if(mp.get(q.getFirst())==Integer.toString(curDist+1))
                    {
                        for(Cell cl : q)
                        {
                            curFld.add(cl);
                        }
                        InformListener(curFld);
                        curFld.clear();
                    }
                }
                curDir = curDir.Rotate(Rotation.Right());
            }
            while(curDir.direct()!=Direction.Up().direct());
        }
        
        
    }
    
}

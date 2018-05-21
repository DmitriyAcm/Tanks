/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coordination;

import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author dmitr
 */
public class Track {
    
    private ArrayList<Vector> _track = new ArrayList<Vector>();
   
    public Track()
    {
        
    }
    
    public void AddVector(Direction dir)
    {
        if(!_track.isEmpty())
        {
            Vector cur = _track.get(_track.size()-1);
            ArrayList<Vector> newVect = cur.AddVector(new Vector(dir,1));
            
            _track.remove(cur);
            for(Vector vec : newVect)
            {
                _track.add(vec);
            }
        }
        else
        {
            _track.add(new Vector(dir,1));
        }
    }
    
    public Direction GetVector()
    {
        if(_track.isEmpty())
        {
            return null;
        }
        Direction cur = _track.get(0).next();
        
        while(cur==null)
        {
            _track.remove(0);
            if(_track.isEmpty())
            {
                return null;
            }
            cur = _track.get(0).next();
        }
        return cur;
    }
    
    public void Reverse()
    {
        Collections.reverse(_track);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;

import Coordination.Direction;
import Coordination.Rotation;
import Coordination.Vector;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import javax.swing.ImageIcon;

/**
 *
 * @author dmitr
 */
public class RuledBullet extends Bullet{
    Cell _base,_to;
    static String namefiles = "src/tanksgames/Img/RuledBullet/1.png";
    
    public RuledBullet(Direction dir, int Lenght,GameField field, Cell to)
    {
        super(dir,Lenght,field,3);
        _to = to;
    }
    
    @Override
    public ArrayList<Vector> traectory()
    {
        _base = _field.FindCell(this);
        
        ArrayList<Vector> list = new ArrayList<Vector>();
        
        LinkedList<Cell> q = new LinkedList<Cell>();
        
        HashMap<Cell,String> mp = new HashMap<Cell,String>();
        
        Cell pos = _base;
        
        q.add(pos);
        mp.put(pos,"1");
        
        boolean isFound = false; 
        
        while(!q.isEmpty() && !isFound)
        {
            Cell curCell = q.pop();
            int curDist = Integer.parseInt((String)mp.get(curCell));
            
            if(curDist>=super._LenghtFlight)
            {
                continue;
            }
            
            Direction curDir = Direction.Up();
            do
            {
                Cell newCell = curCell.nextCell(curDir);
                
                if(newCell == _to || newCell != null && (newCell._objects.isEmpty() || !(newCell._objects.get(0) instanceof Water)) && mp.get(newCell)==null)
                {
                    q.add(newCell);
                    mp.put(newCell, Integer.toString(curDist+1));
                }
                
                if(newCell == _to)
                {
                    isFound=true;
                    break;
                }
                
                curDir = curDir.Rotate(Rotation.Right());
            }
            while(curDir.direct()!=Direction.Up().direct());
        }
        
        Cell curCell = _to;
        
        if(isFound)
        {
            while(curCell != _base)
            {
                Direction curDir = Direction.Up();
                do
                {
                    Cell newCell = curCell.nextCell(curDir);

                    String curDist = (String)mp.get(newCell);

                    if(curDist!= null && Integer.parseInt(curDist)+1 == Integer.parseInt((String)mp.get(curCell)))
                    {       
                        list.add(new Vector(curDir.Rotate(Rotation.Right()).Rotate(Rotation.Right()),1));
                        curCell = newCell;
                        break;
                    }

                    curDir = curDir.Rotate(Rotation.Right());
                }
                while(curDir.direct()!=Direction.Up().direct());
            }
        }
        
        return list;
    }
    
    @Override
    public BufferedImage PaintImage()
    {
        return super.getImage(namefiles);
    }
}

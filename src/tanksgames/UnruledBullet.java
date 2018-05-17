/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;

import Coordination.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author dmitr
 */
public class UnruledBullet extends Bullet{
    static String namefiles = "src/tanksgames/Img/UnruledBullet/1.png";
    
    public UnruledBullet(Direction dir, GameField field)
    {
        super(dir,(int)1e9,field,1);
    }
    
    @Override
    public ArrayList<Vector> traectory()
    {
        ArrayList<Vector> list = new ArrayList<Vector>();
        
        Cell curCell = _field.FindCell(this).nextCell(_direct);
        
        while(curCell!=null && !((curCell._objects.size()>1 || (curCell._objects.size()>0) && !(curCell._objects.get(0) instanceof Water || curCell._objects.get(0) instanceof Bullet))))
        {
            list.add(new Vector(_direct,1));
            curCell = curCell.nextCell(_direct);
        }
       
        if(curCell!=null)
        {
            list.add(new Vector(_direct,1));
        }
        
        return list;
    }
    
    @Override
    public BufferedImage PaintImage()
    {
        return super.getImage(namefiles);
    }
}

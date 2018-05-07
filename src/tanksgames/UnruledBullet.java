/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;

import Coordination.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author dmitr
 */
public class UnruledBullet extends Bullet{
    
    UnruledBullet(Direction dir, GameField field)
    {
        super(dir,(int)1e9,field,1);
    }
    
    @Override
    public ArrayList<Vector> traectory()
    {
        ArrayList<Vector> list = new ArrayList<Vector>();
        
        list.add(new Vector(_direct));
        
        return list;
    }
    
    @Override
    public ImageIcon getImage()
    {
        return new ImageIcon("src/tanksgames/Img/UnruledBullet/2.png");
    }
}

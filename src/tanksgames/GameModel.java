/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import view.ColorObject;
import java.util.Random;
import Coordination.Coordinate;
import Listeners.FireRuledBulletEvent;
import Listeners.FireRuledBulletListener;
import java.util.ArrayList;


/**
 *
 * @author dmitr
 */
public class GameModel {
    private final int _begCountStep;
    public final int _distRuledBullet;
    private final GameField _field;
    
    public final Player[] _players = new Player[2];
    
    private int curPlayer=1;
    
    public void ChangePlayer()
    {
        curPlayer()._numStep=0;
        curPlayer^=1;
        curPlayer()._numStep=_begCountStep;
        
    }
    
    public void PassStep()
    {
        curPlayer().tank()._cooldown--;
        ChangePlayer();
    }
    
    public Player curPlayer()
    {
        return _players[curPlayer];
    }
    
    public GameModel(int cntStep, int dist, int height, int width, ColorObject frst, ColorObject scd, int fpos, int spos)
    {
        _begCountStep = cntStep;
        _distRuledBullet = dist;
        
        _field = new GameField(height,width);
        
        int f1 = ((fpos>>1)&1);
        int f2 = ((fpos)&1);

        int s1 = ((spos>>1)&1);
        int s2 = ((spos)&1);
        
        _players[0]=new Player(_field,_field.GetCell(new Coordinate(f2*(_field.width()-1), f1*(_field.height()-1))),frst, this);
        _players[1]=new Player(_field,_field.GetCell(new Coordinate(s2*(_field.width()-1), s1*(_field.height()-1))),scd, this);
                     
       GenerateObject();
    }
    
    private void GenerateObject()
    {
        Random random = new java.util.Random();
        
    }
    
    public GameField field()
    {
        return _field;
    }
    
    // -- обработка слушателей
    
    private ArrayList<FireRuledBulletListener> _listeners = new ArrayList<>();
    
    public void AddListener(FireRuledBulletListener list)
    {
        _listeners.add(list);
    }
    
    public void RemoveListener(FireRuledBulletListener list)
    {
        _listeners.remove(list);
    }
    
    public void InformAboutStep()
    {
        FireRuledBulletEvent event = new FireRuledBulletEvent(this);
        for(FireRuledBulletListener i : _listeners)
        {
            i.RepaintField(event);
        }
    }
}

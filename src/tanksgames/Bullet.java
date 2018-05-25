/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import java.util.ArrayList;
import Coordination.*;
import Listeners.FireRuledBulletEvent;
import Listeners.FireRuledBulletListener;
import Listeners.ShockWaveEvent;
import Listeners.ShockWaveListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import view.GamePanel;

/**
 *
 * @author dmitr
 */
public abstract class Bullet extends DynamicObject {
    protected int _LenghtFlight;
    private final int _Radius;
    static public GamePanel _panel;
    
    
    Bullet(Direction dir, int lenFly, GameField field, int radius)
    {
        super(dir,1,field);
        _LenghtFlight = lenFly;
        _Radius = radius; 
    }
    
    private Timer timer = null;
    Cell position;
    ShockWave shock= null;
    
    public void Fire()
    {
        Track track = traectory();
        _panel.BulletFly=1;
        /*if(track.isEmpty())
        {
            throw new NullPointerException("Запуск снаряда по несуществующему пути");
        }*/
        
        position=_field.FindCell(this);
        System.out.println("Paint move");
            
        timer = new Timer(200, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent f)
            {
                
                Direction dir1 = track.GetVector();
                if(dir1 != null)
                {
                    _direct=dir1;
                    moveTo(dir1);
                    position = position.nextCell(dir1);
                    return;
                }
                else if(shock==null)
                {
                    shock = new ShockWave(position, _Radius);
                    return;
                }
                
                ArrayList<Cell> wave = shock.GetFrontWave();
                
                if(wave!=null)
                {
                    InformAboutFire(wave);
                }
                else
                {
                    InformAboutPaint();
                    _panel.BulletFly=0;
                    timer.stop();
                }
            }
        });
        timer.start();
    }
    
    public abstract Track traectory();
    
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
    
    private void InformAboutFire(ArrayList<Cell> obl)
    {
        ShockWaveEvent event = new ShockWaveEvent(this,obl);
        for(ShockWaveListener i : _listeners)
        {
            i.ExplosiveBullet(event);
        }
    }
    
    //
    
    static private ArrayList<FireRuledBulletListener> _listenersPainter = new ArrayList<>();
    
    public static void AddListener(FireRuledBulletListener list)
    {
        _listenersPainter.add(list);
    }
    
    public static void RemoveListener(FireRuledBulletListener list)
    {
        _listenersPainter.remove(list);
    }
    
    public void InformAboutPaint()
    {
        FireRuledBulletEvent event = new FireRuledBulletEvent(this);
        for(FireRuledBulletListener i : _listenersPainter)
        {
            i.RepaintField(event);
        }
    }
}

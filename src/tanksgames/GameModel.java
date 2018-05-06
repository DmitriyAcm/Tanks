/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import view.Color;

/**
 *
 * @author dmitr
 */
public class GameModel {
    private final int _begCountStep;
    private final int _distRuledBullet;
    public final GameField _field;
    
    public final Player[] _players = new Player[2];
    
    private int curPlayer=0;
    
    void changePlayer()
    {
        curPlayer^=1;
    }
    
    Player curPlayer()
    {
        return _players[curPlayer];
    }
    
    GameModel(int cntStep, int dist, int height, int width, Color frst, Color scd)
    {
        _begCountStep = cntStep;
        _distRuledBullet = dist;
        
        _field = new GameField(height,width);
        
        _players[0]=new Player(frst,_field);
        _players[1]=new Player(scd,_field);
                     
       GenerateObject();         
    }
    
    private void GenerateObject()
    {
        
    }
    
    private class GameOverListerner implements
    {
        
    }
}

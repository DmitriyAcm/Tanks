/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import Coordination.Color;

/**
 *
 * @author dmitr
 */
public class Player {
    int _PointStep;
    Color _color;
    Tank tank;
    
    
    Player(Color color, int hp)
    {
        _color = color;
        
    }
    
    void NextStep()
    {
        _PointStep=0;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgames;
import wiew.Color;

/**
 *
 * @author dmitr
 */
public class Player {
    public int _PointStep;
    private Color _color;
    public Tank _tank;
    public Headquarters _head;
    
    Player(Color color, int hp)
    {
        _color = color;
        
    }
    
    void NextStep()
    {
        _PointStep=0;
    }
}

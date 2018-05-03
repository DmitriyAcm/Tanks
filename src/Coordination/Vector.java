/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coordination;

/**
 *
 * @author dmitr
 */
public class Vector {
    private Direction _dir;
    private int _lenght;
    
    public Vector(Direction dir, int len)
    {
        _dir = dir;
        _lenght = len;
    }
    
    public Vector(Direction dir)
    {
        _dir = dir;
        _lenght = (int)1e9;
    }
    
    Direction next()
    {
        if(_lenght>0)
        {
            _lenght--;
            return _dir;
        }
        else
        {
            return null;
        }
    }
}

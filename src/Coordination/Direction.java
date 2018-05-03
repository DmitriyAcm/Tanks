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
public class Direction {
    
    public static final int NUMDIRECT = 4;
    
    private int _dir;
    
    private Direction(int curDir)
    {
        _dir = curDir;
    }
    
    public static Direction Up()
    {
        return new Direction(0);
    }
    
    public static Direction Right()
    {
        return new Direction(1);
    }
    
    public static Direction Down()
    {
        return new Direction(2);
    }
    
    public static Direction Left()
    {
        return new Direction(3);
    }
    
    public Direction Rotate(Rotation rot)
    {
        return new Direction((_dir+rot.DirRotate()+NUMDIRECT)%NUMDIRECT);
    }
    
    public int direct()
    {
        return _dir;
    }
}

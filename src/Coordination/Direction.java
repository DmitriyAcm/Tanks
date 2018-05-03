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
    
    int dir;
    
    private Direction(int curDir)
    {
        dir = curDir;
    }
    
    static Direction Up()
    {
        return new Direction(0);
    }
    
    static Direction Right()
    {
        return new Direction(1);
    }
    
    static Direction Down()
    {
        return new Direction(2);
    }
    
    static Direction Left()
    {
        return new Direction(3);
    }
    
    Direction Rotate(Rotation rot)
    {
        return new Direction((dir+rot.DirRotate()+4)%4);
    }
}

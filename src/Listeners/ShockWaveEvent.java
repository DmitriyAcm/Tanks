/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

import java.util.ArrayList;
import java.util.EventObject;
import tanksgames.Cell;

/**
 *
 * @author dmitr
 */
public class ShockWaveEvent extends EventObject{
    public ArrayList<Cell> _list;
    
    public ShockWaveEvent(Object source, ArrayList<Cell> curList)
    {
        super(source);
        _list = curList;
    }
}

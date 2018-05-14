/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

import java.util.ArrayList;
import tanksgames.Cell;

/**
 *
 * @author dmitr
 */
public class MoveableEvent {
    public ArrayList<Cell> _list = new ArrayList<>();
    
    public MoveableEvent(Object source, ArrayList<Cell> list)
    {
        _list = list;
    }
}

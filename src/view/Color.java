/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.util.ArrayList;
/**
 *
 * @author dmitr
 */
public class Color {
    private final int _idColor;
    private static ArrayList<String> HaveColor;
    
    private Color(int col)
    {
        if(HaveColor.contains(Integer.toString(col)))
        {
            throw new NullPointerException("Этот цвет уже выбран");
        }
        _idColor = col;
    }
    
    public Color RED()
    {
        return new Color(0);
    }
    
    public Color BLUE()
    {
        return new Color(1);
    }
    
    public Color GREEN()
    {
        return new Color(2);
    }
    
    public Color BLACK()
    {
        return new Color(3);
    }
    
    public int getColor()
    {
        return _idColor;
    }
}

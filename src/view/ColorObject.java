/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.awt.Color;
import java.util.ArrayList;
/**
 *
 * @author dmitr
 */
public class ColorObject {
    private final int _idColor;
    private static ArrayList<String> HaveColor = new ArrayList<>();
    private final static int _numColor = 4;
    
    public final static int RED = 0;
    public final static int BLUE = 1;
    public final static int GREEN = 2;
    public final static int BLACK = 3;
    
    private final static Color[] cols = {Color.RED,Color.BLUE,Color.GREEN,Color.BLACK};
    
    public static int GetColor(Color obj)
    {
        int cnt=0;
        for(Color it : cols)
        {
            if(obj.equals(it))
            {
                break;
            }
            cnt++;
        }
        return cnt;
    }
    
    private ColorObject(int col)
    {
        if(CheckColor(col))
        {
            throw new NullPointerException("Этот цвет уже выбран");
        }
        HaveColor.add(Integer.toString(col));
        _idColor = col;
    }
    
    public static ColorObject GetColor(int color)
    {
        return new ColorObject(color);
    }

    public Color GetColor()
    {
        return cols[_idColor];
    }
    
    public static boolean CheckColor(int col)
    {
        return HaveColor.contains(Integer.toString(col));
    }
    
    public void RemoveColor(int col)
    {
        HaveColor.remove(Integer.toString(col));
    }
}

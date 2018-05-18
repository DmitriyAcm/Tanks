/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
/**
 *
 * @author dmitr
 */
public class ColorObject implements Comparable<ColorObject>{
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
    
    @Override
    public int compareTo(ColorObject obj)
    {
        return this._idColor==obj._idColor?1:0;
    }
    
    
    public static BufferedImage PaintImage(BufferedImage image, ColorObject col)
    {
        WritableRaster rast = image.getRaster();
        
        for(int i = 0 ; i < rast.getHeight(); ++i)
        {
            for(int j = 0; j < rast.getWidth(); ++j)
            {
                int pix[] = rast.getPixel(j,i,new int[4]);
                if(col.compareTo(ColorObject.GetColor(ColorObject.BLACK))==1)
                {
                    pix[0]=pix[1];
                    pix[2]=pix[1];
                }
                else if(col.compareTo(ColorObject.GetColor(ColorObject.BLUE))==1)
                {

                    int buf = pix[1];
                    pix[1]=pix[2];
                    pix[2]=buf;

                }
                else if(col.compareTo(ColorObject.GetColor(ColorObject.RED))==1)
                {
                    int buf = pix[1];
                    pix[1]=pix[0];
                    pix[0]=buf;
                }
                
                rast.setPixel(j, i, pix);
            }
        }
        image.setData(rast);
        return image;
    }
}

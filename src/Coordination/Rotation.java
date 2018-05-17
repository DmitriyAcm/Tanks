/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coordination;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 *
 * @author dmitr
 */
public class Rotation {
    private int _Rot;
    private Rotation(int Rot)
    {
        _Rot = Rot;
    }
    
    public static Rotation Left()
    {
        return new Rotation(-1);
    }
    
    public static Rotation Right()
    {
        return new Rotation(1);
    }
    
    public int DirRotate()
    {
        return _Rot;
    }
    
    static public BufferedImage RotateSquadImage(BufferedImage image)
    {
        if(image.getHeight()!=image.getWidth())
        {
            throw new NullPointerException("Переворачиваемая картинка не квадратная");
        }
        
        BufferedImage resImage = new BufferedImage(image.getWidth(),image.getWidth(),BufferedImage.TYPE_INT_RGB);
        
        WritableRaster resRaster = resImage.getRaster(); 
        
        WritableRaster oldRaster = image.getRaster(); 
        
        for(int i=0;i<resRaster.getHeight();++i)
        {
            for(int j=0;j<resRaster.getWidth();++j)
            {
                resRaster.setPixel(resRaster.getWidth()-i-1, j, oldRaster.getPixel(j, i, new int[4]));
            }
        }
        
        resImage.setData(resRaster);
        
        return resImage;
    }
}

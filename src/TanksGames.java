
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import view.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author dmitr
 */
public class TanksGames {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame app = new ControllView();
        app.setVisible(false);
    }
}

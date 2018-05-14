/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Coordination.Coordinate;
import Coordination.Rotation;
import Listeners.ShockWaveEvent;
import Listeners.ShockWaveListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import tanksgames.GameModel;
import javax.swing.border.Border;
import tanksgames.Cell;
import tanksgames.ShockWave;
import tanksgames.UnruledBullet;
import tanksgames.Object;

/**
 *
 * @author dmitr
 */
public class GamePanel extends JFrame implements KeyListener {
    private JPanel fieldPanel = new JPanel();
    
    private JPanel infoPanel = new JPanel();
    private JButton labelInfo = new JButton();
    private JLabel playerInfo = new JLabel();
    
    private JMenuBar menu = null;
    private final String fileItems[] = new String []{"Exit"};
    
    private final int CELL_SIZE = 50;
    private final int TITLE_HEIGHT = 40;
    
    private final GameModel _model;
    
    private JButton[][] _field;
    
    private static ImageIcon _bang;
    
    public GamePanel(GameModel model) {
        super();
            
        _model = model;
        ShockWave.AddListener(new GameOverListerner());
        
        this.setTitle("Танчики");
        
        Dimension fieldDimension = new Dimension(CELL_SIZE*_model.field().width(), CELL_SIZE*_model.field().height());
        
        this.setPreferredSize(fieldDimension);
        this.setMinimumSize(fieldDimension);
        this.setMaximumSize(fieldDimension);
       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        fieldPanel.setDoubleBuffered(true);
        createField();
        setEnabledField(true);
        this.add(fieldPanel);
        
        pack();
        setResizable(false);
        addKeyListener(this);
        
        _model.ChangePlayer();
        
        try
        {
            _bang = new ImageIcon(ImageIO.read(new File("src/tanksgames/Img/Bang/1.png")));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private void createField(){
        
        fieldPanel.setDoubleBuffered(true);
        fieldPanel.setLayout(new GridLayout(_model.field().height(), _model.field().width()));
        
        Dimension fieldDimension = new Dimension(CELL_SIZE*_model.field().height(), CELL_SIZE*_model.field().width());
        
        fieldPanel.setPreferredSize(fieldDimension);
        fieldPanel.setMinimumSize(fieldDimension);
        fieldPanel.setMaximumSize(fieldDimension);
        
        repaintField();
    }
    
    public void repaintField() {
        
        fieldPanel.removeAll();
        _field = new JButton[_model.field().height()][_model.field().width()];
        
        for (int row = 0; row < _model.field().height(); row++) 
        {
            for (int col = 0; col < _model.field().width(); col++) 
            {
                JButton button = new JButton("");
                
                _field[row][col] = button;
                
                Cell curCell = _model.field().GetCell(new Coordinate(col, row));
                
                button.setIcon(GetCellImage(curCell));
                button.setFocusable(false);
                fieldPanel.add(button);
                button.addActionListener(new ClickListener());
            }
        }

        fieldPanel.validate();
    }
    
    private ImageIcon GetCellImage(Cell curCell)
    {
        BufferedImage image = WhiteImg();
                
        for(Object obj : curCell._objects)
        {
            image = Concat(image,obj.PaintImage());
        }
        return new ImageIcon(image);
    }
    
    private BufferedImage WhiteImg()
    {
        BufferedImage image = new BufferedImage(CELL_SIZE,CELL_SIZE,BufferedImage.TYPE_INT_RGB);
        WritableRaster rast = image.getRaster();

        for(int i=0;i<rast.getHeight();++i)
        {
            for(int j=0;j<rast.getWidth();++j)
            {
                int[] pix = rast.getPixel(j, i, new int[4]);
                pix[0]=255;
                pix[1]=255;
                pix[2]=255;
                rast.setPixel(j, i, pix);
            }
        }

        image.setData(rast);
                
        return image;
    }
    
    private BufferedImage Concat(BufferedImage left, BufferedImage right)
    {

        WritableRaster rastLeft = left.getRaster();
        Raster rastRight = right.getRaster();

        for(int i=0;i<rastLeft.getHeight();++i)
        {
            for(int j=0;j<rastLeft.getWidth();++j)
            {
                int[] pixR = rastRight.getPixel(j, i, new int[4]);
                
                if(!(pixR[0]==255 && pixR[1]==255 && pixR[2]==255))
                {
                    rastLeft.setPixel(j, i, pixR);
                }
            }
        }

        left.setData(rastLeft);
                
        return left;
    }
    
    
    private void setEnabledField(boolean on){

        Component comp[] = fieldPanel.getComponents();
        for(Component c : comp)
        {    c.setEnabled(on);   }
    }  
    
    // -------------- Обработка клавиш
    @Override
    public void keyReleased(KeyEvent ke)
    {
    
    }
    
    @Override
    public void keyTyped(KeyEvent ke)
    {
    
    }
    
    @Override
    public void keyPressed(KeyEvent ke)
    {
        if(_model.curPlayer()._numStep<=0)
            return;
        
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_A:
                _model.curPlayer().tank().Rotate(Rotation.Left());
                _model.curPlayer()._numStep--;
                break;
            case KeyEvent.VK_D:
                _model.curPlayer().tank().Rotate(Rotation.Right());
                _model.curPlayer()._numStep--;
                break;
            case KeyEvent.VK_W:
                if(_model.curPlayer().tank().Move())
                {
                    _model.curPlayer()._numStep--;
                }
                break;
            case KeyEvent.VK_S:
                if(_model.curPlayer().tank().Fire(new UnruledBullet(_model.curPlayer().tank()._direct,_model.field())))
                {
                    _model.curPlayer()._numStep--;
                }
                break;
            case KeyEvent.VK_SPACE:
                _model.PassStep();
                break;
            default:
                break;
        }
        
        if(_model.curPlayer()._numStep<=0)
        {
            _model.ChangePlayer();
        }
    }
    ///////////////////////////////////
    
    // ударная волна
    private class GameOverListerner implements ShockWaveListener{
        @Override
        public void ExplosiveBullet(ShockWaveEvent e){
            for(Cell cur : e._list)
            {
                Coordinate coord = _model.field().FindCoord(cur);
                
                JButton curBut = _field[coord.getY()][coord.getX()];
                
                ImageIcon f = (ImageIcon)curBut.getIcon();
                
                curBut.setIcon(ImageIcon(Concat(f,_bang)));
            }
            try
            {
                sleep(500);
            }
            catch(InterruptedException g)
            {
                
            }
            for(Cell cur : e._list)
            {
                Coordinate coord = _model.field().FindCoord(cur);
                
                JButton curBut = _field[coord.getY()][coord.getX()];
                
                curBut.setIcon(GetCellImage(cur));
            }
        }
    }
    ///////////////////////////////////
    
    // Класс слушателя поля
    // 
    private class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           
            JButton button = (JButton) e.getSource();
            
            // Ставим на поле метку текущего игрока
            //Point p = buttonPosition(button);
            //_model.activePlayer().setLabelTo(_model.field(), p);
        }
    }
    ///////////////////////////////////
}

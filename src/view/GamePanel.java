/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Coordination.Coordinate;
import Coordination.Rotation;
import Listeners.FireRuledBulletEvent;
import Listeners.FireRuledBulletListener;
import Listeners.MoveableEvent;
import Listeners.MoveableListener;
import Listeners.ShockWaveEvent;
import Listeners.ShockWaveListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import tanksgames.GameModel;
import javax.swing.border.Border;
import tanksgames.Bullet;
import tanksgames.Cell;
import tanksgames.ShockWave;
import tanksgames.UnruledBullet;
import tanksgames.Object;
import tanksgames.RuledBullet;
/**
 *
 * @author dmitr
 */
public class GamePanel extends JFrame implements KeyListener {
    private JPanel windowPanel = new JPanel();
    
    private boolean CurBulletRuledForm = false;
    
    private final int CELL_SIZE = 50;
    private final int TITLE_HEIGHT = 40;
    
    private final GameModel _model;
    
    private JButton[][] _field;
    
    private BufferedImage _bang;
    private BufferedImage _green;
    private BufferedImage _IconOkey;
    private BufferedImage _IconCooldown;
    
    private static final String folder = "Img/";
    
    private ActionListener _MenuList = null;
    
    public int BulletFly = 0;
    
    public GamePanel(GameModel model, ActionListener MenuListener) {
        super();
        Bullet._panel = this;
        _model = model;
        _MenuList=MenuListener;
        // слушатели
        addKeyListener(this);
        Bullet.AddListener(new GameOverListerner());
        Bullet.AddListener(new ChangePlayer());
        _model.AddListener(new ChangePlayer());
        _model.field().AddListener(new PaintMove());
        //
        
        this.setTitle("Танчики");
        
        try
        {
            _IconOkey = ImageIO.read(new File(folder + "Okey.png"));
            _IconCooldown = ImageIO.read(new File(folder + "Сooldown.png"));
            _green = ImageIO.read(new File(folder + "Green/1.png"));
            _bang = ImageIO.read(new File(folder + "Bang/1.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Создание верхней панели информации
        windowPanel.add(createUpMenu());
        
        // Создание игрового поля
        windowPanel.add(createField());
        
        this.add(windowPanel);
        
        _model.ChangePlayer();
        
        pack();
        setResizable(false);
    }
    
    private JPanel createUpMenu()
    {
        JPanel UpperMenu = new JPanel();
        
        // Текущий цвет
        JPanel ColorPanel = new JPanel();
        
        UpperMenu.add(ColorPanel);
        UpperMenu.setPreferredSize(new Dimension(120,300));
        JButton But = new JButton(); 
        
        But.setPreferredSize(new Dimension(CELL_SIZE,CELL_SIZE));
        But.setFocusable(false);
        But.setBorderPainted(false);
        ColorPanel.add(But);
        
        // Количество оставшихся щагов и перезарядка
        //Очки хода
        
        Font font = new Font("Courier",0,23);
        
        JPanel PointPanel = new JPanel();
        UpperMenu.add(PointPanel);
        
        PointPanel.setPreferredSize(new Dimension(100,100));
        PointPanel.setBorder(BorderFactory.createTitledBorder("Очки хода"));
        
        But = new JButton();
        PointPanel.add(But);
        
        
        But.setPreferredSize(new Dimension(CELL_SIZE,CELL_SIZE));
        But.setFocusable(false);
        But.setBorderPainted(false);
        
        But.setFont(font);
        
        // перезарядка
        
        JPanel CooldownPanel = new JPanel();
        UpperMenu.add(CooldownPanel);
        
        CooldownPanel.setPreferredSize(new Dimension(100,100));
        CooldownPanel.setBorder(BorderFactory.createTitledBorder("Перезарядка"));
        
        But = new JButton();
        CooldownPanel.add(But);
        
        But.setPreferredSize(new Dimension(CELL_SIZE,CELL_SIZE));
        But.setFocusable(false);
        But.setBorderPainted(false);
        
        return UpperMenu;
    }
    
    private void UpdateColor()
    {
        
        JPanel UpperPanel = (JPanel)windowPanel.getComponent(0);
        JPanel ColorPanel = (JPanel)UpperPanel.getComponent(0);
        JPanel PointPanel = (JPanel)UpperPanel.getComponent(1);
        JPanel CooldownPanel = (JPanel)UpperPanel.getComponent(2);
        
        JButton But1 = (JButton)ColorPanel.getComponent(0);
        JButton But2 = (JButton)PointPanel.getComponent(0);
        JButton But3 = (JButton)CooldownPanel.getComponent(0);
        
        int curPlayer = 0;
        if(_model._players[1]==_model.curPlayer())
        {
            curPlayer = 1;
        }
        
        But1.setBackground(_model.curPlayer().tank()._color.GetColor());
        
        UpperPanel.setBorder(BorderFactory.createTitledBorder("Ход "+Integer.toString(curPlayer+1)+"-го игрока"));
        
        But2.setText(Integer.toString(_model.curPlayer()._numStep));
        
        if(_model.curPlayer().tank().Recharge())
        {
            But3.setIcon(new ImageIcon(_IconCooldown));
        }
        else
        {
            But3.setIcon(new ImageIcon(_IconOkey));
        }
    }
    
    private JPanel createField(){
        
        JPanel fieldPanel = new JPanel();
        
        fieldPanel.setDoubleBuffered(true);
        fieldPanel.setLayout(new GridLayout(_model.field().height(), _model.field().width()));
        
        Dimension fieldDimension = new Dimension(CELL_SIZE*_model.field().height(), CELL_SIZE*_model.field().width());
        
        fieldPanel.setPreferredSize(fieldDimension);
        fieldPanel.setMinimumSize(fieldDimension);
        fieldPanel.setMaximumSize(fieldDimension);
        
        fieldPanel.removeAll();
        _field = new JButton[_model.field().height()][_model.field().width()];
        
        for (int row = 0; row < _model.field().height(); row++) 
        {
            for (int col = 0; col < _model.field().width(); col++) 
            {
                JButton button = new JButton("");
                
                _field[row][col] = button;
                _field[row][col].addActionListener(new ClickListener());
                
                fieldPanel.add(button);
            }
        }
        fieldPanel.validate();
        return fieldPanel;
    }
    
    public void repaintField() {
        
        setEnabledField(true);

        System.out.println("Paint field");
        
        for (int row = 0; row < _model.field().height(); row++) 
        {
            for (int col = 0; col < _model.field().width(); col++) 
            {       
                Cell curCell = _model.field().GetCell(new Coordinate(col, row));     
                
                _field[row][col].setIcon(new ImageIcon(GetCellImage(curCell)));
                _field[row][col].setFocusable(false);
                _field[row][col].setBorderPainted(false);
            }
        }
    }
    
    public void RuledBulletMode(boolean mode){

        setEnabledField(true);
        //this.remove(fieldPanel);
        for (int row = 0; row < _model.field().height(); row++) 
        {
            for (int col = 0; col < _model.field().width(); col++) 
            {       
                Cell curCell = _model.field().GetCell(new Coordinate(col, row));
                
                RuledBullet bul = new RuledBullet(_model.curPlayer().tank()._direct,_model._distRuledBullet,_model.field(),curCell);
                
                Cell beg = _model.field().FindCell(_model.curPlayer().tank());
                beg.AddObject(bul);
                
                Boolean Check = !mode || bul.traectory().GetVector()!=null;
                
                _field[row][col].setBorderPainted(mode);
                _field[row][col].setFocusable(!Check);
                _field[row][col].setEnabled(Check);
                beg.DeleteObject(bul);    
            }
        }

    }
    
    private BufferedImage GetCellImage(Cell curCell){
        BufferedImage image = Concat(WhiteImg(),_green);
                
        for(Object obj : curCell._objects)
        {
            image = Concat(image,obj.PaintImage());
        }
        return image;
    }
    
    private BufferedImage WhiteImg(){
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
    
    private BufferedImage Concat(BufferedImage left, BufferedImage right){

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

        Component comp[] = windowPanel.getComponents();
        for(Component c : ((JPanel)comp[1]).getComponents())
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
        if(BulletFly==0)
        {
            switch (ke.getKeyCode()) {
                case KeyEvent.VK_A:
                    _model.curPlayer().tank().Rotate(Rotation.Left());
                    _model.curPlayer().DecrementStep();
                    break;
                case KeyEvent.VK_D:
                    _model.curPlayer().tank().Rotate(Rotation.Right());
                    _model.curPlayer().DecrementStep();
                    break;
                case KeyEvent.VK_W:
                    if(_model.curPlayer().tank().Move())
                    {
                       _model.curPlayer().DecrementStep();
                    }
                    break;
                case KeyEvent.VK_S:
                    if(!_model.curPlayer().tank().Recharge())
                    {
                        _model.curPlayer().tank().Fire(new UnruledBullet(_model.curPlayer().tank()._direct,_model.field()));
                        _model.curPlayer().DecrementStep();           
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    _model.PassStep();
                    break;
                case KeyEvent.VK_Z:
                    if(!_model.curPlayer().tank().Recharge())
                    {
                        CurBulletRuledForm^=true;
                        RuledBulletMode(CurBulletRuledForm);
                    }
                default:
                    break;
            }
        }
    }
    ///////////////////////////////////
    
    private Cell FindCell(JButton cur)
    {
        for (int row = 0; row < _model.field().height(); row++) 
        {
            for (int col = 0; col < _model.field().width(); col++) 
            {       
                if(_field[row][col]==cur)
                {
                   return _model.field().GetCell(new Coordinate(col,row));
                }
            }
        }
        
        return null;
    }
    
    private int countEndsWindow = 0;
    private void next1(int GameCode)
    {
        this.setEnabled(false);
        this.setVisible(false);
        countEndsWindow++;
        new EndGamePanel(_MenuList,GameCode);
    }
    
    private void PrintExplose(ArrayList<Cell> _list)
    {
        System.out.println("Paint explose");
        for(Cell cur : _list)
        {
            Coordinate coord = _model.field().FindCoord(cur);
            
            JButton curBut = _field[coord.getY()][coord.getX()];
            
            curBut.setIcon(new ImageIcon(Concat(GetCellImage(FindCell(curBut)),_bang)));
        }
    }
    
    // ударная волна
    private class GameOverListerner implements ShockWaveListener{
        @Override
        public void ExplosiveBullet(ShockWaveEvent e){
            
            PrintExplose(e._list);
            
            int ends = _model.CompletionGame();
            if(ends!=0 && countEndsWindow==0)
            {
                next1(ends);
            }           
        }
    }
    ///////////////////////////////////
    
    // Классы слушателя поля
    // 
    private class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(CurBulletRuledForm)
            {
                _model.curPlayer().tank().Fire(new RuledBullet(_model.curPlayer().tank(). _direct,_model._distRuledBullet,_model.field(),FindCell((JButton) e.getSource())));
                _model.curPlayer().DecrementStep();
                CurBulletRuledForm=false;
            }
        }
    }
    
    private class ChangePlayer implements FireRuledBulletListener {
        @Override
        public void RepaintField(FireRuledBulletEvent e)
        {
            repaintField();
            UpdateColor();
        }
    }

    private class PaintMove implements MoveableListener {
        @Override
        public void move(MoveableEvent e)
        {
            for(Cell cur : e._list)
            {
                Coordinate coord = _model.field().FindCoord(cur);

                JButton curBut = _field[coord.getY()][coord.getX()];

                curBut.setIcon(new ImageIcon(GetCellImage(FindCell(curBut))));
            }
        }
    }
    ///////////////////////////////////
}

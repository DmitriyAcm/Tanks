/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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
import tanksgames.ShockWave;
import tanksgames.UnruledBullet;

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

        for (int row = 0; row < _model.field().height(); row++) 
        {
            for (int col = 0; col < _model.field().width(); col++) 
            {
                JButton button = new JButton("");
                
                ImageIcon img = null;
                
                if(_model.field().GetCell(col, row)._AirObj!=null)
                {
                    img = _model.field().GetCell(col, row)._AirObj.getImage();
                    
                }
                
                if(_model.field().GetCell(col, row)._GroundObj!=null)
                {
                    img = _model.field().GetCell(col, row)._GroundObj.getImage();
                }
                
                if(img != null)
                {
                    button.setIcon(img);
                }
                
                button.setFocusable(false);
                fieldPanel.add(button);
                button.addActionListener(new ClickListener());
            }
        }

        fieldPanel.validate();
    }
      
    private void setEnabledField(boolean on){

        Component comp[] = fieldPanel.getComponents();
        for(Component c : comp)
        {    c.setEnabled(on);   }
    }  
    
    
    // Классы слушателей
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
    
    // ударная волна
    private class GameOverListerner implements ShockWaveListener{
        @Override
        public void ExplosiveBullet(ShockWaveEvent e){
            //if()
            {
                
            }
        }
    }
}

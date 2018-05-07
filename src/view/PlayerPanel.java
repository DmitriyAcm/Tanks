/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tanksgames.Cell;

/**
 *
 * @author dmitr
 */
public class PlayerPanel extends JFrame{
    public ColorObject _col;
    public int _pos = 0;
    public JPanel _colorPanel;
    public JPanel _cornorPanel;
    public JButton _next;
    int blockPos = -1;
    
    PlayerPanel(ActionListener MenuListener, int num)
    {
        super();
        
        this.setTitle("Танчики");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension fieldDimension = new Dimension(400, 500);
        
        this.setPreferredSize(fieldDimension);
        this.setMinimumSize(fieldDimension);
        this.setMaximumSize(fieldDimension);
        
        
        // -- цветовая панель
        _colorPanel = panColor();
        /////
        
        // -- угловая панель
        _cornorPanel = panCornor();
        ////
        
        // -- панель кнопок
        JPanel panelButton = panButton(MenuListener);
        ////
        
        
        JPanel mainPanel = new JPanel(new GridLayout(3,1));
        
        mainPanel.add(_colorPanel);
        
        mainPanel.add(_cornorPanel);
        
        mainPanel.add(panelButton);
        
        mainPanel.setBorder(BorderFactory.createTitledBorder("Настройки "+Integer.toString(num)+"-го игрока"));
        this.add(mainPanel);
    }
    
    public void setBlock(int col)
    {
        blockPos = col;
    }
    
    JPanel panButton(ActionListener MenuListener)
    {
        JPanel panelButton = new JPanel();
        
        JButton nextButton = new JButton();
        nextButton.setText("Далее");
        nextButton.addActionListener(MenuListener);
        _next = nextButton;
        _next.setEnabled(false);
        
        JButton exitButton = new JButton();
        exitButton.setText("Выход");
        exitButton.addActionListener(MenuListener);
        
        panelButton.add(nextButton);
        panelButton.add(exitButton);
        
        return panelButton;
    }
    
    JPanel panColor()
    {
        JPanel colorPanel = new JPanel(new FlowLayout());
        
        ColorListener list = new ColorListener();
 
        JButton red = new JButton("RED");
        red.setBackground(Color.red);
        red.addActionListener(list);
        //red.setEnabled(!ColorObject.CheckColor(ColorObject.RED));
        red.setPreferredSize(new Dimension(80,80));
        
        JButton blue = new JButton("BLUE");
        blue.setBackground(Color.blue);
        blue.addActionListener(list);
        //blue.setEnabled(!ColorObject.CheckColor(ColorObject.BLUE));
        blue.setPreferredSize(new Dimension(80,80));
        
        JButton green = new JButton("GREEN");
        green.setBackground(Color.green);
        green.addActionListener(list);
        //green.setEnabled(!ColorObject.CheckColor(ColorObject.GREEN));
        green.setPreferredSize(new Dimension(80,80));
        
        JButton black = new JButton("BLACK");
        black.setBackground(Color.black);
        black.addActionListener(list);
        //black.setEnabled(!ColorObject.CheckColor(ColorObject.BLACK));
        black.setPreferredSize(new Dimension(80,80));
        
        colorPanel.add(red);
        colorPanel.add(blue);
        colorPanel.add(green);
        colorPanel.add(black);
        
        colorPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        colorPanel.setBorder(BorderFactory.createTitledBorder("Выбор цвета"));
        
        return colorPanel;
    }
    
    JPanel panCornor()
    {
        JPanel cornorPanel = new JPanel(new GridLayout(2,2));
        
        CornorListener list = new CornorListener();
        
        JButton red = new JButton("1");
        red.addActionListener(list);
        red.setEnabled(false);
        red.setPreferredSize(new Dimension(80,80));
        
        JButton blue = new JButton("2");
        blue.addActionListener(list);
        blue.setEnabled(false);
        blue.setPreferredSize(new Dimension(80,80));
        
        JButton green = new JButton("3");
        green.addActionListener(list);
        green.setEnabled(false);
        green.setPreferredSize(new Dimension(80,80));
        
        JButton black = new JButton("4");
        black.addActionListener(list);
        black.setEnabled(false);
        black.setPreferredSize(new Dimension(80,80));
        
        cornorPanel.add(red);
        cornorPanel.add(blue);
        cornorPanel.add(green);
        cornorPanel.add(black);
        
        cornorPanel.setBorder(BorderFactory.createTitledBorder("Выбор угла появления штаба"));
        return cornorPanel;
    }
    
    public class ColorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            
            if ("RED".equals(command)) {
                _col = ColorObject.GetColor(ColorObject.RED);
            }
            else if("BLUE".equals(command))
            {
                _col = ColorObject.GetColor(ColorObject.BLUE);
            }
            else if("GREEN".equals(command))
            {
                _col = ColorObject.GetColor(ColorObject.GREEN);
            }
            else if("BLACK".equals(command))
            {
                _col = ColorObject.GetColor(ColorObject.BLACK);
            }
            for(Component i : _colorPanel.getComponents())
            {
                i.setBackground(Color.GRAY);
                i.setEnabled(false);
            }
            int cnt=0;
            for(Component i : _cornorPanel.getComponents())
            {
                if(blockPos!=cnt)
                {
                    i.setEnabled(true);
                }
                cnt++;
            }
            ((JButton) e.getSource()).setBackground(_col.GetColor());
        }
    }
    
    public class CornorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            
            _pos = Integer.parseInt(command)-1;
            
            for(Component i : _cornorPanel.getComponents())
            {
                i.setEnabled(false);
            }
            ((JButton) e.getSource()).setBackground(_col.GetColor());
            _next.setEnabled(true);
        }
    }
}

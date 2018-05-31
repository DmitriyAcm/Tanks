/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;

/**
 *
 * @author dmitr
 */
public class InfoPanel extends JFrame {
    
    public int height;
    public int weight;
    public int lenghtFly;
    public int cntStep;
    
    private int cnt=0;
    JSpinner[] spins = new JSpinner[4];
    
    
    public InfoPanel(ActionListener MenuListener)
    {
        super();
        
        this.setTitle("Танчики");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension fieldDimension = new Dimension(500, 225);
        
        //this.setPreferredSize(fieldDimension);
        //this.setMinimumSize(fieldDimension);
        //this.setMaximumSize(fieldDimension);
        
        JPanel InfoPanel = new JPanel(new SpringLayout());
        
        JPanel alignmentPanel = new JPanel(new SpringLayout());
        InfoPanel.setBorder(BorderFactory.createTitledBorder("Правила игры"));
        
        InfoPanel.add(alignmentPanel);
        
        
        alignmentPanel.setBorder(BorderFactory.createTitledBorder("Управление"));
        
        
        
        //JPanel panelButton = new JPanel(new FlowLayout());
        
        alignmentPanel.add(new JLabel("Нажатие A --- Поворот влево."));
        alignmentPanel.add(new JLabel("Нажатие D --- Поворот вправо."));
        alignmentPanel.add(new JLabel("Нажатие S --- Выстрел ракетой без наведения."));
        alignmentPanel.add(new JLabel("Нажатие W --- Поехать на одну клетку вперед."));
        alignmentPanel.add(new JLabel("Нажатие Z --- Наведение управляемого снаряда. Далее указать мышкой целевую ячейку."));
        alignmentPanel.add(new JLabel("Нажатие Space --- Пропуск хода."));
        
         //Lay out the panel.
        SpringUtilities.makeCompactGrid(alignmentPanel,
                                6, 1, //rows, cols
                                6, 6,        //initX, initY
                                6, 6);       //xPad, yPad
        
        JPanel LifePanel = new JPanel(new SpringLayout());
        LifePanel.setBorder(BorderFactory.createTitledBorder("Количество жизней"));
        
        LifePanel.add(new JLabel("Колиство жизней танка --- 3."));
        LifePanel.add(new JLabel("Колиство жизней штаба --- 2."));
        LifePanel.add(new JLabel("Колиство жизней стенки --- (от 1 до 3)."));
        
                //Lay out the panel.
        SpringUtilities.makeCompactGrid(LifePanel,
                                3, 1, //rows, cols
                                6, 6,        //initX, initY
                                6, 6);       //xPad, yPad
        
        InfoPanel.add(LifePanel);
        
        
        //this.add(alignmentPanel);
        JPanel panelButton = new JPanel(new FlowLayout());
        
        JButton nextButton = new JButton();
        nextButton.setText("Далее");
        nextButton.addActionListener(MenuListener);
        panelButton.add(nextButton);
        
        JButton exitButton = new JButton();
        exitButton.setText("Выход");
        exitButton.addActionListener(MenuListener);
        panelButton.add(exitButton);
        
        InfoPanel.add(panelButton);
        
        SpringUtilities.makeCompactGrid(InfoPanel,
                                3, 1, //rows, cols
                                6, 6,        //initX, initY
                                6, 6);       //xPad, yPad
        
        this.add(InfoPanel);
        this.pack();
    }
}


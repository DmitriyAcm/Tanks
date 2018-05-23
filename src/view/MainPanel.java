/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
/**
 *
 * @author dmitr
 */
public class MainPanel extends JFrame {
    
    public int height;
    public int weight;
    public int lenghtFly;
    public int cntStep;
    
    private int cnt=0;
    JSpinner[] spins = new JSpinner[4];
    
    
    public MainPanel(ActionListener MenuListener)
    {
        super();
        
        this.setTitle("Танчики");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension fieldDimension = new Dimension(500, 225);
        
        this.setPreferredSize(fieldDimension);
        this.setMinimumSize(fieldDimension);
        this.setMaximumSize(fieldDimension);
        
        JPanel alignmentPanel = CreatePanel();
        alignmentPanel.setBorder(BorderFactory.createTitledBorder("Настройка игры"));
   
        //this.add(alignmentPanel);
        JPanel panelButton = new JPanel(new FlowLayout());
        
        JButton nextButton = new JButton();
        nextButton.setText("Далее");
        nextButton.addActionListener(MenuListener);
        
        JButton exitButton = new JButton();
        exitButton.setText("Выход");
        exitButton.addActionListener(MenuListener);
        
        panelButton.add(nextButton);
        panelButton.add(exitButton);
        
        JPanel mainPanel = new JPanel(new FlowLayout());
        
        mainPanel.add(alignmentPanel);
        mainPanel.add(panelButton);
        
        this.add(mainPanel);
    }

    void calcValue()
    {
        height = (int)spins[2].getValue();
        weight = (int)spins[3].getValue();
        lenghtFly = (int)spins[1].getValue();
        cntStep = (int)spins[0].getValue();
    }
    
    JPanel CreatePanel()
    {
        JPanel p = new JPanel(new SpringLayout());
        CreateSpinner(p,"Количество шагов хода",1);
        CreateSpinner(p,"Дальность полета управляемого снаряда",1);
        CreateSpinner(p,"Высота поля",5);
        CreateSpinner(p,"Ширина поля",5);
        
        //Lay out the panel.
        int numPairs = 4;
        SpringUtilities.makeCompactGrid(p,
                                numPairs, 2, //rows, cols
                                6, 6,        //initX, initY
                                6, 6);       //xPad, yPad
        
        
        return p;
    }
    
    void CreateSpinner(JPanel p, String s, int leftbound)
    {    
        JLabel l = new JLabel(s, JLabel.TRAILING);
        p.add(l);
        
        SpinnerNumberModel model1 = new SpinnerNumberModel(leftbound, leftbound, 15, 1);
        JSpinner textField = new JSpinner(model1);
        
        l.setLabelFor(textField);
        p.add(textField);
        
        spins[cnt] = textField;
        
        cnt=(cnt+1)%4;
    }
}

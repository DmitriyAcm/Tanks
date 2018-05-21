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
import javax.swing.SpringLayout;

/**
 *
 * @author dmitr
 */
public class EndGamePanel extends JFrame{
    
    public EndGamePanel(ActionListener MenuListener, int CodeGame)
    {
        super();
        
        this.setTitle("Танчики");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension fieldDimension = new Dimension(500, 225);
        //this.add(alignmentPanel);
        this.setPreferredSize(fieldDimension);
        this.setMinimumSize(fieldDimension);
        this.setMaximumSize(fieldDimension);
        
        JPanel alignmentPanel = new JPanel(new FlowLayout());
        
        if(CodeGame<3)
        {
            alignmentPanel.add(new JLabel("Выйграл " + Integer.toString(((CodeGame-1)^1)+1) + " игрок"));
        }
        else
        {
            alignmentPanel.add(new JLabel("Ничья!"));
        }
        JPanel panelButton = new JPanel(new FlowLayout());
        
        JButton exitButton = new JButton();
        exitButton.setText("Выход");
        exitButton.addActionListener(MenuListener);
        
        panelButton.add(exitButton);
        
        JPanel mainPanel = new JPanel(new SpringLayout());
        
        mainPanel.add(alignmentPanel);
        mainPanel.add(panelButton);
        
        SpringUtilities.makeCompactGrid(mainPanel,
                                2, 1, //rows, cols
                                6, 6,        //initX, initY
                                6, 6);       //xPad, yPad
        
        this.add(mainPanel);
        
        this.setVisible(true);
    }
    
}

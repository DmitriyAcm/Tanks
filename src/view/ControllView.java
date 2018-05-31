/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import tanksgames.GameModel;



/**
 *
 * @author dmitr
 */
public class ControllView extends JFrame{
    
    ///////////////////////////////////////// TODO ОТЛАДКА curWindow = 1
    private int curWindow = 4;
    
    MainPanel mains = new MainPanel(new NewMenuListener());
    PlayerPanel player1 = new PlayerPanel(new NewMenuListener(),1);  
    PlayerPanel player2 = new PlayerPanel(new NewMenuListener(),2);  
    InfoPanel info = new InfoPanel(new NewMenuListener());
    public ControllView()
    {
        view();
    }
    
    void view()
    {
        mains.calcValue();
        mains.setVisible(false);
        player1.setVisible(false);
        player2.setVisible(false);
        info.setVisible(false);
        
        
        switch (curWindow) {
            case 1:
                mains.setVisible(true);
                break;
            case 2:
                player1.setVisible(true);
                break;
            case 3:
                player2._colorPanel.getComponents()[ColorObject.GetColor(player1._col.GetColor())].setEnabled(false);
                player2._colorPanel.getComponents()[ColorObject.GetColor(player1._col.GetColor())].setBackground(Color.GRAY);
                player2.setBlock(player1._pos);
                player2.setVisible(true);
                break;
            case 4:
                info.setVisible(true);
                break;
            case 5:
                
                GameModel model = new GameModel(3,4,20,20,ColorObject.GetColor(ColorObject.GREEN),ColorObject.GetColor(ColorObject.BLUE),0,3);
                
                //GameModel model = new GameModel(mains.cntStep,mains.lenghtFly+1,mains.height,mains.weight,player1._col,player2._col,player1._pos,player2._pos);
                GamePanel game = new GamePanel(model, new NewMenuListener());
                game.setVisible(true);
                break;
            default:
                break;
        }
    }
    
    void next()
    {
        curWindow+=1;
        view();
    }
    
    public class NewMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if ("Выход".equals(command)) {
                System.exit(0);
            }
            if ("Далее".equals(command)) {
                next();
            }  
        }
    }
}

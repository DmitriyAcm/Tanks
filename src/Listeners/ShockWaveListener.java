/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;
import java.util.EventListener;
/**
 *
 * @author dmitr
 */
public interface ShockWaveListener extends EventListener{
    void ExplotionBullet(ShockWaveEvent e);
}

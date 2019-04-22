/*package TNGListner;


import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;

import com.graphics.gui.*;

public class BlockingDialog extends JFrame {

    private Timer timer;
    public static JDialog blocker;
    public static JProgressBar progress;
   
   

    public BlockingDialog(String text,Color color) {
    	
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 100);
        setLocationRelativeTo(ToolFrame.frmMobileAutomationTray);
        
       
        blocker = new JDialog(this, true);
        blocker.setLayout(new FlowLayout());
        blocker.setUndecorated(true);
       // blocker.setLocationRelativeTo(ToolFrame.frmMobileAutomationTray);
        blocker.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        
        blocker.add(new JLabel(text));  
       
        progress = new JProgressBar();
        progress.setIndeterminate(true);
       
        progress.setForeground(color);
      
        blocker.add(progress);
        blocker.pack();
        

        timer = new Timer(100, new ActionListener() {
        	
        	 public void actionPerformed(ActionEvent e) {
                 doSomeWork();
             }
         });
         timer.setRepeats(false);
         timer.start();
     }	
    
    private void doSomeWork() {
        Runnable runnable = new Runnable() {
        public void run() {
              
                showBlocker();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                  
                } finally {                
                 //  hideBlocker();
                }
            }
        };
        new Thread(runnable).start();
    }
   
   
    private void showBlocker() {
        // this executes off-EDT                
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // this executes on-EDT
                blocker.setLocationRelativeTo(BlockingDialog.this);
                blocker.setVisible(true);
            }
        });
    }
    
    private void hideBlocker() {
        // this executes off-EDT
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // this executes on-EDT
                blocker.setVisible(false);
               // timer.restart();
            }
        });
    }
    
   
    
    
    
    public static void main(String[] args,final String text,final Color color) {
        // this is called off-EDT
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                // this is called on-EDT
                new BlockingDialog(text,color).setVisible(false);
                
            }
        });
    } 
    
    
        	
        	
 }
        	
        
*/
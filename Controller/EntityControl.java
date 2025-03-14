package Controller;
import View.BakerPanel;
import View.Display;
import View.RaccoonPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/** Control the visibility of panel entities  */
public class EntityControl extends MouseAdapter {
    RaccoonPanel rcPanel;
    BakerPanel bkPanel;

    Display display;

    public EntityControl(RaccoonPanel rcPanel, BakerPanel bkPanel, Display display) {
        this.rcPanel = rcPanel;
        this.bkPanel = bkPanel;
       this.display = display;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        JLabel bakerLabel = display.getBakerLabel();
        ArrayList<JLabel> raccoonLabels = display.getRaccoonLabels();

        if (source == bakerLabel) { // Verify if the click comes from the baker label
            System.out.println("Baker clicked!");
            rcPanel.setVisible(false);
            bkPanel.setBounds(Display.FRAME_W, 0, BakerPanel.WIDTH, BakerPanel.HEIGHT);
            bkPanel.requestFocus();
            display.add(bkPanel);
            bkPanel.setVisible(true);
        } else {
            for(JLabel raccoonLabel : raccoonLabels) {
                if (source == raccoonLabel) { // Verify if the click comes from the raccoon label
                    System.out.println("Raccoon clicked!");
                    bkPanel.setVisible(false);
                    rcPanel.setBounds(Display.FRAME_W, 0, RaccoonPanel.WIDTH, RaccoonPanel.HEIGHT); // Position the panel off-screen to the right
                    display.add(rcPanel);
                    rcPanel.setVisible(true);
                }
            }
        }
    }

}

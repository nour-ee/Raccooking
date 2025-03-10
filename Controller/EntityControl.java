package Controller;
import View.BakerPanel;
import View.Display;
import View.RaccoonPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        JLabel raccoonLabel = display.getRaccoonLabel();

        if (source == bakerLabel) { // Verify if the click comes from the baker label
            System.out.println("Baker clicked!");
            rcPanel.setVisible(false);
            display.add(bkPanel, BorderLayout.EAST);
            bkPanel.setVisible(true);
        } else if (source == raccoonLabel) { // Verify if the click comes from the raccoon label
            System.out.println("Raccoon clicked!");
            bkPanel.setVisible(false);
            display.add(rcPanel, BorderLayout.EAST);
            rcPanel.setVisible(true);
        }
    }

}

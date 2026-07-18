package org.colorblindcolorpicker;

import javax.swing.*;
import java.awt.*;

public class TransparentScreen extends JFrame {

    TransparentScreen() {
        setUndecorated(true);
        setAlwaysOnTop(true);
        setBackground(new Color(0, 0, 0, 1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        setBounds(gd.getDefaultConfiguration().getBounds());
        setVisible(true);
        toFront();
        requestFocus();
        requestFocusInWindow();
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

}

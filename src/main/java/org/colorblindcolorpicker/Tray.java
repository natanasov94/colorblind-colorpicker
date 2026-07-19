package org.colorblindcolorpicker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Tray {

    public Tray() throws IOException {
        final PopupMenu popup = new PopupMenu();
        Image image = ImageIO.read(
                Objects.requireNonNull(
                        getClass().getResource("/colorblindcolorpicker-icon.png")
                )
        );
        final TrayIcon trayIcon = new TrayIcon(image);
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Colorblind Color Picker");
        final SystemTray tray = SystemTray.getSystemTray();

        MenuItem exitItem = new MenuItem("Exit");

        //Add components to pop-up menu
        popup.add(exitItem);
        exitItem.addActionListener(e -> {
            SystemTray.getSystemTray().remove(trayIcon);
            System.exit(0);
        });
        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}

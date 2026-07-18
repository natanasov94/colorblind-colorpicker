package org.colorblindcolorpicker;

import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;

import javax.swing.*;
import java.awt.*;

public class GlobalMouseListener implements NativeMouseInputListener {
    public void nativeMouseClicked(NativeMouseEvent e) {
        Point point = MouseInfo.getPointerInfo().getLocation();
        if (Context.isCombinationPressed) {
            getColorOfCursorLocation(point);
            Context.isCombinationPressed = false;
        }
    }

    private void getColorOfCursorLocation(Point point) {
        Robot robot;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        Color color = robot.getPixelColor(point.x, point.y);
        Context.transparentScreen.dispose();
        Context.transparentScreen = null;
        SwingUtilities.invokeLater(() -> new ColorPopup(color.getRed(), color.getGreen(), color.getBlue()));
    }
}

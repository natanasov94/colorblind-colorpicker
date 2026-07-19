package org.colorblindcolorpicker;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.exit(1);
        }
        GlobalScreen.addNativeMouseListener(new GlobalMouseListener());
        GlobalScreen.addNativeKeyListener(new KeyListener());
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Tray();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

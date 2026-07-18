package org.colorblindcolorpicker;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;

public class KeyListener implements NativeKeyListener {

    private boolean ctrlPressed = false;
    private boolean altPressed = false;
    private boolean shiftPressed = false;

    public void nativeKeyPressed(NativeKeyEvent e) {
        switch (e.getKeyCode()) {
            case NativeKeyEvent.VC_CONTROL:
                ctrlPressed = true;
                break;
            case NativeKeyEvent.VC_ALT:
                altPressed = true;
                break;
            case NativeKeyEvent.VC_SHIFT:
                shiftPressed = true;
                break;
            case NativeKeyEvent.VC_SLASH:
                if(ctrlPressed && altPressed && shiftPressed) {
                    Context.isCombinationPressed = true;
                    SwingUtilities.invokeLater(() ->
                            Context.transparentScreen = new TransparentScreen()
                    );
                }
                break;
            case NativeKeyEvent.VC_ESCAPE:
                Context.isCombinationPressed = false;
                if (Context.transparentScreen != null) {
                    Context.transparentScreen.dispose();
                }
                break;
            default:
                break;
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        switch (e.getKeyCode()) {
            case NativeKeyEvent.VC_CONTROL:
                ctrlPressed = false;
                break;
            case NativeKeyEvent.VC_ALT:
                altPressed = false;
                break;
            case NativeKeyEvent.VC_SHIFT:
                shiftPressed = false;
                break;
            default:
                break;
        }
    }
}

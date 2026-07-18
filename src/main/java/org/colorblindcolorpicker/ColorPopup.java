package org.colorblindcolorpicker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ColorPopup extends JFrame {

    private static final Color BG = new Color(35, 35, 40);
    private static final Color FG = new Color(240, 240, 240);
    private static final Color SUB = new Color(180, 180, 180);

    public ColorPopup(int r, int g, int b) {
        super("Color");

        setUndecorated(true);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Color color = new Color(r, g, b);
        String hex = String.format("#%02X%02X%02X", r, g, b);

        float[] hsv = Color.RGBtoHSB(r, g, b, null);
        float hue = hsv[0] * 360f;
        float sat = hsv[1] * 100f;
        float val = hsv[2] * 100f;

        JPanel root = getRoot();

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);

        // ===== Preview =====

        JPanel preview = new JPanel();
        preview.setPreferredSize(new Dimension(80, 80));
        preview.setBackground(color);
        preview.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        // ===== Text =====

        JPanel text = new JPanel();
        text.setOpaque(false);
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(ColorClassifier.classify(color).getName());
        title.setForeground(FG);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        JLabel rgb = new JLabel(
                String.format("RGB %3d, %3d, %3d", r, g, b));
        rgb.setForeground(SUB);
        rgb.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        JLabel hsvLabel = new JLabel(
                String.format("HSV %6.1f°, %5.1f%%, %5.1f%%",
                        hue, sat, val));
        hsvLabel.setForeground(SUB);
        hsvLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        JLabel hexLabel = new JLabel(hex);
        hexLabel.setForeground(SUB);
        hexLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));

        text.add(title);
        text.add(Box.createVerticalStrut(8));
        text.add(rgb);
        text.add(Box.createVerticalStrut(4));
        text.add(hsvLabel);
        text.add(Box.createVerticalStrut(4));
        text.add(hexLabel);

        JPanel center = new JPanel(new BorderLayout(15, 0));
        center.setOpaque(false);
        center.add(preview, BorderLayout.WEST);
        center.add(text, BorderLayout.CENTER);

        root.add(top, BorderLayout.NORTH);
        root.add(center, BorderLayout.CENTER);

        setContentPane(root);

        getRootPane().registerKeyboardAction(
                e -> dispose(),
                KeyStroke.getKeyStroke("ESCAPE"),
                JComponent.WHEN_IN_FOCUSED_WINDOW
        );

        pack();

        Point p = MouseInfo.getPointerInfo().getLocation();
        setLocation(p.x + 20, p.y + 20);

        setBackground(new Color(0, 0, 0, 0));
        setVisible(true);
    }

    private static JPanel getRoot() {
        JPanel root = new JPanel(new BorderLayout(15, 15)) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(BG);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);

                g2.setColor(new Color(70, 70, 75));
                g2.drawRoundRect(
                        0, 0,
                        getWidth() - 1,
                        getHeight() - 1,
                        18, 18);

                g2.dispose();
            }
        };

        root.setOpaque(false);
        root.setBorder(new EmptyBorder(18, 18, 18, 18));

        return root;
    }
}
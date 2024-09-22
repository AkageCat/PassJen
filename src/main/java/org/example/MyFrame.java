package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class MyFrame extends JFrame {
    public MyFrame() {
        ImageIcon showIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/show.png")));
        ImageIcon hideIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/hide.png")));
        ImageIcon copyIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/copy.png")));
//        ImageIcon favoriteIcon = new ImageIcon(Main.class.getResource("\\resources\\images\\favorite.png"));
//        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 8, 8));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Password Generator");

        String[] options = {"Apple Easy to Type", "Passcode", "Password"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        getContentPane().add(comboBox);

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
        getContentPane().add(jPanel);
        JTextField passwordLength = new JTextField("6");
        comboBox.addActionListener(e -> {
            String selectedItem = Objects.requireNonNull(comboBox.getSelectedItem()).toString();
            jPanel.removeAll();
            switch (selectedItem) {
                case "Apple Easy to Type":
                    break;
                case "Passcode", "Password":
                    jPanel.add(new JLabel("Length"));
                    jPanel.add(passwordLength);
                    break;
            }
            jPanel.revalidate();
            jPanel.repaint();
            pack();
            repaint();
        });

        JButton generateButton = new JButton("Generate");
        getContentPane().add(generateButton);
        generateButton.addActionListener(e -> {
            Generator generator = new Generator();
            Password password = new Password();
            if (comboBox.getSelectedItem() == options[0]) {
                password.setValue(generator.generateAppleEasyToTypePassword());
            } else if (comboBox.getSelectedItem() == options[1]) {
                password.setValue(generator.generatePasscode(Integer.parseInt(passwordLength.getText())));
            } else if (comboBox.getSelectedItem() == options[2]) {
                password.setValue(generator.generatePassword(Integer.parseInt(passwordLength.getText())));
            }
            JDialog jDialog = new JDialog(this, "Password", true);
            jDialog.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 8));
            JPasswordField passwordField = new JPasswordField(password.getValue());
//            passwordField.setFont(new Font("Ubuntu Sans Mono", Font.BOLD, 14));

            Font ubuntuSansMono;
            try {
                InputStream inputStream = Main.class.getResourceAsStream("/fonts/UbuntuSansMono-VariableFont_wght.ttf");
                ubuntuSansMono = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(24f);
            } catch (FontFormatException | IOException ex) {
                throw new RuntimeException(ex);
            }
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(ubuntuSansMono);
            passwordField.setFont(ubuntuSansMono);

            jDialog.add(passwordField);
            JButton showButton = new JButton(showIcon);
            jDialog.add(showButton);
            showButton.addActionListener(event -> {
                if (password.isVisible()) {
                    passwordField.setEchoChar('*');
                    password.setVisible(false);
                    showButton.setIcon(showIcon);
                } else {
                    passwordField.setEchoChar((char) 0);
                    password.setVisible(true);
                    showButton.setIcon(hideIcon);
                }
            });
            JButton copyButton = new JButton(copyIcon);
            jDialog.add(copyButton);
            copyButton.addActionListener(event -> {
                StringSelection stringSelection = new StringSelection(passwordField.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            });
//            MyButton myButton = new MyButton(favoriteIcon);
//            jDialog.add(myButton);
            jDialog.pack();
            jDialog.setLocationRelativeTo(this);
            jDialog.setVisible(true);
        });
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

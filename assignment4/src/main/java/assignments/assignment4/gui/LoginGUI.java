package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;
    GridBagConstraints gbc;

    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gbc = new GridBagConstraints(); 
        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // TODO
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        idLabel = new JLabel("Masukan ID Anda:");
        mainPanel.add(idLabel, gbc);

        idTextField = new JTextField();
        idTextField.setPreferredSize(new Dimension(500, 30));
        idTextField.setVisible(true);
        mainPanel.add(idTextField, gbc);

        passwordLabel = new JLabel("Masukkan password Anda:");
        mainPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(500, 30));
        passwordField.setVisible(true);
        mainPanel.add(passwordField, gbc);

        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(150, 30)); // setting ukuran
        loginButton.setVisible(true);
        loginButton.setFocusable(false);

        backButton = new JButton("Kembali");
        backButton.setPreferredSize(new Dimension(150, 30)); // setting ukuran
        backButton.setVisible(true);
        backButton.setFocusable(false);
        
        JButton[] buttons = {
            loginButton, backButton
        };
        ActionListener[] listeners = {
                e -> handleLogin(),
                e -> handleBack()
        };

        gbc.anchor = GridBagConstraints.CENTER;

        for (int i = 0; i < buttons.length; i++) {
            JButton button = buttons[i];
            button.addActionListener(listeners[i]);
            mainPanel.add(button, gbc);
        }
    }


    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        idTextField.setText("");
        passwordField.setText("");
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        String id = idTextField.getText();
        String password = String.valueOf(passwordField.getPassword());

        if ((password.equals(""))||(id.equals(""))){
            JOptionPane.showMessageDialog(null, "Semua field diatas wajib diisi!", "Empty Field", JOptionPane.ERROR_MESSAGE);
        }
        else if (!MainFrame.getInstance().login(id, password)){
            JOptionPane.showMessageDialog(null, "ID atau password invalid.", "Invalid ID or Password", JOptionPane.ERROR_MESSAGE);
        }
        idTextField.setText("");
        passwordField.setText("");
    }
}

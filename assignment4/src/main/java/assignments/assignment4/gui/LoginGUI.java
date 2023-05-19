package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment4.MainFrame;

//import library yang dibutuhkan 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    // inisiasi atribut yang akan digunakan
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

    // inisiasi constructor kelas 
    public LoginGUI(LoginManager loginManager) { 
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));
        mainPanel.setBackground(MainFrame.c1);
        gbc = new GridBagConstraints(); 
        initGUI();

        add(mainPanel, BorderLayout.CENTER); // menambahkan mainPanel ke JPanel di Frame
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // membuat komponen yang akan muncul di GUI
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        idLabel = new JLabel("Masukan ID Anda:");
        mainPanel.add(idLabel, gbc);

        idTextField = new JTextField();
        idTextField.setPreferredSize(new Dimension(665, 30));
        idTextField.setVisible(true);
        mainPanel.add(idTextField, gbc);

        passwordLabel = new JLabel("Masukkan password Anda:");
        mainPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(665, 30));
        passwordField.setVisible(true);
        mainPanel.add(passwordField, gbc);

        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(150, 50)); // setting ukuran
        loginButton.setForeground(MainFrame.c2);
        loginButton.setFocusable(false);

        backButton = new JButton("Kembali");
        backButton.setPreferredSize(new Dimension(150, 50)); // setting ukuran
        backButton.setVisible(true);
        backButton.setFocusable(false);
        
        JButton[] buttons = {
            loginButton, backButton
        };
        ActionListener[] listeners = { // membuat actionlistener untuk masing-masing button
                e -> handleLogin(),
                e -> handleBack()
        };

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        for (int i = 0; i < buttons.length; i++) {
            JButton button = buttons[i];
            button.addActionListener(listeners[i]);// menambahkan actionlistener ke button
            mainPanel.add(button, gbc); // menambahkan button ke mainpanel
        }
    }


    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        idTextField.setText(""); // mengosongkan kembali tiap textfield
        passwordField.setText(""); 
        MainFrame.getInstance().navigateTo(HomeGUI.KEY); // navigasi ke homepage
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        String id = idTextField.getText(); // mengambil value untuk id
        String password = String.valueOf(passwordField.getPassword()); // mengambil value untuk password

        if ((password.equals(""))||(id.equals(""))){ // jika ada yang masih kosong maka tidak valid
            JOptionPane.showMessageDialog(null, "Semua field diatas wajib diisi!", "Empty Field", JOptionPane.ERROR_MESSAGE);
        }
        else if (!MainFrame.getInstance().login(id, password)){ // jika saat login return false maka invalid
            JOptionPane.showMessageDialog(null, "ID atau password invalid.", "Invalid ID or Password", JOptionPane.ERROR_MESSAGE);
        }
        idTextField.setText(""); // mengosongkan textfield
        passwordField.setText("");
    }
}


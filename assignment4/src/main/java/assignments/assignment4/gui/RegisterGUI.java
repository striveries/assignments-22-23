package assignments.assignment4.gui;

import static assignments.assignment1.NotaGenerator.isNumeric;
import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.member.MemberSystemGUI;

import javax.naming.NameAlreadyBoundException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;
    GridBagConstraints gbc;

    public RegisterGUI(LoginManager loginManager) {
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
        
        nameLabel = new JLabel("Masukkan nama Anda");
        mainPanel.add(nameLabel, gbc);

        nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(500, 30));
        nameTextField.setVisible(true);
        mainPanel.add(nameTextField, gbc);

        phoneLabel = new JLabel("Masukkan nomor handphone Anda:");
        mainPanel.add(phoneLabel, gbc);

        phoneTextField = new JTextField();
        phoneTextField.setPreferredSize(new Dimension(500, 30));
        phoneTextField.setVisible(true);
        mainPanel.add(phoneTextField, gbc);

        passwordLabel = new JLabel("Masukkan password Anda");
        mainPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(500, 30));
        passwordField.setVisible(true);
        mainPanel.add(passwordField, gbc);

        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(150, 30)); // setting ukuran
        registerButton.setVisible(true);
        registerButton.setFocusable(false);

        backButton = new JButton("Kembali");
        backButton.setPreferredSize(new Dimension(150, 30)); // setting ukuran
        backButton.setVisible(true);
        backButton.setFocusable(false);
        
        JButton[] buttons = {
            registerButton, backButton
        };
        ActionListener[] listeners = {
                e -> handleRegister(),
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
        reset();
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        // TODO
        // tambahkan member ke data
        String nama = nameTextField.getText();
        String noHp = phoneTextField.getText();
        String password = String.valueOf(passwordField.getPassword());
        if ((noHp.equals(""))||(password.equals(""))||(nama.equals(""))){
            JOptionPane.showMessageDialog(null, "Semua field diatas wajib diisi!", "Empty Field", JOptionPane.ERROR_MESSAGE);
        }
        else if (!isNumeric(noHp)){
            JOptionPane.showMessageDialog(null, "Nomor handphone harus berisi angka", "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
            phoneTextField.setText("");
        }
        else {
            Member newMember = loginManager.register(nama, noHp, password);
            if (newMember != null){
                JOptionPane.showMessageDialog(null, ("Berhasil membuat user dengan ID " + newMember.getId()),"Registration Successful", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, String.format("User dengan nama %s dan nomor hp %s sudah ada!", nama, noHp),"Registration Failed", JOptionPane.ERROR_MESSAGE);
            }
            reset();
            MainFrame.getInstance().navigateTo(HomeGUI.KEY);
        }
    }

    public void reset(){
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
    }

}

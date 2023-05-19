package assignments.assignment4.gui;

import static assignments.assignment1.NotaGenerator.isNumeric;
import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

// import library yang dibutuhkan
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class RegisterGUI extends JPanel {
    // inisiasi variabel yang dibutuhkan
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
        super(new BorderLayout()); 
        this.loginManager = loginManager; 

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(MainFrame.c1);
        gbc = new GridBagConstraints(); 
        initGUI(); // Menambahkan kompinen gui
        add(mainPanel, BorderLayout.CENTER); // menambahkan mainpanel ke kumpulan JPanel di mainframe
    }
    
    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // setting gridbagconstraints untuk tampilan layout gui
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // menambahkan komponen yang akan dimunculkan pada GUI Register
        nameLabel = new JLabel("Masukkan nama Anda:");
        mainPanel.add(nameLabel, gbc);

        nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(670, 30));
        nameTextField.setVisible(true);
        mainPanel.add(nameTextField, gbc);

        phoneLabel = new JLabel("Masukkan nomor handphone Anda:");
        mainPanel.add(phoneLabel, gbc);

        phoneTextField = new JTextField();
        phoneTextField.setPreferredSize(new Dimension(670, 30));
        phoneTextField.setVisible(true);
        mainPanel.add(phoneTextField, gbc);

        passwordLabel = new JLabel("Masukkan password Anda");
        mainPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(670, 30));
        passwordField.setVisible(true);
        mainPanel.add(passwordField, gbc);

        gbc.fill = GridBagConstraints.NONE;

        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(150, 50)); // setting ukuran
        registerButton.setForeground(MainFrame.c2);
        registerButton.setVisible(true);
        registerButton.setFocusable(false);

        backButton = new JButton("Kembali");
        backButton.setPreferredSize(new Dimension(150, 50)); // setting ukuran
        backButton.setVisible(true);
        backButton.setFocusable(false);
        
        // mengumpulkan button
        JButton[] buttons = {
            registerButton, backButton
        };
        // membuat actionlistener untuk tiap button
        ActionListener[] listeners = {
                e -> handleRegister(),
                e -> handleBack()
        };

        gbc.anchor = GridBagConstraints.CENTER;

        for (int i = 0; i < buttons.length; i++) { // iterasi tiap button
            JButton button = buttons[i];
            button.addActionListener(listeners[i]); // menambahkan actionlistener ke button
            mainPanel.add(button, gbc); // menambahkan button ke mainpanel
        }

    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        reset(); // mengosongkantiap elemen GUI seperti textfield
        MainFrame.getInstance().navigateTo(HomeGUI.KEY); // navigasi ke homeGUI
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        String nama = nameTextField.getText(); // mengambil informasi nama dari textfield
        String noHp = phoneTextField.getText(); // mengambil informasi noHP dari textfield
        String password = String.valueOf(passwordField.getPassword());  // mengambil informasi password dari passwordfield
        if ((noHp.equals(""))||(password.equals(""))||(nama.equals(""))){ // jika ada field yang kosong maka tidak valid
            JOptionPane.showMessageDialog(null, "Semua field diatas wajib diisi!", "Empty Field", JOptionPane.ERROR_MESSAGE);
        }
        else if (!isNumeric(noHp)){ // jika nomorhp bukan digit maka tidak valid
            JOptionPane.showMessageDialog(null, "Nomor handphone harus berisi angka", "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
            phoneTextField.setText("");
        }
        else {
            Member newMember = loginManager.register(nama.trim(), noHp, password); // inisiasi member baru dengan memanggil method register milik login manager
            if (newMember != null){
                JOptionPane.showMessageDialog(null, ("Berhasil membuat user dengan ID " + newMember.getId()),"Registration Successful", JOptionPane.INFORMATION_MESSAGE);
            }
            else { // jika newMember berisi null maka berarti tidak valid
                JOptionPane.showMessageDialog(null, String.format("User dengan nama %s dan nomor hp %s sudah ada!", nama, noHp),"Registration Failed", JOptionPane.ERROR_MESSAGE);
            }
            reset(); // mengosongkan tiap elemen GUI seperti textfield
            MainFrame.getInstance().navigateTo(HomeGUI.KEY);// navigasi ke homeGUI
        }
    }
    // method untuk mengosongkan tiap elemen GUI seperti textfield
    public void reset(){
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
    }

}

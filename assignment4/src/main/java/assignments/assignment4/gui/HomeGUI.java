package assignments.assignment4.gui;

import assignments.assignment3.nota.NotaManager;
import assignments.assignment4.MainFrame;
import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

// import library yang dibutuhkan
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeGUI extends JPanel{
    // insiiasi atribut yang akan digunakan
    public static final String KEY = "HOME"; 
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;
    GridBagConstraints gbc;

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout, Feel free to make any changes

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();  
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));
        initGUI();
        mainPanel.setBackground(MainFrame.c1); // setup warna background
        add(mainPanel, BorderLayout.CENTER); // menambahkan mainPanel ke JPanel di mainframe
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // membuat komponen yang akan muncul pada tampilan GUI
        titleLabel = new JLabel("Selamat datang di CuciCuci System!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel);

        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(150, 50)); // setting ukuran
        loginButton.setVisible(true);
        loginButton.setFocusable(false);
        
        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(150, 50)); // setting ukuran
        registerButton.setVisible(true);
        registerButton.setFocusable(false);

        toNextDayButton = new JButton("Next Day");
        toNextDayButton.setPreferredSize(new Dimension(150, 50)); // setting ukuran
        toNextDayButton.setVisible(true);
        toNextDayButton.setFocusable(false);

        JButton[] buttons = {
            loginButton, registerButton, toNextDayButton
        };
        ActionListener[] listeners = { // membuat actionlistener untuk masing-masing button
                e -> handleToLogin(),
                e -> handleToRegister(),
                e -> handleNextDay()
        };

        if (buttons.length != listeners.length) {
            throw new IllegalStateException("Number of buttons and listeners must be equal.");
        }
        // setting gridbagconstraints untuk layout GUI
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(5, 5, 5, 5);

        for (int i = 0; i < buttons.length; i++) { // iterasi tiap button
            JButton button = buttons[i];
            button.addActionListener(listeners[i]); // menambahkan actionlistener ke tiap button
            mainPanel.add(button, gbc); // menambahkan panel button ke mainpanel
        }

        dateLabel = new JLabel("Hari ini: " + fmt.format(cal.getTime())); // menampilkan detail tanggal hari tersebut
        mainPanel.add(dateLabel, gbc);

    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        MainFrame.getInstance().navigateTo(RegisterGUI.KEY); // navigasi ke halaman register
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame.getInstance().navigateTo(LoginGUI.KEY); // navigasi ke halaman login
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        NotaManager.toNextDay();
        JOptionPane.showMessageDialog(null, "Kamu tidur hari ini... zzz...", "sleeping time", JOptionPane.INFORMATION_MESSAGE);
        dateLabel.setText(("Hari ini:" + fmt.format(cal.getTime())));
    }
}

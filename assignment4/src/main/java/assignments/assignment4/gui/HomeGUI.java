package assignments.assignment4.gui;

import assignments.assignment3.nota.NotaManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static assignments.assignment3.nota.NotaManager.toNextDay;
import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

public class HomeGUI extends JPanel{
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
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        titleLabel = new JLabel("Selamat datang di CuciCuci System!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel);

        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(150, 30)); // setting ukuran
        loginButton.setVisible(true);
        loginButton.setFocusable(false);
        
        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(150, 30)); // setting ukuran
        registerButton.setVisible(true);
        registerButton.setFocusable(false);

        toNextDayButton = new JButton("Next Day");
        toNextDayButton.setPreferredSize(new Dimension(150, 30)); // setting ukuran
        toNextDayButton.setVisible(true);
        toNextDayButton.setFocusable(false);

        JButton[] buttons = {
            loginButton, registerButton, toNextDayButton
        };
        ActionListener[] listeners = {
                e -> handleToLogin(),
                e -> handleToRegister(),
                e -> handleNextDay()
        };

        if (buttons.length != listeners.length) {
            throw new IllegalStateException("Number of buttons and listeners must be equal.");
        }
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(5, 5, 5, 5);

        for (int i = 0; i < buttons.length; i++) {
            JButton button = buttons[i];
            button.addActionListener(listeners[i]);
            mainPanel.add(button, gbc);
        }

        dateLabel = new JLabel("Hari ini: " + fmt.format(cal.getTime()));
        mainPanel.add(dateLabel, gbc);

    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        MainFrame.getInstance().navigateTo(RegisterGUI.KEY);
        
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame.getInstance().navigateTo(LoginGUI.KEY);
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

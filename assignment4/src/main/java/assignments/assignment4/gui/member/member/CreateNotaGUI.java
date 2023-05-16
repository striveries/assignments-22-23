package assignments.assignment4.gui.member.member;

import static assignments.assignment1.NotaGenerator.isNumeric;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private JPanel mainPanel;
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;
    GridBagConstraints gbc;
    private String[] jenisPaket = {
        "Express", "Fast", "Reguler"
    };
    private boolean isSetrika = false;
    private boolean isAntar = false;

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

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
        // TODO
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        paketLabel = new JLabel("Paket Laundry:");
        paketLabel.setPreferredSize(new Dimension(280, 30));
        mainPanel.add(paketLabel, gbc);

        paketComboBox = new JComboBox<>(jenisPaket);
        paketComboBox.setPreferredSize(new Dimension(150, 30));
        paketComboBox.setVisible(true);
        gbc.gridx = 1;
        mainPanel.add(paketComboBox, gbc);
        
        ActionListener[] listeners = {
                e -> createNota(),
                e -> handleBack(),
                e -> showPaket()
        };

        showPaketButton = new JButton("Show Paket");
        showPaketButton.setPreferredSize(new Dimension(150, 30)); // setting ukuran
        showPaketButton.setVisible(true);
        showPaketButton.setFocusable(false);
        gbc.gridx = 2;
        showPaketButton.addActionListener(listeners[2]);
        mainPanel.add(showPaketButton, gbc);

        beratLabel = new JLabel("Berat Cucian (Kg):");
        beratLabel.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(beratLabel, gbc);

        beratTextField = new JTextField();
        beratTextField.setPreferredSize(new Dimension(150, 30));
        beratTextField.setVisible(true);
        gbc.gridx = 1;
        mainPanel.add(beratTextField, gbc);
        

        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000/kg)");
        setrikaCheckBox.setBounds(100,100, 50,50);  
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;  
        mainPanel.add(setrikaCheckBox, gbc);

        antarCheckBox = new JCheckBox("Tambah Antar Service (2000/4kg pertama, kemudian 500/kg)");
        antarCheckBox.setBounds(100,100, 50,50);  
        gbc.gridy = 3;
        mainPanel.add(antarCheckBox, gbc);

        createNotaButton = new JButton("Buat Nota");
        createNotaButton.setPreferredSize(new Dimension(620, 30)); // setting ukuran
        createNotaButton.setVisible(true);
        createNotaButton.setFocusable(false);
        gbc.gridy = 4;
        gbc.gridwidth = 3;  
        createNotaButton.addActionListener(listeners[0]);
        mainPanel.add(createNotaButton, gbc);

        backButton = new JButton("Kembali");
        backButton.setPreferredSize(new Dimension(620, 30)); // setting ukuran
        backButton.setVisible(true);
        backButton.setFocusable(false);
        gbc.gridy = 5;
        backButton.addActionListener(listeners[1]);
        mainPanel.add(backButton, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        // TODO
        String selectedPaket = (String) paketComboBox.getSelectedItem(); 
        String strBerat = beratTextField.getText();
        if(!strBerat.equals("")){
            if(isNumeric(beratTextField.getText())){
                int berat = Integer.parseInt(beratTextField.getText());
                if (berat > 0){
                    if (berat < 2) {
                        JOptionPane.showMessageDialog(null, "Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg", "Info", JOptionPane.INFORMATION_MESSAGE);
                        berat = 2;
                    }
                    
                    Nota newNota = new Nota(memberSystemGUI.getLoggedInMember(), berat, selectedPaket, fmt.format(cal.getTime()));
                    
                    if (setrikaCheckBox.isSelected()){
                        newNota.addService(new SetrikaService());
                    }
                    if (antarCheckBox.isSelected()){
                        newNota.addService(new AntarService());
                    }
                    NotaManager.addNota(newNota);
                    memberSystemGUI.getLoggedInMember().addNota(newNota);
                    JOptionPane.showMessageDialog(null, "Nota berhasil dibuat!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    reset();
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Berat Cucian harus berisi angka", "Error", JOptionPane.ERROR_MESSAGE);
        beratTextField.setText("");
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // TODO
        reset();
        MainFrame.getInstance().navigateTo(MemberSystemGUI.KEY);
    }

    private void reset(){
        paketComboBox.setSelectedIndex(0);
        beratTextField.setText("");
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
    }
}

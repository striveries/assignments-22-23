package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

// import library yang dibutuhkan
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MemberSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "MEMBER"; // setting key untuk class MemberSystemGUI

    public MemberSystemGUI(SystemCLI systemCLI) { // inisiasi constructor
        super(systemCLI);
    }

    @Override // getter KEY
    public String getPageName(){
        return KEY;
    }
    // getter untuk loggedInMember
    public Member getLoggedInMember(){
        return loggedInMember;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements MemberSystem.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        // membuat button yang akan muncul pada memberSystemGUI
        JButton createNotaButton = new JButton("Saya ingin laundry");
        createNotaButton.setVisible(true);
        createNotaButton.setFocusable(false);

        JButton showDetailNotaButton = new JButton("Lihat detail nota saya");
        showDetailNotaButton.setVisible(true);
        showDetailNotaButton.setFocusable(false); 
        return new JButton[]{ // return button yang telah dibuat
            createNotaButton, showDetailNotaButton
        };
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{ // return actionlistener yang akan digunakan oleh button yang telah dibuat
                e -> createNota(),
                e -> showDetailNota(),
        };
    }

    /**
     * Menampilkan detail Nota milik loggedInMember.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void showDetailNota() {
        String listDetailNota = ""; // menyimpan semua text detail nota
        JTextArea detailNotaTextArea = new JTextArea(); // tempat detail nota ditampilkan

        if (loggedInMember.getNotaList().length == 0){// jika tidak ada nota di notalist
            detailNotaTextArea.setText("Belum pernah laundry di CuciCuci, hiks :'(");
            
        } else{
            for (Nota nota:loggedInMember.getNotaList()) { // iterasi tiap nota di dalam notaLIst
                listDetailNota += (nota.toString() + "\n"); // menambahkan detail nota ke listdetailNota
            }
            detailNotaTextArea.setText(listDetailNota); // menambahkan isi total detial nota ke textarea
        }
        // setting textarea
        detailNotaTextArea.setColumns(30);
        detailNotaTextArea.setLineWrap(true);
        detailNotaTextArea.setWrapStyleWord(true);
        detailNotaTextArea.setSize(detailNotaTextArea.getPreferredSize().width, 1);
        detailNotaTextArea.setEditable(false);

        JScrollPane scrollPane =  new JScrollPane(detailNotaTextArea); // menambahkan textarea ke scrollPane agar bisa discroll
        scrollPane.setPreferredSize(new Dimension(400, 300)); // setting ukuran
        JOptionPane.showMessageDialog(null, scrollPane, "Detail Nota", JOptionPane.INFORMATION_MESSAGE); // menambahkan scrollpane ke optionpane agar menjadi popup
    }

    /**
     * Pergi ke halaman CreateNotaGUI.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void createNota() {
        // navigasi ke panel createnota
        MainFrame.getInstance().navigateTo(CreateNotaGUI.KEY);
    }

}

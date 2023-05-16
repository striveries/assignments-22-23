package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MemberSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "MEMBER";

    public MemberSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }

    @Override
    public String getPageName(){
        return KEY;
    }

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
        // TODO
        JButton createNotaButton = new JButton("Saya ingin laundry");
        createNotaButton.setVisible(true);
        createNotaButton.setFocusable(false);

        JButton showDetailNotaButton = new JButton("Lihat detail nota saya");
        showDetailNotaButton.setVisible(true);
        showDetailNotaButton.setFocusable(false); 
        return new JButton[]{
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
        return new ActionListener[]{
                e -> createNota(),
                e -> showDetailNota(),
        };
    }

    /**
     * Menampilkan detail Nota milik loggedInMember.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void showDetailNota() {
        // TODO
        String listDetailNota = "";
        JTextArea detailNotaTextArea = new JTextArea();

        if (loggedInMember.getNotaList().length == 0){
            detailNotaTextArea.setText("Belum pernah laundry di CuciCuci, hiks :'(");
            
        } else{
            for (Nota nota:loggedInMember.getNotaList()) {
                listDetailNota += (nota.toString() + "\n");
            }
            detailNotaTextArea.setText(listDetailNota);
        }
            
        detailNotaTextArea.setColumns(30);
        detailNotaTextArea.setLineWrap(true);
        detailNotaTextArea.setWrapStyleWord(true);
        detailNotaTextArea.setSize(detailNotaTextArea.getPreferredSize().width, 1);
        detailNotaTextArea.setEditable(false);

        JScrollPane scrollPane =  new JScrollPane(detailNotaTextArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Detail Nota", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Pergi ke halaman CreateNotaGUI.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void createNota() {
        // TODO
        MainFrame.getInstance().navigateTo(CreateNotaGUI.KEY);
    }

}

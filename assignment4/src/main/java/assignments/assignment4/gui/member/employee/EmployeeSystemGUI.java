package assignments.assignment4.gui.member.employee;

// import library yang dibutuhkan
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.gui.member.AbstractMemberGUI;
import javax.swing.*;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE"; // inisiasi key EmployeeSystem

    // constructor untuk EmployeeSystemGUI
    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }


    @Override 
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        // membuat button untuk nyuci time
        JButton cuciButton = new JButton("It's nyuci time");
        cuciButton.setVisible(true);
        cuciButton.setFocusable(false);

        // membuat button untuk nyuci time
        JButton displayNotaButton = new JButton("Display List Nota");
        displayNotaButton.setVisible(true);
        displayNotaButton.setFocusable(false); 
        return new JButton[]{
            cuciButton, displayNotaButton // return button yang akan dimunculkan pada GUI Employee System
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
        return new ActionListener[]{ // return actionListener yang akan digunakan oleh Button yang telah dibuat sebelumnya
                e -> cuci(),
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void displayNota() {
        if (NotaManager.notaList.length <= 0){ // jika belum ada nota yang terdaftar maka merilis pesan belum ada nota
            JOptionPane.showMessageDialog(null, "Belum ada nota", "List Nota", JOptionPane.ERROR_MESSAGE);
        } else {
            String notaStatus = "";
            for (Nota nota: NotaManager.notaList) { // iterasi tiap nota dalam notalist dan menampilkan statusnya saat ini
                notaStatus += (nota.getNotaStatus() + "\n");
            }
            JOptionPane.showMessageDialog(null, notaStatus,"List Nota", JOptionPane.INFORMATION_MESSAGE); // memunculkan status tiap nota
        }
        
    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() { 
        String nyuciResult = "";
        JOptionPane.showMessageDialog(null, String.format("Stand back! %s is beginning to nyuci!",loggedInMember.getNama()),"Nyuci Time", JOptionPane.INFORMATION_MESSAGE); // memunculkan pesan stand back

        if (NotaManager.notaList.length <= 0){ // jika belum ada nota yang terdaftar maka merilis pesan belum ada yang bisa dicuci
            JOptionPane.showMessageDialog(null, "Nothing to cuci here", "Nyuci Results", JOptionPane.ERROR_MESSAGE);
        } else {

            for (Nota nota: NotaManager.notaList) { // iterasi tiap nota dalam notalist dan mengerjakan tiap nota dengan method kerjakan
                nyuciResult += (nota.kerjakan() + "\n");
            }
            JOptionPane.showMessageDialog(null, nyuciResult,"Nyuci Results", JOptionPane.INFORMATION_MESSAGE); // menampilkan hasil cucian
        }

    }
    
}

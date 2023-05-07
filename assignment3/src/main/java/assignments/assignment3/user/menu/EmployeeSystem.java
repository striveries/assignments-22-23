package assignments.assignment3.user.menu;

// import library yang dibutuhkan 
import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.notaList;

public class EmployeeSystem extends SystemCLI {
    /**
     * Membuat object baru EmployeeSystem dan mendaftarkan Employee pada CuciCuci
     */
    public EmployeeSystem() {
        memberList = new Member[]{ // inisiasi employee
                new Employee("Dek Depe", "akuDDP"),
                new Employee("Depram", "musiktualembut"),
                new Employee("Lita Duo", "gitCommitPush"),
                new Employee("Ivan Hoshimachi", "SuamiSahSuisei"),
        };
    }

    /**
     * Memproses pilihan dari employee yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user logout.
     */
    @Override
    protected boolean processChoice(int choice) { // method untuk memproses pilihan employee
        boolean logout = false; 
        switch (choice) {
            case 1 -> nyuciTime();
            case 2 -> displayListNota();
            case 3 -> logout = true;
            default -> System.out.println("Pilihan tidak valid, silakan coba lagi.");
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Employee.
     */
    @Override 
    protected void displaySpecificMenu() { // meencetak pilihan menu
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }

    private void nyuciTime(){
        System.out.printf("Stand back! %s beginning to nyuci!\n", loginMember.getNama()); 
        for(Nota nota : notaList){
            System.out.println(nota.kerjakan()); // Employee mengerjakan tiap nota dalam notaList
        }
        
    }
    
    private void displayListNota(){
        for(Nota nota : notaList){ // mencetak status tiap nota dalam notaList
            System.out.println(nota.getNotaStatus());
        }
        
    }
}
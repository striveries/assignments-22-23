package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.notaList;

public class EmployeeSystem extends SystemCLI {

    /**
     * Membuat object baru EmployeeSystem dan mendaftarkan Employee pada CuciCuci
     */
    public EmployeeSystem() {
        memberList = new Member[]{
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
    protected boolean processChoice(int choice) {
        boolean logout = false;
        // TODO:
        
        switch (choice) {
            case 1 -> nyuciTime();
            case 2 -> displayListNota();
            case 3 -> logout = true;
            default -> System.out.println("Pilihan tidak valid, silakan coba lagi.");
        }
    
        // validasi?

        return logout;
    }

    /**
     * Displays specific menu untuk Employee.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }

    private void nyuciTime(){
        System.out.printf("Stand back! %s beginning to nyuci!\n", getNama());
        for(Nota nota : notaList){
            if (nota.isReady()){
                System.out.printf("Nota %i : %s\n", nota.getId(), nota.getNotaStatus)
            }
            else{
                System.out.printf("Nota %i : %s\n", nota.getId(), nota.doWork())
            }
        }
    }
    
    private void displayListNota(){
        for(Nota nota : notaList){
            System.out.printf("Nota %i : %s\n", nota.getId(), nota.getNotaStatus)
        }
        
    }
}
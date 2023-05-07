package assignments.assignment3.user.menu;

// import method yang diperlukan
import assignments.assignment3.user.Member;
import java.util.Scanner;

public abstract class SystemCLI {
    // inisiasi atribut yang dibutuhkan
    protected Member[] memberList = new Member[0];
    protected Member loginMember;
    protected Scanner in;

    /**
     * Otentikasi pengguna dengan ID dan password yang diberikan dan memulai sesi pengguna.
     * Akan berhenti jika logout atau ID / Password salah.
     *
     * @param in -> Scanner object untuk membaca input.
     * @param inputId -> ID user yang akan diautentikasi.
     * @param inputPassword -> password user yang akan diautentikasi.
     */
    public void login(Scanner in, String inputId, String inputPassword){ // method untuk autentikasi user saat login
        Member authMember = authUser(inputId, inputPassword); // auth user akan me-return user tersebut apabila input valid, apabila tidak valid maka akan return null
        if (authMember != null) { // jika authMember valid maka login sukses
            this.in = in;
            System.out.println("Login successful!");
            run(in, authMember);
            return;
        }
        System.out.println("Invalid ID or password."); // jika authMember tidak valid maka tidak bisa login
    };

    /**
     * Memulai sesi pengguna dan menangani input.
     *
     * @param in -> Scanner object untuk membaca input.
     * @param member -> Member object yang menggunakan sistem.
     */
    public void run(Scanner in, Member member){
        loginMember = member;
        boolean logout = false;
        while (!logout) {
            displayMenu();
            int choice = in.nextInt(); // validasiiiiiiiii
            in.nextLine();
            logout = processChoice(choice);
        }
        loginMember = null;
        System.out.println("Logging out...");
    }

    /**
     * Mengecek semua user dengan ID dan password yang diberikan.
     *
     * @param id -> ID pengguna yang akan diautentikasi.
     * @param pass -> password pengguna untuk mengautentikasi.
     * @return  Member object yang diautentikasi, null jika autentikasi gagal.
     */
    public Member authUser(String id, String pass) {
        for (Member user : memberList) { // iterasi tiap member dalam memberList
            if (!user.getId().equals(id)) { // jika id nya tidak sama denganid yang sedang diterasi maka loop ini akan di-skip
                continue;
            }
            if(user.login(id, pass)){ // jika return user login adalah true maka return Member user tersebut
                return user;
            }
            return null;
        }
        return null;
    };

    /**
     * Memeriksa apakah ada Member dengan ID yang diberikan.
     *
     * @param id -> ID yang akan diperiksa.
     * @return true jika ada member dengan ID yang diberikan, false jika tidak.
     */
    public boolean isMemberExist(String id){ // method untuk mengecek apakah member ada di dalam memberList
        for (Member member: memberList) { // iterasi tiap member dalam memberList
            if(member.getId().equals(id)){ // jika id yang sedang diiterasi sama dnegan id parameter maka akan return true, dimana user member exist
                return true;
            }
        }
        return false;
    }

    /**
     * Displays main menu untuk user yang menggunakan sistem.
     */
    protected void displayMenu(){ // mencetak main menu
        System.out.printf("\nLogin as : %s\nSelamat datang %s!\n\n", loginMember.getId(), loginMember.getNama());
        displaySpecificMenu(); // mencetak menu sesuai dengan jenis user / rolesnya
        System.out.print("Apa yang ingin Anda lakukan hari ini? ");
    }

    /**
     * Memproses pilihan dari pengguna yang menggunakan sistem sesuai dengan rolesnya.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    protected abstract boolean processChoice(int choice);

    /**
     * Displays specific menu sesuai class yang menginheritnya.
     */
    protected abstract void displaySpecificMenu();
}
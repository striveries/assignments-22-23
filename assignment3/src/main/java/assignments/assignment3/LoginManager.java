package assignments.assignment3;

// import library yang dibutuhkan
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;

public class LoginManager {
    // inisiasi atribut yang dibutuhkan
    private final EmployeeSystem employeeSystem;
    private final MemberSystem memberSystem;

    // inisiasi constructor
    public LoginManager(EmployeeSystem employeeSystem, MemberSystem memberSystem) {
        this.employeeSystem = employeeSystem;
        this.memberSystem = memberSystem;
    }

    /**
     * Method mapping dari ke SystemCLI yang sesuai.
     *
     * @param id -> ID dari user yang akan menggunakan SystemCLI
     * @return SystemCLI object yang sesuai dengan ID, null if  ID tidak ditemukan.
     */
    public SystemCLI getSystem(String id){ // mengarahkan user ke system yang sesuai dengan rolesnya berdasarkan ID
        if(memberSystem.isMemberExist(id)){ // jika id user ada di memberSystem maka akan diarahkan ke memberSystem
            return memberSystem;
        }
        if(employeeSystem.isMemberExist(id)){ // jika id user ada di employeeSystem maka akan diarahkan ke memberSystem
            return employeeSystem;
        }
        return null;
    }

    /**
     * Mendaftarkan member baru dengan informasi yang diberikan.
     *
     * @param nama -> Nama member.
     * @param noHp -> Nomor handphone member.
     * @param password -> Password akun member.
     * @return Member object yang berhasil mendaftar, return null jika gagal mendaftar.
     */
    public Member register(String nama, String noHp, String password) {   // mendaftarkan member baru 
        String id = NotaGenerator.generateId(nama, noHp); // membuat id member baru dengan method generateId milik NotaGenerator
        if (!memberSystem.isMemberExist(id)){ // member baru harus tidak ada di dalam memberSystem sebelumnya
            Member newMember = new Member(nama, NotaGenerator.generateId(nama, noHp), password); // instansiasi newMember
            memberSystem.addMember(newMember); // menambahkan member baru ke memberList
            return newMember;
        }
        return null;
    }
}
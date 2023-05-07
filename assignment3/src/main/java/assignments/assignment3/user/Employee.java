package assignments.assignment3.user;

public class Employee extends Member {
    public static int employeeCount = 0; // untuk menghitung jumlah employee

    // inisiasi constructor untuk kelas Employee
    public Employee(String nama, String password) {
        super(nama, generateId(nama), password);
    }
    /**
     * Membuat ID employee dari nama employee dengan format
     * NAMA_DEPAN-[jumlah employee, mulai dari 0]
     * Contoh: Dek Depe adalah employee pertama yang dibuat, sehingga IDnya adalah DEK-0
     *
     * @param nama -> Nama lengkap dari employee
     */
    private static String generateId(String nama) { // method yang membuat ID untuk employee
        String id = nama.toUpperCase();
         if (id.contains(" ")){ // memotong string agar hanya 1 kata pertama yang diambil
             id = nama.substring(0,nama.indexOf(' ')).toUpperCase();
         }
        id = id+"-"+employeeCount;
        employeeCount += 1;
        return id;
    }
}

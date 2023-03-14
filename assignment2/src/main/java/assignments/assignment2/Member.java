package assignments.assignment2;

// import assignments.assignment1.NotaGenerator;

/*
[1] Properti class Member lengkap sesuai requirement
[1] Tipe data semua properti pada class Member sesuai requirement
[2] Tidak ada properti internal class Member yang diakses dari tempat lain
[2] Melakukan komputasi ID dengan tepat
[2] Increment bonus counter saat Member dengan ID yang sama menggenerate Nota
[2] Reset bonus counter saat Member berhasil mendapat diskon
 */

public class Member {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter;

    public Member(String nama, String noHp) {
        // TODO: buat constructor untuk class ini
        this.nama = nama;
        this.noHp = noHp;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    public String getNama(){
        return nama;
    }

    public String getId(){
        return id;
    }

    public void setId(String newId){
        id = newId;
    }

    public String getNoHp(){
        return noHp;
    }
}

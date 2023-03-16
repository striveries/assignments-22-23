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
    private String nama; // inisiasi atribut dengan jenis private agar tidak bisa diakses secara langsung oleh kelas lain
    private String noHp;
    private String id;
    private int bonusCounter;

    public Member(String nama, String noHp) { // inisiasi constructor untuk class Member
        // TODO: buat constructor untuk class ini
        this.nama = nama;
        this.noHp = noHp;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    public String getNama(){ // getter untuk mengambil atribut nama
        return nama;
    }

    public String getId(){// getter untuk mengambil atribut id
        return id;
    }

    public void setId(String newId){ // setter untuk mengeset id
        id = newId; 
    }

    public String getNoHp(){// getter untuk mengambil atribut no hp
        return noHp;
    }

    public int getBonusCounter(){ // getter untuk mengambil atribut bonus counter
        return bonusCounter;
    }

    public void plusBonusCounter(){ // menambahkan bonus counter 1 nilai
        bonusCounter += 1;
    }

    public void resetBonusCounter(){ // mereset ulang nilai bonus counter menjadi 0
        bonusCounter = 0;
    }

}

package assignments.assignment3.user;

// import library yang dibutuhkan
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

public class Member {
    // inisiasi atribut yang dibutuhkan
    protected String id;
    protected String password;
    protected String nama;
    protected String noHp;
    protected Nota[] notaList = new Nota[0];

    // inisiasi constructor Member
    public Member(String nama, String id, String password) {
        this.nama = nama;
        this.id = id;
        this.password = password;
    }

    /**
     * Method otentikasi member dengan ID dan password yang diberikan.
     *
     * @param id -> ID anggota yang akan diautentikasi.
     * @param password -> password anggota untuk mengautentikasi.
     * @return true jika ID dan password sesuai dengan instance member, false jika tidak.
     */
    public boolean login(String id, String password) { 
        return id.equals(this.id) && authenticate(password);  // jika id dan password parameter valid maka return true
    }

    /**
     * Menambahkan nota baru ke NotaList instance member.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public void addNota(Nota nota) {
        Nota[] temp = new Nota[notaList.length + 1]; // membuat temporary variable array dengan panjang 1 lebih besar dari notaList
        for (int i = 0; i < temp.length - 1; i++) { // iterasi sambil menyalin isi notaList ke temp
            temp[i] = notaList[i];
        }
        temp[temp.length-1] = nota; // menambahkan nota baru ke array temp
        notaList = temp; // meng-assign nilai temp var ke notaList

        NotaManager.addNota(nota); // menambahkan nota ke array kumpulan nota di NotaManager
        System.out.println("Nota berhasil dibuat!");
    }

    /**
     * Method otentikasi member dengan password yang diberikan.
     *
     * @param password -> sandi password anggota untuk mengautentikasi.
     * @return true jika ID dan password sesuai dengan instance member, false jika tidak.
     */
    protected boolean authenticate(String password) {
        if(password.equals(this.password)){ // jika password dari parameter sama dengan password milik member maka valid dan return true
            return true;
        }
        return false;
    }

    // Dibawah ini adalah getter

    public String getNama() {
        return nama;
    }

    public String getId() {
        return id;
    }

    public Nota[] getNotaList() {
        return notaList;
    }
}
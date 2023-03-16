package assignments.assignment2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
[1] Properti class Nota lengkap sesuai requirement
[1] Tipe data semua properti pada class Nota sesuai requirement
[2] Tidak ada properti internal Class Nota yang diakses dari tempat lain
[3] Mengimplementasikan method nextDay dengan tepat / sisaHariPengerjaan dikelola dengan tepat
[3] Mengimplementasikan ambil nota dengan tepat
 */

public class Nota {
    // inisiasi constructor  dengan jenis private agar tidaj bisa diakses dari tempat lain
    private int idNota;
    private String paket;
    private Member member;
    private int berat;
    private String tanggalMasuk;
    private boolean isReady;
    private int harga;
    private int sisaHariPengerjaan;
    private static int idNotaCounter = 0; // inisiasi idNotaCounter dengan modifier static karena variabel ini tidak bergantung pada objek tertentu
    

    // TODO: tambahkan attributes yang diperlukan untuk class ini
    public Nota(Member member, String paket, int berat, String tanggalMasuk) { // inisiasi constructor kelas Nota
        // TODO: buat constructor untuk class ini
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        setHargaWaktu(paket); // mengeset sisa hari pengerjaan dan harga sesuai dengan paket
        setIsReady(); // mengeset nilai isReady sesuai dengan nilai sisa hari pengerjaan
        setIdNota(); // mengeset nilai id nota sesuai dengan nilai idnotacounter saat ini
        
    }
    
    // TODO: tambahkan methods yang diperlukan untuk class ini

    public int getIdNota(){ // getter untuk mengambil atribut idNota
        return idNota;
    }
    
    public void setIdNota(){ // setter untuk mengubah atribut nama
        idNota = idNotaCounter;
        idNotaCounter++;
    }

    public int getBerat(){ // getter untuk mengambil atribut berat
        return berat;
    }

    public String getTanggalMasuk(){ // getter untuk mengambil atribut tanggal masuk
        return tanggalMasuk;
    }

    public String getPaket(){ // getter untuk mengambil atribut paket
        return paket;
    }

    public int getHarga(){ // getter untuk mengambil atribut harga
        return harga;
    }

    public int getSisaHariPengerjaan(){ // getter untuk mengambil atribut sisa hari pengerjaan
        return sisaHariPengerjaan;
    }

    public boolean getIsReady(){ // getter untuk mengambil atribut isready
        return isReady;
    }

    public void setIsReady(){ // Setter untuk mengubah atribut nama sesuai sisaharipengerjaan
        if (sisaHariPengerjaan > 0){
            isReady = false;
        }
        else{
            isReady = true;
        }
    }
 
    public void setHargaWaktu(String paket){ // mengeset sisa hari pengerjaan dan harga sesuai dengan paket
        // klasifikasi paket laundry dengan perbedaan harga dan waktu pengerjaan
        if (paket.equalsIgnoreCase("express")){
            sisaHariPengerjaan = 1;
            harga = 12000;
        }
        else if (paket.equalsIgnoreCase("fast")){
            sisaHariPengerjaan = 2;
            harga = 10000;
        }
        else if (paket.equalsIgnoreCase("reguler")){
            sisaHariPengerjaan = 3;
            harga = 7000;
        }
    }

    public void nextDay(){ // mengurangi sisa hari pengerjaan 
        if (sisaHariPengerjaan > 0){
            sisaHariPengerjaan -= 1;
        }
        else{
            sisaHariPengerjaan = 0;
        }
        setIsReady();
    }

    public String getStatus(){ // method untuk mengecek apakah nota sudah bisa diambil atau belum
        if (isReady == true){
            return "Sudah dapat diambil!";
        }
        return "Belum bisa diambil :(";
    }

    public String generateNota(String id, String paket, int berat, String tanggalTerima){
        
        // memformat tanggal dan menambahkan tanggal selesai berdasarkan waktu pengerjaan (laundryTime)
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // mencetak sesuai pattern yang dibutuhkan
        LocalDate dateTerima = LocalDate.parse(tanggalTerima, dateTimeFormatter); // mengeset tanggal terima sesuai pattern
        LocalDate dateSelesai = dateTerima.plusDays(sisaHariPengerjaan); // memberikan perkiraan hari selesai dengan menambahkan sisa hari pengerjaan ke hari awal
        String dateReceived = dateTerima.format(dateTimeFormatter);  // memformat ulang dalam bentuk string
        String dateCompleted = dateSelesai.format(dateTimeFormatter);
        String tulisanHarga;
        if (member.getBonusCounter() == 3){ // mengecek apabila bonus counter telah mencapai 3, maka akan diberikan diskon
            tulisanHarga =  berat + " kg x " + harga + " = " + harga*berat+ " = " + harga*berat/2 +  " (Discount member 50%!!!)\n";
            member.resetBonusCounter(); // nilai bonus counter direset ulang
        }
        else{
            tulisanHarga = berat + " kg x " + harga + " = " + harga*berat+ "\n" ; // jika tidak, maka akan dicetak tanpa diskon
        }

        String nota = "ID    : "+id+"\n" + // mencetak nota
                "Paket : "+paket+"\n" +
                "Harga :\n" +
                tulisanHarga +
                "Tanggal Terima  : "+dateReceived+"\n" +
                "Tanggal Selesai : "+dateCompleted+"\n" + 
                "Status      	: "+getStatus();
        return nota;
    }

  
}

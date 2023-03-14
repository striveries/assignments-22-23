package assignments.assignment2;

import assignments.assignment1.NotaGenerator;
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
    private int idNota;
    private String paket;
    private Member member;
    private int berat;
    private String tanggalMasuk;
    private boolean isReady;
    private int harga;
    private int sisaHariPengerjaan;
    

    // TODO: tambahkan attributes yang diperlukan untuk class ini
    public Nota(String paket, int berat, String tanggalMasuk) {
        // TODO: buat constructor untuk class ini
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        setHargaWaktu(paket);
        setIsReady();
    }

    public int getIdNota(){
        return idNota;
    }
    
    public void setIdNota(int newIdNota){
        idNota = newIdNota;
    }

    public String getPaket(){
        return paket;
    }

    public int getHarga(){
        return harga;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }

    public boolean getIsReady(){
        return isReady;
    }

    public void setIsReady(){
        if (sisaHariPengerjaan > 0){
            isReady = false;
        }
        else{
            isReady = true;
        }
    }

    public void setHargaWaktu(String paket){
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

    public void nextDay(){
        if (sisaHariPengerjaan > 0){
            sisaHariPengerjaan -= 1;
        }
        else{
            sisaHariPengerjaan = 0;
        }
        setIsReady();
    }

    public String getStatus(){
        if (isReady == true){
            return "Sudah dapat diambil!";
        }
        return "Belum bisa diambil :(";
    }


    public String generateNota(String id, String paket, int berat, String tanggalTerima){
        // untuk berat kurang dari 2, dibulatkan menjadi 2
        if (berat < 2){
            berat = 2;
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
        }
        
        // memformat tanggal dan menambahkan tanggal selesai berdasarkan waktu pengerjaan (laundryTime)
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTerima = LocalDate.parse(tanggalTerima, dateTimeFormatter);
        LocalDate dateSelesai = dateTerima.plusDays(sisaHariPengerjaan);
        String dateReceived = dateTerima.format(dateTimeFormatter);
        String dateCompleted = dateSelesai.format(dateTimeFormatter);

        String nota = "ID    : "+id+"\n" +
                "Paket : "+paket+"\n" +
                "Harga :\n" +
                berat + " kg x " + harga + " = " + harga*berat+ "\n" +
                "Tanggal Terima  : "+dateReceived+"\n" +
                "Tanggal Selesai : "+dateCompleted+"\n" + 
                "Status      	: "+this.getStatus()+"\n";
        return nota;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
  
}

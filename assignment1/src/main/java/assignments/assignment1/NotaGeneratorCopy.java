/*
 * Nama : Calista Sekar Pamaja
 * NPM  : 2206082064
 * Kelas DDP2 D
 */

 package assignments.assignment1;
 import java.time.LocalDate; // Import the LocalDateTime untuk tanggal
 import java.time.format.DateTimeFormatter; // Import DateTimeFormatter untuk memformat tanggal
 import java.util.Scanner;
 import java.util.*;
 
 public class NotaGeneratorCopy {
     // memanggil scanner
     private static final Scanner input = new Scanner(System.in);
     /* Program berjalan di main program utama
      * Terdapat beberapa method dalam program ini :
      * generateID : memproses parameter nama dan nomor hp menjadi nomor ID
      * generateNota : memproses parameter yang diterima menjadi struk nota
      */
     public static void main(String[] args) {
         // main program yang memunculkan interface program
         boolean a = true; // variabel untuk mengontrol do-while loop
         do{
             printMenu(); //memanggil method untuk memunculkan menu pilihan aktivitas
             System.out.print("Pilihan : ");
             try {  // menggunakan try-catch untuk menangani kesalahan input dari user
                 int choice = input.nextInt(); 
                 input.nextLine();
                 System.out.println("================================");
                 // conditional untuk 3 pilihan menu : 0 : exit / 1 : buat ID / 2 : buat Nota
                 if (choice == 0){ // menu exit
                     System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                     a = false;
                 }
                 else if (choice == 1){ // menu buat ID
                     System.out.println("Masukkan nama Anda: ");
                     String name = input.nextLine();
                     System.out.println("Masukkan nomor handphone Anda: ");
                     String noHP = input.nextLine();
 
                     while (noHP.matches("\\d+") == false){ // loop untuk validasi nomor HP
                         System.out.println("Nomor hp hanya menerima digit");
                         System.out.println("Masukkan nomor handphone Anda: ");
                         noHP = input.nextLine();
                     }
                     System.out.println("ID Anda : " + generateId(name, noHP));
                 }
                 else if (choice == 2){ // menu buat nota
                     System.out.println("Masukkan nama Anda: ");
                     String name = input.nextLine();
                     System.out.println("Masukkan nomor handphone Anda: ");
                     String noHP = input.nextLine();
                     while (noHP.matches("\\d+") == false){ // loop untuk validasi nota
                         System.out.println("Nomor hp hanya menerima digit");
                         System.out.println("Masukkan nomor handphone Anda: ");
                         noHP = input.nextLine();
                     }
                     System.out.println("Masukkan tanggal terima: ");
                     String tanggalTerima = input.nextLine();
                     System.out.println("Masukkan paket laundry: ");
                     String paket = input.nextLine();
                     paket = paket.toLowerCase();
                     // loop untuk validasi paket laundry
                     while (!("express".equalsIgnoreCase(paket) || "reguler".equalsIgnoreCase(paket) || "fast".equalsIgnoreCase(paket))){
                         if (paket.equals("?")){
                             showPaket();
                             System.out.println("Masukkan paket laundry: ");
                             paket = input.nextLine();
                             continue;
                         }
                         System.out.printf("Paket %s tidak diketahui", paket);
                         System.out.println();
                         System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                         System.out.println("Masukkan paket laundry: ");
                         paket = input.nextLine();
                     }
                     // loop untuk meminta input dan validasi berat cucian
                     int beratCucian = 0;
                     while (true) {
                         try {
                             System.out.println("Masukkan berat cucian Anda [Kg]: ");
                             beratCucian = Integer.parseInt(input.nextLine());
                             if (beratCucian <= 0) {
                                 throw new NumberFormatException();
                             }
                             break; // Input is valid, break out of the loop
                         } catch (NumberFormatException e) {
                             System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                         }
                     }
                     // memanggil dan mencetak method generate nota          
                     System.out.println(generateNota(generateId(name, noHP), paket, beratCucian, tanggalTerima));            }
         } catch (InputMismatchException e){ // menangani error dari kesalahan input user
                 System.out.println("Pilihan tidak diketahui, silakan periksa kembali!");
                 input.nextLine();  
             }
         }  
         while (a); // loop akan berjalan selama a bernilai true
     } 
     
     public static boolean validasiNoHp(String noHp){
        while (noHp.matches("\\d+") == false){ // loop untuk validasi nomor HP
            System.out.println("Nomor hp hanya menerima digit");
            System.out.println("Masukkan nomor handphone Anda: ");
            noHp = input.nextLine();
        }
        return true;
     }

     /**
      * Method untuk menampilkan menu di NotaGenerator.
      */
     private static void printMenu() {
         System.out.println("Selamat datang di NotaGenerator!");
         System.out.println("==============Menu==============");
         System.out.println("[1] Generate ID");
         System.out.println("[2] Generate Nota");
         System.out.println("[0] Exit");
     }
     /**
      * Method untuk menampilkan paket.
      */
     private static void showPaket() {
         System.out.println("+-------------Paket-------------+");
         System.out.println("| Express | 1 Hari | 12000 / Kg |");
         System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
         System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
         System.out.println("+-------------------------------+");
     }
     // method untuk menghasilkan id
     public static String generateId(String nama, String nomorHP){
         String numID = nama.toUpperCase();
         if (numID.contains(" ")){ // memotong string agar hanya 1 kata pertama yang diambil
             numID = nama.substring(0,nama.indexOf(' ')).toUpperCase();
         }
         int checkSum = 7; // menghitung checksum dengan default 7 dari spacing

         // menghitung checksum dari nama
         for (int i=0; i<numID.length(); i++){
             char letter = numID.charAt(i);
             if (((int)letter > 47)&&((int)letter < 58)){ // check berdasarkan ascii number angka
                checkSum += (int) letter - 48;
             }
            else if (((int)letter > 64)&&((int)letter < 91)){ // check berdasarkan ascii number huruf
                checkSum += (int) letter - 64;
            }
            else{ // selain angka dan huruf dihitung sebagai 7
                checkSum += 7;
            }
         }
         // menghitung checksum dari nomor hp
         for(int j=0;j<nomorHP.length();j++){ 
             checkSum += Character.getNumericValue(nomorHP.charAt(j));
         }
         String checkSumStr = String.format("%02d", (checkSum%100));
         String result = numID+"-"+nomorHP+"-"+checkSumStr;
         return result;
     }
 
     // method untuk menghasilkan nota
     public static String generateNota(String id, String paket, int berat, String tanggalTerima){
         // untuk berat kurang dari 2, dibulatkan menjadi 2
         if (berat < 2){
             berat = 2;
             System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
         }
         int laundryTime =   0;
         int harga = 0;
         // klasifikasi paket laundry dengan perbedaan harga dan waktu pengerjaan
         if (paket.equalsIgnoreCase("express")){
             laundryTime = 1;
             harga = 12000;
         }
         else if (paket.equalsIgnoreCase("fast")){
             laundryTime = 2;
             harga = 10000;
         }
         else if (paket.equalsIgnoreCase("reguler")){
             laundryTime = 3;
             harga = 7000;
         }
         // memformat tanggal dan menambahkan tanggal selesai berdasarkan waktu pengerjaan (laundryTime)
         DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         LocalDate dateTerima = LocalDate.parse(tanggalTerima, dateTimeFormatter);
         LocalDate dateSelesai = dateTerima.plusDays(laundryTime);
         String dateReceived = dateTerima.format(dateTimeFormatter);
         String dateCompleted = dateSelesai.format(dateTimeFormatter);
         
         System.out.println("Nota Laundry");    
         String nota = "ID    : "+id+"\n" +
                 "Paket : "+paket+"\n" +
                 "Harga :\n" +
                 berat + " kg x " + harga + " = " + harga*berat+ "\n" +
                 "Tanggal Terima  : "+dateReceived+"\n" +
                 "Tanggal Selesai : "+dateCompleted;
         return nota;
     }
 }
 
 
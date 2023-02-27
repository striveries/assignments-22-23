package assignments.assignment1;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.*;

public class NotaGene {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        // TODO: Implement interface menu utama
        
        boolean a = true;
        do{
            printMenu();
            System.out.print("Pilihan : ");
            try { 
                int choice = input.nextInt();
                input.nextLine();
                System.out.println("================================");
            if (choice == 0){
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                a = false;
            }
            else if (choice == 1){ // generate ID
                System.out.println("Masukkan nama Anda: ");
                String name = input.next();
                input.nextLine();
                System.out.println("Masukkan nomor handphone Anda: ");
                String noHP = input.next();

                while (noHP.matches("\\d+") == false){
                    System.out.println("Nomor hp hanya menerima digit");
                    System.out.println("Masukkan nomor handphone Anda: ");
                    noHP = input.next();
                }
                System.out.println("ID Anda : " + generateId(name, noHP));
            }
            
            else if (choice == 2){
                System.out.println("Masukkan nama Anda: ");
                String name = input.next();
                input.nextLine();
                System.out.println("Masukkan nomor handphone Anda: ");
                String noHP = input.next();
                input.nextLine();
                System.out.println("Masukkan tanggal terima: ");
                String tanggalTerima = input.next();
                System.out.println("Masukkan paket laundry: ");
                String paket = input.next();
                paket = paket.toLowerCase();

                while (!("express".equalsIgnoreCase(paket) || "reguler".equalsIgnoreCase(paket) || "fast".equalsIgnoreCase(paket))){
                    if (paket.equals("?")){
                        showPaket();
                        System.out.println("Masukkan paket laundry: ");
                        paket = input.next();
                        continue;
                    }
                    System.out.printf("Paket %s tidak diketahui", paket);
                    System.out.println();
                    System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                    System.out.println("Masukkan paket laundry: ");
                    paket = input.next();
                    input.nextLine();
                }
                    
                int beratCucian = 0;
                while (true) {
                    try {
                        System.out.print("Masukkan berat cucian Anda [Kg]: ");
                        beratCucian = Integer.parseInt(input.nextLine());
                        if (beratCucian <= 0) {
                            throw new NumberFormatException();
                        }
                        break; // Input is valid, break out of the loop
                    } catch (NumberFormatException e) {
                        System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    }
                }          
                generateNota(generateId(name, noHP), paket, beratCucian, tanggalTerima);
            }
         } catch (InputMismatchException e){
                System.out.println("Pilihan tidak diketahui, silakan periksa kembali!");
                input.nextLine();  
            }
        }  
        while (a);
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

    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing
     *
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
     */
    public static String generateId(String nama, String nomorHP){
        // TODO: Implement generate ID sesuai soal.
            String numID = nama.toUpperCase();

        int checkSum = 7;
        for (int i=0; i<nama.length(); i++){
            char letter = numID.charAt(i);
            checkSum += (int) letter - 64;
        }
        for(int j=0;j<nomorHP.length();j++){
            checkSum += Character.getNumericValue(nomorHP.charAt(j));
        }
        String checkSumStr = String.format("%02d", checkSum);
        String result = numID+"-"+nomorHP+"-"+checkSumStr;
        return result;
    }

    /**
     *
     * Method untuk membuat Nota.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing.
     *
     * @return string nota dengan format di bawah:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
     *         <p>Tanggal Terima  : [tanggalTerima]
     *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
     */

    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        // TODO: Implement generate nota sesuai soal.
        if (berat < 2){
            berat = 2;
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
        }
        System.out.println("Nota Laundry");
        System.out.println("ID    : " + id);
        System.out.println("Paket : " + paket);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        try{  
            cal.setTime(formatter.parse(tanggalTerima));
         }catch(Exception e){  
            System.out.println();  
          } 
        
        paket = paket.toLowerCase();
        int laundryTime =   0;
        int harga = 0;
        /*
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        Date date = formatter.parse(tanggalTerima);
        Date tanggalSelesai = date.
         */
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
        cal.add(Calendar.DAY_OF_MONTH, laundryTime);
        String tanggalSelesai = formatter.format(cal.getTime());
        System.out.println("Harga :");
        System.out.println(berat + " kg x " + harga + " = " + harga*berat);
        System.out.println("Tanggal Terima : " + tanggalTerima);
        System.out.println("Tanggal Selesai : " + tanggalSelesai);
        return "success"; // bingung returnnya gimanaya
    }
}


package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;

import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Nota> notaList = new ArrayList<Nota>();
    private static ArrayList<Member> memberList = new ArrayList<Member>();
    private static int idNotaCounter = 0;

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser(); // generate member
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser(); // list member
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    private static void handleGenerateUser() {
        // TODO: handle generate user
        System.out.println("Masukkan nama Anda: ");
        String nama = input.nextLine();
        System.out.println("Masukkan nomor handphone Anda: ");
        String noHP = input.nextLine();

        while (noHP.matches("\\d+") == false){ // loop untuk validasi nomor HP
            System.out.println("Field nomor hp hanya menerima digit.");
            noHP = input.nextLine();
        }

        if (validateMember(generateId(nama, noHP)) == false){
            Member newMember = new Member(nama, noHP);
            memberList.add(newMember);
            newMember.setId(generateId(nama, noHP));
            System.out.printf("Berhasil membuat member dengan ID %s!\n", newMember.getId());
        }
        else {
            System.out.printf("Member dengan nama %s dan nomor hp %s sudah ada!\n", nama, noHP);
        }
    }

    private static void handleGenerateNota() {
        System.out.println("Masukan ID member: ");
        String id = input.nextLine();
       
        if (validateMember(id) == false){
           System.out.printf("Member dengan %s tidak ditemukan!\n",id);
        }
        else {
            System.out.println("Masukan paket laundry: ");
            String paket = input.nextLine();
            paket = paket.toLowerCase();
            // loop untuk validasi paket laundry
            while (!("express".equalsIgnoreCase(paket) || "reguler".equalsIgnoreCase(paket) || "fast".equalsIgnoreCase(paket))){
                if (paket.equals("?")){
                    System.out.println("+-------------Paket-------------+");
                    System.out.println("| Express | 1 Hari | 12000 / Kg |");
                    System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
                    System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
                    System.out.println("+-------------------------------+");
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
            System.out.printf("[ID Nota = %d]\n",idNotaCounter);
            idNotaCounter += 1;
            // memanggil dan mencetak method generate nota          
            String tanggalMasuk = fmt.format(cal.getTime());
            Nota newNota = new Nota(paket, beratCucian, tanggalMasuk);
            notaList.add(newNota);
            System.out.println(newNota.generateNota(id, paket, beratCucian, tanggalMasuk)); 
         }   
}

    private static void handleListNota() {
        // TODO: handle list semua nota pada sistem
        System.out.printf("Terdaftar %d nota dalam sistem.\n",notaList.size());
        for (int i=0;i<notaList.size();i++){
            System.out.printf("- [%d] Status      	: %s\n",i,notaList.get(i).getStatus());
        }
    }

    private static void handleListUser() {
        // TODO: handle list semua user pada sistem
        System.out.printf("Terdaftar %d member dalam sistem.\n",memberList.size());
        for (int i=0;i<memberList.size();i++){
            Member thisMember = memberList.get(i);
            System.out.printf("- %s : %s\n", thisMember.getId(), thisMember.getNama());
        }
    }

    private static void handleAmbilCucian() {
        // TODO: handle ambil cucian
        int idNota = 0;
        
        while (true) {
            try {
                System.out.println("Masukan ID nota yang akan diambil: ");
                idNota = Integer.parseInt(input.nextLine());
                if (idNota < 0) {
                    throw new NumberFormatException();
                }
                break; // Input is valid, break out of the loop
            } catch (NumberFormatException e) {
                System.out.println("ID nota berbentuk angka!");
            }
        }

        if (validateNota(idNota) == false){
            System.out.printf("Nota dengan ID %d tidak ditemukan!\n", idNota);
        }
        else{
            for (Nota thisNota : notaList){
                if (thisNota.getIsReady()){
                    System.out.printf("Nota dengan ID %d berhasil diambil!", idNota);
                    System.out.println(notaList);
                    notaList.remove(idNota);
                    System.out.println(notaList);
                    break;
                }
                else{
                    System.out.printf("Nota dengan ID %d gagal diambil!\n", idNota);
                    break;
                }
            }
        }

        // for (Nota nota : notaList){
        //     if (idNota == nota.getIdNota()){
        //         if (nota.getIsReady()){
        //             // belom validasi is ready
        //             System.out.printf("Nota dengan ID %d berhasil diambil!", idNota);
        //             System.out.println(notaList);
        //             notaList.remove(idNota);
        //             System.out.println(notaList);
        //         }
        //         else{
        //             System.out.printf("Nota dengan ID %d gagal diambil!\n", idNota);
        //         }
        //     }
        // }
        // System.out.printf("Nota dengan ID %d tidak ditemukan!\n", idNota);

    }


    private static void handleNextDay() {
        // TODO: handle ganti hari
        System.out.println("Dek Depe tidur hari ini... zzz...");

        for(Nota thisNota : notaList){
            thisNota.nextDay();
            if (thisNota.getIsReady() == true){
                System.out.printf("Laundry dengan nota ID %d sudah dapat diambil!\n", thisNota.getIdNota());
            }
        }
        
        System.out.println("Selamat pagi dunia!");
        System.out.println("Dek Depe: It's CuciCuci Time.");;
    }

    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

    private static boolean validateMember(String idMember){
        for (Member member : memberList){
            if (idMember.equals(member.getId())){
                return true;
            }
        }
        return false;
    }

    private static boolean validateNota(int idNota){
        for (Nota nota : notaList){
            if (idNota == nota.getIdNota()){
                return true;
            }
        }
        return false;
    }

 
    

    

}

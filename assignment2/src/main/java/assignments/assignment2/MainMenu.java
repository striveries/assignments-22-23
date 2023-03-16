package assignments.assignment2;

//mengimport library yang dibutuhkan
import java.text.SimpleDateFormat; //  library untuk formatting tanggal
import java.util.Calendar; // library untuk tanggal harian
import java.util.Scanner; // library untuk meminta input
import java.util.ArrayList; // library untuk data structure

import static assignments.assignment1.NotaGenerator.*; // mengimport TP 1 untuk menggunakan method generateId

public class MainMenu {
    private static final Scanner input = new Scanner(System.in); // inisiasi scanner
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy"); // membuat format untuk tanggal
    private static Calendar cal = Calendar.getInstance(); // inisiasi kalendar harian
    private static ArrayList<Nota> notaList = new ArrayList<Nota>(); // inisiasi arraylist untuk menampung nota aktif
    private static ArrayList<Member> memberList = new ArrayList<Member>(); // inisiasi arraylist untuk menampung member

    public static void main(String[] args) { // main program untuk meminta input pilihan menu yang ingin dijalankan
        boolean isRunning = true;
        while (isRunning) { // selama isRunning bernilai true, program akan terus berjalan
            printMenu(); //mencetak pilihan menu
            System.out.print("Pilihan : "); 
            String command = input.nextLine(); // meminta input pilihan menu
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

    /* Method untuk menambahkan member baru 
        1. input nama dan nomor hp akan divalidasi apakah sudah ada atau belum dengan memprosesnya ke method generateId
        2. jika input valid maka akan diinisiasi newMember yang kemudian ditambahkan ke memberList
        3. newMember tersebut kemudian diset id nya sesuai dengan output generateId
     */ 
    private static void handleGenerateUser() {
        // TODO: handle generate user
        // meminta input ke user
        System.out.println("Masukkan nama Anda: ");
        String nama = input.nextLine();
        System.out.println("Masukkan nomor handphone Anda: ");
        String noHP = input.nextLine();

        // validasi nomor HP dengan regex, program keluar dari loop hanya jika input valid
        while (noHP.matches("\\d+") == false){ // jika bukan digit maka user perlu input ulang
            System.out.println("Field nomor hp hanya menerima digit.");
            noHP = input.nextLine();
        }
        
        // validasi apakah member dengan id yang sama sudah pernah ada
        if (validateMember(generateId(nama, noHP)) == false){ // jika belum ada, newMember diinisasi dan ditambahkan ke memberList
            Member newMember = new Member(nama, noHP);
            memberList.add(newMember);
            newMember.setId(generateId(nama, noHP)); // mengeset id newMember sesuai output method generateId
            System.out.printf("Berhasil membuat member dengan ID %s!\n", newMember.getId());
        }
        else { // 
            System.out.printf("Member dengan nama %s dan nomor hp %s sudah ada!\n", nama, noHP);
        }
    }

    private static void handleGenerateNota() {
        System.out.println("Masukan ID member: ");
        String id = input.nextLine();
        Member thisMember = null; 
        
        if (validateMember(id) == false){
            System.out.printf("Member dengan %s tidak ditemukan!\n",id);
        }
        else {
            System.out.println("Masukan paket laundry: ");
            String paket = input.nextLine();

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
            // untuk berat kurang dari 2, dibulatkan menjadi 2
            if (beratCucian < 2){
                beratCucian = 2;
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            }
        
            // memanggil dan mencetak method generate nota          
            String tanggalMasuk = fmt.format(cal.getTime());
            for (Member member : memberList){
                if (id.equals(member.getId())){
                    thisMember = member;
                }
            }
            thisMember.plusBonusCounter(); 
            Nota newNota = new Nota(thisMember, paket, beratCucian, tanggalMasuk);
            notaList.add(newNota);
            System.out.println("Berhasil menambahkan nota!");
            System.out.printf("[ID Nota = %d]\n", newNota.getIdNota());
            System.out.println(newNota.generateNota(id, paket, beratCucian, tanggalMasuk)); 
         }   
}

    private static void handleListNota() {
        // TODO: handle list semua nota pada sistem
        System.out.printf("Terdaftar %d nota dalam sistem.\n",notaList.size());
        for (Nota thisNota : notaList){
            int thisId = thisNota.getIdNota();
            System.out.printf("- [%d] Status      	: %s\n",thisId,thisNota.getStatus());
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
        String strIdNota;
        
        while (true) {
            try {
                System.out.println("Masukan ID nota yang akan diambil: ");
                strIdNota = input.nextLine();
                idNota = Integer.parseInt(strIdNota);
                if (idNota < 0) {
                    throw new NumberFormatException();
                }
                break; // Input is valid, break out of the loop
            } catch (NumberFormatException e) {
                System.out.println("ID nota berbentuk angka!");
            }
        }

        if (validateNota(idNota) == false){
            System.out.printf("Nota dengan ID %s tidak ditemukan!\n", strIdNota);
        }
        else{
            for (Nota thisNota : notaList){
                    if (idNota == thisNota.getIdNota()){
                        if (thisNota.getIsReady()){
                            System.out.printf("Nota dengan ID %s berhasil diambil!\n", strIdNota);
                            notaList.remove(thisNota);
                            break;
                        }
                        else{
                            System.out.printf("Nota dengan ID %s gagal diambil!\n", strIdNota);
                            break;
                        }
                }
            }
        }
    }


    private static void handleNextDay() {
        // TODO: handle ganti hari
        cal.add(Calendar.DAY_OF_YEAR,1);
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

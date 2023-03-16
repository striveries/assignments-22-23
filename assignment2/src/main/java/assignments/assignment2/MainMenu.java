/*
 * Nama : Calista Sekar Pamaja
 * NPM : 2206082064
 * Kelas : D
 * Kode Asdos : AYP
 */

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

    /* handleGenerateUser : Method untuk menambahkan member baru 
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
        else { // jika sudah ada maka program akan memulai main program ulang
            System.out.printf("Member dengan nama %s dan nomor hp %s sudah ada!\n", nama, noHP);
        }
    }

    /* handleGenerateNota : Method untuk menambahkan nota baru
        1. input id member akan divalidasi dengan method validateMember dengan dicek ke notaList
        2. apabila ada di notaList, maka dilanjutkan dengan meminta input paket laundry dan berat cucian
        3. program kemudian menginisasi nota baru dan menambahkan nota baru ke listNota
        4. program akan mencetak string nota
     */
    private static void handleGenerateNota() {
        System.out.println("Masukan ID member: "); 
        String id = input.nextLine();// meminta input id member
        Member thisMember = null; // inisasi thisMember
        
        if (validateMember(id) == false){ // jika member tidak valid, program mulai mainprogram ulang
            System.out.printf("Member dengan %s tidak ditemukan!\n",id);
        }
        else {
            System.out.println("Masukan paket laundry: ");
            String paket = input.nextLine();  // meminta input paket laundry

            // loop untuk validasi paket laundry
            while (!("express".equalsIgnoreCase(paket) || "reguler".equalsIgnoreCase(paket) || "fast".equalsIgnoreCase(paket))){ // dicek dengan insensitive pada tiga jenis paket
                if (paket.equals("?")){ // mencetak jenis-jenis paket jika input "?"
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
                paket = input.nextLine(); // loop akan terus berjalan sampai input valid
            }
            // loop untuk meminta input dan validasi berat cucian
            int beratCucian = 0;
            while (true) {
                try {
                    System.out.println("Masukkan berat cucian Anda [Kg]: ");
                    beratCucian = Integer.parseInt(input.nextLine());
                    if (beratCucian <= 0) { // jika berat cucian tidak valid, maka akan masuk ke block catch
                        throw new NumberFormatException();
                    }
                    break; // jika berat cucian valid, akan keluar dari loop
                } catch (NumberFormatException e) {
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                }
            }
            // untuk berat kurang dari 2, dibulatkan menjadi 2
            if (beratCucian < 2){
                beratCucian = 2;
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            }
        
            String tanggalMasuk = fmt.format(cal.getTime()); // mengeset tanggalMasuk dengan tanggal minggu ini
            for (Member member : memberList){
                if (id.equals(member.getId())){
                    thisMember = member; // assign thisMember dengan data member yang id-nya sesuai
                }
            }
            thisMember.plusBonusCounter();  // menambahkan nilai bonuscounter
            Nota newNota = new Nota(thisMember, paket, beratCucian, tanggalMasuk); // inisiasi nota baru dengan class nota
            notaList.add(newNota); // menambahkan nota baru ke notaList
            System.out.println("Berhasil menambahkan nota!");
            System.out.printf("[ID Nota = %d]\n", newNota.getIdNota());
            System.out.println(newNota.generateNota(id, paket, beratCucian, tanggalMasuk)); // mencetak output format nota dengan memanggil method generateNota milik objek newNota
         }   
}
    // method untuk menampilkan nota yang tersedia di notaList
    private static void handleListNota() {
        // TODO: handle list semua nota pada sistem
        System.out.printf("Terdaftar %d nota dalam sistem.\n",notaList.size());
        for (Nota thisNota : notaList){ // iterasi tiap elemen dalam notaList dan mencetak status dari nota tsb
            int thisId = thisNota.getIdNota();
            System.out.printf("- [%d] Status      	: %s\n",thisId,thisNota.getStatus()); // melihat status dari nota dengan memanggil method getStatus 
        }
    }
    // method untuk menampilkan member yang tersedia dalam memberList
    private static void handleListUser() {
        // TODO: handle list semua user pada sistem
        System.out.printf("Terdaftar %d member dalam sistem.\n",memberList.size()); // mencetak jumlah member
        for (int i=0;i<memberList.size();i++){ // mengiterasi setiap elemen dalam memberList untuk mencetak detail tiap membernya
            Member thisMember = memberList.get(i);
            System.out.printf("- %s : %s\n", thisMember.getId(), thisMember.getNama());
        }
    }

    // method untuk menghapus nota cucian yang diambil dari notaLis
    private static void handleAmbilCucian() {
        // TODO: handle ambil cucian
        int idNota = 0; // inisiasi variabel idNota dalam bentuk int dan strIdNota dalam bentuk string
        String strIdNota; 
        
        while (true) { // loop untuk validasi id nota
            try {
                System.out.println("Masukan ID nota yang akan diambil: ");
                strIdNota = input.nextLine();
                idNota = Integer.parseInt(strIdNota);
                if (idNota < 0) { // jika id nota tidak valid maka akan amsuk ke block catch
                    throw new NumberFormatException();
                }
                break; // jika input valid maka akan keluar dari loop
            } catch (NumberFormatException e) {
                System.out.println("ID nota berbentuk angka!");
            }
        }

        if (validateNota(idNota) == false){ // jika id nota tidak valid, maka akan kembali ke main menu
            System.out.printf("Nota dengan ID %s tidak ditemukan!\n", strIdNota);
        }
        else{
            for (Nota thisNota : notaList){ // iterasi tiap elemen dari notaList
                    if (idNota == thisNota.getIdNota()){ 
                        if (thisNota.getIsReady()){ // validasi apakah thisNota sudah siap untuk diambil
                            System.out.printf("Nota dengan ID %s berhasil diambil!\n", strIdNota);
                            notaList.remove(thisNota); // menghapus thisNota yang mau diambil dari notaList
                            break;
                        }
                        else{
                            System.out.printf("Nota dengan ID %s gagal diambil!\n", strIdNota);
                            break; //keluar dari loop dan kembali ke main program
                        }
                }
            }
        }
    }

    // menambahkan satu hari ke hari selanjutnya
    private static void handleNextDay() {
        // TODO: handle ganti hari
        cal.add(Calendar.DAY_OF_YEAR,1); // mengeset calendar dnegan menambahkannya satu hari
        System.out.println("Dek Depe tidur hari ini... zzz...");
        for(Nota thisNota : notaList){
            thisNota.nextDay(); // iterasi tiap elemen dalam notaList, jika sudah siap maka akan dicetak pesannya
            if (thisNota.getIsReady() == true){ 
                System.out.printf("Laundry dengan nota ID %d sudah dapat diambil!\n", thisNota.getIdNota());
            }
        }
        System.out.println("Selamat pagi dunia!");
        System.out.println("Dek Depe: It's CuciCuci Time.");;
    }

    private static void printMenu() { // mencetak menu untuk main program
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

    // validasi apabila idmember sudah ada di dalam memberList, jika ada maka return true
    private static boolean validateMember(String idMember){
        for (Member member : memberList){
            if (idMember.equals(member.getId())){
                return true;
            }
        }
        return false;
    }
    // validasi apabila idnota ada pada notalist, jika ada maka return true
    private static boolean validateNota(int idNota){
        for (Nota nota : notaList){
            if (idNota == nota.getIdNota()){
                return true;
            }
        }
        return false;
    }

 
    

    

}

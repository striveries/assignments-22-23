package assignments.assignment3.user.menu;
import java.io.OutputStream;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.*;
import assignments.assignment3.user.Member;
import assignments.assignment3.MainMenu;


public class MemberSystem extends SystemCLI {
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) { // memproses pilihan user
        boolean logout = false;
        switch (choice) {
            case 1 -> laundryBaru();
            case 2 -> detailNotaSaya();
            case 3 -> logout = true; // jika logout bernilai true maka system akan berhenti
            default -> System.out.println("Pilihan tidak valid, silakan coba lagi.");
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() { // mencetak pilihan menu yang dapat dipilih member
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) { // menambahkan member baru ke memberList dengan expansi panjang array denga loop
        Member[] temp = new Member[memberList.length + 1];  // membuat temporary variable array dengan panjang 1 lebih besar dari notaList
        for (int i = 0; i < temp.length - 1; i++) { // iterasi sambil menyalin isi notaList ke temp
            temp[i] = memberList[i]; 
        }
        temp[temp.length-1] = member; // menambahkan nota baru ke array temp
        memberList = temp; // meng-assign nilai temp var ke notaList
    }

    private void laundryBaru(){ // membuat nota laundry baru
        System.out.println("Masukan paket laundry:");
        NotaGenerator.showPaket();// menampilkan jenis paket
        String paket = getPaket(); // meminta input jenis paket dengan method getPaket() milik NotaGenerator
        int berat = NotaGenerator.getBerat(); // meminta input berat paket dengan method getBerat() milik NotaGenerator
        System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?\nHanya tambah 1000 / kg");
        System.out.print("[Ketik x untuk tidak mau]: ");
        String setrika = in.nextLine(); // input pilihan setrika service
        System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!\nCuma 2000 / 4kg, kemudian 500 / kg");
        System.out.print("[Ketik x untuk tidak mau]: ");
        String antar = in.nextLine(); // input pilihan antar service
        String tanggalTerima = NotaManager.fmt.format(NotaManager.cal.getTime()); // setting tanggal sesuai tanggal hari ini
        Nota newNota = new Nota(loginMember, berat, paket, tanggalTerima); // membuat nota baru sesuai order laundry

        if (!setrika.equalsIgnoreCase("x")){ // jika user input selain x
            newNota.addService(new SetrikaService()); // service setrika akan ditambahkan ke array kumpulan service milik nota tersebut
        }
        if (!antar.equalsIgnoreCase("x")){ // jika user input selain x
            newNota.addService(new AntarService()); // service setrika akan ditambahkan ke array kumpulan service milik nota tersebut
        }
       loginMember.addNota(newNota);// menambahkan nota baru ke kumpulan nota milik member
       
    }

    private void detailNotaSaya(){ // mencetak detail nota
        for(Nota myNota : loginMember.getNotaList()){
            System.out.println(myNota); // secara otomatis akan memanggil method toString() milik nota tersebut
        }
    }

    public static String getPaket() {
        String paket = "";
        while (true) {
            System.out.println("Masukan paket laundry:");
            paket = MainMenu.in.nextLine();

            if (paket.equals("?")) {
                NotaGenerator.showPaket();
                continue;
            }

            if (NotaGenerator.toHargaPaket(paket) < 0) {
                System.out.printf("Paket %s tidak diketahui\n", paket);
                System.out.println("[ketik ? untuk mencari tahu jenis paket]");
            } else {
                break;
            }
        }
        return paket;
    }
}
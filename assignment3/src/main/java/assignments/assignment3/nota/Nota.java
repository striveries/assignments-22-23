package assignments.assignment3.nota;
import assignments.assignment3.nota.service.*;
import assignments.assignment3.user.Member;
import assignments.assignment1.NotaGenerator;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Nota {
    // inisiasi atribut yang dibutuhkan
    private int idNota;
    private String paket;
    private Member member;
    private  int berat;
    private String tanggalMasuk;
    private boolean isDone = false;
    private LaundryService[] services = new LaundryService[]{new CuciService()};
    private int sisaHariPengerjaan;
    private long baseHarga;
    public static int totalNota;

    // inisiasi constructor untuk class Nota
    public Nota(Member member, int berat, String paket, String tanggal) {
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggal;
        this.sisaHariPengerjaan = NotaGenerator.toHariPaket(paket); // inisisasi nilai awal sisaHariPengerjaan sesuai jenis paketnya
        this.idNota = totalNota++;
    }

    public void addService(LaundryService service){ // menambahkan service yang dipilih ke array kumpulan services
        LaundryService[] temp = new LaundryService[services.length + 1];
        for (int i = 0; i < temp.length - 1; i++) {
            temp[i] = services[i];
        }
        temp[temp.length-1] = service;
        services = temp;
    }

    public String kerjakan(){ // mengerjakan satu pekerjaan dalam array services
        for(LaundryService service:services){
            if(!service.isDone()){ // jika pekerjaan belum selesai maka akan dikerjakan
                return String.format("Nota %d : %s", this.idNota, service.doWork()); // langsung keluar dari loop
            }
        } // jika semua pekerjaan sudah dilakukan maka akan return sudah selesai
        return String.format("Nota %d : Sudah selesai.", this.idNota);
    }

    public void toNextDay() { 
        sisaHariPengerjaan--; // decrement nilai sisaHariPengerjaan
    }

    public long calculateHarga(){
        long harga = NotaGenerator.toHargaPaket(paket) * berat; // inisiasi baseHarga sesuai jenis paket dengan mengambil data di NotaGenerator
        for (LaundryService service : services){ // menambahkan biaya dari additional services yang dipilih
            harga += service.getHarga(berat);
        }
        if((sisaHariPengerjaan<=0)&&(!isDone())){ // jika sudah melewati masa deadline (sisaHariPengerjaan negatif) dan pekerjaan masih belum selesai
            harga -= ((sisaHariPengerjaan*-1) * 2000); // mengurangi harga sesuai biaya kompensasi
        }
        if (harga<0){ // jika harga bernilai negatif maka akan diubah ke nilai minimum 0
            harga = 0;
        }
        return harga;
    }

    public String getNotaStatus(){
        if(isDone()){ // mengecek status nota sesuai hasil isDone()
            return String.format("Nota %d : Sudah selesai.", getIdNota());
        }
        return String.format("Nota %d : Belum selesai.", getIdNota());
    }

    @Override
    // meng-override toString() dengan menambahkan detail Service List
    public String toString(){
        // setting untuk tanggalMasuk
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
        Calendar cal = Calendar.getInstance(); 
        int year = Integer.parseInt(tanggalMasuk.substring(6)); 
        int month = Integer.parseInt(tanggalMasuk.substring(3, 5)) - 1;
        int date = Integer.parseInt(tanggalMasuk.substring(0, 2));
        cal.set(year, month, date);

        // String Formatting untuk return nota
        String nota = "";
        nota += String.format("[ID Nota = %d]\n",idNota);
        nota += "ID    : " + member.getId() + "\n";
        nota += "Paket : " + paket + "\n";
        nota += "Harga :\n";
        nota += String.format("%d kg x %d = %d\n", berat, NotaGenerator.toHargaPaket(paket), (berat * NotaGenerator.toHargaPaket(paket)));

        nota += "tanggal terima  : " + tanggalMasuk + "\n";
        cal.add(Calendar.DATE, NotaGenerator.toHariPaket(paket)); // memajukkan tanggal sesuai hari pengerjaan untuk tanggal selesai
        nota += "tanggal selesai : " + formatter.format(cal.getTime());
        nota += "\n--- SERVICE LIST ---\n";
        for (LaundryService service : services){ // iterasi tiap service yang dipilih oleh user dan mencetaknya
            nota += "-" + service.getServiceName() + " @ Rp." + service.getHarga(berat) + "\n";
        }
        baseHarga = calculateHarga(); // kalkulasi harga untuk laundry
        nota += "Harga Akhir: " + baseHarga ;
        if((sisaHariPengerjaan<=0)&&(!isDone())){ // jika sisa hari pengerjaan sudah bernilai negatif 
            nota += "\nAda kompensasi keterlambatan " + (sisaHariPengerjaan*-1) +" * 2000 hari"; // kompensasi sebesar 2000 per hari keterlambatan
        }
        return nota;
    }

    /* 
    mengecek apakah nota sudah selesai dikerjakan 
    return true jika semua service dalam nota sudah dikerjakan
    return false jika belum
    */
    public boolean isDone() {
        for(LaundryService service : services){ // iterasi tiap service dalam array services 
            if(!service.isDone()){ // jika ada service yang belum selesai maka akan return false, alias belum selesai
                return false;
            }
        }
        isDone = true; // jika nota sudah selesai maka akan mengubah flag isDone menjadi true
        return true; 
    }

    // Dibawah ini adalah getter

    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public int getIdNota() {
        return idNota;
    }

    public String getTanggal() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }
   
    public LaundryService[] getServices(){
        return services;
    }

}

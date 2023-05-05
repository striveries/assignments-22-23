package assignments.assignment3.nota;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;
import assignments.assignment2.Nota;

public class Nota {
    private int idNota;
    private String paket;
    private Member member;
    private  int berat;
    private String tanggalMasuk;
    private boolean isDone;
    private LaundryService[] services;
    private int sisaHariPengerjaan;
    private long baseHarga;
    public static int totalNota;

    public Nota(Member member, int berat, String paket, String tanggal) {
        //TODO
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggal = tanggal;

    }

    public void addService(LaundryService service){
        //TODO
    }

    public String kerjakan(){
        // TODO
        return "";
    }
    public void toNextDay() {
        // TODO
    }

    public long calculateHarga(){
        // TODO
        return -1;
    }

    public String getNotaStatus(){
        // TODO
        if(isDone()){
            return "Sudah selesai."
        }
        return "Belum selesai.";
    }

    @Override
    public String toString(){
        // TODO
        return "";
    }

    // Dibawah ini adalah getter

    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public String getTanggal() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }
    public boolean isDone() {
        return isDone;
    }

    public LaundryService[] getServices(){
        return services;
    }
}

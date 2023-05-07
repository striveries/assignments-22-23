package assignments.assignment3.nota.service;

public class AntarService implements LaundryService{
    boolean isCalled = false; // berfungsi sebagai flag untuk menandakan apakah doWork() pernah dipanggil atau tidak

    @Override
    public String doWork() {
        isCalled = true; // mengubah flag isCalled menjadi true
        return "Sedang mengantar..."; // me-return String sesuai jenis service
    }

    @Override
    public boolean isDone() {
        if (isCalled){ // jika variable isCalled bernilai true maka akan me-return true
            return true;
        } // jika isCalled bernilai false maka akan me-return false
        return false;
    }

    @Override
    public long getHarga(int berat) { // method untuk me-return nilai harga sesuai beratnya
        if (berat < 4){
            return 2000; // untuk berat di-bawah 4kg maka biaya fee nya 2000
        }
        else{
            return 500*berat; // harga service antar adalah Rp500 per kilogram
        }
    }

    @Override
    public String getServiceName() {
        return "Antar"; // me-return nama jenis service
    }
}

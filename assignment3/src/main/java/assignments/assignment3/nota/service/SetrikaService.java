package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    boolean isCalled = false;// berfungsi sebagai flag untuk menandakan apakah doWork() pernah dipanggil atau tidak
    @Override
    public String doWork() { 
        isCalled = true;  // mengubah flag isCalled menjadi true
        return "Sedang menyetrika..."; // me-return String sesuai jenis service
    }

    @Override
    public boolean isDone() {
        if (isCalled){ // jika variable isCalled bernilai true maka akan me-return true
            return true;
        } // jika isCalled bernilai false maka akan me-return false
        return false;
    }

    @Override
    public long getHarga(int berat) {
        return 1000*berat; // harga untuk service strika adalah 1000 per kilogram 
    }

    @Override
    public String getServiceName() {
        return "Setrika"; // me-return nama jenis service
    }
}

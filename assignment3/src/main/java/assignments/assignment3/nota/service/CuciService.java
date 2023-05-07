package assignments.assignment3.nota.service;

public class CuciService implements LaundryService{
    boolean isCalled = false; // berfungsi sebagai flag untuk menandakan apakah doWork() pernah dipanggil atau tidak

    @Override
    public String doWork() { 
        // TODO
        isCalled = true; // mengubah flag isCalled menjadi true
        return "Sedang mencuci..."; // me-return String sesuai jenis service
    }

    @Override
    public boolean isDone() {

        if (isCalled){ // jika variable isCalled bernilai true maka akan me-return true
            return true;
        }
        return false; // jika isCalled bernilai false maka akan me-return false
    }

    @Override
    public long getHarga(int berat) { 
        return 0; // untuk service cuci harga-nya 0
    }

    @Override
    public String getServiceName() {
        return "Cuci"; // me-return nama jenis service
    }
}


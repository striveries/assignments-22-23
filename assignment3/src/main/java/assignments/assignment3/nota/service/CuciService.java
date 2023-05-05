package assignments.assignment3.nota.service;

public class CuciService implements LaundryService{
    @Override
    public String doWork() {
        // TODO
        return "Sedang mencuci...";
    }

    @Override
    public boolean isDone() {
        // TODO BINGUNGGGG
        return false;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Cuci";
    }
}

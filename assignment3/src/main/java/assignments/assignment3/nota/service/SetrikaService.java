package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    @Override
    public String doWork() {
        // TODO
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return false;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        return 1000*berat;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}

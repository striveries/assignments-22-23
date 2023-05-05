package assignments.assignment3.nota.service;

import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    @Override
    public String doWork() {
        // TODO
        isDone();
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        // TODO BINGUUUUUUUUUUUUNG
        return true;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        if (berat < 4){
            return 2000;
        }
        else{
            return 500*berat;
        }
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}

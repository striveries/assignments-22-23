package assignments.assignment3.nota;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NotaManager {
    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Calendar cal = Calendar.getInstance();
    public static Nota[] notaList = new Nota[0];

    /**
     * Skips ke hari berikutnya dan update semua entri nota yang sesuai.
     */
    public static void toNextDay(){
        //TODO: implement skip hari
        cal.add(Calendar.DATE, 1);
        for(Nota nota:notaList){
            nota.toNextDay();
        }
        System.out.println("Kamu tidur hari ini... zzz...");
    }

    /**
     * Menambahkan nota baru ke NotaList.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public static void addNota(Nota nota){
        Nota[] temp = new Nota[notaList.length + 1]; // membuat temporary variable array dengan panjang 1 lebih besar dari notaList
        for (int i = 0; i < temp.length - 1; i++) { // iterasi sambil menyalin isi notaList ke temp
            temp[i] = notaList[i];
        }
        temp[temp.length-1] = nota; // menambahkan nota baru ke array temp
        notaList = temp; // meng-assign nilai temp var ke notaList
    }
}

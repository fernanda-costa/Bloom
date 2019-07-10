package br.com.fernandacosta.bloom.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataHelper {

    public static String formataData(Date dia){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(dia);
    }
}

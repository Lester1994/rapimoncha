package una.android.rapimoncha.entidades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Lester on 31/03/2015.
 */
public class Consulta {
    private int idConsulta;
    private String tiConsulta;
    private String teConsulta;
    private Calendar feConsulta;
    private Consulta respondeA;
    private Subcripcion subcripcion;


    public Consulta() {
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getTiConsulta() {
        return tiConsulta;
    }

    public void setTiConsulta(String tiConsulta) {
        this.tiConsulta = tiConsulta;
    }

    public String getTeConsulta() {
        return teConsulta;
    }

    public void setTeConsulta(String teConsulta) {
        this.teConsulta = teConsulta;
    }

    public Calendar getFeConsulta() {
        return feConsulta;
    }

    public void setFeConsulta(Calendar feConsulta) {
        this.feConsulta = feConsulta;
    }

    public Consulta getRespondeA() {
        return respondeA;
    }

    public void setRespondeA(Consulta respondeA) {
        this.respondeA = respondeA;
    }

    public Subcripcion getSubcripcion() {
        return subcripcion;
    }

    public void setSubcripcion(Subcripcion subcripcion) {
        this.subcripcion = subcripcion;
    }
    public String getDateTimeConsulta(){
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         return dateFormat.format(feConsulta.getTime());
    }
}

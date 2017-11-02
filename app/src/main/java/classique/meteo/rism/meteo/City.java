package classique.meteo.rism.meteo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by grim on 14/10/17.
 */

public class City implements Serializable {

    private String ville;
    private String pays;
    private String date = "";
    private float vitesse = 0;
    private String direction = "";
    private float pression = 0;
    private float temperature = 0;

    public City(String Ville, String Pays){
        this.ville = Ville;
        this.pays = Pays;
    }

    public String getVille() {

        return ville;
    }

    public void setVille(String ville) {

        this.ville = ville;
    }

    public String getPays() {

        return pays;
    }

    public void setPays(String pays) {

        this.pays = pays;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getVitesse() {

        return vitesse;
    }

    public void setVitesse(float vitesse) {

        this.vitesse = vitesse;
    }

    public String getDirection() {

        return direction;
    }

    public void setDirection(String direction) {

        this.direction = direction;
    }

    public float getPression() {

        return pression;
    }

    public void setPression(float pression) {

        this.pression = pression;
    }

    public float getTemperature() {

        return temperature;
    }

    public void setTemperature(float temperature) {

        this.temperature = temperature;
    }


    @Override
    public String toString() {

        return this.getVille() + " (" + this.getPays() + ")";
    }

}

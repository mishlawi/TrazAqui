package Model;

import java.awt.geom.Point2D;
import java.io.Serializable;


public class Admin extends Ator implements Serializable {

    public Admin() {
        super();
    }

    public Admin(String email, String referencia, String nome, String password, Point2D.Double morada, long nif) {
        super(email, referencia, nome, password, morada, nif);
    }

    public Admin(Ator a) {
        super(a.getEmail(), a.getReferencia(), a.getNome(), a.getPassword(), a.getMorada(), a.getNif());
    }

    public Admin clone(){
        return new Admin(this);
    }




}

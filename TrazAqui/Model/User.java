package Model;


import java.awt.geom.Point2D;
import java.io.Serializable;

import java.util.HashMap;

import java.util.Map;


public class User extends Ator implements Serializable {
private Map<String,Encomenda> encomendas;

    /**
     * Constructores
     */

    public User() {
        super();
        encomendas= new HashMap<>();


    }

    public User(String ref,String email, String nome, String password, Point2D.Double morada, long nif, Map<String,Encomenda> encomendas) {
        super(email, ref, nome, password,morada ,nif );
        encomendas = encomendas;
    }

    public User(User a) {
        super(a.getEmail(),a.getReferencia(),a.getNome(),a.getPassword(),a.getMorada(),a.getNif());
        encomendas = a.getEncomendas();

    }


    public Map<String,Encomenda> getEncomendas(){
        Map<String,Encomenda> aux = new HashMap<>();
        for(Map.Entry<String,Encomenda> e:this.encomendas.entrySet())
            aux.put(e.getKey(),e.getValue());
        return aux;

    }


    public void setEncomendas(Map<String,Encomenda>enc){
        this.encomendas = new HashMap<>();
        enc.entrySet().forEach(e-> this.encomendas.put(e.getKey(),
                e.getValue().clone()));
    }

    public void adicionaEncomendaUser(Encomenda e) {
        this.encomendas.put(e.getReferencia(),e.clone());
    }





    public User clone() {
        return new User(this);
    }

    /**
     * Metodos
     */
    @Override
    public String toString() {
        return super.toString()+
                "encomendas=" + encomendas +
                '}';
    }



/*

    public boolean aceitaEntrega(Servico s, String referencia){

    }

*/



}

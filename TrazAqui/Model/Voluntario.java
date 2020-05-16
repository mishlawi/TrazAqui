package Model;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;


public class Voluntario extends Transporte
{
    //Variáveis de instância
    private Map<String,Encomenda> encomendas; //registo de encomendas



    //Construtores

    public Voluntario(){
        super();
        this.encomendas = new HashMap<>();


    }

    public Voluntario(String referencia, boolean disponibilidade, float raio, boolean certeficado, double classificacao, Point2D.Double latiLongi, int numeroEntregas, double velocidadeMedia, Map<String,Encomenda> enc, String password) {
        super(referencia, disponibilidade,raio, certeficado,  classificacao, latiLongi, numeroEntregas,velocidadeMedia,password);
        setEncomendas(enc);
       }


    public Voluntario(Voluntario e) {
            super(e.getReferencia(),e.isDisponivel(),e.getRaio(),e.isCerteficado(),e.getClassificacao(),e.getLocalizacao(),e.getNumeroEntregas(),e.getVelocidadeMedia(), e.getPassword());
            setEncomendas(e.getEncomendas());

    }

    // Getters

    public Map<String,Encomenda> getEncomendas(){
        Map<String,Encomenda> aux = new HashMap<>();
            for (Map.Entry<String,Encomenda> e : this.encomendas.entrySet())
                aux.put(e.getKey(),e.getValue().clone());
            return aux;
    }


    // Setters



    public void setEncomendas(Map<String,Encomenda>enc){
        this.encomendas = new HashMap<>();
        enc.entrySet().forEach(e-> this.encomendas.put(e.getKey(),
                e.getValue().clone()));
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voluntario that = (Voluntario) o;
        return  encomendas.equals(that.encomendas);
    }


    @Override
    public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(super.toString());

            sb.append("Encomendas: ").append(this.encomendas).append("\n");
            return sb.toString();
        }

    public Voluntario clone(){
        return new Voluntario(this);
    }



    /* metodos */

    public void updateLocalizacao(Point2D.Double n){
        setLocalizacao(n);
    }






}

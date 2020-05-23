package Model;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;


public class Voluntario extends Transporte
{
    //Variáveis de instância
    private Map<String,Encomenda> encomendas; //registo de encomendas k:referencia encomenda



    //Construtores

    public Voluntario(){
        super();
        this.encomendas = new HashMap<>();
    }

    public Voluntario(String email, String referencia, String nome, String password, Point2D.Double morada, long nif, boolean disponibilidade, float raio, boolean certeficado, double classificacao, int numeroEntregas , double velocidadeMedia, double nrKms, Map<String,Encomenda> enc) {
        super(email, referencia, nome, password, morada,nif, disponibilidade,raio, certeficado,  classificacao, numeroEntregas, velocidadeMedia, nrKms);
        setEncomendas(enc);
       }


    public Voluntario(Voluntario e) {
        super(e.getEmail(),e.getReferencia(),e.getNome(),e.getPassword(),e.getMorada(),e.getNif(),e.isDisponivel(),e.getRaio(),e.isCerteficado(),e.getClassificacao(),e.getNumeroEntregas(),e.getVelocidadeMedia(),e.getNumeroKms());

            setEncomendas(e.getEncomendas());

    }

    // Getters

    public Map<String,Encomenda> getEncomendas(){
        Map<String,Encomenda> aux = new HashMap<>();
            for (Map.Entry<String,Encomenda> e : this.encomendas.entrySet())
                aux.put(e.getKey(),e.getValue().clone());
            return aux;
    }


    public Map<String,Encomenda> getEncomendasEfetuadas(){
        Map<String,Encomenda> aux = new HashMap<>();
        for (Map.Entry<String,Encomenda> e : this.encomendas.entrySet())
            if(e.getValue().isEfetuada()) aux.put(e.getKey(),e.getValue().clone());
        return aux;
    }

    public Map<String,Encomenda> getEncomendasPedidas(){
        Map<String,Encomenda> aux = new HashMap<>();
        for (Map.Entry<String,Encomenda> e : this.encomendas.entrySet())
            if(!e.getValue().isEfetuada()) aux.put(e.getKey(),e.getValue().clone());
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
        setMorada(n);
    }






}

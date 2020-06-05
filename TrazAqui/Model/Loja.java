package Model;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.*;

public class Loja extends Ator implements Serializable {


    //Variáveis de instância

    private String referencia;
    private int fila;
    private float espera; //tempo médio de espera por cliente
    private Map<String,Encomenda> encomendas;
    private List<Produto> produtos;




    public Loja(){

        this.referencia = "";
        this.fila = 0;
        this.espera = 0;
        this.encomendas = new HashMap<>();

    }


    public Loja(String nome,String referencia, Point2D.Double moradaLoja, int fila, float espera, Map<String,Encomenda> enc) {

        this.referencia =  referencia;
        this.fila = fila;
        this.espera = espera;
        this.encomendas = enc;

    }

    public Loja (Loja a) {
    setNome(a.getNome());
    setReferencia(a.getReferencia());
    setFila(a.getFila());
    setEspera(a.getEspera());
    this.encomendas = a.getEncomendas();

    }






    public String getReferencia(){
        return this.referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public float getEspera() {
        return espera;
    }

    public void setEspera(float espera) {
        this.espera = espera;
    }

    public Map<String,Encomenda> getEncomendas(){
        Map<String,Encomenda> aux = new HashMap<>();
        for (Map.Entry<String,Encomenda> e : this.encomendas.entrySet())
            aux.put(e.getKey(),e.getValue().clone());
        return aux;
    }

    public void setEncomendas(Map<String,Encomenda>enc){
        this.encomendas = new HashMap<>();
        enc.entrySet().forEach(e-> this.encomendas.put(e.getKey(),
                e.getValue().clone()));
    }

    @Override
    public String toString() {
        return super.toString()  +
                ", fila=" + fila +
                ", espera=" + espera +
                ", encomendas=" + encomendas +
                ", produtos=" + produtos
                ;
    }


    /*Metodos*/


    public void adicionaEncomendaLoja(Encomenda e) {
        this.encomendas.put(e.getReferencia(),e.clone());
    }



    public Loja clone(){
        return new Loja(this);
    }



    /**
    Métodos
     */

    public double tempoTotalEspera(){
        return this.getFila() * this.getEspera();
    }

}

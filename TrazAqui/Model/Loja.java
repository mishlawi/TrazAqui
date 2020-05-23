package Model;

import java.awt.geom.Point2D;
import java.util.*;

public class Loja extends Ator {


    //Variáveis de instância

    private String referencia;
    private int fila;
    private float espera; //tempo médio de espera por cliente
    private List<Encomenda> encomendas;
    private Servico servico;



    public Loja(){

        this.referencia = "";
        this.fila = 0;
        this.espera = 0;
        this.encomendas = new ArrayList<>();
        this.servico = new Servico();
    }


    public Loja(String nome,String referencia, Point2D.Double moradaLoja, int fila, float espera, List<Encomenda> enc, Servico s) {

        this.referencia =  referencia;
        this.fila = fila;
        this.espera = espera;
        this.encomendas = enc;
        this.servico = s;
    }

    public Loja (Loja a) {
    setNome(a.getNome());
    setReferencia(a.getReferencia());
    setFila(a.getFila());
    setEspera(a.getEspera());
    this.encomendas = a.getPedidos();
    this.servico = a.getServico();
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

    public Servico getServico() {
        return servico;
    }

    /*Metodos*/

    /*basicamente retira todos os pedidos existentes para esta loja */

    public List<Encomenda> getPedidos(){
    Map<String,Encomenda> aux = this.servico.getDataBase().getEncomendas();
    List <Encomenda> res = new ArrayList<>();
        for(Map.Entry<String,Encomenda> e: aux.entrySet()){
                if(e.getValue().getLoja().equals(this.referencia))
                    res.add(e.getValue().clone());
        }
    return res;
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

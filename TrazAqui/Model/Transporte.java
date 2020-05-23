package Model;

import java.awt.geom.Point2D;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public  class Transporte extends Ator {


    /**
    * Variaveis de instancia
     */
    private boolean disponibilidade;
    private float raio;
    private boolean certeficado;
    private double classificacao;
    private int numeroEntregas;
    private double velocidadeMedia; //em km/h
    private double numeroKms;

    public Transporte(){
        super();
        this.disponibilidade = true;
        this.raio = 0;
        this.certeficado = false;
        this.classificacao = 0;
        this.numeroEntregas = 0;
        this.velocidadeMedia = 0;
        this.numeroKms = 0;


    }

    public Transporte(String email, String referencia, String nome, String password, Point2D.Double morada, long nif, boolean disponibilidade, float raio, boolean certeficado, double classificacao, int NumeroEntregas , double VelocidadeMedia, double nrKms) {
        super(email,referencia,nome,password, morada, nif);
        this.disponibilidade = disponibilidade;
        this.raio = raio;
        this.certeficado = certeficado;
        this.classificacao = classificacao;
        this.numeroEntregas = NumeroEntregas;
        this.velocidadeMedia = VelocidadeMedia;
        this.numeroKms = nrKms;
    }

    public Transporte(Transporte a){
        super(a.getEmail(),a.getReferencia(),a.getNome(),a.getPassword(),a.getMorada(),a.getNif());
        this.disponibilidade = a.isDisponivel();
        this.raio = a.getRaio();
        this.certeficado = a.isCerteficado();
        this.classificacao = a.getClassificacao();
        this.numeroEntregas = a.getNumeroEntregas();
        this.velocidadeMedia = a.getVelocidadeMedia();
        this.numeroKms = a.getNumeroKms();

    }



    public boolean isDisponivel() {
        return disponibilidade;
    }

    public float getRaio() {
        return raio;
    }

    public boolean isCerteficado() {
        return certeficado;
    }

    public double getClassificacao() {
        return classificacao;
    }


    public int getNumeroEntregas() {
        return numeroEntregas;
    }

    public double getVelocidadeMedia() {
        return velocidadeMedia;
    }




    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public void setRaio(float raio) {
        this.raio = raio;
    }

    public void setCerteficado(boolean certeficado) {
        this.certeficado = certeficado;
    }


    public void setClassificacao(double classificacao) {
        //media = (x + y + z) / n
        double anterior = getClassificacao()*this.getNumeroEntregas(); // = x+y+z
        setNumeroEntregas(this.getNumeroEntregas()+1); //n+1
        this.classificacao = (classificacao+anterior)/this.getNumeroEntregas();// = k+(x+y+z)/n+1
    }



    public void setNumeroEntregas(int numeroEntregas) {
        this.numeroEntregas = numeroEntregas;
    }


    public double getNumeroKms() {
        return numeroKms;
    }

    public void setNumeroKms(double numeroKms) {
        this.numeroKms = numeroKms;
    }








    /*metodos*/



    public void aceitaEncomenda(Encomenda a) {
        setDisponibilidade(false);
    }

    /*Aceitar encomendas do tipo médico*/

    public void aceitaMedicamentos(boolean state){
        setDisponibilidade(true);

    }



    public double distancia (Encomenda a){
       return  a.getMoradaLoja().distance(this.getMorada())+a.getMoradaLoja().distance(a.getMoradaUtilizador());
    }

    public boolean distanciaValida(Encomenda a){
        if (a.getMoradaLoja().distance(this.getMorada())>getRaio()) return false;
        else return true;
    }

    public void addKms(Encomenda a){
        double b = this.getNumeroKms();
        b+=distancia(a);
        setNumeroKms(b);

    }



    public void tempoViagem(Encomenda a) {

    Double tempo = (a.getMoradaLoja().distance(this.getMorada())*60)/this.getVelocidadeMedia();
    Duration duracao = Duration.ofMinutes(tempo.longValue());

        a.setTempo(duracao);

    }

/*
    public void encomendaEntregue (Encomenda a, Servico s){
        tempoViagem(a);
        s.adicionaEfetuado(a);
        Map<String,Encomenda> aux = s.getPedidos();
        aux.remove(a.getReferencia());
        s.setPedidos(aux);
        this.setDisponibilidade(true);

    }
*/

    /**
     * outros
     */


    public  Transporte clone(){
        return new Transporte(this);
    }


}

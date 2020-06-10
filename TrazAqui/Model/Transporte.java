package Model;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public  class Transporte extends Ator implements Serializable {


    /**
    * Variaveis de instancia
     */
    private boolean disponibilidade;
    private double raio;
    private boolean certeficado;
    private double classificacao;
    private int numeroEntregas;
    private double velocidadeMedia; //em km/h
    private double numeroKms;
    private Map<String,Encomenda> encomendas;

    public Transporte(){
        super();
        this.disponibilidade = true;
        this.raio = 0;
        this.certeficado = false;
        this.classificacao = 0;
        this.numeroEntregas = 0;
        this.velocidadeMedia = 0;
        this.numeroKms = 0;
        this.encomendas = new HashMap<>();


    }

    public Transporte(String email, String referencia, String nome, String password, Point2D.Double morada, long nif, boolean disponibilidade, double raio, boolean certeficado, double classificacao, int NumeroEntregas , double VelocidadeMedia, double nrKms,Map<String,Encomenda> encomendas) {
        super(email,referencia,nome,password, morada, nif);
        this.disponibilidade = disponibilidade;
        this.raio = raio;
        this.certeficado = certeficado;
        this.classificacao = classificacao;
        this.numeroEntregas = NumeroEntregas;
        this.velocidadeMedia = VelocidadeMedia;
        this.numeroKms = nrKms;
        this.encomendas = encomendas;
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
        this.encomendas = a.getEncomendas();

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


    public boolean isDisponivel() {
        return disponibilidade;
    }

    public double getRaio() {
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

    public void setRaio(double raio) {
        this.raio = raio;
    }

    public void setCerteficado(boolean certeficado) {
        this.certeficado = certeficado;
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

    public void adicionaEncomendaTransporte(Encomenda e) {
        this.encomendas.put(e.getReferencia(),e.clone());
    }

    public void aceitaEncomenda(Encomenda a) {
        setDisponibilidade(false);
    }

    public void encomendaConcluida() {
        setDisponibilidade(true);
    }

    /*Aceitar encomendas do tipo médico*/

    public void aceitaMedicamentos(boolean state){
        setDisponibilidade(true);

    }

    public void setClassificacao(double classificacao) {
        //media = (x + y + z) / n
        double anterior = getClassificacao()*this.getNumeroEntregas(); // = x+y+z
        setNumeroEntregas(this.getNumeroEntregas()+1); //n+1
        this.classificacao = (classificacao+anterior)/this.getNumeroEntregas();// = k+(x+y+z)/n+1
    }

    //Distancia morada transportador -> loja ; loja -> utilizador
    public double distancia (Encomenda a){
       return  a.getLoja().getMorada().distance(this.getMorada()); //.distance(a.getComprador().getMorada());

    }

    public boolean distanciaValida(Encomenda a){
        if (a.getLoja().getMorada().distance(this.getMorada())>getRaio()) return false;
        else return true;
    }
    //Adiciona a distancia moradatransportador->loja -> utilizador
    public void addKms(Encomenda a){
        double b = this.getNumeroKms();
        b+=distancia(a);
        this.setNumeroKms(b);

    }

    public double tempoViagem(Encomenda a) {


    double tempo = ((a.getLoja().getMorada().distance(this.getMorada())+a.getComprador().getMorada().distance(a.getLoja().getMorada()))*60)/this.getVelocidadeMedia();


        return tempo;
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
            if(!e.getValue().isAceiteTransportador()) aux.put(e.getKey(),e.getValue().clone());
        return aux;
    }

    public List<Encomenda> encPedidasData(){
        return getEncomendasPedidas().values().stream().
                sorted(new DataComparator()).collect(Collectors.toList());
    }

    public void removeEncomendaTransportador(Encomenda e) {
        this.getEncomendas().remove(e.getReferencia());
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

    @Override
    public String toString() {
        return "Transporte{" +
                "disponibilidade=" + disponibilidade +
                ", raio=" + raio +
                ", certeficado=" + certeficado +
                ", classificacao=" + classificacao +
                ", numeroEntregas=" + numeroEntregas +
                ", velocidadeMedia=" + velocidadeMedia +
                ", numeroKms=" + numeroKms +
                '}';
    }

    /**
     * outros
     */


    public  Transporte clone(){
        return new Transporte(this);
    }


}

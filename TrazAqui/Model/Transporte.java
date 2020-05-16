package Model;

import java.awt.geom.Point2D;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Transporte extends Ator {

    private String email; //aka referencia
    private boolean disponibilidade;
    private float raio;
    private boolean certeficado;
    private double classificacao;
    private Point2D.Double localizacao;
    private int numeroEntregas;
    private double velocidadeMedia; //em km/h
    private String password;

    public Transporte(){
        this.email= new String();
        this.disponibilidade = true;
        this.raio = 0;
        this.certeficado = false;
        this.classificacao = 0;
        this.localizacao = null;
        this.numeroEntregas = 0;
        this.velocidadeMedia = 0;
        this.password = new String();
    }

    public Transporte(String email, boolean disponibilidade, float raio, boolean certeficado, double classificacao,Point2D.Double localizacao, int NumeroEntregas , double VelocidadeMedia, String password) {
        this.email = email;
        this.disponibilidade = disponibilidade;
        this.raio = raio;
        this.certeficado = certeficado;
        this.classificacao = classificacao;
        this.localizacao = localizacao;
        this.numeroEntregas = NumeroEntregas;
        this.velocidadeMedia = VelocidadeMedia;
        this.password = password;
    }

    public Transporte(Transporte a){
        this.email = a.getReferencia();
        this.disponibilidade = a.isDisponivel();
        this.raio = a.getRaio();
        this.certeficado = a.isCerteficado();
        this.classificacao = a.getClassificacao();
        this.localizacao = a.getLocalizacao();
        this.numeroEntregas = a.getNumeroEntregas();
        this.velocidadeMedia = a.getVelocidadeMedia();
        this.password = a.getPassword();
    }


    public String getReferencia() {
        return email;
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

    public Point2D.Double getLocalizacao() {
        return localizacao;
    }

    public int getNumeroEntregas() {
        return numeroEntregas;
    }

    public double getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public String getPassword() {
        return password;
    }

    public void setReferencia(String email) {
        this.email = email;
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

    public void setLocalizacao(Point2D.Double localizacao) {
        this.localizacao = localizacao;

    }

    public void setNumeroEntregas(int numeroEntregas) {
        this.numeroEntregas = numeroEntregas;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public abstract Transporte clone();


    /*metodos*/



    public void aceitaEncomenda(Encomenda a) {
        setDisponibilidade(false);
    }

    /*Aceitar encomendas do tipo médico*/

    public void aceitaMedicamentos(boolean state){
        setDisponibilidade(true);

    }

    //public abstract boolean distanciaValida(Encomenda a);

    public double distancia (Encomenda a){
       return  a.getMoradaLoja().distance(this.getLocalizacao())+a.getMoradaLoja().distance(a.getMoradaUtilizador());
    }

    public boolean distanciaValida(Encomenda a){
        if (a.getMoradaLoja().distance(this.getLocalizacao())>getRaio()) return false;
        else return true;
    }



    public void tempoViagem(Encomenda a) {

    Double tempo = (a.getMoradaLoja().distance(this.getLocalizacao())*60)/this.getVelocidadeMedia();
    Duration duracao = Duration.ofMinutes(tempo.longValue());

        a.setTempo(duracao);
        //a.getData().toLocalTime().plus(duracao);
        }
    /*ENCOMENDA ENTREGUE -> TEMPO DE ENTREGA FINALIZADO*/

    public void encomendaEntregue (Encomenda a, Servico s){
        tempoViagem(a);
        s.adicionaEfetuado(a);
        Map<String,Encomenda> aux = s.getPedidos();
        aux.remove(a.getReferencia());
        s.setPedidos(aux);
        this.setDisponibilidade(true);

    }





}

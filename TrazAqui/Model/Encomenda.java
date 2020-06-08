package Model;

import java.awt.geom.Point2D;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Encomenda implements Serializable
{
     //Variáveis de instância
    private User comprador; //vai ter o email do utilizador
    private Transporte distribuidor; //vai ter o email da empresa/voluntario que efetuou a ecomenda
    private Loja loja; //email da loja a que foi comprado
    private String referencia;
    private float peso;
    private LocalDateTime data; //DD-MM-AA H:M:S a que a encomenda foi feita
    private Duration tempo; //tempo que demorou o transporte de uma entrega
    private List<Produto> produtos;
    private double custoProdutos;
    private double custoTransporte;
    private boolean aceiteTransportador;
    private boolean entregaEfetuada;

    //Construtores
    
    public Encomenda(){

        this.comprador = new User();
        this.distribuidor = new Transporte();
        this.loja = new Loja();
        this.referencia="";
        this.peso=0;
        this.data=null;//LocalDate.now();
        this.tempo=null;
        this.produtos = new ArrayList<>();
        this.custoProdutos = 0;
        this.custoTransporte =0;
        this.aceiteTransportador =false;
        this.entregaEfetuada = false;

    }
    
    public Encomenda(User comprador, Transporte distribuidor, Loja loja, Point2D.Double moradaLoja, Point2D.Double moradaUtilizador, String referencia, float peso, LocalDateTime date, Duration tempo, List<Produto> lst,double custoProdutos, double custoTransporte, boolean efetuada){
       this.comprador = comprador;
       this.distribuidor = distribuidor;
       this.loja = loja;
       this.referencia=referencia;
       this.peso=peso;
       this.data = date;
       this.tempo = tempo;
       setProdutos(lst);
       this.custoProdutos = custoProdutos;
       this.custoTransporte = custoTransporte;
       this.aceiteTransportador =false;
       this.entregaEfetuada = efetuada;
    }


    public Encomenda(Encomenda a){
       this.distribuidor = a.getDistribuidor();
       this.comprador = a.getComprador();
       this.loja = a.getLoja();
       this.referencia=a.getReferencia();
       this.peso=a.getPeso();
       this.data = a.getData();
       this.tempo = a.getTempo();
       this.produtos=a.getProdutos();
       this.custoProdutos = a.getCusto();
       this.custoTransporte = a.getCustoTransporte();
       this.entregaEfetuada = a.isEfetuada();

    }
    
    // Getters
    public User getComprador() {
        return this.comprador;
    }

    public Transporte getDistribuidor() {
        return this.distribuidor;
    }

    public Loja getLoja(){return this.loja; }

    
    public String getReferencia(){
        return this.referencia;
    }

    public float getPeso(){

        return this.peso;
    }

    public double getCustoProdutos() {
        return custoProdutos;
    }

    public boolean isAceiteTransportador() {
        return aceiteTransportador;
    }

    public boolean isEntregaEfetuada() {
        return entregaEfetuada;
    }

    public LocalDateTime getData() {
        return data;
    }

    public List<Produto> getProdutos(){
        ArrayList<Produto> lst = new ArrayList<>();
            for(Produto p: this.produtos)
                lst.add(p.clone());
            return lst;
    }

    public Duration getTempo() {
        return tempo;
    }

    public double getCusto() {
        return custoProdutos;
    }

    public double getCustoTransporte(){
        return custoTransporte;
    }

    public boolean isEfetuada() {
        return entregaEfetuada;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setComprador(User comprador) {
        this.comprador = comprador;
    }

    public void setDistribuidor(Transporte distribuidor) {
        this.distribuidor = distribuidor;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public void setTempo(Duration tempo) {
        this.tempo = tempo;
    }

    public void setReferencia(String referencia){
        this.referencia=referencia;
    }
    
    public void setPeso(float peso){
        this.peso=peso;
    }

    public void setProdutos(List<Produto> prod){
        this.produtos = new ArrayList<>();

        for(Produto p: prod)
        this.produtos.add(p.clone());
    }

    public void setEfetuada(boolean efetuada) {
        this.entregaEfetuada = efetuada;
    }

    public void setCustoTransporte(double custoTransporte) {
        this.custoTransporte = custoTransporte;
    }

    /*metodos*/

    public void setCusto(){
        ArrayList<Double> aux = new ArrayList<>();
        for(Produto p:this.getProdutos())
            aux.add(p.getPreco()*p.getQuantidade());
        double sum = 0;
        for(Double d : aux)
            sum += d;
        this.custoProdutos= sum;
    }

    public void setPesoEncomenda(){
        ArrayList<Double> aux = new ArrayList<>();
        for(Produto p:this.getProdutos())
            aux.add(( p.getPeso()*p.getQuantidade()));
        double sum = 0;
        for(Double d : aux)
            sum += d;
        this.custoProdutos= sum;
    }


    public void DefineEncomenda(List<Produto> p, User u, Loja l, Transporte t, float peso){

        this.setLoja(l);
        this.setDistribuidor(t);
        this.setComprador(u);
        this.setProdutos(p);
        this.setData(LocalDateTime.now()); //data e hora do pedido
        this.setPeso(peso);

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Encomenda)) return false;
        Encomenda encomenda = (Encomenda) o;
        return Float.compare(encomenda.peso, peso) == 0 &&
                Double.compare(encomenda.custoProdutos, custoProdutos) == 0 &&
                entregaEfetuada == encomenda.entregaEfetuada &&
                comprador.equals(encomenda.comprador) &&
                distribuidor.equals(encomenda.distribuidor) &&
                loja.equals(encomenda.loja) &&
                referencia.equals(encomenda.referencia) &&
                data.equals(encomenda.data) &&
                tempo.equals(encomenda.tempo) &&
                produtos.equals(encomenda.produtos);
    }



    @Override
    public String toString() {
        return "Encomenda{" +
                "comprador=" + comprador +
                ", distribuidor=" + distribuidor +
                ", loja=" + loja +
                ", referencia='" + referencia + '\'' +
                ", peso=" + peso +
                ", data=" + data +
                ", tempo=" + tempo +
                ", produtos=" + produtos +
                ", custoProdutos=" + custoProdutos +
                ", custoTransporte=" + custoTransporte +
                ", efetuada=" + entregaEfetuada +
                '}';
    }

    public Encomenda clone(){
        return new Encomenda(this);
    }



}

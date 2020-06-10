package Model;

import java.awt.geom.Point2D;

import java.io.Serializable;

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
    private double peso;
    private LocalDateTime data; //DD-MM-AA H:M:S a que a encomenda foi feita
    private double tempo; //tempo que demorou o transporte de uma entrega
    private List<Produto> produtos;
    private double custoProdutos;
    private double custoTransporte;
    private boolean aceiteTransportador;
    private boolean entregaEfetuada;
    private boolean aceiteCliente;

    //Construtores
    
    public Encomenda(){

        this.comprador = new User();
        this.distribuidor = new Transporte();
        this.loja = new Loja();
        this.referencia="";
        this.peso=0;
        this.data=null;//LocalDate.now();
        this.tempo=0;
        this.produtos = new ArrayList<>();
        this.custoProdutos = 0;
        this.custoTransporte =0;
        this.aceiteTransportador =false;
        this.entregaEfetuada = false;
        this.aceiteCliente = false;


    }
    
    public Encomenda(User comprador, Transporte distribuidor, Loja loja, String referencia, double peso, LocalDateTime date, double tempo, List<Produto> lst,double custoProdutos, double custoTransporte, boolean aceite, boolean efetuada, boolean aceitecliente){
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
       this.aceiteTransportador = aceite;
       this.entregaEfetuada = efetuada;
       this.aceiteCliente = aceitecliente;
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
       this.custoProdutos = a.getCustoProdutos();
       this.custoTransporte = a.getCustoTransporte();
       this.aceiteTransportador = a.isAceiteTransportador();
       this.entregaEfetuada = a.isEfetuada();
       this.aceiteCliente = a.isAceiteCliente();

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

    public double getPeso(){

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

    public boolean isAceiteCliente() {
        return aceiteCliente;
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

    public double getTempo() {
        return tempo;
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

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }

    public void setReferencia(String referencia){
        this.referencia=referencia;
    }
    
    public void setPeso(double peso){
        this.peso=peso;
    }

    public void setProdutos(List<Produto> prod){
        this.produtos = new ArrayList<>();

        for(Produto p: prod)
        this.produtos.add(p.clone());
    }

    public void setAceiteCliente(boolean aceiteCliente) {
        this.aceiteCliente = aceiteCliente;
    }

    public void setEfetuada(boolean efetuada) {
        this.entregaEfetuada = efetuada;
    }

    public void setCustoTransporte(double custoTransporte) {
        this.custoTransporte = custoTransporte;
    }

    public void setCustoProdutos(double custoProdutos) {
        this.custoProdutos = custoProdutos;
    }
    /*metodos*/

    public void setCusto(){
        ArrayList<Double> aux = new ArrayList<>();

        for(Produto p:this.getProdutos())
            aux.add(p.getPreco()*p.getQuantidade());
        double sum = 0;
        for(Double d : aux){

            sum += d;
            }

            setCustoProdutos(sum);

    }

    public void setPesoEncomenda(){
        ArrayList<Double> aux = new ArrayList<>();
        for(Produto p:this.getProdutos())
            aux.add(( p.getPeso()*p.getQuantidade()));
        double sum = 0;
        for(Double d : aux)
            sum += d;
        this.setPeso(sum);
    }

    public void setAceiteTransportador(boolean aceiteTransportador) {
        this.aceiteTransportador = aceiteTransportador;
    }

    public void DefineEncomenda(List<Produto> p, User u, Loja l, double custo,  double peso){

        this.setLoja(l);
        this.setCusto();
        this.setComprador(u);
        this.setProdutos(p);
        this.setData(LocalDateTime.now()); //data e hora do pedido
        this.setPeso(peso);

    }





    @Override
    public String toString() {
        return "Encomenda{" +
                "comprador=" + comprador +
                ", distribuidor=" + distribuidor +
                ", loja=" + loja +
                ", referencia='" + referencia +
                ", peso=" + peso +
                ", data=" + data +
                ", tempo=" + tempo +
                ", produtos=" + produtos +
                ", custoProdutos=" + custoProdutos +
                ", custoTransporte=" + custoTransporte +
                ", efetuada=" + entregaEfetuada +
                '}';
    }

    public String toStringNav() {
        return  "comprador=" + this.getComprador().getNome() +
                ", loja=" + this.getLoja().getNome() +
                ", referencia='" + referencia + '\'' +
                ", peso=" + peso +
                ", pedido feito em " + this.getData().toLocalDate()
                + " às " + this.getData().toLocalTime()
                ;
    }

    public String toStringNavpreco() {
        return  ", loja=" + this.getLoja().getNome() +
                " referencia='" + referencia + '\'' +
                "peso=" + peso +
                " requesitado em " + this.getData().toLocalDate()
                + " às " + this.getData().toLocalTime() +
                " valor unidades " + this.getCustoProdutos() +
                " valor transporte " +this.getCustoTransporte()
                ;
    }

    public Encomenda clone(){
        return new Encomenda(this);
    }



}

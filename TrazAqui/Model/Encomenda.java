package Model;

import java.awt.geom.Point2D;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Encomenda
{
     //Variáveis de instância
    private User comprador; //vai ter o email do utilizador
    private Transporte distribuidor; //vai ter o email da empresa/voluntario que efetuou a ecomenda
    private Loja loja; //email da loja a que foi comprado
    private Point2D.Double moradaLoja; //morada origem é morada da loja
    private Point2D.Double moradaUtilizador; // morada destino é morada do utilizador
    private String referencia;
    private float peso;
    private LocalDateTime data; //DD-MM-AA H:M:S a que a encomenda foi feita
    private Duration tempo; //tempo que demorou o transporte de uma entrega
    private List<Produto> produtos;
    private double custo;
    private boolean efetuada;



    
    //Construtores
    
    public Encomenda(){

        this.comprador = new User();
        this.distribuidor = new Transporte();
        this.loja = new Loja();
        this.moradaLoja=null;
        this.moradaUtilizador=null;
        this.referencia="";
        this.peso=0;
        this.data=null;//LocalDate.now();
        this.tempo=null;
        this.produtos = new ArrayList<>();
        this.custo = 0;
        this.efetuada = false;

    }
    
    public Encomenda(User comprador, Transporte distribuidor, Loja loja, Point2D.Double moradaLoja, Point2D.Double moradaUtilizador, String referencia, float peso, LocalDateTime date, Duration tempo, List<Produto> lst,double custo, boolean efetuada){
       this.comprador = comprador;
       this.distribuidor = distribuidor;
       this.loja = loja;
       this.moradaLoja=moradaLoja;
       this.moradaUtilizador=moradaUtilizador;
       this.referencia=referencia;
       this.peso=peso;
       this.data = date;
       this.tempo = tempo;
       setProdutos(lst);
       this.custo = custo;
       this.efetuada = efetuada;
    }



    public Encomenda(Encomenda a){
       this.distribuidor = a.getDistribuidor();
       this.comprador = a.getComprador();
       this.loja = a.getLoja();
       this.moradaLoja=a.getMoradaLoja();
       this.moradaUtilizador=a.getMoradaUtilizador();
       this.referencia=a.getReferencia();
       this.peso=a.getPeso();
       this.data = a.getData();
       this.tempo = a.getTempo();
       this.produtos=a.getProdutos();
       this.custo = a.getCusto();
       this.efetuada = a.isEfetuada();

    }
    
    // Getters
    public User getComprador() {
        return this.comprador;
    }

    public Transporte getDistribuidor() {
        return this.distribuidor;
    }

    public Loja getLoja(){return this.loja; }

    public Point2D.Double getMoradaLoja(){
        return this.moradaLoja;
    }
    
    public Point2D.Double getMoradaUtilizador(){
        return this.moradaUtilizador;
    }
    
    public String getReferencia(){
        return this.referencia;
    }

    public float getPeso(){

        return this.peso;
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
        return custo;
    }

    public boolean isEfetuada() {
        return efetuada;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setMoradaLoja(Point2D.Double moradaLoja){
        this.moradaLoja=moradaLoja;
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

    public void setMoradaUtilizador(Point2D.Double moradaUtilizador){
        this.moradaUtilizador=moradaUtilizador;
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

    /*metodos*/

    public double setCusto(){
        ArrayList<Double> aux = new ArrayList<>();
        for(Produto p:this.getProdutos())
            aux.add(p.getPreco());
        double sum = 0;
        for(Double d : aux)
            sum += d;
        return sum;
    }

/*VER*/


    private void geraReferencia(){

    }



     public boolean equals(Object o){
        if(this == o)
        return true;
        if((o==null) || (o.getClass() != this.getClass())) 
        return false;
        else{
        Encomenda a = (Encomenda) o;
        return this.distribuidor.equals(a.getDistribuidor())
                && this.comprador.equals(a.getComprador())
                && this.moradaLoja == a.getMoradaLoja()
                && this.moradaUtilizador == a.getMoradaUtilizador()
                && this.referencia.equals(a.getReferencia())
                && this.peso == a.getPeso();

        }
    }



    public String toString(){
        StringBuffer sb = new StringBuffer();

        sb.append("Referência de Origem"); sb.append(this.getDistribuidor()+"\n");
        sb.append("Referência do Destino"); sb.append(this.getComprador()+"\n");
        sb.append("Morada de Origem: "); sb.append(this.getMoradaLoja()+"\n");
        sb.append("Morada de Destino: "); sb.append(this.getMoradaUtilizador()+"\n");
        sb.append("Referência: "); sb.append(this.getReferencia()+"\n");
        sb.append("Peso: "); sb.append(this.getPeso()+"\n");
        sb.append ("Data da encomenda: "); sb.append(this.getData()+"\n");


        return sb.toString();
    }

    public Encomenda clone(){
        return new Encomenda(this);
    }
}

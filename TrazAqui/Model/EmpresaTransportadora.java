package Model;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class EmpresaTransportadora extends Transporte
{
    //Variáveis de instância


    private double taxa; //taxa de transporte
    private Map<String,Encomenda> encomendas; //encomendas feitas
    private double totalFaturado;



    public EmpresaTransportadora(){
        super();
        this.taxa = 0;
        this.encomendas = new HashMap <> ();
        this.totalFaturado = 0;
    }


    public EmpresaTransportadora(String email,String referencia, String nome, String password, Point2D.Double morada, long nif, boolean disponibilidade, float raio, boolean certeficado, double classificacao, int numeroEntregas , double velocidadeMedia, double numeroKms, double custoTransporte,Map<String,Encomenda> encomendas,double totalFaturado) {

        super(email,referencia,nome, password, morada,nif, disponibilidade,raio, certeficado,  classificacao, numeroEntregas,velocidadeMedia, numeroKms);
        this.taxa = custoTransporte;
        this.encomendas = encomendas;
        this.totalFaturado = totalFaturado;

    }

    public EmpresaTransportadora (EmpresaTransportadora e){
        super(e.getEmail(),e.getReferencia(),e.getNome(), e.getPassword(),e.getMorada(),e.getNif(),e.isDisponivel(),e.getRaio(),e.isCerteficado(),e.getClassificacao(),e.getNumeroEntregas(),e.getVelocidadeMedia(),e.getNumeroKms());
        setTaxa(e.getTaxa());
        setEncomendas(e.getEncomendas());
        setTotalFaturado(e.getTotalFaturado());

    }

    //GETTER

   public double getTaxa() {
        return taxa;
    }

    public Map<String,Encomenda> getEncomendas(){
        Map<String,Encomenda> aux = new HashMap<>();
        for (Map.Entry<String,Encomenda> e : this.encomendas.entrySet())
            aux.put(e.getKey(),e.getValue().clone());
        return aux;
    }

    public double getTotalFaturado() {
        return totalFaturado;
    }

    //SETTER

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    public void setEncomendas(Map<String,Encomenda>enc){
        this.encomendas = new HashMap<>();
        enc.entrySet().forEach(e-> this.encomendas.put(e.getKey(),
                e.getValue().clone()));
    }

    public void setTotalFaturado(double totalFaturado) {
        this.totalFaturado = totalFaturado;
    }

    public boolean equals(Object o){
        if(this == o)
        return true;
        if((o==null) || (o.getClass() != this.getClass())) 
        return false;
        else{
        EmpresaTransportadora a = (EmpresaTransportadora) o;
        return this.taxa == a.getTaxa();
        }
    }
    



        public EmpresaTransportadora clone(){
        return new EmpresaTransportadora(this);
    }


/*Métodos*/

    public double defineCusto(Encomenda a ){
    return ((this.getMorada().distance(a.getMoradaLoja())+a.getMoradaLoja().distance(a.getMoradaUtilizador()))*this.getTaxa()*a.getPeso());
    }

    //adiciona valor de um transporte ao total faturado
    public void addFatura(Encomenda a){
        setTotalFaturado(getTotalFaturado()+defineCusto(a));
    }

    public boolean distanciaValida(Encomenda a){
        if (a.getMoradaLoja().distance(this.getMorada())>getRaio()) return false;
        else return true;
    }










}


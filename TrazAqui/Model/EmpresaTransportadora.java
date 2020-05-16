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


    public EmpresaTransportadora(String referencia, boolean disponibilidade, float raio, boolean certeficado, double classificacao, Point2D.Double latiLongi, int numeroEntregas, double velocidadeMedia, double custoTransporte,Map<String,Encomenda> encomendas, String password,double totalFaturado) {

        super( referencia, disponibilidade,raio, certeficado,  classificacao, latiLongi, numeroEntregas, velocidadeMedia, password);
        this.taxa = custoTransporte;
        this.encomendas = encomendas;
        this.totalFaturado = totalFaturado;

    }

    public EmpresaTransportadora (EmpresaTransportadora e){
        super(e.getReferencia(),e.isDisponivel(),e.getRaio(),e.isCerteficado(),e.getClassificacao(),e.getLocalizacao(),e.getNumeroEntregas(),e.getVelocidadeMedia(), e.getPassword());
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
    return ((getLocalizacao().distance(a.getMoradaLoja())+a.getMoradaLoja().distance(a.getMoradaUtilizador()))*this.getTaxa()*a.getPeso());
    }


    public boolean distanciaValida(Encomenda a){
        if (a.getMoradaLoja().distance(getLocalizacao())>getRaio()) return false;
        else return true;
    }








}


package Model;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
/*
public class Servico {
    private Map<String, Encomenda> pedidos; //k: referencia da loja, V: Encomenda
    private Map<String, Encomenda> efetuados;
    private TrazAqui trazAqui;
*/

    /*Construtores*/
/*
    public Servico() {
        this.pedidos = new HashMap<>();
        this.efetuados = new HashMap<>();
        this.trazAqui = new TrazAqui();
    }


    public Servico(Map<String, Encomenda> pedidos, Map<String, Encomenda> efetuados, TrazAqui db) {
        this.pedidos = pedidos;
        this.efetuados = efetuados;
        this.trazAqui = db;
    }

    public Servico(Servico s) {
        this.pedidos = s.getPedidos();
        this.efetuados = s.getEfetuados();
        this.trazAqui = s.getDataBase();
    }

*/
    /*getters*/
/*
    public Map<String, Encomenda> getPedidos() {
        Map<String, Encomenda> aux = new HashMap<>();
        for (Map.Entry<String, Encomenda> e : this.pedidos.entrySet())
            aux.put(e.getKey(), e.getValue().clone());
        return aux;
    }

    public Map<String, Encomenda> getEfetuados() {
        Map<String, Encomenda> aux = new HashMap<>();
        for (Map.Entry<String, Encomenda> e : this.efetuados.entrySet())
            aux.put(e.getKey(), e.getValue().clone());
        return aux;
    }
*/
    /*setters*/

/*
    public void setPedidos(Map<String, Encomenda> ped) {
        this.pedidos = new HashMap<>();
        ped.entrySet().forEach(e -> this.pedidos.put(e.getKey(),
                e.getValue().clone()));
    }

    public void setEfetuados(Map<String, Encomenda> eft) {
        this.efetuados = new HashMap<>();
        eft.entrySet().forEach(e -> this.efetuados.put(e.getKey(),
                e.getValue().clone()));
    }

    public TrazAqui getDataBase() {
        return this.trazAqui;
    }

*/
    /*Metodos*/




/*

}
*/

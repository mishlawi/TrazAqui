package Model;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Servico {
    private Map<String, Encomenda> pedidos; //k: referencia da loja, V: Encomenda
    private Map<String, Encomenda> efetuados;
    private DataBase dataBase;


    /*Construtores*/

    public Servico() {
        this.pedidos = new HashMap<>();
        this.efetuados = new HashMap<>();
        this.dataBase = new DataBase();
    }


    public Servico(Map<String, Encomenda> pedidos, Map<String, Encomenda> efetuados, DataBase db) {
        this.pedidos = pedidos;
        this.efetuados = efetuados;
        this.dataBase = db;
    }

    public Servico(Servico s) {
        this.pedidos = s.getPedidos();
        this.efetuados = s.getEfetuados();
        this.dataBase = s.getDataBase();
    }


    /*getters*/

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

    /*setters*/


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

    public DataBase getDataBase() {
        return this.dataBase;
    }


    /*Metodos*/

    //define adicoes aos pedidos --> KEY: referencia encomenda || VALUE: encomenda
    public void adicionaPedido(Encomenda e) {
        this.pedidos.put(e.getReferencia(), e.clone());
    }

    //obter encomenda a partir da sua referencia (key)
    public Encomenda getEncomenda(String referencia) {
        return this.getPedidos().get(referencia);
    }

    //define adicoes aos efetuados
    public void adicionaEfetuado(Encomenda e) {
        this.efetuados.put(e.getReferencia(), e.clone());
    }


    //filtra as empresas disponiveis e que conseguem fazer a entrega nas moradas da encomenda
    public Map<String, EmpresaTransportadora> EncomendaEmpresas(Encomenda a) {
        Map<String, EmpresaTransportadora> aux = new HashMap<>();
        for (Map.Entry<String, Transporte> e : this.dataBase.getTransportador().entrySet()) {
            if (e.getValue() instanceof EmpresaTransportadora) {
                EmpresaTransportadora ep = (EmpresaTransportadora) e.getValue();
                if (ep.isDisponivel() && ep.distanciaValida(a)) aux.put(e.getKey(), ep.clone());
            }
        }

        return aux;
    }


    //filtra os voluntarios disponiveis e que conseguem fazer a entrega nas moradas da encomenda
    public Map<String, Voluntario> EncomendaVoluntarios(Encomenda a) {
        Map<String, Voluntario> aux = new HashMap<>();

        for (Map.Entry<String, Transporte> e : this.dataBase.getTransportador().entrySet()) {
            if (e.getValue() instanceof Voluntario) {
                Voluntario ep = (Voluntario) e.getValue();
                if (ep.isDisponivel() && ep.distanciaValida(a)) aux.put(e.getKey(), ep.clone());
            }
        }

        return aux;
    }

    //filtra os transportadores disponiveis e que conseguem fazer a entrega nas moradas da encomenda
    public Map<String, Transporte> EncomendaTransporte(Encomenda a) {
        Map<String, Transporte> aux = new HashMap<>();

        for (Map.Entry<String, Transporte> e : this.dataBase.getTransportador().entrySet()) {

            if (e.getValue().isDisponivel() && e.getValue().distanciaValida(a))
                aux.put(e.getKey(), e.getValue().clone());
        }

        return aux;
    }

    //escolhe o transportador ( voluntario ou empresa) que percorre menos distancia ate a
    public Transporte sortEncomendaTransporte(Encomenda a, Map<String, Transporte> map1) {
        String aux = "";
        double distancia = 0;
        for (Map.Entry<String, Transporte> e : map1.entrySet()) {
            if (e.getValue().distancia(a) > distancia) {
                distancia = e.getValue().distancia(a);
                aux = e.getKey();
            }
        }
       return map1.get(aux);
    }





}


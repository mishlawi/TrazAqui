package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase {

    private Map<String,Loja> lojas;
    private Map<String,User> users;
    private Map<String,Transporte> transporte;
    private Map<String,Encomenda> encomendas; //k: referencia encomenda  //todas as encomendas ja feitas


    public DataBase(){
        this.lojas = new HashMap<>();
        this.users = new HashMap<>();
        this.transporte = new HashMap<>();
        this.encomendas = new HashMap<>();
    }


    public DataBase(Map<String, Loja> lojas, Map<String, User> users, Map<String, Encomenda> encomendas) {
        this.lojas = lojas;
        this.users = users;

       this.encomendas = encomendas;
    }

    public DataBase(DataBase db) {
        this.lojas = db.getLojas();
        this.users = db.getUsers();
        this.transporte = db.getTransportador();
        this.encomendas = db.getEncomendas();
    }


    public Map<String,Encomenda> getEncomendas(){
        Map<String,Encomenda> aux = new HashMap<>();
        for(Map.Entry<String,Encomenda> e:this.encomendas.entrySet())
            aux.put(e.getKey(),e.getValue());
        return aux;

    }

    public Map<String, Loja> getLojas() {
        Map <String,Loja> aux = new HashMap<>();
        for(Map.Entry<String,Loja> e:this.lojas.entrySet())
            aux.put(e.getKey(),e.getValue().clone()); //este clone a da classe aluno
        return aux;
    }

    public void setLojas(Map<String, Loja> lj) {
        this.lojas = new HashMap<>();
        lj.entrySet().forEach(e-> this.lojas.put(e.getKey(),
                e.getValue().clone()));
    }

    public Map<String, User> getUsers() {
        Map <String,User> aux = new HashMap<>();
        for(Map.Entry<String,User> e:this.users.entrySet())
            aux.put(e.getKey(),e.getValue().clone()); //este clone a da classe aluno
        return aux;
    }

    public void setUsers(Map<String, User> usr) {
        this.users = new HashMap<>();
        usr.entrySet().forEach(e-> this.users.put(e.getKey(),
                e.getValue().clone()));
    }



//retorna os transportadores(empresas+voluntarios)
    public Map<String,Transporte> getTransportador(){
        Map <String,Transporte> aux = new HashMap<>();
        for(Map.Entry<String,Transporte> e:this.transporte.entrySet())
            aux.put(e.getKey(),e.getValue().clone());
        return aux;
    }
//retorna a as empresas de transporte
    public Map<String,EmpresaTransportadora> getEmpresaTransporte(){
        Map <String,EmpresaTransportadora> aux = new HashMap<>();
        for(Map.Entry<String,Transporte> e:this.transporte.entrySet())
            if  (e.getValue() instanceof EmpresaTransportadora){
                EmpresaTransportadora ep = (EmpresaTransportadora) e.getValue();
                aux.put(e.getKey(),ep.clone());
            }

        return aux;
    }
    //retorna os voluntarios
    public Map<String,Voluntario> getVoluntariosTransporte(){
        Map <String,Voluntario> aux = new HashMap<>();
        for(Map.Entry<String,Transporte> e:this.transporte.entrySet())
            if  (e.getValue() instanceof Voluntario){
                Voluntario ep = (Voluntario) e.getValue();
                aux.put(e.getKey(),ep.clone());
            }

        return aux;
    }



    //dispoe as encomendas em lista por utilizador k: email user v:lista de encomendas do utlizador
    public Map<String, List<Encomenda>> getUtilizadorEncomendas(){
    Map<String,List<Encomenda>> aux = new HashMap<>();
    for(Map.Entry<String,Encomenda> e:this.encomendas.entrySet())
        if(aux.containsKey(e.getValue().getComprador())) { //se o map ja tem encomendas guardadas de um utilizador
           List<Encomenda> lista = aux.get(e.getValue().getComprador()); //cria uma lista com as encomendas anteriormente guardadas para um utilizador
            lista.add(e.getValue().clone());            //adiciona a nova encomenda
            aux.put(e.getValue().getComprador(), lista); //adiciona a nova lista ao utilizador
        }
        else{                                               //se nao existirem ocorrencias no map
            List<Encomenda> lista2 = new ArrayList<>();      //cria se uma lista
            lista2.add(e.getValue().clone());                // adiciona-se o elemento a lista
            aux.put(e.getValue().getComprador(),lista2);     //adiciona se lista ao utilizador
        }
    return aux;
    }




}


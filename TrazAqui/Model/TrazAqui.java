package Model;

import java.io.*;


import java.util.*;
import java.util.stream.Collectors;

public class TrazAqui {



    private Map<String,Loja> lojas; //k : email
    private Map<String,User> users; // k: email
    private Map<String,Transporte> transporte; // k: email
    private Map<String,Encomenda> encomendas; //k: referencia da encomenda
    private User userIn;
    private Voluntario voluntarioIn;
    private EmpresaTransportadora empresaIn;
    private Loja lojaIn;
    private Admin adminIn;
    private Encomenda encomenda;



    public TrazAqui(){
        this.lojas = new HashMap<>();
        this.users = new HashMap<>();
        this.transporte = new HashMap<>();
        this.encomendas = new HashMap<>();
        this.userIn = new User();
        this.voluntarioIn = new Voluntario();
        this.empresaIn = new EmpresaTransportadora();
        this.lojaIn = new Loja();
        this.encomenda = new Encomenda();


    }



    public TrazAqui(Map<String, Loja> lojas, Map<String, User> users, User userIn, EmpresaTransportadora empresa, Voluntario voluntario, Loja loja, Map<String, Encomenda> encomendas, Encomenda enc) {
        this.lojas = lojas;
        this.users = users;
        this.encomendas = encomendas;
        this.userIn = userIn;
        this.voluntarioIn = voluntario;
        this.empresaIn = empresa;
        this.lojaIn = loja;
        this.encomenda = enc;

    }
    public TrazAqui(TrazAqui db) {
        this.lojas = db.getLojas();
        this.users = db.getUsers();
        this.transporte = db.getTransportador();
        this.encomendas = db.getEncomendas();
        this.userIn = db.getClienteIn();
        this.voluntarioIn = db.getVoluntarioIn();
        this.empresaIn = db.getEmpresaIn();
        this.encomenda = db.getEncomenda();
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



    public Map<String, User> getUsers() {
        Map <String,User> aux = new HashMap<>();
        for(Map.Entry<String,User> e:this.users.entrySet())
            aux.put(e.getKey(),e.getValue().clone()); //este clone a da classe aluno
        return aux;
    }


    public User getClienteIn()
    {
        return this.userIn.clone();
    }

    public void setClienteIn(User userIn) {
        this.userIn = userIn;
    }

    public Voluntario getVoluntarioIn() {
        return voluntarioIn;
    }

    public void setVoluntarioIn(Voluntario voluntarioIn) {
        this.voluntarioIn = voluntarioIn;
    }

    public EmpresaTransportadora getEmpresaIn() {
        return empresaIn;
    }

    public void setEmpresaIn(EmpresaTransportadora empresaIn) {
        this.empresaIn = empresaIn;
    }

    public Loja getLojaIn() {
        return lojaIn;
    }

    public void setLojaIn(Loja lojaIn) {
        this.lojaIn = lojaIn;
    }

    public Encomenda getEncomenda() {
        return encomenda;
    }

    public void setEnc(Encomenda enc) {
        this.encomenda = enc;
    }

    public void setUsers(Map<String, User> usr) {
        this.users = new HashMap<>();
        usr.entrySet().forEach(e-> this.users.put(e.getKey(),
                e.getValue().clone()));
    }


    public void setLojas(Map<String, Loja> lj) {
        this.lojas = new HashMap<>();
        lj.entrySet().forEach(e-> this.lojas.put(e.getKey(),
                e.getValue().clone()));
    }

    /**
     * "getters" auxiliares
     * @return
     */


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



    //dispoe as encomendas ja efetuadas em lista por utilizador k: email user v:lista de encomendas do utlizador
    public Map<String, List<Encomenda>> getUtilizadorEncomendas(){
    Map<String,List<Encomenda>> aux = new HashMap<>();
    for(Map.Entry<String,Encomenda> e: listToMapEncomendasEfetuadas().entrySet())
        if(aux.containsKey(e.getValue().getComprador())) { //se o map ja tem encomendas guardadas de um utilizador
           List<Encomenda> lista = aux.get(e.getValue().getComprador()); //cria uma lista com as encomendas anteriormente guardadas para um utilizador
            lista.add(e.getValue().clone());            //adiciona a nova encomenda
            aux.put(e.getValue().getComprador().getEmail(), lista); //adiciona a nova lista ao utilizador
        }
        else{                                               //se nao existirem ocorrencias no map
            List<Encomenda> lista2 = new ArrayList<>();      //cria se uma lista
            lista2.add(e.getValue().clone());                // adiciona-se o elemento a lista
            aux.put(e.getValue().getComprador().getEmail(),lista2);     //adiciona se lista ao utilizador
        }
    return aux;
    }




    public List<Encomenda> getEncomendasNaoEfetuadas()
    {
        List<Encomenda> aux = new ArrayList<>();
        for(Encomenda v : this.encomendas.values())
            if (v.isEfetuada()==false){
                Encomenda enc = (Encomenda) v.clone();
                aux.add(enc);
            }
        return aux;
    }

    public List<Encomenda> getEncomendasEfetuadas()
    {
        List<Encomenda> aux = new ArrayList<>();
        for(Encomenda v : this.encomendas.values())
            if (v.isEfetuada()==true){
                Encomenda enc = v.clone();
                aux.add(enc);
            }
        return aux;
    }

    public Map<String,Encomenda> listToMapEncomendasEfetuadas(){

            Map<String, Encomenda> map = getEncomendasEfetuadas().stream()
                    .collect(Collectors.toMap(Encomenda::getReferencia, encomenda -> encomenda.clone()));
            return map;
    }


    //obter encomenda a partir da sua referencia (key)
    public Encomenda getEncomenda(String referencia) {
        return getEncomendas().get(referencia);
    }


    /**
     * encomenda
     */

    public void defUser(){
        getEncomenda().setComprador(this.userIn);
    }

    public void adicionaPedido(Encomenda e) {
        this.encomendas.put(e.getReferencia(),e.clone());
    }



    /**
     *
     * Adicao de encomendas, lojas e voluntarios a base de dados
     */


    public void RegistaLoja (Loja l) throws MailRegistadoException {
        if (this.getLojas().containsKey(l.getEmail())) throw new MailRegistadoException("Email já registado");
        this.getLojas().put(l.getEmail(), l.clone());
    }


    public void registarUtilizador(User c)throws MailRegistadoException
    {
        if (this.getUsers().containsKey(c.getEmail())) throw new MailRegistadoException("Email já registado");
        this.getUsers().put(c.getEmail(), c.clone());
    }

    public void registarVoluntario (Voluntario v) throws MailRegistadoException
    {
        if(this.getTransportador().containsKey(v.getEmail())) throw new MailRegistadoException("Email já registado");
        this.getTransportador().put(v.getEmail(), v.clone());
    }

    public void registarEmpresa (EmpresaTransportadora e) throws MailRegistadoException
    {
        if(this.getTransportador().containsKey(e.getEmail())) throw new MailRegistadoException("Email já registado");
        this.getTransportador().put(e.getEmail(), e.clone());
    }

    /**
     *
     * Inicio de sessao de clientes, lojas e transportadores
     */


    //inicio de sessao de cliente
    public void iniciaSessaoC(String email, String password) throws LoginErradoException
    {
        User u = users.get(email);
        if (u==null) throw new LoginErradoException("Email não registado");
        if(u.getPassword().equals(password)) this.userIn = u;
        else  throw new LoginErradoException("Password errada");
    }

    //inicio de sessao de empresa
    public void iniciaSessaoE(String email, String password) throws LoginErradoException
    {
        EmpresaTransportadora e = getEmpresaTransporte().get(email);
        if (e==null) throw new LoginErradoException("Email não registado");
        if(e.getPassword().equals(password)) this.empresaIn = e;
        else  throw new LoginErradoException("Password errada");
    }

    //inicio de sessao de voluntario
    public void iniciaSessaoV(String email, String password) throws LoginErradoException
    {
        Voluntario v = getVoluntariosTransporte().get(email);
        if (v==null) throw new LoginErradoException("Email não registado");
        if(v.getPassword().equals(password)) this.voluntarioIn = v;
        else  throw new LoginErradoException("Password errada");
    }

    //inicio de sessao de loja
    public void iniciaSessaoL(String email, String password) throws LoginErradoException
    {
        Loja l = getLojas().get(email);
        if (l==null) throw new LoginErradoException("Email não registado");
        if(l.getPassword().equals(password)) this.lojaIn = l;
        else  throw new LoginErradoException("Password errada");
    }


    /**
     * metodos
     */

    public List<EmpresaTransportadora> top10Kms(){
        return getEmpresaTransporte().values().stream()
                .sorted(new KmComparator())
                .limit(10).collect(Collectors.toList());
    }

    public List<User> topUsers(){
    Map<String,List<Encomenda>> aux = getUtilizadorEncomendas();
    List<User> users = new ArrayList<User>();
        Map<String,Integer>res= new HashMap<>();
        for(Map.Entry<String,List<Encomenda>> e: aux.entrySet()) {
            res.put(e.getKey(),e.getValue().size());

        }
        for(Map.Entry<String,Integer> e: res.entrySet()){
            users.add(getUsers().get(e.getKey()));
        }
        Collections.sort(users, Collections.reverseOrder());
        users=users.stream().limit(10).collect(Collectors.toList());

        return users;
        }




    //filtra as empresas disponiveis e que conseguem fazer a entrega nas moradas da encomenda
    public Map<String, EmpresaTransportadora> EncomendaEmpresas(Encomenda a) {
        Map<String, EmpresaTransportadora> aux = new HashMap<>();
        for (Map.Entry<String, Transporte> e : getTransportador().entrySet()) {
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

        for (Map.Entry<String, Transporte> e : getTransportador().entrySet()) {
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

        for (Map.Entry<String, Transporte> e : getTransportador().entrySet()) {

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

    public void daClassificacao(int classificacao){
        String classificado = this.getEncomenda().getDistribuidor().getEmail();
        this.getTransportador().get(classificado).setClassificacao(classificacao);
    }



    /**
Carregamento de dados
 **/
    public static TrazAqui lerDados() throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("TrazAqui.data"));
        TrazAqui db = (TrazAqui) ois.readObject();
        ois.close();
        return db;
    }


    public void gravar() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("TrazAqui.data"));
        oos.writeObject(this);

        oos.flush();
        oos.close();
    }

}


package Model;

import java.io.*;


import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TrazAqui implements Serializable {



    private Map<String,Loja> lojas;
    private Map<String,User> users;
    private Map<String,Transporte> transporte; // k: email
    private Map<String,Encomenda> encomendas; //k: referencia da encomenda
    private Map<String,Produto> produtos;
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
        this.produtos = new HashMap<>();
        this.userIn = new User();
        this.voluntarioIn = new Voluntario();
        this.empresaIn = new EmpresaTransportadora();
        this.lojaIn = new Loja();
        this.encomenda = new Encomenda();


    }


    public TrazAqui(Map<String, Loja> lojas, Map<String, User> users,Map<String,Produto> produtos, User userIn, EmpresaTransportadora empresa, Voluntario voluntario, Loja loja, Map<String, Encomenda> encomendas, Encomenda enc) {
        this.lojas = lojas;
        this.users = users;
        this.encomendas = encomendas;
        this.produtos = produtos;
        this.userIn = userIn;
        this.voluntarioIn = voluntario;
        this.empresaIn = empresa;
        this.lojaIn = loja;
        this.encomenda = enc;

    }
    public TrazAqui(TrazAqui db) {
        this.lojas = db.getLojas();
        this.users = db.getUsers();
        this.produtos = db.getProdutos();
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
            aux.put(e.getKey(),e.getValue().clone());
        return aux;
    }




    public Map<String, User> getUsers() {
        Map <String,User> aux = new HashMap<>();
        for(Map.Entry<String,User> e:this.users.entrySet())
            aux.put(e.getKey(),e.getValue().clone());
        return aux;
    }

    public Map<String, Produto> getProdutos() {
        Map <String,Produto> aux = new HashMap<>();
        for(Map.Entry<String,Produto> e:this.produtos.entrySet())
            aux.put(e.getKey(),e.getValue().clone());
        return aux;
    }

    public Map<String, User> getUsersEmail() {
        Map <String,User> aux = new HashMap<>();
        for(Map.Entry<String,User> e:this.users.entrySet())
            aux.put(e.getValue().getEmail(),e.getValue().clone());
        return aux;


    }

    public Map<String, Loja> getLojasEmail() {
        Map <String,Loja> aux = new HashMap<>();
        for(Map.Entry<String,Loja> e:this.lojas.entrySet())
            aux.put(e.getValue().getEmail(),e.getValue().clone());
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

    public Map<String,Transporte> getTransportadorEmail(){
        Map <String,Transporte> aux = new HashMap<>();
        for(Map.Entry<String,Transporte> e:this.transporte.entrySet())
            aux.put(e.getValue().getEmail(),e.getValue().clone());
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


    public Map<String,EmpresaTransportadora> getEmpresaTransporteEmail(){
        Map <String,EmpresaTransportadora> aux = new HashMap<>();
        for(Map.Entry<String,Transporte> e:this.transporte.entrySet())
            if  (e.getValue() instanceof EmpresaTransportadora){
                EmpresaTransportadora ep = (EmpresaTransportadora) e.getValue();
                aux.put(e.getValue().getEmail(),ep.clone());
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

    public Map<String,Voluntario> getVoluntariosTransporteEmail(){
        Map <String,Voluntario> aux = new HashMap<>();
        for(Map.Entry<String,Transporte> e:this.transporte.entrySet())
            if  (e.getValue() instanceof Voluntario){
                Voluntario ep = (Voluntario) e.getValue();
                aux.put(e.getValue().getEmail(),ep.clone());
            }

        return aux;
    }



    //dispoe as encomendas ja efetuadas em lista por utilizador k: referencia user v:lista de encomendas do utlizador
    public Map<String, List<Encomenda>> getUtilizadorEncomendas(){
    Map<String,List<Encomenda>> aux = new HashMap<>();
    for(Map.Entry<String,Encomenda> e: listToMapEncomendasEfetuadas().entrySet())
        if(aux.containsKey(e.getValue().getComprador())) { //se o map ja tem encomendas guardadas de um utilizador
           List<Encomenda> lista = aux.get(e.getValue().getComprador()); //cria uma lista com as encomendas anteriormente guardadas para um utilizador
            lista.add(e.getValue().clone());            //adiciona a nova encomenda
            aux.put(e.getValue().getComprador().getReferencia(), lista); //adiciona a nova lista ao utilizador
        }
        else{                                               //se nao existirem ocorrencias no map
            List<Encomenda> lista2 = new ArrayList<>();      //cria se uma lista
            lista2.add(e.getValue().clone());                // adiciona-se o elemento a lista
            aux.put(e.getValue().getComprador().getReferencia(),lista2);     //adiciona se lista ao utilizador
        }
    return aux;
    }

    public List<EmpresaTransportadora> empresasOrdenadasCusto(){
        Set<EmpresaTransportadora> res = new TreeSet<>(new ComparatorTaxa());
        for(Transporte a : this.transporte.values()){
            if(a instanceof EmpresaTransportadora){
                EmpresaTransportadora e = (EmpresaTransportadora) a;
            res.add(e.clone());
            }
        }

        return res.stream().collect(Collectors.toList());
    }

    public EmpresaTransportadora empresaMaisBarata(){
        List<EmpresaTransportadora> emp = empresasOrdenadasCusto();
        int n = emp.size()-1;
        EmpresaTransportadora e = emp.get(n);
        return e;
    }


    public void adicionaTransportador(Transporte t) {
        this.transporte.put(t.getReferencia(),t.clone());
    }
    public void adicionaUser(User t) {
        this.users.put(t.getReferencia(),t.clone());
    }
    public void adicionaLoja(Loja j) {
        this.lojas.put(j.getReferencia(), j.clone());
    }

    public void adicionaProduto(Produto j) {
        this.produtos.put(j.getReferencia(), j.clone());
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

    public void defUserEncomenda(){
        this.encomenda.setComprador(this.userIn);
    }

    public void adicionaEncomenda(Encomenda e) {
        this.encomendas.put(e.getReferencia(),e.clone());
    }






    /**
     *
     * Adicao de encomendas, lojas e voluntarios a base de dados
     */



    public void RegistaLoja (Loja l) throws MailRegistadoException {
        if (this.getLojas().containsKey(l.getReferencia())) throw new MailRegistadoException("Loja já registada");
        this.lojas.put(l.getReferencia(), l.clone());
    }


    public void registarUtilizador(User c)throws MailRegistadoException
    {
        if (this.getUsers().containsKey(c.getReferencia())) throw new MailRegistadoException("Utilizador já registado");
        this.users.put(c.getReferencia(), c.clone());
    }

    public void registarVoluntario (Voluntario v) throws MailRegistadoException
    {
        if(this.getTransportador().containsKey(v.getReferencia())) throw new MailRegistadoException("Voluntario já registado");
        this.transporte.put(v.getReferencia(), v.clone());
    }

    public void registarEmpresa (EmpresaTransportadora e) throws MailRegistadoException
    {
        if(this.getTransportador().containsKey(e.getReferencia())) throw new MailRegistadoException("empresa já registado");
        this.transporte.put(e.getReferencia(), e.clone());
    }

    public void RegistaLojaEmail (Loja l) throws MailRegistadoException {
        if (this.getLojasEmail().containsKey(l.getEmail())) throw new MailRegistadoException("Email loja já registado");
        this.lojas.put(l.getReferencia(), l.clone());
    }


    public void registarUtilizadorEmail (User c)throws MailRegistadoException
    {
        if (this.getUsersEmail().containsKey(c.getEmail())) throw new MailRegistadoException("Email utilizador já registado");
        this.users.put(c.getReferencia(), c.clone());
    }

    public void registarVoluntarioEmail (Voluntario v) throws MailRegistadoException
    {
        if(this.getTransportadorEmail().containsKey(v.getEmail())) throw new MailRegistadoException("Email voluntario já registado");
        this.transporte.put(v.getReferencia(), v.clone());
        System.out.println(this.getVoluntariosTransporte());
    }

    public void registarEmpresaEmail (EmpresaTransportadora e) throws MailRegistadoException
    {
        if(this.getTransportadorEmail().containsKey(e.getEmail())) throw new MailRegistadoException("Email empresa já registado");
        this.transporte.put(e.getReferencia(), e.clone());
    }


    /**
     *
     * Inicio de sessao de clientes, lojas e transportadores
     */


    //inicio de sessao de cliente
    public void iniciaSessaoC(String email, String password) throws LoginErradoException
    {

        User u = this.getUsersEmail().get(email);
        if (u==null) throw new LoginErradoException("Email cliente não registado");
        if(u.getPassword().equals(password)) this.userIn = u;
        else  throw new LoginErradoException("Password errada");
    }

    //inicio de sessao de empresa
    public void iniciaSessaoE(String email, String password) throws LoginErradoException
    {
        EmpresaTransportadora e = this.getEmpresaTransporteEmail().get(email);
        if (e==null) throw new LoginErradoException("Email empresa não registado");
        if(e.getPassword().equals(password)) this.empresaIn = e;
        else  throw new LoginErradoException("Password errada");
    }

    //inicio de sessao de voluntario
    public void iniciaSessaoV(String email, String password) throws LoginErradoException
    {
        Voluntario v = this.getVoluntariosTransporteEmail().get(email);
        if (v==null) throw new LoginErradoException("Email voluntario não registado");
        if(v.getPassword().equals(password)) this.voluntarioIn = v;
        else  throw new LoginErradoException("Password errada");
    }

    //inicio de sessao de loja
    public void iniciaSessaoL(String email, String password) throws LoginErradoException
    {
        Loja l = this.getLojasEmail().get(email);
        System.out.println(l);
        if (l==null) throw new LoginErradoException("Email loja não registado");
        if(l.getPassword().equals(password)) this.lojaIn = l;
        else  throw new LoginErradoException("Password errada");
    }

    /**
     * metodos com Datas
     */


    //encomendas pedidas por ordem
    public List<Encomenda> encGlobaisData(){
        return this.encomendas.values().stream().sorted(new DataComparator()).collect(Collectors.toList());
    }

    public double totalFaturadoPeriodo(String referencia, LocalDate data){
        List<Encomenda> a = encGlobaisData().stream().filter(e->e.getDistribuidor().getReferencia().equals(referencia)).filter(e->e.getData().toLocalDate().isBefore(data)).sorted(new DataComparator()).collect(Collectors.toList());
        double res=0;
        for(Encomenda e : a){
            res+=e.getCustoTransporte();
        }
    return res;
    }




    public Map<LocalDate,List<Encomenda>> encPorDatas(Map<String,Encomenda> aux ){
//
        Map<LocalDate,List<Encomenda>> time = new HashMap<>();

        for(Map.Entry<String,Encomenda> e: aux.entrySet()){
            if(time.containsKey(e.getValue().getData().toLocalDate())){
                List<Encomenda> a = new ArrayList<>();
                a = time.get(e.getValue().getData().toLocalDate());
                a.add(e.getValue().clone());
                time.put(e.getValue().getData().toLocalDate(),a);
            }
            else{
                List<Encomenda> a = new ArrayList<>();
                a.add(e.getValue().clone());
                time.put(e.getValue().getData().toLocalDate(),a);
            }
        }
        return time;
    }



//mostra a lista de encomendas num dado periodo
    public void showEncomenda(LocalDate data, LocalDate data2, int x) {
        List<Encomenda> res = new ArrayList<>();
        if (x == 1) {
            for (Map.Entry<LocalDate, List<Encomenda>> entry : this.encPorDatas(getClienteIn().getEncomendas()).entrySet()) {
                if (entry.getKey().equals(data) || entry.getKey().equals(data2) || entry.getKey().isAfter(data) || entry.getKey().isBefore(data2)) {
                    Iterator<Encomenda> it = entry.getValue().iterator();
                    while (it.hasNext()) {
                        Encomenda l = it.next();
                        res.add(l.clone());
                        System.out.println(l.toString());
                    }

                }

            }
        }
        if (x == 2)
            for (Map.Entry<LocalDate, List<Encomenda>> entry : this.encPorDatas(getEmpresaIn().getEncomendas()).entrySet()) {
                if (entry.getKey().equals(data) || entry.getKey().equals(data2) || entry.getKey().isAfter(data) || entry.getKey().isBefore(data2)) {
                    Iterator<Encomenda> it = entry.getValue().iterator();
                    while (it.hasNext()) {
                        Encomenda l = it.next();
                        res.add(l.clone());
                        System.out.println(l.toString());
                    }

                }
            }
        else {
            for (Map.Entry<LocalDate, List<Encomenda>> entry : this.encPorDatas(getVoluntarioIn().getEncomendas()).entrySet()) {
                if (entry.getKey().equals(data) || entry.getKey().equals(data2) || entry.getKey().isAfter(data) || entry.getKey().isBefore(data2)) {
                    Iterator<Encomenda> it = entry.getValue().iterator();
                    while (it.hasNext()) {
                        Encomenda l = it.next();
                        res.add(l.clone());
                        System.out.println(l.toString());
                    }

                }
            }

        }
    }

    public List<EmpresaTransportadora> top10Kms(){
        return getEmpresaTransporte().values().stream()
                .sorted(new KmComparator())
                .limit(10).collect(Collectors.toList());
    }

    public List<User> topUsers(){
    Map<String,List<Encomenda>> aux = getUtilizadorEncomendas();
    List<User> users = new ArrayList<>();
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

    /*basicamente retira todos os pedidos existentes para esta loja */

    public List<Encomenda> getPedidos(){
        Map<String,Encomenda> aux = this.getEncomendas();
        List <Encomenda> res = new ArrayList<>();
        for(Map.Entry<String,Encomenda> e: aux.entrySet()){
            if(e.getValue().getLoja().equals(this.getLojaIn().getReferencia()))
                res.add(e.getValue().clone());
        }
        return res;
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
    private Map<String, Transporte> EncomendaVoluntarios(Encomenda a) {
        Map<String, Transporte> aux = new HashMap<>();

        for (Map.Entry<String, Transporte> e : getTransportador().entrySet()) {
            if (e.getValue() instanceof Voluntario) {
                Transporte ep = e.getValue();
                if (ep.isDisponivel() && ep.distanciaValida(a)) aux.put(e.getKey(), ep.clone());
            }
        }

        return aux;
    }

    //filtra os transportadores disponiveis e que conseguem fazer a entrega nas moradas da encomenda
    private Map<String, Transporte> EncomendaTransporte(Encomenda a) {
        Map<String, Transporte> aux = new HashMap<>();

        for (Map.Entry<String, Transporte> e : getTransportador().entrySet()) {

            if (e.getValue().isDisponivel() && e.getValue().distanciaValida(a))
                aux.put(e.getKey(), e.getValue().clone());
        }

        return aux;
    }

    //escolhe o transportador ( voluntario ou empresa) que percorre menos distancia ate à encomenda a
    public Transporte sortEncomendaTransporte(Encomenda a) {

        Map<String, Transporte> map1 = EncomendaTransporte(a);
        String aux = "";
        double distancia = 0;
        for (Map.Entry<String, Transporte> e : map1.entrySet()) {
            if (e.getValue().distancia(this.getEncomenda().clone()) > distancia) {
                distancia = e.getValue().distancia(a);
                aux = e.getKey();
            }
        }
        return map1.get(aux);
    }

    public Transporte sortEncomendaVoluntario(Encomenda a) {

        Map<String, Transporte> map1 = EncomendaVoluntarios(a);
        String aux = "";
        double distancia = 0;
        for (Map.Entry<String, Transporte> e : map1.entrySet()) {
            if (e.getValue().distancia(this.getEncomenda().clone()) > distancia) {
                distancia = e.getValue().distancia(a);
                aux = e.getKey();
            }
        }
        return map1.get(aux);
    }


    public void daClassificacao(int classificacao){
        String classificado = this.getEncomenda().getDistribuidor().getReferencia();
        this.getTransportador().get(classificado).setClassificacao(classificacao);
    }


    public void geraReferenciaEncomenda(Encomenda a){
        StringBuilder sb = new StringBuilder();
        int sizeMap = this.getEncomendas().size();

        while(this.getEncomendas().containsKey((sb.append("e"+ sizeMap)).toString()))
            sizeMap++;
        a.setReferencia((sb.toString()));
    }

    public User geraReferenciaUser(User e){
        StringBuilder sb = new StringBuilder();
        int sizeMap = this.getUsers().size();

        while(this.getUsers().containsKey((sb.append("u"+ sizeMap)).toString()))
            sizeMap++;
        e.setReferencia((sb.toString()));
        return e;
    }

    public Produto geraReferenciaProduto(Produto a){
        StringBuilder sb = new StringBuilder();
        int sizeMap = this.getProdutos().size();

        while(this.getProdutos().containsKey((sb.append("p"+ sizeMap)).toString()))
            sizeMap++;
        a.setReferencia((sb.toString()));
        return a;
    }

    public Loja geraReferenciaLoja(Loja l){
        StringBuilder sb = new StringBuilder();
        int sizeMap = this.getLojas().size();

        while(this.getEncomendas().containsKey((sb.append("l"+ sizeMap)).toString()))
            sizeMap++;
        l.setReferencia((sb.toString()));
        System.out.println(l);
        return l;
    }

    public EmpresaTransportadora geraReferenciaTransportadorEmpresa(EmpresaTransportadora e){
        StringBuilder sb = new StringBuilder();
        int sizeMap = this.getEmpresaTransporte().size();

        while(this.getEncomendas().containsKey((sb.append("t"+ sizeMap)).toString()))
            sizeMap++;
        e.setReferencia((sb.toString()));
        return e;
    }
    public Voluntario geraReferenciaTransportadorVoluntario (Voluntario e){
        StringBuilder sb = new StringBuilder();
        int sizeMap = this.getVoluntariosTransporte().size();

        while(this.getEncomendas().containsKey((sb.append("v"+ sizeMap)).toString()))
            sizeMap++;
        e.setReferencia((sb.toString()));
        return e;
    }



    //basicamente trata da encomenda
    public void addEncomendaEmpresa(){

        Encomenda e = this.getEmpresaIn().encPedidasData().get(0);
        EmpresaTransportadora t = getEmpresaTransporte().get(e.getDistribuidor().getReferencia());
        t.aceitaEncomenda(e); //diz que esta ocupado numa entrega
        e.setEfetuada(true);
        t.addKms(e);
        t.addFatura(e);
        e.setTempo(t.tempoViagem(e));
        adicionaTransportador(t);
        t.adicionaEncomendaTransporte(e);
        adicionaEncomenda(e); //atualiza registo da encomenda global
        e.setCustoTransporte(t.defineCusto(e));
        this.setEmpresaIn(t);
    }

    public void addEncomendaVoluntario(){
        Encomenda e = this.getEmpresaIn().encPedidasData().get(0);
        Voluntario t = getVoluntariosTransporte().get(e.getDistribuidor().getReferencia());
        t.aceitaEncomenda(e); //diz que esta ocupado numa entrega
        e.setEfetuada(true); //diz que a efetuou
        t.addKms(e); //adiciona kms
        e.setTempo(t.tempoViagem(e)); //tempo da viagem
        t.adicionaEncomendaTransporte(e);
        adicionaTransportador(t); //atualiza os seus dados no map de transportadores
        this.setVoluntarioIn(t);
    }


    /**
     * Guardar encomendas nos registos individuais
     */

    public void addRegistoC ()
    {

        String referencia=this.encomenda.getComprador().getReferencia();
        this.users.get(referencia).adicionaEncomendaUser(this.encomenda);
    }
    public void addRegistoT ()
    {
        String referencia=this.encomenda.getDistribuidor().getReferencia();
        this.transporte.get(referencia).adicionaEncomendaTransporte(this.encomenda);
    }
    public void addRegistoL ()
    {
        String referencia=this.encomenda.getLoja().getReferencia();
        this.lojas.get(referencia).adicionaEncomendaLoja(this.encomenda);
    }

    public User ShowDadosU()
    {
        return this.userIn.clone();
    }
    public EmpresaTransportadora ShowDadosE()
    {
        return this.empresaIn.clone();
    }

    public Voluntario ShowDadosV()
    {
        return this.voluntarioIn.clone();
    }

    public Loja ShowDadosL()
    {
        return this.lojaIn.clone();
    }

    /**
Carregamento de dados
 **/
    public static TrazAqui lerDados() throws IOException, ClassNotFoundException{
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
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


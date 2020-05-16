package Model;


import java.awt.geom.Point2D;
import java.time.LocalDateTime;
import java.util.List;


public class User extends Ator {

    //Variáveis de instância
    private String ref;
    private String email;
    private String nome;
    private String password;
    private Point2D.Double morada;


    //Construtores

    public User() {
        this.ref = "";
        this.email = "";
        this.nome = "";
        this.password = "";
        this.morada = null;

    }

    public User(String ref, String email, String nome, String password, Point2D.Double morada, long nif) {
        this.ref = ref;
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;

    }

    public User(User a) {
        this.ref= a.getRef();
        this.email = a.getEmail();
        this.nome = a.getNome();
        this.password = a.getPassword();
        this.morada = a.getMorada();

    }

    // Getters


    public String getRef() {
        return ref;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNome() {
        return this.nome;
    }

    public String getPassword() {
        return this.password;
    }

    public Point2D.Double getMorada() {
        return this.morada;
    }



    // Setters

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMorada(Point2D.Double morada) {
        this.morada = morada;
    }



    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ((o == null) || (o.getClass() != this.getClass()))
            return false;
        else {
            User a = (User) o;
            return this.email.equals(a.getEmail())
                    && this.nome.equals(a.getNome())
                    && this.password.equals(a.getPassword())
                    && this.morada == a.getMorada();
        }
    }


    public User clone() {
        return new User(this);
    }

    /* Métodos */

    public void daClassificacao(Servico s,int classificacao,String referencia, DataBase db){
    String classificado = s.getEncomenda(referencia).getDistribuidor();
    db.getTransportador().get(classificado).setClassificacao(classificacao);
    }

    public void fazPedido(Loja lj,List<Produto> p, Servico ser, float peso){
        Encomenda a = new Encomenda();
        a.setMoradaUtilizador(this.getMorada());
        a.setMoradaLoja(lj.getMoradaLoja());
        a.setLoja(lj.getReferencia());
        a.setComprador(this.getRef());
        a.setProdutos(p);
        a.setData(LocalDateTime.now()); //data e hora do pedido
        a.setPeso(peso);
        geraReferencia(a,ser);
        ser.adicionaPedido(a);
    }


    public void geraReferencia(Encomenda a, Servico s){
        StringBuilder sb = new StringBuilder();
        int sizeMap = s.getDataBase().getEncomendas().size();

        while(s.getDataBase().getEncomendas().containsKey((sb.append("e"+ sizeMap)).toString()))
            sizeMap++;
            a.setReferencia((sb.append("e" + sizeMap).toString()));
    }




/*

    public boolean aceitaEntrega(Servico s, String referencia){

    }

*/



}

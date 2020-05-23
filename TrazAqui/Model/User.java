package Model;


import java.awt.geom.Point2D;
import java.time.LocalDateTime;
import java.util.List;


public class User extends Ator {


    /**
     * Constructores
     */

    public User() {
        super();

    }

    public User(String ref,String email, String nome, String password, Point2D.Double morada, long nif) {
        super(email, ref, nome, password,morada ,nif );

    }

    public User(User a) {
        super(a.getEmail(),a.getReferencia(),a.getNome(),a.getPassword(),a.getMorada(),a.getNif());

    }





    public User clone() {
        return new User(this);
    }

    /**
     * Metodos
     */



    public void setEncomenda(Loja lj,List<Produto> p, User u, Loja l, Transporte t, float peso){
        Encomenda a = new Encomenda();
        a.setMoradaUtilizador(this.getMorada());
        a.setMoradaLoja(lj.getMorada());
        a.setLoja(l);
        a.setComprador(u);
        a.setProdutos(p);
        a.setData(LocalDateTime.now()); //data e hora do pedido
        a.setPeso(peso);
       // geraReferencia(a,t);

    }


    public void geraReferencia(Encomenda a, TrazAqui t){
        StringBuilder sb = new StringBuilder();
        int sizeMap = t.getEncomendas().size();

        while(t.getEncomendas().containsKey((sb.append("e"+ sizeMap)).toString()))
            sizeMap++;
            a.setReferencia((sb.append("e" + sizeMap).toString()));
    }




/*

    public boolean aceitaEntrega(Servico s, String referencia){

    }

*/



}

package Model;

import java.io.Serializable;

public abstract class Ator implements Serializable
{
    //variaveis de instância
    private String email;
    private String nome;
    private String password;
    //private String morada;
    private String datanascimento;
    private int nif;

    /**
     * Constructores
     */
    public Ator()
    {
        this.email="";
        this.nome="";
        this.password="";
      //  this.morada="";
        this.datanascimento="";
        this.nif=0;
    }

    public Ator(String email,String nome,String password,String morada,String datanascimento,
                  int nif)
    {
        this.email=email;
        this.nome=nome;
        this.password=password;
      //  this.morada=morada;
        this.datanascimento=datanascimento;
        this.nif=nif;
    }

    public Ator(Ator a)
    {
        this.email=a.getEmail();
        this.nome=a.getNome();
        this.password=a.getPassword();
        // this.morada=a.getMorada();
        this.datanascimento=a.getDataN();
        this.nif=a.getNif();
    }
    /**
     * Getters
     */
    public String getEmail()
    {
        return this.email;
    }
    public String getNome()
    {
        return this.nome;
    }
    public String getPassword()
    {
        return this.password;
    }
    /*
    public String getMorada()
    {
        return this.morada;
    }
    */

    public String getDataN()
    {
        return this.datanascimento;
    }
    public int getNif ()
    {
        return this.nif;
    }
    /**
     * Setters
     */
    public void setEmail(String email)
    {
        this.email=email;
    }
    public void setNome(String nome)
    {
        this.nome=nome;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }
    /*
    public void setMorada(String morada)
    {
        this.morada=morada;
    }
    */
    public void setDataN(String datanascimento)
    {
        this.datanascimento=datanascimento;
    }
    public void setNif(int nif)
    {
        this.nif=nif;
    }

    public boolean equals(Object o)
    {
        if(o==this) return true;
        if(o==null || o.getClass() != this.getClass()) return false;
        Ator a = (Ator) o;
        return a.getEmail().equals(this.email) && a.getNif()==this.nif;
    }

    public abstract Ator clone();

    public String toString ()
    {
        StringBuilder sb = new StringBuilder ();
        sb.append("Email: ").append(this.email).append("\nNome: ").append(this.nome)
                //.append("\nMorada: ").append(this.morada)
                .append("\nData Nascimento: ").append(this.datanascimento)
                .append("\nNIF: ").append(this.nif);
        return sb.toString();
    }
}
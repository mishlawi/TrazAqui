package Model;

import java.io.Serializable;
import java.util.Objects;

public class Produto implements Serializable {

    private String referencia;
    private String nome;
    private float quantidade;
    private double preco;
    private boolean medicinal;

    public Produto(){
        this.referencia = "";
        this.nome= new String();
        this.quantidade= 0;
        this.preco = 0;
        this.medicinal = false;
    }


    public Produto(String referencia, String nome, float quantidade, double preco ,boolean medicinal) {
        this.referencia = referencia;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.medicinal = medicinal;
    }

    public Produto(Produto a){
        this.referencia = a.getReferencia();
        this.nome = a.getNome();
        this.quantidade = a.getQuantidade();
        this.preco = a.getPreco();
        this.medicinal = a.isMedicinal();
    }

    //GETTER AND SETTER


    public String getReferencia() {
        return referencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getQuantidade() {
        return quantidade;
    }


    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isMedicinal() {
        return medicinal;
    }

    public void setMedicinal(boolean medicinal) {
        this.medicinal = medicinal;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return quantidade == produto.quantidade &&
                medicinal == produto.medicinal &&
                nome.equals(produto.nome);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "referencia='" + referencia + '\'' +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                ", medicinal=" + medicinal +
                '}';
    }

    public Produto clone()
    {
        return new Produto(this);
    }

}



package Model;

import java.util.Objects;

public class Produto {

    private String nome;
    private int quantidade;
    private double preco;
    private boolean medicinal;

    public Produto(){
        this.nome= new String();
        this.quantidade= 0;
        this.preco = 0;
        this.medicinal = false;
    }


    public Produto(String nome, int quantidade, double preco ,boolean medicinal) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.medicinal = medicinal;
    }

    public Produto(Produto a){
        this.nome = a.getNome();
        this.quantidade = a.getQuantidade();
        this.preco = a.getPreco();
        this.medicinal = a.isMedicinal();
    }

    //GETTER AND SETTER
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }


    public void setQuantidade(int quantidade) {
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



    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return quantidade == produto.quantidade &&
                medicinal == produto.medicinal &&
                nome.equals(produto.nome);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("Nome produto: ");
        sb.append(this.getNome() + "\n");
        sb.append("Quantidade: ");
        sb.append(this.getQuantidade() + "\n");
        sb.append("Uso Medicinal? ");
        sb.append(this.isMedicinal() + "\n");

        return sb.toString();

    }

    public Produto clone()
    {
        return new Produto(this);
    }

}



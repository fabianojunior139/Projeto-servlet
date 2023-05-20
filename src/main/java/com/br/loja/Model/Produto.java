package com.br.loja.Model;

public class Produto {
    private Long id;
    private Long codigo;
    private String nome;
    private String categoria;
    private Double valor;
    private int quantidade;

    public Produto() {
    }

    //Contrutor sem id
    public Produto(Long codigo, String nome, String categoria, Double valor, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    //Contrutor que cadastra o tudo do produto
    public Produto(Long id, Long codigo, String nome, String categoria, Double valor, int quantidade) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

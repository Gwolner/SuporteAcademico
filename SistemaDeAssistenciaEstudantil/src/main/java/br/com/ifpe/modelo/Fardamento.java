package br.com.ifpe.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Fardamento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_fardamento")
    private long idFardamento;
    
    @Column(name="tamanho_pedido", nullable = false, length = 100)
    private String tamanhoPedido;
    
    @Column(name="tamanho_entregue", nullable = false, length = 100)
    private String tamanhoEntregue;
    
    @Column(name="quantidade_entregue", nullable = false, length = 2)
    private int quantidadeEntregue;
    
    @Column(name="id_aluno", nullable = false)
    private long idAluno; //Confirmar se após a cardinalidade esse campo é necessário
    
    @Column(name="id_situacao", nullable = false) 
    private long idSituacao; //Confirmar se após a cardinalidade esse campo é necessário
    
    @Column(name="id_tamanho", nullable = false)
    private long idTamanho; //Confirmar se após a cardinalidade esse campo é necessário

    @ManyToOne 
    protected Aluno aluno;
    
    public long getIdFardamento() {
        return idFardamento;
    }

    public void setIdFardamento(long idFardamento) {
        this.idFardamento = idFardamento;
    }

    public String getTamanhoPedido() {
        return tamanhoPedido;
    }

    public void setTamanhoPedido(String tamanhoPedido) {
        this.tamanhoPedido = tamanhoPedido;
    }

    public String getTamanhoEntregue() {
        return tamanhoEntregue;
    }

    public void setTamanhoEntregue(String tamanhoEntregue) {
        this.tamanhoEntregue = tamanhoEntregue;
    }

    public int getQuantidadeEntregue() {
        return quantidadeEntregue;
    }

    public void setQuantidadeEntregue(int quantidadeEntregue) {
        this.quantidadeEntregue = quantidadeEntregue;
    }

    public long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(long idAluno) {
        this.idAluno = idAluno;
    }

    public long getIdSituacao() {
        return idSituacao;
    }

    public void setIdSituacao(long idSituacao) {
        this.idSituacao = idSituacao;
    }

    public long getIdTamanho() {
        return idTamanho;
    }

    public void setIdTamanho(long idTamanho) {
        this.idTamanho = idTamanho;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }


}

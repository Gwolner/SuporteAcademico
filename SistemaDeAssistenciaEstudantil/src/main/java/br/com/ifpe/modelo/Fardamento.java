package br.com.ifpe.modelo;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_fardamento")
@Access(AccessType.FIELD)
public class Fardamento implements Serializable {   
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_fardamento")
    private Long idFardamento;
    
    @Column(name="tamanho_pedido", nullable = false, length = 100)
    private String tamanhoPedido;
    
    @Column(name="tamanho_entregue", nullable = false, length = 100)
    private String tamanhoEntregue;
    
    @Column(name="quantidade_entregue", nullable = false, length = 2)
    private int quantidadeEntregue;
    
    
    //Cardinalidade Aluno 1 : N Fardamento 
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_aluno", referencedColumnName = "id_aluno")
    protected Aluno aluno;
    
    //Cardinalidade Situacao 1 : N Fardamento 
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_situacao", referencedColumnName = "id_situacao")
    protected Situacao situacao;
    
    //Cardinalidade Tamanho 1 : N Fardamento 
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tamanho", referencedColumnName = "id_tamanho") 
    protected Tamanho tamanho;

    
    public Long getIdFardamento() {
        return idFardamento;
    }

    public void setIdFardamento(Long idFardamento) {
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

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFardamento != null ? idFardamento.hashCode():0);
        return hash;
    }
    
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Fardamento)) {
            return false; //Se não for a instância desejada, retorna false;
        }else{ 
            Fardamento other = (Fardamento) o;
            return !((this.idFardamento == null && other.idFardamento != null)
                    ||(this.idFardamento != null && 
                    !this.idFardamento.equals(other.idFardamento))
            );
        /* 
         * Se a sentença for verdadeira, retorna false. 
         * Se for falsa, retorna true.        
         */
        }
    }
}

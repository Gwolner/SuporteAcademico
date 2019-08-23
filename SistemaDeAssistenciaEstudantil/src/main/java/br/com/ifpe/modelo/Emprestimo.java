package br.com.ifpe.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class Emprestimo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_emprestimo")
    private long idEmprestimo;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="data_entrega", nullable = false)
    private Date dataEntrega;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="data_devolucao", nullable = false)
    private Date dataDevolucao;
    
    @Column(nullable = false, length = 20)
    private String status;
    
    @Column(name="id_livro", nullable = false)
    private long idLivro; //Confirmar se após a cardinalidade esse campo é necessário
    
    @Column(name="id_aluno", nullable = false)
    private long idAluno; //Confirmar se após a cardinalidade esse campo é necessário

    
    public long getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(long idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(long idLivro) {
        this.idLivro = idLivro;
    }

    public long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(long idAluno) {
        this.idAluno = idAluno;
    }
    
}

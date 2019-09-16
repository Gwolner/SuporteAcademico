package br.com.ifpe.modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;

@Entity
@Table(name="tb_emprestimo")
@Access(AccessType.FIELD)
public class Emprestimo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_emprestimo")
    private long idEmprestimo;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="data_entrega", nullable = true)
    private Date dataEntrega;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="data_devolucao", nullable = true)
    private Date dataDevolucao;
    
    @Column(name="status", nullable = false, length = 20)
    private String status;
    
    
    //Cardinalidade Usuario 1 : N Emprestimo
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario") 
    protected Usuario usuario;
    
    //Cardinalidade Livro 1 : N Emprestimo
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_livro", referencedColumnName = "id_livro") 
    protected Livro livro;

    
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

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    
    
}

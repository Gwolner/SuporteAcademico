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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="TB_EMPRESTIMO")
@Access(AccessType.FIELD)
public class Emprestimo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_emprestimo")
    private Long idEmprestimo;
    
    @NotBlank
    @Column(name="data_entrega")
    @Temporal(javax.persistence.TemporalType.DATE)
//    @Column(name="data_entrega", nullable = true)
    private Date dataEntrega;
    
    @NotBlank
    @Column(name="data_devolucao")
    @Temporal(javax.persistence.TemporalType.DATE)
//    @Column(name="data_devolucao", nullable = true)
    private Date dataDevolucao;
    
    @NotBlank
    @Size(max=20)
    @Column(name="status")
//    @Column(name="status", nullable = false, length = 20)
    private String status;
    
    
    //Cardinalidade Usuario 1 : N Emprestimo
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario") 
    protected Usuario usuario;
    
    //Cardinalidade Livro 1 : N Emprestimo
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)    
    @JoinColumn(name = "id_livro", referencedColumnName = "id_livro") 
    protected Livro livro;

    
    public Long getIdEmprestimo() {
        return this.idEmprestimo;
    }

    public void setIdEmprestimo(Long idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public Date getDataEntrega() {
        return this.dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getDataDevolucao() {
        return this.dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Livro getLivro() {
        return this.livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmprestimo != null ? idEmprestimo.hashCode():0);
        return hash;
    }
    
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Emprestimo)) {
            return false; //Se não for a instância desejada, retorna false;
        }else{ 
            Emprestimo other = (Emprestimo) o;
            return !((this.idEmprestimo == null && other.idEmprestimo != null)
                    ||(this.idEmprestimo != null && 
                    !this.idEmprestimo.equals(other.idEmprestimo))
            );
        /* 
         * Se a sentença for verdadeira, retorna false. 
         * Se for falsa, retorna true.        
         */
        }
    }
    
}

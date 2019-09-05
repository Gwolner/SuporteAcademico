package br.com.ifpe.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tb_aluno")
@Access(AccessType.FIELD)
public class Aluno implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_aluno")
    private long idAluno;
    
    @Column(name="nome_aluno", nullable = false, length = 100)
    private String nomeAluno;
    
    @Column(name="curso", nullable = false, length = 150)
    private String curso;
    
    @Column(name="turno", nullable = false, length = 10)
    private String turno;
    
    @Column(name="matricula", nullable = false, length = 15)
    private String matricula;
    
    @Column(name="cpf", nullable = false, length = 15)
    private int cpf;
    
    @Column(name="celular", nullable = false, length = 11)
    private int celular;
    
    @Column(name="email", nullable = false, length = 70)
    private String email;
    
    @Transient //Campo que não será persistido. Apenas exibido na interface
    private String referencia; 

    
    //Cardinalidade Aluno 1 : N Fardamento
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "aluno",
            fetch = FetchType.LAZY)
    protected List<Fardamento> fardamento;
    
    //Cardinalidade Aluno 1 : N Emprestimo
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "aluno",
            fetch = FetchType.LAZY)
    protected List<Emprestimo> emprestimo;
    
    
    public long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(long idAluno) {
        this.idAluno = idAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
   
    public List<Fardamento> getFardamento() {
        return fardamento;
    }

    public void setFardamento(List<Fardamento> fardamento) {
        this.fardamento = fardamento;
    }

    public List<Emprestimo> getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(List<Emprestimo> emprestimo) {
        this.emprestimo = emprestimo;
    }
   
}

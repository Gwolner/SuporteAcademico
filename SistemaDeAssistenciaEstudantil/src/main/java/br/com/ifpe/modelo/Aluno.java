package br.com.ifpe.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tb_aluno")
@DiscriminatorValue(value="A") //Valor usado no campo descriminador
@PrimaryKeyJoinColumn( //Definindo a PK de Aluno
        name="id_aluno", //Nome da coluna PK de Aluno
        referencedColumnName="id_usuario" //Referencia a PK de Usuario
)
public class Aluno extends Usuario implements Serializable{
    
    @Column(name="curso", nullable = false, length = 150)
    private String curso;
    
    @Column(name="turno", nullable = false, length = 10)
    private String turno;
    
    @Column(name="matricula", nullable = false, length = 15)
    private String matricula;
    
    @Column(name="responsavel", nullable = false, length = 100)
    private String responsavel;
    
    @Column(name="contato_responsavel", nullable = false, length = 11)
    private long contatoResponsavel;
    
    @Column(name="email", nullable = false, length = 70)
    private String email;
    
    @Transient //Campo que não será persistido, apenas exibido na interface
    private String referencia; 

    
    //Cardinalidade Aluno 1 : N Fardamento
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "aluno",
            fetch = FetchType.LAZY)
    protected List<Fardamento> fardamentos;
    
    //Cardinalidade Aluno N : N Bolsa
    @ManyToMany
    @JoinTable(
        name="tb_aluno_x_tb_bolsa", //Nome da tabela associativa
        joinColumns= //Colaboração desta Entidade
            @JoinColumn(
                name="id_aluno", //Nome da coluna na tabelaa associativa
                referencedColumnName="id_aluno" //Relação com esta coluna 
            ), 
        inverseJoinColumns= //Colaboração da Entidade oposta
            @JoinColumn(
                name="id_bolsa", //Nome da coluna na tabelaa associativa
                referencedColumnName="id_bolsa"
            )//Relação com coluna da Entidade oposta
    )
    protected List<Bolsa> bolsas;

    
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

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public long getContatoResponsavel() {
        return contatoResponsavel;
    }

    public void setContatoResponsavel(long contatoResponsavel) {
        this.contatoResponsavel = contatoResponsavel;
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

    public List<Fardamento> getFardamentos() {
        return fardamentos;
    }

    public void setFardamentos(List<Fardamento> fardamentos) {
        this.fardamentos = fardamentos;
    }

    public List<Bolsa> getBolsas() {
        return bolsas;
    }

    public void setBolsas(List<Bolsa> bolsas) {
        this.bolsas = bolsas;
    }
    
    
  }

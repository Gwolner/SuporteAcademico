package br.com.ifpe.modelo;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="TB_ALUNO")
@DiscriminatorValue(value="A") //Valor usado no campo descriminador
@PrimaryKeyJoinColumn( //Definindo a PK de Aluno
        name="id_aluno", //Nome da coluna PK de Aluno
        referencedColumnName="id_usuario" //Referencia a PK de Usuario
)
public class Aluno extends Usuario implements Serializable{
    
    public Aluno() {
        this.fardamentos = new ArrayList<>();
        this.bolsas = new ArrayList<>();
    }
    
    @NotBlank
    @Size(max=150)
    @Column(name="curso")
//    @Column(name="curso", nullable = false, length = 150)
    private String curso;
    
    @NotBlank
    @Size(max=10)
    @Column(name="turno")
//    @Column(name="turno", nullable = false, length = 10)
    private String turno;
    
    @NotBlank
    @Size(max=15)
    @Column(name="matricula")
//    @Column(name="matricula", nullable = false, length = 15)
    private String matricula;
    
    @NotBlank
    @Size(max=100)
    @Column(name="responsavel")
//    @Column(name="responsavel", nullable = false, length = 100)
    private String responsavel;
    
    @NotBlank
    @Size(max=11)
    @Column(name="contato_responsavel")
//    @Column(name="contato_responsavel", nullable = false, length = 11)
    private Long contatoResponsavel;
    
    @NotBlank
    @Size(max=70)
    @Column(name="email")
//    @Column(name="email", nullable = false, length = 70)
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
        name="TB_ALUNO_x_TB_BOLSA", //Nome da tabela associativa
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
        return this.curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTurno() {
        return this.turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getResponsavel() {
        return this.responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Long getContatoResponsavel() {
        return this.contatoResponsavel;
    }

    public void setContatoResponsavel(Long contatoResponsavel) {
        this.contatoResponsavel = contatoResponsavel;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReferencia() {
        //Tratar campo transiente
        return this.referencia;
    }

    public void setReferencia(String referencia) {
        //Tratar campo transiente
        this.referencia = referencia;
    }

    
    
    
    public List<Fardamento> getFardamentos() {
        return this.fardamentos;
    }

    public boolean addFardamento(Fardamento f) {
        if (!fardamentos.contains(f)) {
            f.setAluno(this);
            return fardamentos.add(f);
        } else {
            return false;
        }   
    }
    
    public void addAllFardamentos(List<Fardamento> fardamentos) {
        for (Fardamento fardamento : fardamentos) {
            this.addFardamento(fardamento);
        }
    }
    
    
    
    
    
    public List<Bolsa> getBolsas() {
        return this.bolsas;
    }

     public boolean addBolsa(Bolsa b) {
        if (!bolsas.contains(b)) {
            b.addAluno(this);
            return bolsas.add(b);
        } else {
            return false;
        }   
    }
    
    public void addAllBolsas(List<Bolsa> bolsas) {
        for (Bolsa bolsa : bolsas) {
            this.addBolsa(bolsa);
        }
    }
    
    public boolean removeBolsa(Object o) {
        if(!(o instanceof Bolsa)){
            return false;
        }else{
            return bolsas.remove(o);
        }    
    }
    
    public boolean removeFardamento(Object o) {
        if(!(o instanceof Fardamento)){
            return false;
        }else{
            return fardamentos.remove(o);
        }    
    }
   
    
    
    @Override
    public int hashCode(){
        int hash = 0;
        hash += (super.getIdUsuario() != null ? super.getIdUsuario().hashCode():0);
        return hash;
    }
    
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Aluno)) {
            return false; //Se não for a instância desejada, retorna false;
        }else{ 
            Aluno other = (Aluno) o;
            return !((super.getIdUsuario() == null && other.getIdUsuario() != null)
                    ||(super.getIdUsuario() != null && 
                    !super.getIdUsuario().equals(other.getIdUsuario()))
            );
        /* 
         * Se a sentença for verdadeira, retorna false. 
         * Se for falsa, retorna true.        
         */
        }
    }
    
    
    
    
  }

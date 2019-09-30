package br.com.ifpe.modelo;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="TB_LIVRO")
@Access(AccessType.FIELD)
public class Livro implements Serializable {
    
    public Livro() {
        this.emprestimos = new ArrayList<>();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_livro")
    private Long idLivro;
    
    @NotBlank
    @Size(max=45)
    @Column(name="materia")
//    @Column(name="materia", nullable = false, length = 45)
    private String materia;
    
    @NotBlank
    @Size(max=150)
    @Column(name="titulo")
//    @Column (name="titulo", nullable = false, length = 150)
    private String titulo;
    
    @NotBlank
    @Size(max=100)
    @Column(name="autor")
//    @Column(name="autor", nullable = false, length = 100)
    private String autor;
    
    @NotBlank
    @Size(max=100)
    @Column(name="isbn")
//    @Column(name="isbn", nullable = false, length = 100)
    private Long isbn;
    
    @NotBlank
    @Size(max=5)
    @Column(name="quantidade")
//    @Column(name="quantidade",nullable = false, length = 5)
    private int quantidade;
    
    
    //Cardinalidade Livro 1 : N Emprestimo
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "livro", 
            fetch = FetchType.LAZY)
    protected List<Emprestimo> emprestimos;

    //Cardinalidade Volume 1 : N Livro    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_volume", referencedColumnName = "id_volume")
    protected Volume volume;

    
    public Long getIdLivro() {
        return this.idLivro;
    }

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }

    public String getMateria() {
        return this.materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Long getIsbn() {
        return this.isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Volume getVolume() {
        return this.volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public List<Emprestimo> getEmprestimos() {
        return this.emprestimos;
    }

    public boolean addEmprestimo(Emprestimo e) {
        if (!emprestimos.contains(e)) {
            e.setLivro(this);
            return emprestimos.add(e);
        } else {
            return false;
        }      
    }
    
    public void addAllEmprestimos(List<Emprestimo> emprestimos) {
        for (Emprestimo emprestimo : emprestimos) {
            this.addEmprestimo(emprestimo);
        }
    }
    
    public boolean removeEmprestimo(Object o) {
        if(!(o instanceof Emprestimo)){
            return false;
        }else{
            return emprestimos.remove(o);
        }    
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLivro != null ? idLivro.hashCode():0);
        return hash;
    }
    
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Livro)) {
            return false; //Se não for a instância desejada, retorna false;
        }else{ 
            Livro other = (Livro) o;
            return !((this.idLivro == null && other.idLivro != null)
                    ||(this.idLivro != null && 
                    !this.idLivro.equals(other.idLivro))
            );
        /* 
         * Se a sentença for verdadeira, retorna false. 
         * Se for falsa, retorna true.        
         */
        }
    }
}
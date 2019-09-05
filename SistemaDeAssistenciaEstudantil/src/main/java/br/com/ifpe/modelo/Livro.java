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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_livro")
@Access(AccessType.FIELD)
public class Livro implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_livro")
    private long idLivro;
    
    @Column(name="materia", nullable = false, length = 45)
    private String materia;
    
    @Column (name="titulo", nullable = false, length = 150)
    private String titulo;
    
    @Column(name="autor", nullable = false, length = 100)
    private String autor;
    
    @Column(name="isbn", nullable = false, length = 100)
    private long isbn;
    
    @Column(name="quantidade",nullable = false, length = 5)
    private int quantidade;
    
    
    //Cardinalidade Livro 1 : N Emprestimo
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "livro", 
            fetch = FetchType.LAZY)
    protected List<Emprestimo> emprestimo;

    //Cardinalidade Volume 1 : N Livro
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_volume", referencedColumnName = "id_volume")
    protected Volume volume;

    
    public long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(long idLivro) {
        this.idLivro = idLivro;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public List<Emprestimo> getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(List<Emprestimo> emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }
}
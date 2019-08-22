package br.com.ifpe.modelo;

//@Entity
public class Aluno {
    
    //@id
    //@GeneratedValue(strategy)
    private long idAluno;
    private String nomeAluno;
    private String curso;
    private String turno;
    private String matricula;
    private long cpf;
    private long celular;
    private String email;
    //@Transient
    private String referencia;

    
}

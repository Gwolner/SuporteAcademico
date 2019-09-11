package br.com.ifpe.modelo;

import br.com.ifpe.modelo.Emprestimo;
import br.com.ifpe.modelo.Fardamento;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-11T16:57:43")
@StaticMetamodel(Aluno.class)
public class Aluno_ { 

    public static volatile SingularAttribute<Aluno, String> nomeAluno;
    public static volatile ListAttribute<Aluno, Emprestimo> emprestimo;
    public static volatile SingularAttribute<Aluno, String> curso;
    public static volatile ListAttribute<Aluno, Fardamento> fardamento;
    public static volatile SingularAttribute<Aluno, String> matricula;
    public static volatile SingularAttribute<Aluno, Long> cpf;
    public static volatile SingularAttribute<Aluno, Long> idAluno;
    public static volatile SingularAttribute<Aluno, Integer> celular;
    public static volatile SingularAttribute<Aluno, String> turno;
    public static volatile SingularAttribute<Aluno, String> email;

}
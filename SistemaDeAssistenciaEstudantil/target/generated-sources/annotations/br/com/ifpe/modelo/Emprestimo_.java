package br.com.ifpe.modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-23T00:49:33")
@StaticMetamodel(Emprestimo.class)
public class Emprestimo_ { 

    public static volatile SingularAttribute<Emprestimo, Long> idEmprestimo;
    public static volatile SingularAttribute<Emprestimo, Date> dataEntrega;
    public static volatile SingularAttribute<Emprestimo, Long> idLivro;
    public static volatile SingularAttribute<Emprestimo, Long> idAluno;
    public static volatile SingularAttribute<Emprestimo, Date> dataDevolucao;
    public static volatile SingularAttribute<Emprestimo, String> status;

}
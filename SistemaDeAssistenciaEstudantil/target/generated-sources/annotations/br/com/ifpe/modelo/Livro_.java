package br.com.ifpe.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-25T18:36:05")
@StaticMetamodel(Livro.class)
public class Livro_ { 

    public static volatile SingularAttribute<Livro, Long> isbn;
    public static volatile SingularAttribute<Livro, Long> idLivro;
    public static volatile SingularAttribute<Livro, String> titulo;
    public static volatile SingularAttribute<Livro, String> materia;
    public static volatile SingularAttribute<Livro, Integer> quantidade;
    public static volatile SingularAttribute<Livro, String> autor;
    public static volatile SingularAttribute<Livro, Long> idVolume;

}
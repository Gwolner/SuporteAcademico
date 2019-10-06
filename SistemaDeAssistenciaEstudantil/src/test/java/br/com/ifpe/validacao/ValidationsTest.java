package br.com.ifpe.validacao;

import br.com.ifpe.modelo.Livro;
import br.com.ifpe.teste.GenericTest;
import static br.com.ifpe.validacao.GenericTest.logger;
import java.util.Set;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidationsTest extends GenericTest {

    //Dado um Id para Livro, tenta substituir o ISBN dele por um outro
    //que é propositalmente inválido.
    @Test
    public void validarIsbn() {
        logger.info("Executando validarIsbn()");
        try {
            Long id = 1L;
            Long param = null; //ISBN INVÁLIDO!
            Query update = em.createQuery("UPDATE Livro AS l SET l.isbn= ?1 WHERE l.idLivro = ?2");
            update.setParameter(1, param);
            update.setParameter(2, id);
            update.executeUpdate();
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> exceptions = e.getConstraintViolations();
            for (ConstraintViolation exc : exceptions) {
                assertThat(exc.getMessage(),
                        CoreMatchers.anyOf(                                
                                startsWith("ISBN Inválido.")
                        )
                );
            }

        }
    }

    //Dado um Id para Aluno, tenta substituir a matrícula dele por uma outra
    //que é propositalmente inválida.
    @Test
    public void validarMatricula() {
        logger.info("Executando validarMatricula()");
        try {
            Long id = 1L;
            String param = "12345"; //Matrícula INVÁLIDA!
            Query update = em.createQuery("UPDATE Aluno AS a SET a.matricula= ?1 WHERE a.idUsuario = ?2");
            update.setParameter(1, param);
            update.setParameter(2, id);
            update.executeUpdate();
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> exceptions = e.getConstraintViolations();
            for (ConstraintViolation exc : exceptions) {
                assertThat(exc.getMessage(),
                        CoreMatchers.anyOf(                                
                                startsWith("Matricula inválida.")
                        )
                );
            }

        }
    }

    //Dado o id de uma bolsa, deletá-la.
    //Em seguida, executa-se um select buscando essa bolsa, que agora
    //não existe.
    //Espera-se que seja lançada uma exceção do tipo NoResultException.
    @Test(expected = NoResultException.class)
    public void validarException() {
        logger.info("Executando validarException()");
        Long id = (long)1;
        Query delete = em.createQuery("DELETE FROM Bolsa AS b WHERE b.idBolsa = ?1");
        delete.setParameter(1, id);
        delete.executeUpdate();
        String jpql = "SELECT b FROM Bolsa b WHERE b.idBolsa = ?1";
        TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
        query.setParameter(1, id);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.getSingleResult();
    }
    
    
    //Dado um Id para Tamanho, tenta substituir seu valor por uma outro
    //que é propositalmente inválido.
    //Esse teste usa o Pattern inline.
    @Test
    public void validarDesTamanho() {
        logger.info("Executando validarDesTamanho()");
        try {
            Long id = 1L;
            String param = "PPP"; //Descrição INVÁLIDA!
            Query update = em.createQuery("UPDATE Tamanho AS t SET t.descricaoTamanho= ?1 WHERE t.idTamanho = ?2");
            update.setParameter(1, param);
            update.setParameter(2, id);
            update.executeUpdate();
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> exceptions = e.getConstraintViolations();
            for (ConstraintViolation exc : exceptions) {
                assertThat(exc.getMessage(),
                        CoreMatchers.anyOf(                                
                                startsWith("Descrição de tamanho fora do formato da Regex.")
                        )
                );
            }

        }
    }


}

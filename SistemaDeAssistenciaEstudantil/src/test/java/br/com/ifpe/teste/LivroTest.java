package br.com.ifpe.teste;

import br.com.ifpe.modelo.Livro;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author nicolas
 */
public class LivroTest {

    private static EntityManagerFactory emf;
    private EntityManager em;

    public LivroTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("tarefas");
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();
    }

    @After
    public void tearDown() {
        em.close();
    }

    @Test
    public void persistirLivro() {
        Livro livro = new Livro();
        livro.setTitulo("Java: Como Programar");
        livro.setAutor("Harvey Deitel");
        em.persist(livro);
        em.flush();
        assertNotNull(livro.getIdLivro());
    }
    
    @Test
    public void atualizarLivro() {
        String novoAutor = "Paul Deitel and Harvey Deitel";        
        Long id = 1L;
        Livro livro = em.find(Livro.class, id);
        livro.setAutor(novoAutor);
        em.flush();
        String jpql = "SELECT c FROM Livro c WHERE c.idAutor = ?1";
        TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        livro = query.getSingleResult();
        assertEquals(novoAutor, livro.getAutor());      
    }
    
    @Test
    public void atualizarLivroMerge() {
        String novoAutor = "Paul and Harvey Deitel";  
        Long id = 1L;        
        Livro livro = em.find(Livro.class, id);     
        livro.setAutor(novoAutor);
        em.clear();
        em.merge(livro);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        livro = em.find(Livro.class, id, properties);
        assertEquals(novoAutor, livro.getAutor());
    }
    
    @Test
    public void removerLivro() {
        Livro livro = em.find(Livro.class, 9L);
        em.remove(livro);
        assertNull(livro);
    }
}

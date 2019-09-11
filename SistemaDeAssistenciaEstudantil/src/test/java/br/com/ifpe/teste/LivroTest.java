package br.com.ifpe.teste;

import br.com.ifpe.modelo.Livro;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
    private static EntityTransaction et;

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
        et = em.getTransaction();
        et.begin();
        
        inserirLivroTesteRemove();
    }

    @After
    public void tearDown() {
        try {
            et.commit();
        } catch (Exception ex) {
            System.out.println("ERRO!");
        }
        em.close();
    }

    public void inserirLivroTesteRemove() {
        String materia = "Arquitetura de computadores";
        String titulo = "Organização e Arquitetura de Computadores";
        String autor = "Andrew Tanenbaum";
        long isbn = 123123000;
        int quantidade = 9;

        Long id = 9L;

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setMateria(materia);
        livro.setIsbn(isbn);
        livro.setQuantidade(quantidade);
        livro.setIdLivro(id);
        
        em.persist(livro);
        em.flush();
    }
    
    @Test
    public void persistirLivro() {
        String materia = "LPOO";
        String titulo = "Java: Como Programar";
        String autor = "Harvey Deitel";
        long isbn = 666123123;
        int quantidade = 10;

        Long id = 1L;

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setMateria(materia);
        livro.setIsbn(isbn);
        livro.setQuantidade(quantidade);

        em.persist(livro);
        em.flush();

        assertNotNull(em.find(Livro.class, id));
    }
    
    @Test
    public void atualizarLivro() {
        String novoAutor = "Paul Deitel and Harvey Deitel";        
        Long id = 1L;
        Livro livro = em.find(Livro.class, id);
        livro.setAutor(novoAutor);
        em.flush();
        String jpql = "SELECT c FROM Livro c WHERE c.idLivro = ?1";
        TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        livro = query.getSingleResult();
        assertEquals(novoAutor, livro.getAutor());      
    }
    
    @Test
    public void atualizarLivroMerge() {
        String novaMateria = "Algoritmo";  
        Long id = 1L;        
        Livro livro = em.find(Livro.class, id);     
        livro.setMateria(novaMateria);
        em.clear();
        em.merge(livro);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        livro = em.find(Livro.class, id, properties);
        assertEquals(novaMateria, livro.getMateria());
    }
    
    @Test
    public void removerLivro() {
        long id = 9L;
        
        Livro livro = em.find(Livro.class, id);
        em.remove(livro);

        assertNull(em.find(Livro.class, id));
    }
}

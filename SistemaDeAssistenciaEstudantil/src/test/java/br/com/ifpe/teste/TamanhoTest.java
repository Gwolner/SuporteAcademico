package br.com.ifpe.teste;

import br.com.ifpe.modelo.Tamanho;
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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TamanhoTest {
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
        
        inserirTamanhoTesteRemove();
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

    public void inserirTamanhoTesteRemove() {
        Tamanho tamanho = new Tamanho();      
        Long id = 9L;

        tamanho.setDescricaoTamanho("XG");
        tamanho.setIdTamanho(id);

        em.persist(tamanho);
        em.flush();
    }
    
    @Test
    public void persistirTamanho() {
        Tamanho tamanho = new Tamanho();      
        Long id = 1L;

        tamanho.setDescricaoTamanho("P");
        tamanho.setIdTamanho(id);

        em.persist(tamanho);
        em.flush();
        assertNotNull(em.find(Tamanho.class, id));
    }
    
    @Test
    public void atualizarTamanho() {
        String novoTamanho = "M";        
        Long id = 1L;        

        Tamanho tamanho = em.find(Tamanho.class, id);
        tamanho.setDescricaoTamanho(novoTamanho);

        em.flush();
        String jpql = "SELECT c FROM Tamanho c WHERE c.idTamanho = ?1";
        TypedQuery<Tamanho> query = em.createQuery(jpql, Tamanho.class);

        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);

        tamanho = query.getSingleResult();
        assertEquals(novoTamanho, tamanho.getDescricaoTamanho());  
    }
    
    @Test
    public void atualizarTamanhoMerge() {
        String novoTamanho = "PP";  
        Long id = 1L;        

        Tamanho tamanho = em.find(Tamanho.class, id);        
        tamanho.setDescricaoTamanho(novoTamanho);   

        em.clear();
        em.merge(tamanho);

        Map<String, Object> properties = new HashMap<>();        
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);

        tamanho = em.find(Tamanho.class, id, properties);        
        assertEquals(novoTamanho, tamanho.getDescricaoTamanho());
    }
    
    @Test
    public void removerTamanho() {
        Tamanho tamanho = em.find(Tamanho.class, 9L);
        em.remove(tamanho);
        assertNull(em.find(Tamanho.class, 9L));
    }
}

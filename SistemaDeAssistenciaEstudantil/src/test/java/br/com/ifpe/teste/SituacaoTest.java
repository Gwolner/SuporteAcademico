package br.com.ifpe.teste;

import br.com.ifpe.modelo.Situacao;
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


public class SituacaoTest {
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
        
        inserirSituacaoTesteRemove();
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

    public void inserirSituacaoTesteRemove() {
        Long idTemp = 9L;
        Situacao situacao = new Situacao();       
        situacao.setDescricaoSituacao("Rasgou");
        situacao.setIdSituacao(idTemp);
        em.persist(situacao);
        em.flush();
    }
    
    @Test
    public void persistirSituacao() {
        Long idTemp = 1L;
        Situacao situacao = new Situacao();       
        situacao.setDescricaoSituacao("Novo");
        situacao.setIdSituacao(idTemp);
        em.persist(situacao);
        em.flush();
        assertNotNull(em.find(Situacao.class, idTemp));
    }
    
    @Test
    public void atualizarSituacao() {
        String novoEstado = "Em estoque";        
        Long id = 1L;        
        Situacao situacao = em.find(Situacao.class, id);
        situacao.setDescricaoSituacao(novoEstado);
        em.flush();
        String jpql = "SELECT c FROM Situacao c WHERE c.idSituacao = ?1";
        TypedQuery<Situacao> query = em.createQuery(jpql, Situacao.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        situacao = query.getSingleResult();
        assertEquals(novoEstado, situacao.getDescricaoSituacao());  
    }
    
    @Test
    public void atualizarSituacaoMerge() {
        String novoAutor = "Pronto para emprestar";  
        Long id = 1L;        
        Situacao situacao = em.find(Situacao.class, id);    
        situacao.setDescricaoSituacao(novoAutor);
        em.clear();
        em.merge(situacao);
        Map<String, Object> properties = new HashMap<>();        
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);        
        situacao = em.find(Situacao.class, id, properties);        
        assertEquals(novoAutor, situacao.getDescricaoSituacao());
    }
    
    @Test
    public void removerSituacao() {
        Situacao situacao = em.find(Situacao.class, 9L);
        em.remove(situacao);
        assertNull(em.find(Situacao.class, 9L));
    }
}

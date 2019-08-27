package br.com.ifpe.teste;

import br.com.ifpe.modelo.Fardamento;
import br.com.ifpe.modelo.Situacao;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nicolas
 */
public class SituacaoTest {
    //Acredito que não seria necessário criar uma tabela pra situação
    //Talvez simplesmente guardar um int que referencie um enum no Java
    //já sirva.
    public SituacaoTest() {
    }
    
    private static EntityManagerFactory emf;
    private static final Logger logger = Logger.getGlobal();
    private EntityManager em;
    
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
    public void persistirSituacao() {
        //Esse teste não vai passar, todos os campos estão com nullable=false
        Situacao situacao = new Situacao();       
        situacao.setDescricaoSituacao("Novo");
        
        em.persist(situacao);
        em.flush();
        assertNotNull(situacao.getDescricaoSituacao());
    }
    
    @Test
    public void atualizarSituacao() {
        String novoEstado = "Em estoque";        
        Long id = 1L;
        
        Situacao situacao = em.find(Situacao.class, id);
        situacao.setDescricaoSituacao(novoEstado);
        em.flush();
        String jpql = "SELECT c FROM Situacao c WHERE c.idDescricaoSituacao = ?1";
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
        Situacao situacao = em.find(Situacao.class, 1L);
        em.remove(situacao);
        assertNull(situacao);
    }
}

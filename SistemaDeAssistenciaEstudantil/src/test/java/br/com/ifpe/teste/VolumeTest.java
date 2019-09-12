package br.com.ifpe.teste;

import br.com.ifpe.modelo.Volume;
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


public class VolumeTest {
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
        
        inserirVolumeTesteRemove();
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

    public void inserirVolumeTesteRemove() {
        Volume volume = new Volume();      

        Long idTemp = 9L;

        volume.setDescricaoVolume("Volume 1");
        volume.setIdVolume(idTemp);

        em.persist(volume);
        em.flush();
    }
    
    @Test
    public void persistirVolume() {
        Volume volume = new Volume();      

        Long idTemp = 1L;

        volume.setDescricaoVolume("Volume Unico");
        volume.setIdVolume(idTemp);

        em.persist(volume);
        em.flush();

        assertNotNull(em.find(Volume.class, idTemp));
    }
    
    @Test
    public void atualizarVolume() {
        String novoVolume = "Vol. 2";        
        Long id = 1L;        

        Volume volume = em.find(Volume.class, id);
        volume.setDescricaoVolume(novoVolume);

        em.flush();
        String jpql = "SELECT c FROM Volume c WHERE c.idVolume = ?1";
        TypedQuery<Volume> query = em.createQuery(jpql, Volume.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        volume = query.getSingleResult();
        assertEquals(novoVolume, volume.getDescricaoVolume());  
    }
    
    @Test
    public void atualizarVolumeMerge() {
        String novoVolume = "Vol. 3 - Rev. 2";   
        Long id = 1L;       

        Volume volume = em.find(Volume.class, id);    
        volume.setDescricaoVolume(novoVolume);

        em.clear();
        em.merge(volume);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        volume = em.find(Volume.class, id, properties);
        assertEquals(novoVolume, volume.getDescricaoVolume());
    }
    
    @Test
    public void removerVolume() {
        Volume volume = em.find(Volume.class, 9L);
        em.remove(volume);
        assertNull(em.find(Volume.class, 9L));
    }
}

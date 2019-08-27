/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpe.teste;

import br.com.ifpe.modelo.Tamanho;
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
public class TamanhoTest {
    
    private static EntityManagerFactory emf;
    private EntityManager em;
    
    public TamanhoTest() {
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
    public void persistirTamanho() {
        //Esse teste não vai passar, todos os campos estão com nullable=false
        Tamanho tamanho = new Tamanho();       
        tamanho.setDescricaoTamanho("Pequeno");
        em.persist(tamanho);
        em.flush();
        assertNotNull(tamanho.getDescricaoTamanho());
    }
    
    @Test
    public void atualizarTamanho() {
        String novoTamanho = "Medio";        
        Long id = 1L;
        
        Tamanho tamanho = em.find(Tamanho.class, id);
        tamanho.setDescricaoTamanho(novoTamanho);
        em.flush();
        String jpql = "SELECT c FROM Tamanho c WHERE c.descricaoTamanho = ?1";
        TypedQuery<Tamanho> query = em.createQuery(jpql, Tamanho.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        tamanho = query.getSingleResult();
        assertEquals(novoTamanho, tamanho.getDescricaoTamanho());  
    }
    
    @Test
    public void atualizarTamanhoMerge() {
        String novoAutor = "Pronto para emprestar";  
        Long id = 1L;
        
        Tamanho tamanho = em.find(Tamanho.class, id);        

        tamanho.setDescricaoTamanho(novoAutor);
        
        em.clear();
        em.merge(tamanho);
        Map<String, Object> properties = new HashMap<>();
        
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        
        tamanho = em.find(Tamanho.class, id, properties);
        
        assertEquals(novoAutor, tamanho.getDescricaoTamanho());
    }
    
    @Test
    public void removerTamanho() {
        Tamanho tamanho = em.find(Tamanho.class, 1L);
        em.remove(tamanho);
        assertNull(tamanho);
    }
}

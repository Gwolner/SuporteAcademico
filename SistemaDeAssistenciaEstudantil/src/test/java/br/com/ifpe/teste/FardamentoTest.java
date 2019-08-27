/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpe.teste;

import br.com.ifpe.modelo.Fardamento;
import br.com.ifpe.modelo.Livro;
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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author nicolas
 */
public class FardamentoTest {

    private static EntityManagerFactory emf;
    private static final Logger logger = Logger.getGlobal();
    private EntityManager em;

    public FardamentoTest() {
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
    public void persistirFardamento() {
        //Esse teste não vai passar, todos os campos estão com nullable=false
        Fardamento fardamento = new Fardamento();       
        fardamento.setIdTamanho(1L);
        
        em.persist(fardamento);
        em.flush();
        assertNotNull(fardamento.getIdTamanho());
    }
    
    @Test
    public void atualizarFardamento() {
        /**/
    }
    
    @Test
    public void atualizarLivroMerge() {
        /**/
    }
    
    @Test
    public void removerFardamento() {
        Fardamento fardamento = em.find(Fardamento.class, 1L);
        em.remove(fardamento);
        assertNull(fardamento);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpe.teste;

import br.com.ifpe.modelo.Aluno;
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
public class AlunoTest {

    private static EntityManagerFactory emf;
    private static final Logger logger = Logger.getGlobal();
    private EntityManager em;

    public AlunoTest() {
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
    public void persistirAluno() {
        Aluno aluno = new Aluno();
        em.persist(aluno);
        em.flush();
        assertNotNull(aluno.getIdAluno());
    }
    
    @Test
    public void atualizarAluno() {
        String nomeAluno = "Herbert Leonardo";
        String curso = "TADS";
        int numero = 98765432;
        
        Long id = 1L;
        Aluno aluno = em.find(Aluno.class, id);
        aluno.setNomeAluno(nomeAluno);
        aluno.setCurso(curso);
        aluno.setCelular(numero);
        em.flush();
        String jpql = "SELECT c FROM Aluno c WHERE c.idAluno = ?1";
        TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        aluno = query.getSingleResult();
        assertEquals(nomeAluno, aluno.getNomeAluno());      
    }
    
    @Test
    public void atualizarAlunoMerge() {
        String novoNomeAluno = "Herbert Leonardo 2";
        String novoCurso = "ADS (2)";
        int novoNumero = 9999999;        
        Long id = 1L;
        
        Aluno aluno = em.find(Aluno.class, id);        
        
        aluno.setNomeAluno(novoNomeAluno);
        aluno.setCurso(novoCurso);
        aluno.setCelular(novoNumero);
        
        em.clear();
        em.merge(aluno);
        Map<String, Object> properties = new HashMap<>();
        
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        
        aluno = em.find(Aluno.class, id, properties);
        
        assertEquals(novoNomeAluno, aluno.getNomeAluno());
        assertEquals(novoCurso, aluno.getCurso());
    }
    
    @Test
    public void removerAluno() {
        Aluno aluno = em.find(Aluno.class, 9L);
        em.remove(aluno);
        assertNull(aluno);
    }
}

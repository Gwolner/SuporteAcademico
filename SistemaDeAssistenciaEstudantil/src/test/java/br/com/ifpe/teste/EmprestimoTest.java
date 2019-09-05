/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpe.teste;

import br.com.ifpe.modelo.Aluno;
import br.com.ifpe.modelo.Emprestimo;
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
public class EmprestimoTest {

    private static EntityManagerFactory emf;
    private static final Logger logger = Logger.getGlobal();
    private EntityManager em;

    public EmprestimoTest() {
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
    public void persistirEmprestimo() {
        //AQUI ASSOCIA-SE O ID DE GUILHERME COM O PRIMEIRO LIVRO INSERIDO
        Emprestimo emprestimo = new Emprestimo();
        Aluno a = new Aluno();
        emprestimo.setAluno(a);
        //emprestimo.setIdAluno(1L);
        em.persist(emprestimo);
        em.flush();
        assertNotNull(a.getIdAluno());
    }
    
    @Test
    public void atualizarEmprestimo() {
        //Cria um novo aluno e associa o Id dele com o do emprestimo
        Aluno aluno = new Aluno();
        aluno.setNomeAluno("Nicolas");
        
        //id do primeiro aluno
        Long id = 1L;
        
        //A ideia é buscar o emprestimo do primeiro aluno, e mudar o id desse 
        //emprestimo pro id do aluno criado agora.
        Emprestimo emprestimo = em.find(Emprestimo.class, id);
        emprestimo.setAluno(aluno);
        em.flush();
        String jpql = "SELECT c FROM Emprestimo c WHERE c.idAluno = ?1";
        TypedQuery<Emprestimo> query = em.createQuery(jpql, Emprestimo.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        emprestimo = query.getSingleResult();
        assertEquals(aluno.getIdAluno(), emprestimo.getAluno().getIdAluno());      
    }
    
    @Test
    public void atualizarEmprestimoMerge() {
        Aluno aluno = new Aluno();
        aluno.setNomeAluno("Nicolas");
        
        Long id = 1L;
        
        Emprestimo emprestimo = em.find(Emprestimo.class, id);        

        emprestimo.setAluno(aluno);
        
        em.clear();
        em.merge(emprestimo);
        Map<String, Object> properties = new HashMap<>();
        
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        
        emprestimo = em.find(Emprestimo.class, id, properties);
        
        assertEquals(emprestimo.getAluno().getIdAluno(), aluno.getIdAluno());
    }
    
    @Test
    public void removerEmprestimo() {
        Emprestimo emprestimo = em.find(Emprestimo.class, 1L);
        em.remove(emprestimo);
        assertNull(emprestimo);
    }
}

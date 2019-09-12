package br.com.ifpe.teste;

import br.com.ifpe.modelo.Aluno;
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


public class AlunoTest {
    private static EntityManagerFactory emf;
    private EntityManager em;
    private static EntityTransaction et;

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
        et = em.getTransaction();
        et.begin();
        
        inserirAlunoTesteRemove();
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

    @Test
    public void persistirAluno() {
        String nomeAluno = "Guilherme Wolner";
        String curso = "Geografia";
        int numero = 98765432;        
        String turno = "Integral";        
        Long id = 1L;
        String matricula = "20172y6-rc0000";
        long cpf = 66666666666L;
        String email = "gwolner@gmail.com";

        Aluno aluno = new Aluno();

        aluno.setNomeAluno(nomeAluno);
        aluno.setCurso(curso);
        aluno.setCelular(numero);
        aluno.setTurno(turno);
        aluno.setMatricula(matricula);
        aluno.setCpf(cpf);
        aluno.setEmail(email);
        
        em.persist(aluno);
        em.flush();
        assertNotNull(em.find(Aluno.class, id));
    }
    
    @Test
    public void atualizarAluno() {
        String nomeAluno = "Herbert Leonardo";
        String curso = "TADS";
        int numero = 98765432;        
        String turno = "Noite";        
        Long id = 1L;
        String matricula = "20172y6-rc0000";
        long cpf = 12312312312L;
        String email = "hg3@gmail.com";

        Aluno aluno = em.find(Aluno.class, id);
        aluno.setNomeAluno(nomeAluno);
        aluno.setCurso(curso);
        aluno.setCelular(numero);
        aluno.setTurno(turno);
        aluno.setMatricula(matricula);
        aluno.setCpf(cpf);
        aluno.setEmail(email);

        em.flush();
        
        String jpql = "SELECT c FROM Aluno c WHERE c.idAluno = ?1";
        TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        aluno = query.getSingleResult();

        assertEquals(nomeAluno, aluno.getNomeAluno());      
        assertEquals(curso, aluno.getCurso());   
        assertEquals(numero, aluno.getCelular());   
        assertEquals(turno, aluno.getTurno());   
        assertEquals(matricula, aluno.getMatricula());   
        assertEquals(cpf, aluno.getCpf());   
        assertEquals(email, aluno.getEmail());  
    }
    
    @Test
    public void atualizarAlunoMerge() {
        String novoNomeAluno = "Herbert Leonardo 2";
        String novoCurso = "ADS (2)";
        String novoTurno = "Noite";
        String novaMatricula = "20192y6-rc0100";
        long novoCpf = 25225225287L;
        String novoEmail = "herb.dbz@yahoo.com";
        int novoNumero = 9999999;        
        Long id = 1L;        
        
        Aluno aluno = em.find(Aluno.class, 1L);
        
        aluno.setNomeAluno(novoNomeAluno);
        aluno.setCurso(novoCurso);
        aluno.setCelular(novoNumero);
        aluno.setTurno(novoTurno);
        aluno.setMatricula(novaMatricula);
        aluno.setEmail(novoEmail);
        aluno.setCpf(novoCpf);
        
        em.clear();
        em.merge(aluno);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        aluno = em.find(Aluno.class, id, properties);

        assertEquals(novoNomeAluno, aluno.getNomeAluno());
        assertEquals(novoCurso, aluno.getCurso());
        assertEquals(novoNumero, aluno.getCelular());   
        assertEquals(novoTurno, aluno.getTurno());   
        assertEquals(novaMatricula, aluno.getMatricula());   
        assertEquals(novoCpf, aluno.getCpf());   
        assertEquals(novoEmail, aluno.getEmail());  
    }
    
    @Test
    public void removerAluno() {
        Aluno aluno = em.find(Aluno.class, 9L);
        em.remove(aluno);
        assertNull(em.find(Aluno.class, 9L));
    }
    
    private void inserirAlunoTesteRemove() {
        String nomeAluno = "Guilherme Wolner2";
        String curso = "Geografia Total";
        int numero = 777777;        
        String turno = "Tarde";        
        Long id = 9L;
        String matricula = "2000rc0000";
        long cpf = 60066L;
        String email = "gwolner333@gmail.com";

        Aluno aluno = new Aluno();

        aluno.setNomeAluno(nomeAluno);
        aluno.setCurso(curso);
        aluno.setCelular(numero);
        aluno.setTurno(turno);
        aluno.setMatricula(matricula);
        aluno.setCpf(cpf);
        aluno.setEmail(email);
        aluno.setIdAluno(id);
        
        em.persist(aluno);
        em.flush();
    }
}

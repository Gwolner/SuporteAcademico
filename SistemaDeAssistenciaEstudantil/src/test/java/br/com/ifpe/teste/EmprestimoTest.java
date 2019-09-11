/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpe.teste;

import br.com.ifpe.modelo.Aluno;
import br.com.ifpe.modelo.Emprestimo;
import br.com.ifpe.modelo.Fardamento;
import br.com.ifpe.modelo.Livro;
import br.com.ifpe.modelo.Volume;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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
public class EmprestimoTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private static EntityTransaction et;

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
        et = em.getTransaction();
        et.begin();
        
        inserirEmprestimoTesteRemove();
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
    public void persistirEmprestimo() {
        Emprestimo emprestimo = new Emprestimo();
        long idEmprestimo = 1L;
        String statusEmprestimo = "Emprestado";
        
        String nomeAluno = "Paulo Abadie";
        String curso = "ADS";
        int numero = 55557896;        
        String turno = "Manhã";    
        String matricula = "2010ii-iiiii";
        long cpf = 232323232323L;
        String email = "abadie@abadie.com";
        long idAluno = 3L;
        
        Aluno aluno = new Aluno();
        aluno.setNomeAluno(nomeAluno);
        aluno.setCurso(curso);
        aluno.setCelular(numero);
        aluno.setTurno(turno);
        aluno.setMatricula(matricula);
        aluno.setCpf(cpf);
        aluno.setEmail(email);
        aluno.setIdAluno(idAluno);
        
        em.persist(aluno);
        em.flush();
        
        emprestimo.setAluno(em.find(Aluno.class, idAluno));
        emprestimo.setIdEmprestimo(idEmprestimo);
        emprestimo.setStatus(statusEmprestimo);

        em.persist(emprestimo);
        em.flush();

        assertNotNull(em.find(Emprestimo.class, idEmprestimo));
    }
    
    @Test
    public void atualizarEmprestimo() {
        long id = 1L;

        String statusAtual = "atrasado";

        Emprestimo emprestimo = em.find(Emprestimo.class, id);
        emprestimo.setStatus(statusAtual);
        em.flush();
        String jpql = "SELECT c FROM Emprestimo c WHERE c.idEmprestimo = ?1";
        TypedQuery<Emprestimo> query = em.createQuery(jpql, Emprestimo.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        emprestimo = query.getSingleResult();

        assertEquals(statusAtual, emprestimo.getStatus());      
    }
    
    @Test
    public void atualizarEmprestimoMerge() {
        Long id = 1L;
        String statusAtual = "Devolvido";
        
        Emprestimo emprestimo = em.find(Emprestimo.class, id);        

        emprestimo.setStatus(statusAtual);
        
        em.clear();
        em.merge(emprestimo);
        Map<String, Object> properties = new HashMap<>();
        
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        
        emprestimo = em.find(Emprestimo.class, id, properties);
        
        assertEquals(emprestimo.getStatus(), statusAtual);
    }
    
    @Test
    public void removerEmprestimo() {
        Emprestimo emprestimo = em.find(Emprestimo.class, 9L);
        em.remove(emprestimo);
        assertNull(em.find(Emprestimo.class, 9L));
    }

    private void inserirEmprestimoTesteRemove(){

        //Data de entrega
        Calendar entrega = Calendar.getInstance();
        entrega.set(Calendar.YEAR, 2019);
        entrega.set(Calendar.MONTH, Calendar.SEPTEMBER);
        entrega.set(Calendar.DAY_OF_MONTH, 2);
        
        //Data devolução
        Calendar devolucao = Calendar.getInstance();
        devolucao.set(Calendar.YEAR, 2019);
        devolucao.set(Calendar.MONTH, Calendar.SEPTEMBER);
        devolucao.set(Calendar.DAY_OF_MONTH, 25);
        
        //Emprestimo 
        Emprestimo emprestimo = new Emprestimo();
        long idEmprestimo = 9L;
        String statusEmprestimo = "Roubado";
       
        emprestimo.setIdEmprestimo(idEmprestimo);
        emprestimo.setStatus(statusEmprestimo);        
        emprestimo.setDataEntrega(entrega.getTime());
        emprestimo.setDataDevolucao(devolucao.getTime());
        //emprestimo.setAluno(criarAluno());
        //emprestimo.setLivro(criarLivro());

        em.persist(emprestimo);
        em.flush();
    }
    
    private Aluno criarAluno(){
        
        String nomeAluno = "Miguel Nicolas";
        String curso = "ADS";
        int numero = 123222;
        String turno = "Noite";    
        String matricula = "2111ii-iiiii";
        long cpf = 112233L;
        String email = "nicolas@sefaz.com";

        List<Emprestimo> emprestimo = new ArrayList<Emprestimo>();
        List<Fardamento> fardamento = new ArrayList<Fardamento>();
        
        Aluno aluno = new Aluno();
        aluno.setIdAluno(1L);
        aluno.setNomeAluno(nomeAluno);
        aluno.setCurso(curso);
        aluno.setCelular(numero);
        aluno.setTurno(turno);
        aluno.setMatricula(matricula);
        aluno.setCpf(cpf);
        aluno.setEmail(email);
        aluno.setEmprestimo(emprestimo);
        aluno.setFardamento(fardamento);
        aluno.setReferencia("2019-5p!");
        
        em.persist(aluno);
        em.flush();
        
        return aluno;
    }
    
    private Livro criarLivro(){
        
        String materia = "LPOO";
        String titulo = "Java: Como Programar";
        String autor = "Harvey Deitel";
        long isbn = 666123123;
        int quantidade = 10;
        Long id = 1L;
        List<Emprestimo> emprestimo = new ArrayList<Emprestimo>();
        Volume volume = new Volume();
        
        
        Livro livro = new Livro();
        livro.setAutor(autor);
        livro.setIdLivro(id);
        livro.setIsbn(isbn);
        livro.setMateria(materia);
        livro.setQuantidade(quantidade);
        livro.setTitulo(titulo);
        livro.setEmprestimo(emprestimo);
        livro.setVolume(volume);
        
        em.persist(livro);
        em.flush();
        
        return livro;
    }
}

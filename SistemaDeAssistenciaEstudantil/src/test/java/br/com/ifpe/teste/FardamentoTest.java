/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpe.teste;

import br.com.ifpe.modelo.Aluno;
import br.com.ifpe.modelo.Fardamento;
import br.com.ifpe.modelo.Livro;
import br.com.ifpe.modelo.Situacao;
import br.com.ifpe.modelo.Tamanho;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
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

/*
        Fardamento
            Precisa de:
                * tamahhoPedido
                * tamanhoEntregue
                * quantidadeEntregue
                * Aluno
                * Situacao
                * Tamanho
            
*/
public class FardamentoTest {

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
        
        inserirFardamentoTesteRemove();
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
    public void persistirFardamento() {
        //ALUNO
        String nomeAluno = "James Gosling";
        String curso = "Java";
        int numero = 98765432;        
        String turno = "Integral"; 
        String matricula = "java_0001";
        int cpf = 00000077777;
        String email = "jg@oracle.com";

        Aluno aluno = new Aluno();
        aluno.setNomeAluno(nomeAluno);
        aluno.setCurso(curso);
        aluno.setCelular(numero);
        aluno.setTurno(turno);
        aluno.setMatricula(matricula);
        aluno.setCpf(cpf);
        aluno.setEmail(email);

        //SITUACAO
        Situacao situacao = new Situacao();       
        situacao.setDescricaoSituacao("Ok");

        //TAMANHO
        Tamanho tamanho = new Tamanho();     
        tamanho.setDescricaoTamanho("M");

        // -- FARDAMENTO EM SI --
        Fardamento fardamento = new Fardamento(); 
        fardamento.setTamanhoPedido("M");
        fardamento.setTamanhoEntregue("P");
        fardamento.setQuantidadeEntregue(2);
        //fardamento.setAluno(aluno);
        //fardamento.setSituacao(situacao);
        //fardamento.setTamanho(tamanho);

        em.persist(fardamento);
        em.flush();
        assertNotNull(em.find(Fardamento.class, 1L));
    }
    
    @Test
    public void atualizarFardamento() {
        Long id = 1L;
        String tamanhoString = "PP";
        
        Fardamento fardamento = em.find(Fardamento.class, id);

        Tamanho tamanho = new Tamanho();     
        tamanho.setDescricaoTamanho(tamanhoString);

        fardamento.setTamanhoPedido(tamanho.getDescricaoTamanho());
        em.flush();
        String jpql = "SELECT c FROM Fardamento c WHERE c.idFardamento = ?1";
        TypedQuery<Fardamento> query = em.createQuery(jpql, Fardamento.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        fardamento = query.getSingleResult();

        assertEquals(tamanhoString, fardamento.getTamanhoPedido());      
    }
    
    @Test
    public void atualizarFardamentoMerge() {
        String novoTamanho = "GG";  
        Long id = 1L;        

        Fardamento fardamento = em.find(Fardamento.class, id);  
        fardamento.setTamanhoEntregue(novoTamanho);

        em.clear();
        em.merge(fardamento);

        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        fardamento = em.find(Fardamento.class, id, properties);
        
        assertEquals(novoTamanho, fardamento.getTamanhoEntregue());
    }
    
    @Test
    public void removerFardamento() {
        Fardamento fardamento = em.find(Fardamento.class, 9L);
        em.remove(fardamento);
        assertNull(em.find(Fardamento.class, 9L));
    }

    private void inserirFardamentoTesteRemove() {
        
        String nomeAluno = "Ken Thompson";
        String curso = "C";
        int numero = 99900000;        
        String turno = "Integral"; 
        String matricula = "clang_0001";
        int cpf = 00000077777;
        String email = "kt@clang.com";

        Aluno aluno = new Aluno();
        aluno.setNomeAluno(nomeAluno);
        aluno.setCurso(curso);
        aluno.setCelular(numero);
        aluno.setTurno(turno);
        aluno.setMatricula(matricula);
        aluno.setCpf(cpf);
        aluno.setEmail(email);

        //SITUACAO
        Situacao situacao = new Situacao();       
        situacao.setDescricaoSituacao("OKAY");

        //TAMANHO
        Tamanho tamanho = new Tamanho();     
        tamanho.setDescricaoTamanho("G");

        // -- FARDAMENTO EM SI --
        Fardamento fardamento = new Fardamento();
        fardamento.setIdFardamento(9L);
        fardamento.setTamanhoPedido("G");
        fardamento.setTamanhoEntregue("G");
        fardamento.setQuantidadeEntregue(3);
        //fardamento.setAluno(aluno);
        //fardamento.setSituacao(situacao);
        //fardamento.setTamanho(tamanho);

        em.persist(fardamento);
        em.flush();
    }
}

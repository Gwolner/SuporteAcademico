package br.com.ifpe.teste;

import br.com.ifpe.modelo.Aluno;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class GerarTabelas {
    
    public static void main(String[] args) {      
        Aluno aluno = new Aluno();
        preencherObjeto(aluno);
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            //EntityManagerFactory realiza a leitura das informações relativas à "persistence-unit".
            emf = Persistence.createEntityManagerFactory("tarefas");
            em = emf.createEntityManager(); //Criação do EntityManager.
            et = em.getTransaction(); //Recupera objeto responsável pelo gerenciamento de transação.
            et.begin();
            em.persist(aluno);
            et.commit();
        } catch (Exception ex) {
            if (et != null)
                et.rollback();
        } finally {
            if (em != null)
                em.close();       
            if (emf != null)
                emf.close();
        }
    }

    private static void preencherObjeto(Aluno aluno) {
        aluno.setNomeAluno("Guilherme");
//        aluno.setCpf(95606795);
//        aluno.setCelular(95606795);
//        aluno.setCurso("TADS");
//        aluno.setEmail("gwdm@a.com");
//        aluno.setMatricula("20172y6-rc0000");
//        aluno.setTurno("Noite");
//        aluno.setReferencia("Olá");
    }


}

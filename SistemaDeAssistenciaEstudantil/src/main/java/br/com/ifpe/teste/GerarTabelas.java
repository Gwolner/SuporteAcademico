/*
package br.com.ifpe.teste;

import br.com.ifpe.modelo.Tarefa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class GerarTabelas {
    public static void main(String[] args) {
//        EntityManagerFactory factory = Persistence.
//            createEntityManagerFactory("tarefas");
//        
//        EntityManager em = factory.createEntityManager();
//        
//
//        
//        factory.close();
//    }

    Tarefa trf = new Tarefa();
        preencherTarefa(trf);
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            //EntityManagerFactory realiza a leitura das informações relativas à "persistence-unit".
            emf = Persistence.createEntityManagerFactory("tarefas");
            em = emf.createEntityManager(); //Criação do EntityManager.
            et = em.getTransaction(); //Recupera objeto responsável pelo gerenciamento de transação.
            et.begin();
            em.persist(trf);
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

    private static void preencherTarefa(Tarefa trf) {
        trf.setDescricao("Teste JPA");
        trf.setFinalizado(true);
    }


}
*/
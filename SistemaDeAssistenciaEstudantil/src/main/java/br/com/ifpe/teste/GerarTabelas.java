package br.com.ifpe.teste;

import br.com.ifpe.modelo.Aluno;
import br.com.ifpe.modelo.Emprestimo;
import br.com.ifpe.modelo.Fardamento;
import br.com.ifpe.modelo.Livro;
import br.com.ifpe.modelo.Situacao;
import br.com.ifpe.modelo.Tamanho;
import br.com.ifpe.modelo.Volume;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class GerarTabelas {
    
    public static void main(String[] args) {      
        
        //Instanciando Entidades!
        Aluno aluno = new Aluno();
        Emprestimo emprestimo = new Emprestimo();
        Fardamento fardamento = new Fardamento();
        List <Fardamento> fardamentos = new ArrayList();
        Livro livro = new Livro();
        Situacao situacao = new Situacao();
        Tamanho tamanho = new Tamanho();
        Volume volume = new Volume();
        
        //Populando Entidades
        preencherAluno(aluno, fardamentos);
//        preencherEmprestimo(emprestimo);
        preencherFardamento(fardamento);
        preencherLivro(livro);
        aluno.setFardamento(fardamentos);
        fardamento.setAluno(aluno);
//        preencherSituacao(situacao);
//        preencherTamnho(tamanho);
//        preencherVolume(volume);
        
        
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            //EntityManagerFactory realiza a leitura das informações relativas à "persistence-unit".
            emf = Persistence.createEntityManagerFactory("tarefas");
            em = emf.createEntityManager(); //Criação do EntityManager.
            et = em.getTransaction(); //Recupera objeto responsável pelo gerenciamento de transação.
            et.begin();

            //Persistindo Entidades
            em.persist(aluno);
//            em.persist(emprestimo);
            em.persist(fardamento);
            em.persist(livro);
//            em.persist(situacao);
//            em.persist(tamanho);
//            em.persist(volume);
            
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

    private static void preencherAluno(Aluno aluno, List <Fardamento> f) {
        aluno.setNomeAluno("Guilherme");
        aluno.setCpf(95606795);
        aluno.setCelular(95606795);
        aluno.setCurso("TADS");
        aluno.setEmail("gwdm@a.com");
        aluno.setMatricula("20172y6-rc0000");
        aluno.setTurno("Noite");
        aluno.setReferencia("Olá");
    }

//    private static void preencherEmprestimo(Emprestimo emprestimo){
//        emprestimo.setDataDevolucao(23/08/2019);
//        emprestimo.setDataEntrega(08/09/2019);
//        emprestimo.setIdAluno(1);
//        emprestimo.setIdLivro(1);
//        emprestimo.setStatus("Devolvido");
//    }
    
    private static void preencherFardamento(Fardamento fardamento){
        fardamento.setIdAluno(1);
        fardamento.setIdSituacao(1);
        fardamento.setIdTamanho(1);
        fardamento.setQuantidadeEntregue(2);
        fardamento.setTamanhoEntregue("P");
        fardamento.setTamanhoPedido("P");
    }
    
    private static void preencherLivro(Livro livro){
        livro.setAutor("Jonas");
        livro.setIdVolume(1);
        livro.setIsbn(5454541);
        livro.setMateria("Português");
        livro.setQuantidade(200);
        livro.setTitulo("Nova Lingua Portugues");
    }
    
//    private static void preencherSituacao(Situacao situacao){
//        /*
//        Esta tabela deve ser populada APENAS com os registros
//        ENTREGUE e NÃO ENTREGUE.
//        */
//    }
    
//    private static void preencherTamnho(Tamanho tamanho){
//        /*
//        Esta tabela deve ser populada APENAS com os registros
//        PP, P, M, G e XG.
//        */
//    }
    
//    private static void preencherVolume(Volume volume){
//        /*
//        Esta tabela deve ser populada APENAS com os registros
//        VOLUME 1, VOLUME 2, VOLUME 3 e VOLUME ÚNICO 
//        */
//    }

}

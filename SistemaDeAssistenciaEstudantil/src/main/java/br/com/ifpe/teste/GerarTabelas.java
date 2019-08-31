package br.com.ifpe.teste;

import br.com.ifpe.modelo.Aluno;
import br.com.ifpe.modelo.Emprestimo;
import br.com.ifpe.modelo.Fardamento;;
import br.com.ifpe.modelo.Situacao;
import br.com.ifpe.modelo.Tamanho;
import br.com.ifpe.modelo.Volume;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import br.com.ifpe.modelo.Livro;

public class GerarTabelas {
    
    public static void main(String[] args) {      
        
        //Instanciando Entidades!
        Aluno aluno = new Aluno();
        Emprestimo emprestimo = new Emprestimo();
        Fardamento fardamento = new Fardamento();
        Livro livro = new Livro();
        Situacao situacao = new Situacao();
        Tamanho tamanho = new Tamanho();
        Volume volume = new Volume();
        
        //Instanciando Listas
        List <Fardamento> fardamentosAluno = new ArrayList();
        List <Fardamento> fardamentoSituacao = new ArrayList();
        List <Fardamento> fardamentoTamanho = new ArrayList();
        List <Emprestimo> emprestimoAluno = new ArrayList();
        List <Emprestimo> emprestimoLivro = new ArrayList();        
        List <Livro> livroVolume = new ArrayList();
              
        //Populando as Entidades
        preencherAluno(aluno, fardamentosAluno);
        preencherEmprestimo(emprestimo, aluno, livro);
        preencherFardamento(fardamento, aluno, situacao, tamanho);
        preencherLivro(livro, volume, emprestimoLivro);
        preencherSituacao(situacao, fardamentoSituacao);
        preencherTamanho(tamanho, fardamentoTamanho);
        preencherVolume(volume, livroVolume);
        
        
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
            em.persist(emprestimo);
            em.persist(fardamento);
            em.persist(livro);
            em.persist(situacao);
            em.persist(tamanho);
            em.persist(volume);
            
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

    private static void preencherAluno(Aluno aluno, List <Fardamento> fa) {
        aluno.setNomeAluno("Guilherme");
        aluno.setCpf(95606795);
        aluno.setCelular(95606795);
        aluno.setCurso("TADS");
        aluno.setEmail("gwdm@a.com");
        aluno.setMatricula("20172y6-rc0000");
        aluno.setTurno("Noite");
        aluno.setReferencia("Olá");
        aluno.setFardamento(fa);
    }

    private static void preencherEmprestimo(Emprestimo emprestimo, Aluno aluno, 
            Livro livro){

//        emprestimo.setDataDevolucao(23/08/2019);
//        emprestimo.setDataEntrega(08/09/2019);
        emprestimo.setStatus("Devolvido");
        emprestimo.setAluno(aluno);
        emprestimo.setLivro(livro);
    }
    
    private static void preencherFardamento(Fardamento fardamento, Aluno aluno, 
            Situacao sit, Tamanho tam){
        
        fardamento.setTamanhoEntregue("P");
        fardamento.setTamanhoPedido("P");
        fardamento.setQuantidadeEntregue(2);
//        fardamento.setAluno(aluno);
//        fardamento.setTamanho(tam);
//        fardamento.setSituacao(sit);
    }
    
    private static void preencherLivro(Livro livro, Volume vol, 
            List <Emprestimo> el){
        
        livro.setTitulo("O conde de Monte Cristo");
        livro.setAutor("Alexandre Dumas");
        livro.setIsbn(12587);
        livro.setMateria("Paradidático");
        livro.setQuantidade(25);  
//        livro.setVolume(vol);
//        livro.setEmprestimo(el);
    }
    
    private static void preencherSituacao(Situacao situacao, 
            List <Fardamento> fs ){
        
        situacao.setDescricaoSituacao("Não entregue");
//        situacao.setFardamento(fs);
        /*
        Esta tabela deve ser populada APENAS com os registros
        ENTREGUE e NÃO ENTREGUE.
        */
        
    }
    
    private static void preencherTamanho(Tamanho tamanho, 
            List <Fardamento> ft){
        
        tamanho.setDescricaoTamanho("M");
//        tamanho.setFardamento(ft);
        /*
        Esta tabela deve ser populada APENAS com os registros
        PP, P, M, G e XG.
        */
        
    }
    
    private static void preencherVolume(Volume volume, 
            List <Livro> lv){
        
        volume.setDescricaoVolume("Volume único");
//        volume.setLivro(lv);
        /*
        Esta tabela deve ser populada APENAS com os registros
        VOLUME 1, VOLUME 2, VOLUME 3 e VOLUME ÚNICO 
        */
    }

}

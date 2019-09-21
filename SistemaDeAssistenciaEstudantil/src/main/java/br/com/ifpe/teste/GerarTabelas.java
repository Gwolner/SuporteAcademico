package br.com.ifpe.teste;

import br.com.ifpe.modelo.Aluno;
import br.com.ifpe.modelo.Bolsa;
import br.com.ifpe.modelo.Emprestimo;
import br.com.ifpe.modelo.Fardamento;;
import br.com.ifpe.modelo.Situacao;
import br.com.ifpe.modelo.Tamanho;
import br.com.ifpe.modelo.Volume;
import br.com.ifpe.modelo.Professor;
import br.com.ifpe.modelo.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import br.com.ifpe.modelo.Livro;

public class GerarTabelas {
    
    public static void main(String[] args) {      
        
        //Instanciando Entidades!
        Usuario usuario = new Usuario();
        Aluno aluno = new Aluno();
        Professor professor = new Professor();
        Emprestimo emprestimo = new Emprestimo();
        Livro livro = new Livro();
        
        
        Volume volume = new Volume();
        Bolsa bolsa = new Bolsa();
        Fardamento fardamento = new Fardamento();
        Tamanho tamanho = new Tamanho();
        Situacao situacao = new Situacao();
       
        
        
        
        //Populando as Entidades
        preencherAluno(aluno);
        preencherEmprestimo(emprestimo, aluno, livro);
        preencherFardamento(fardamento, aluno, situacao, tamanho);
        preencherLivro(livro, volume);
        preencherSituacao(situacao);
        preencherTamanho(tamanho);
        preencherVolume(volume);
        
        
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
            em.persist(professor);
            em.persist(bolsa);
            em.persist(usuario);
            
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

    private static void preencherAluno(Aluno aluno) {
        aluno.setNomeUsuario("Guilherme");
        aluno.setCpf(95606795L);
        aluno.setContatoResponsavel(95606795L);
        aluno.setCurso("TADS");
        aluno.setEmail("gwdm@a.com");
        aluno.setMatricula("20172y6-rc0000");
        aluno.setTurno("Noite");
        aluno.setReferencia("Olá");
    }

    private static void preencherEmprestimo(Emprestimo emprestimo, Aluno aluno, 
            Livro livro){

//        emprestimo.setDataDevolucao(23/08/2019);
//        emprestimo.setDataEntrega(08/09/2019);
        emprestimo.setStatus("Devolvido");
        emprestimo.setUsuario(aluno);
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
    
    private static void preencherLivro(Livro livro, Volume vol){
        
        livro.setTitulo("O conde de Monte Cristo");
        livro.setAutor("Alexandre Dumas");
        livro.setIsbn(12587L);
        livro.setMateria("Paradidático");
        livro.setQuantidade(25);  
//        livro.setVolume(vol);
//        livro.setEmprestimo(el);
    }
    
    private static void preencherSituacao(Situacao situacao){
        
        situacao.setDescricaoSituacao("Não entregue");
//        situacao.setFardamento(fs);
        /*
        Esta tabela deve ser populada APENAS com os registros
        ENTREGUE e NÃO ENTREGUE.
        */
        
    }
    
    private static void preencherTamanho(Tamanho tamanho){
        
        tamanho.setDescricaoTamanho("M");
//        tamanho.setFardamento(ft);
        /*
        Esta tabela deve ser populada APENAS com os registros
        PP, P, M, G e XG.
        */
        
    }
    
    private static void preencherVolume(Volume volume){
        
        volume.setDescricaoVolume("Volume único");
//        volume.setLivro(lv);
        /*
        Esta tabela deve ser populada APENAS com os registros
        VOLUME 1, VOLUME 2, VOLUME 3 e VOLUME ÚNICO 
        */
    }

}

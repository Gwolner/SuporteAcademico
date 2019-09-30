package testeValidacao;

import br.com.ifpe.modelo.Aluno;
import br.com.ifpe.modelo.Bolsa;
import br.com.ifpe.modelo.Emprestimo;
import br.com.ifpe.modelo.Fardamento;
import br.com.ifpe.modelo.Livro;
import br.com.ifpe.modelo.Professor;
import br.com.ifpe.modelo.Situacao;
import br.com.ifpe.modelo.Tamanho;
import br.com.ifpe.modelo.Usuario;
import br.com.ifpe.modelo.Volume;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;


public class GenericTest {

    protected static EntityManagerFactory emf;
    protected static Logger logger;
    protected EntityManager em;
    protected EntityTransaction et;

    @BeforeClass
    public static void setUpClass() {
        logger = Logger.getGlobal();
        logger.setLevel(Level.INFO);
        //logger.setLevel(Level.SEVERE);
        emf = Persistence.createEntityManagerFactory("tarefas");
        //emf.createEntityManager();
        DbUnitUtil.inserirDados();
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        beginTransaction();
    }

    @After
    public void tearDown() {
        commitTransaction();
        em.close();      
    }

    private void beginTransaction() {
        et = em.getTransaction();
        et.begin();
    }

    private void commitTransaction() {
        try {
            et.commit();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            fail(ex.getMessage());
        }
    }

    protected Date getData(Integer dia, Integer mes, Integer ano) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.DAY_OF_MONTH, dia);
        return c.getTime();
    }
    
    protected Volume criarVolumeInvalido(){
        Volume volume = new Volume();
        volume.setDescricaoVolume(" "); 
        
        return volume;
    }
    
    protected Aluno criarAlunoInvalido(){        
        Aluno aluno = new Aluno();
        aluno.setNomeUsuario("Thomas Edson");
        aluno.setCpf(98920073015L);
        aluno.setRg(457253472);
        aluno.setCurso("Quimica");
        aluno.setResponsavel("Abilbo");
        aluno.setContatoResponsavel(911112222L);
        aluno.setTurno("Manha");
        aluno.setMatricula("20172y6-rc0000");
        aluno.setCpf(90685302083L);
        aluno.setEmail("tedson@bol.com");
        
        return aluno;
    }
    
    protected Bolsa criarBolsaInvalida(){
        Bolsa bolsa = new Bolsa();
        bolsa.setNomeBolsa(
                "PIBICpesquisaintegradoraornamentalsuperioetecnico"
        ); //Nome de bolsa invalido
        bolsa.setTipo("Pesquisa");
        bolsa.setValor(1000000.00); //Valor de bolsa invalido
        
        return bolsa;
    }
    
    protected Emprestimo criarEmprestimoInvalido(){        
        Emprestimo emprestimo = new Emprestimo();
        String statusEmprestimo = ""; //Status invalido
        emprestimo.setStatus(statusEmprestimo);
        
        return emprestimo;
    }
    
    protected Fardamento criarFardamentoInvalido(){
        Fardamento fardamento = new Fardamento();
        fardamento.setQuantidadeEntregue(10); //Quantidade invalida
        return fardamento;
    }
    
    protected Livro criarLivroInvalido(){
        Livro livro = new Livro();
        livro.setTitulo(""); //Titulo invalido
        livro.setAutor(""); //Autor invalido
        livro.setMateria(""); //Materia invalido
        livro.setIsbn(666123123L); //Isbn invalido
        livro.setQuantidade(10);
        
        return livro;
    }
    
    protected Professor criarProfessorInvalido(){
        Professor professor = new Professor();
        professor.setNomeUsuario("");
        professor.setCpf(11122211100L); //Cpf invalido
        professor.setRg(438713588); 
        professor.setDepartamento("AS"); //Depart. invalido
        professor.setRamal(2530L);
        professor.setSiape(306564587); //Siape invalido
        
        return professor;
    }
    
    protected Situacao criarSituacaoInvalida(){        
        Situacao situacao = new Situacao();       
        situacao.setDescricaoSituacao(
                "Entrrega com atraso pelo DAE"
        ); //Descricao invalida
        
        return situacao;
    }
    
    protected Tamanho criarTamanhoInvalido(){
        Tamanho tamanho = new Tamanho();      
        tamanho.setDescricaoTamanho("XGG"); //Tamanho invalida
        
        return tamanho;
    }
    
}

package br.com.ifpe.teste;

import br.com.ifpe.modelo.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;


public class JpqlTest extends GenericTest{
    
//    @Test //Matricula dos alunos com base no servidor de email informado
//    public void matriculaAlunoPorServidorDeEmail() {
//        //logger.info("Executando alunoPorNome()");
//        TypedQuery<Aluno> query = em.createQuery(
//                "SELECT a.matricula FROM Aluno a WHERE a.email LIKE :email",
//                Aluno.class);
//        query.setParameter("email", "%@gmail%");
//        List<Aluno> alunos = query.getResultList();
//
//        for (Aluno aluno : alunos) {
//           assertTrue(aluno.getEmail().contains("@gmail"));
//        }
//
//        assertEquals(7, alunos.size());
//    }
        
    //Uso do MIN e MAX
    @Test
    public void valorMaximoEMinimoDeBolsa() {
        logger.info("Executando valorMaximoEMinimoDeBolsa()");
        Query query = em.createQuery(
                "SELECT MAX(b.valor), MIN(b.valor) FROM Bolsa b"
        );
        Object[] bolsas = (Object[]) query.getSingleResult();

        assertEquals(600.00, bolsas[0]);
        assertEquals(0.00, bolsas[1]);
        
    }
    
    
    //Uso do WHERE BETWWEEN
    @Test
    public void faixaDeValoresDeBolsa() {
        logger.info("Executando faixaDeValoresDeBolsa()");
        Query query = em.createQuery(
                "SELECT b FROM Bolsa b WHERE b.valor BETWEEN ?1 AND ?2",
                Bolsa.class
        );
        query.setParameter(1, 430.00);
        query.setParameter(2, 700.00);
        List<Bolsa> bolsas = query.getResultList();
        
        for (Bolsa bolsa : bolsas) {
           assertTrue(bolsa.getValor() >= 430.00 && bolsa.getValor() <= 700.00);
        }
        
    }
    
    //Uso do NOT NULL
    @Test
    public void volumesNaoNulos() {
        logger.info("Executando volumesNaoNulos()");
        Query query = em.createQuery(
                "SELECT v FROM Volume v WHERE v.descricaoVolume IS NOT NULL",
                Volume.class
        );        
        List<Volume> volumes = query.getResultList();
        
        for (Volume volume : volumes) {
            assertNotNull(volume);
        }
        
    }

    //Uso do IS EMPTY
    @Test
    public void volumes() {
        logger.info("Executando faixaDeValoresDeBolsa()");
        Aluno aluno = em.find(Aluno.class, new Long(2));
        Query query = em.createQuery(
                "SELECT b "+
                "FROM Bolsa b "+
                "WHERE :aluno"+
                "MEMBER OF b",
                Volume.class
        );        
        List<Volume> volumes = query.getResultList();
        
        for (Volume volume : volumes) {
            assertNotNull(volume);
        }
        
    }
    
}

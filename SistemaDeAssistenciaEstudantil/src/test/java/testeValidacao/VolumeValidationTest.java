package testeValidacao;

import br.com.ifpe.modelo.Volume;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import org.junit.Test;


public class VolumeValidationTest extends GenericTest{
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirVolumeInvalido() {
        Volume volume = null;       
        try {
            volume = criarVolumeInvalido();
            em.persist(volume);
            
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                    CoreMatchers.anyOf(                        
                        startsWith("br.com.ifpe.modelo.Volume.descricaoVolume: Volume não pode ser vazio.")
                    )
                );
            }

            //assertEquals(1, constraintViolations.size());            
            throw ex;
        }
    }
}

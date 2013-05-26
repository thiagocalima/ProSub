/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Testes;

import DataMapper.AusenciaJpaController;
import DataMapper.PopulateDB;
import DataMapper.ProfessorJpaController;
import Dominio.Ausencia;
import Dominio.Professor;
import Service.NotificacaoService;
import java.text.ParseException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Leticia
 */
public class NotificacaoServiceTestes {
    
    private NotificacaoService serviceEmTeste;
    private EntityManagerFactory emf;
    private ProfessorJpaController profController;
    
    @BeforeClass
    public static void classSetUp(){
        //emf = Persistence.createEntityManagerFactory("ProSubPU");
        
        
    }
    
    @Before
    public void setUp() {

        emf = Persistence.createEntityManagerFactory("ProSubPU");
        profController = new ProfessorJpaController(emf);
        serviceEmTeste = new NotificacaoService();
        PopulateDB.fullSetupDB();
        
    }
    
    @After
    public void tearDown() {
        PopulateDB.recreateDB();
    }
    // TODO add test methods here.
    // The methods must be anotated with annotation @Test. For example:
    //
     @Test
     public void testeDeveSerPossivelNotificarUmaAusencia() throws ParseException {
         
         List<Professor> professores = profController.findProfessorEntities();
         
         String dataInicio = "20/05/2013";
         String dataFim = "24/05/2013";
         String motivo = "Problemas pessoais";
         
         String codigo = serviceEmTeste.notificarAusencia(professores.get(0).getId(), dataInicio, dataFim, motivo, professores.get(1).getId());
         
         AusenciaJpaController ausController = new AusenciaJpaController(emf);
         
         Ausencia ausencia = ausController.findAusencia(codigo);
         
         Assert.assertNotNull(ausencia);
         
         
         
     }
}
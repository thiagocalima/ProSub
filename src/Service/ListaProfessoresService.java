/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DataMapper.ProfessorJpaController;
import DataMapper.UsuarioJpaController;
import Dominio.Professor;
import Modelos.ProfessorModel;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Rick
 */
public class ListaProfessoresService {
    
   private ProfessorJpaController controller;
    
    public ListaProfessoresService(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProSubPU");
        controller = new ProfessorJpaController(emf);
    }
    
    
    public List<ProfessorModel> ListarProfessores(){
        
        List<Professor> professores = new LinkedList<Professor>(); 
        List<ProfessorModel> modelos = new LinkedList<ProfessorModel>();
        
        professores = controller.findProfessorEntities();
        
        for (Professor prof : professores){
            ProfessorModel model = new ProfessorModel();
            model.Nome = prof.getNome();
            model.id = prof.getId();
            modelos.add(model);
        }
        
        return modelos;
    }
}

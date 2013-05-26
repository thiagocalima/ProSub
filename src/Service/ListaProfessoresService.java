/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DataMapper.ProfessorJpaController;
import Dominio.Professor;
import Modelos.ProfessorModel;
import java.util.List;

/**
 *
 * @author Rick
 */
public class ListaProfessoresService {
    
    private List<ProfessorModel> ListaDeProfessores = null;
    private List<Professor> ListaDeProfessoresDummy = null;
    private ProfessorJpaController ProfJpaController;
    
    
    public List<ProfessorModel> ListarProfessores(){
             
        ListaDeProfessoresDummy = ProfJpaController.findProfessorEntities();
        
        for (Professor prof : ListaDeProfessoresDummy){
            ProfessorModel model = new ProfessorModel();
            model.Nome = prof.getNome();
            model.id = prof.getId();
            ListaDeProfessores.add(model);
        }
        return ListaDeProfessores;
    }
}

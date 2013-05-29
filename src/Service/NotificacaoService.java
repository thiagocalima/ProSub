/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DataMapper.AusenciaJpaController;
import DataMapper.ProfessorJpaController;
import DataMapper.exceptions.NonexistentEntityException;
import Dominio.Aula;
import Dominio.Ausencia;
import Dominio.EstadoAusencia;
import Dominio.Professor;
import Modelos.AusenciaModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Thiago Lima
 */
public class NotificacaoService {
    
    private AusenciaJpaController ausenciaController;
    private ProfessorJpaController profController;
    
    public NotificacaoService(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProSubPU");
        ausenciaController = new AusenciaJpaController(emf);
        periodoController = new PeriodoJpaController(emf);
        profController = new ProfessorJpaController(emf);
    }
    
    public String notificarAusencia(Long idProfessor, String dataInicio, String dataFim, String motivo, Long idProfessorSubstituto) throws ParseException {
        
        Calendar inicio = Calendar.getInstance();
        Calendar fim = Calendar.getInstance();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        inicio.setTime(sdf.parse(dataInicio));
        fim.setTime(sdf.parse(dataFim));
        
        Periodo periodo = new Periodo(inicio, fim);
        
        periodoController.create(periodo);
        
        Professor professor = profController.findProfessor(idProfessor);
        Professor professorSubstituto = profController.findProfessor(idProfessorSubstituto);
        
        Random r = new Random();
        
        String codigo = Integer.toString(r.nextInt(10000));
        
        Ausencia ausencia = new Ausencia(codigo, periodo, professor, motivo);
        ausencia.indicarSubstituto(professorSubstituto);
        
        
        ausenciaController.create(ausencia);
        
        return codigo;
        
    }

    public void editarAusencia(String codigo, String dataInicio, String dataFim, String motivo, Long idSubstituto) throws ParseException, NonexistentEntityException, Exception {
        
        Calendar inicio = Calendar.getInstance();
        Calendar fim = Calendar.getInstance();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        inicio.setTime(sdf.parse(dataInicio));
        fim.setTime(sdf.parse(dataFim));
        
        Periodo periodo = new Periodo(inicio, fim);
        
        periodoController.create(periodo);

        Professor professorSubstituto = profController.findProfessor(idSubstituto);
        
        Random r = new Random();
        
        Ausencia ausencia = ausenciaController.findAusencia(codigo);
        ausencia.setPeriodo(periodo);
        ausencia.setMotivo(motivo);
        ausencia.indicarSubstituto(professorSubstituto);
                
        ausenciaController.edit(ausencia);
    }

    public List<AusenciaModel> listarAusencias() {
        
        List<Ausencia> ausencias = ausenciaController.findAusenciaEntities();
        List<AusenciaModel> modelos = new LinkedList<AusenciaModel>();
        
        for(Ausencia ausencia : ausencias){
            
            AusenciaModel modelo = new AusenciaModel();
            
            modelo.codigo = ausencia.getCodigo();
            modelo.professorAusente = ausencia.getProfessor().getNome();
            modelo.professorSubstituto = ausencia.getIndicacaoSubstituto().getNome();
            modelo.estado = this.determinarEstado(ausencia.getEstado());
            modelo.id = ausencia.getId();
            Periodo p = ausencia.getPeriodo();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            modelo.dataInicio = sdf.format(p.getLimiteInferior().getTime());
            modelo.dataInicio = sdf.format(p.getLimiteSuperior().getTime());
            
            modelos.add(modelo);
        }
        
        return modelos;
    }
    
    private String determinarEstado(EstadoAusencia estado){
        if(estado == EstadoAusencia.Alocacao_Cancelada)
            return "Alocação cancelada";
        else if(estado == EstadoAusencia.Alocacao_Efetuada)
            return "Alocação efetuada";
        else if(estado == EstadoAusencia.Alocacao_Pendente)
            return "Alocação pendente";
        else{
            return "Aulas canceladas";
        }
    }
    

}

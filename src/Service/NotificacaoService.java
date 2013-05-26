/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DataMapper.AusenciaJpaController;
import DataMapper.PeriodoJpaController;
import DataMapper.ProfessorJpaController;
import DataMapper.exceptions.NonexistentEntityException;
import Dominio.Aula;
import Dominio.Ausencia;
import Dominio.Periodo;
import Dominio.Professor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Thiago Lima
 */
public class NotificacaoService {
    
    private AusenciaJpaController ausenciaController;
    private PeriodoJpaController periodoController;
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
    
//    public void EditarNotificacao(Professor professorAusente, Professor professorSubstituto, Calendar dataInicio, Calendar dataFim, Aula aulaInicio, Aula aulaFim, String motivo, Long id) throws NonexistentEntityException, Exception {
//        periodo = new Periodo(dataInicio, dataFim);
//        ausencia = new Ausencia(periodo, professorAusente, motivo);
//        
//        ausencia.setId(id);
//        
//        ausenciaJpaController.edit(ausencia);
//    }

}

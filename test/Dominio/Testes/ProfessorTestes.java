/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio.Testes;

import Dominio.Aula;
import Dominio.AulaComparator;
import Dominio.Periodo;
import Dominio.Professor;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leticia
 */
public class ProfessorTestes {
    
    private Professor objetoEmTeste;
    private Periodo periodo;
    private String nome;
    private List<Aula> aulas;
    
    private Periodo p1;
    private Periodo p2;
    private Periodo p3;
    private Periodo p4;
    private Periodo p5;
    
    private Aula aula1;
    private Aula aula2;
    private Aula aula3;
    private Aula aula4;
    private Aula aula5;
    
    public ProfessorTestes() {
    }
    
    @Before
    public void setUp() {
        
        nome = "ariovaldson";
        aula1 = EasyMock.createMock(Aula.class);
        aula2 = EasyMock.createMock(Aula.class);
        aula3 = EasyMock.createMock(Aula.class);
        aula4 = EasyMock.createMock(Aula.class);
        aula5 = EasyMock.createMock(Aula.class);
            
        objetoEmTeste = new Professor(nome);
        
        Calendar limiteInfP1 = Calendar.getInstance();
        Calendar limiteSupP1 = Calendar.getInstance();
        limiteInfP1.set(0, 0, 0, 22, 40);
        limiteSupP1.set(0, 0, 0, 23, 59);
        
        
         p1 = new Periodo(limiteInfP1, limiteSupP1);
        
        Calendar limiteInfP2 = Calendar.getInstance();
        Calendar limiteSupP2 = Calendar.getInstance();
        
        limiteInfP2.set(0, 0, 0, 20, 40);
        limiteSupP2.set(0, 0, 0, 21, 40);
        
        p2 = new Periodo(limiteInfP2, limiteSupP2);
        
        Calendar limiteInfP3 = Calendar.getInstance();
        Calendar limiteSupP3 = Calendar.getInstance();
        
        limiteInfP3.set(0, 0, 0, 19, 45);
        limiteSupP3.set(0, 0, 0, 20, 59);
                
        p3 = new Periodo(limiteInfP3, limiteSupP3);
        
        
        Calendar limiteInfP4 = Calendar.getInstance();
        Calendar limiteSupP4 = Calendar.getInstance();
        
        limiteInfP4.set(0, 0, 0, 19, 15);
        limiteSupP4.set(0, 0, 0, 20, 00);
        
        p4 = new Periodo(limiteInfP4, limiteSupP4);
        
        Calendar limiteInfP5 = Calendar.getInstance();
        Calendar limiteSupP5 = Calendar.getInstance();
        
        limiteInfP5.set(0, 0, 0, 19, 25);
        limiteSupP5.set(0, 0, 0, 20, 30);
        
        p5 = new Periodo(limiteInfP5, limiteSupP5);
        
        
        EasyMock.expect(aula1.getPeriodo()).andReturn(p1);
        EasyMock.expect(aula2.getPeriodo()).andReturn(p2);
        EasyMock.expect(aula3.getPeriodo()).andReturn(p3);
        EasyMock.expect(aula4.getPeriodo()).andReturn(p4);
        EasyMock.expect(aula5.getPeriodo()).andReturn(p5);
        
        EasyMock.expect(aula1.getDiaDaSemana()).andReturn(Calendar.MONDAY);
        EasyMock.expect(aula2.getDiaDaSemana()).andReturn(Calendar.TUESDAY);
        EasyMock.expect(aula3.getDiaDaSemana()).andReturn(Calendar.WEDNESDAY);
        EasyMock.expect(aula4.getDiaDaSemana()).andReturn(Calendar.THURSDAY);
        EasyMock.expect(aula5.getDiaDaSemana()).andReturn(Calendar.THURSDAY);
        
        aulas = new LinkedList<Aula>();
        
        aulas.add(aula1);
        aulas.add(aula2);
        aulas.add(aula3);
        aulas.add(aula4);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    @Test
    public void testeDeveSerInicializadoComNome() {
        
       
        Assert.assertEquals(objetoEmTeste.getNome(), nome);
    }

    
    @Test
    public void testeDeveSerPossivelAdicionarUmaAulaNaGradeDoProfessor(){
        objetoEmTeste.adicionarAula(aula1);
        
        Assert.assertEquals(objetoEmTeste.getGrade().size(), 1);
        Assert.assertEquals(objetoEmTeste.getGrade().toArray()[0], aula1);
    }
    
    @Test 
    public void testeDeveVerificarCompatibilidadeEntreProfessorEAulas(){
        
        objetoEmTeste.adicionarAula(aula5);
        
        Collections.sort(aulas, new AulaComparator());

        Assert.assertTrue(objetoEmTeste.EhCompativelCom(aulas));
    }
    
    
   
}
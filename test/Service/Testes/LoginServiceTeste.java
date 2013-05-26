/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Testes;

import DataMapper.UsuarioJpaController;
import Dominio.Usuario;
import Service.LoginService;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.Assert;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rick
 */
public class LoginServiceTeste {
    
    Usuario user;
    Usuario user2;
    Usuario user3;
    Usuario user4;
    Usuario user5;
    Usuario user6;
    LoginService LgnService = null;
    List<Usuario> ListaUser;
    
    
    public LoginServiceTeste() {
    }
    
    @Before
    public void setUp() {
        
        
        
        
        user2 = new Usuario("Calebe2","1234566");
        user3 = new Usuario("Calebe3","1234566");
        user4 = new Usuario("Calebe4","1234566");
        user5 = new Usuario("Calebe5","1234566");
        user6 = new Usuario("Calebe","123456");
        

    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void ValidaUsuarioeSenha(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProSubPU");
        UsuarioJpaController UserController = new UsuarioJpaController(emf);
        //UserController.create(user6);
        user = new Usuario("Calebe", "123456");
        System.err.print(user);
       //if (LgnService.VerificarUsuarioESenha(user.getUsuario(), user.getSenha())){
           //Assert.assertTrue(true);
       //}else{
           //Assert.assertFalse(true);
       //}
    }
    
}
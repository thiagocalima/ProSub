/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Dominio.Usuario;
import Modelos.UsuarioModel;
import DataMapper.UsuarioJpaController;
import DataMapper.exceptions.NonexistentEntityException;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.joda.time.Interval;

/**
 *
 * @author Rick
 */
public class LoginService {
    
    private UsuarioJpaController controller;
    
    public LoginService(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProSubPU");
        controller = new UsuarioJpaController(emf);
    }
    
    public boolean VerificarUsuarioESenha(String usuario, String senha){

        List<Usuario> ListaDeUsuario = new LinkedList<Usuario>();
        
        ListaDeUsuario = controller.findUsuarioEntities();
        
        for (Usuario user : ListaDeUsuario){
            if(user.getUsuario().equals(usuario) && user.getSenha().equals(senha))
            {
                return true;
            }
        }
        return false;
    }
    
}

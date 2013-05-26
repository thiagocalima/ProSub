/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Dominio.Usuario;
import Modelos.UsuarioModel;
import DataMapper.UsuarioJpaController;
import DataMapper.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Rick
 */
public class LoginService {
    
    
    private Usuario LoginInformation;
    private List<Usuario> ListaDeUsuario = null;
    private UsuarioJpaController UserJpaController;
    
    public boolean VerificarUsuarioESenha(String usuario, String senha){
        
        LoginInformation.setUsuario(usuario);
        LoginInformation.setSenha(senha);
        
        ListaDeUsuario = UserJpaController.findUsuarioEntities();
        
        for (Usuario user : ListaDeUsuario){
            if(user.getUsuario() == LoginInformation.getUsuario()){
                if(user.getSenha()== LoginInformation.getSenha()){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }

        }
        return true;
        
        
        
    }
    
}

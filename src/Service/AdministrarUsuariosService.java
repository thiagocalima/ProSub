/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DataMapper.UsuarioJpaController;
import DataMapper.exceptions.NonexistentEntityException;
import Dominio.Usuario;
import Modelos.UsuarioModel;
import java.util.List;

/**
 *
 * @author Rick
 */
public class AdministrarUsuariosService {
    
    private Usuario user;
    private List<UsuarioModel> ListaDeUsuarios = null;
    private List<Usuario> ListaDeUsuarioDummy = null;
    private UsuarioJpaController UserJpaController;
    
    
    public AdministrarUsuariosService(){
        
    }
    
    public void SalvarUsuario(String Nome, String Senha, int profile){
        user = new Usuario();
        user.setSenha(Senha);
        user.setPermissao(profile);
        UserJpaController.create(user);
    }
    
    public void EditarUsuario(String Nome, String Senha, int profile, Long id) throws NonexistentEntityException, Exception{
        user = new Usuario();
        user.setUsuario(Nome);
        user.setSenha(Senha);
        user.setPermissao(profile);
        user.setId(id);
        UserJpaController.edit(user);
    }
    
    public List<UsuarioModel> ListarUsuarios(){
        
        ListaDeUsuarioDummy = UserJpaController.findUsuarioEntities();
        
        for (Usuario usuario : ListaDeUsuarioDummy){
            UsuarioModel model = new UsuarioModel();
            model.Usuario = usuario.getUsuario();
            model.Senha = usuario.getSenha();
            model.profile = usuario.getPermissao();
            model.id = usuario.getId();
            ListaDeUsuarios.add(model);
        }
        return ListaDeUsuarios;
        
        
    }





    
}

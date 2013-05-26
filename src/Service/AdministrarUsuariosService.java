/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DataMapper.UsuarioJpaController;
import DataMapper.exceptions.NonexistentEntityException;
import Dominio.Usuario;
import Modelos.UsuarioModel;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Rick
 */
public class AdministrarUsuariosService {
    
   //private UsuarioJpaController controller;
    private EntityManagerFactory emf;
    
    public AdministrarUsuariosService(){
        
        emf = Persistence.createEntityManagerFactory("ProSubPU");
        //controller = new UsuarioJpaController(emf);
    }
    
    public void SalvarUsuario(String Nome, String Senha, int profile){
        UsuarioJpaController controller = new UsuarioJpaController(emf);
        Usuario usuario = new Usuario(Nome);
        usuario.setSenha(Senha);
        usuario.setPermissao(profile);
        controller.create(usuario);
    }
    
    public void EditarUsuario(String senha, int profile, Long id) throws NonexistentEntityException, Exception{
        
        UsuarioJpaController controller = new UsuarioJpaController(emf);
        Usuario usuarioAEditar = controller.findUsuario(id);
        
        usuarioAEditar.setUsuario(usuarioAEditar.getUsuario());
        usuarioAEditar.setSenha(senha);
        usuarioAEditar.setPermissao(profile);
        
        controller.edit(usuarioAEditar);
    }
    
    public List<UsuarioModel> ListarUsuarios(){
        
        UsuarioJpaController controller = new UsuarioJpaController(emf);
        List<Usuario> usuarios = controller.findUsuarioEntities();
        List<UsuarioModel> modelos = new LinkedList<UsuarioModel>();
        
        for (Usuario usuario : usuarios){
            UsuarioModel model = new UsuarioModel();
            model.Usuario = usuario.getUsuario();
            model.Senha = usuario.getSenha();
            model.profile = usuario.getPermissao();
            model.id = usuario.getId();
            modelos.add(model);
        }
        return modelos;   
    }





    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio.Testes;

import Dominio.Usuario;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rick
 */
public class UsuarioTeste {

    private Usuario User;
    private int profile;
    private String Usuario;
    private String Senha;
    
    public UsuarioTeste() {
    }
    
    @Before
    public void setUp() {
        
        User = new Usuario("Calebe");
        profile = 1;
        Usuario = "Calebe";
        Senha = "123456";
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
    public void ValidaSenhaOk(){
        Assert.assertEquals(User.getSenha(), Senha);
        //Alterando senha para teste negativo
        this.Senha = "1234567";
        Assert.assertNotSame(User.getSenha(), this.Senha);
    }
   
    
    @Test
    public void ValidaPermissao(){
        Assert.assertEquals(User.getPermissao(), profile);
        //Alterando profile para teste negativo
        this.profile = 2;
        Assert.assertNotSame(User.getPermissao(), this.profile);
    }
    
    @Test
    public void UsuarioNaoVazio(){
        Assert.assertNotNull(User.getUsuario());
        this.Usuario = null;
        Assert.assertNotSame(User.getUsuario(), this.Usuario);
    }
    
}
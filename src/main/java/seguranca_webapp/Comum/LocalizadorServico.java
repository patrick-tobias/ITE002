package seguranca_webapp.Comum;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import seguranca_webapp.Usuario.Dominio.AutenticarUsuario;
import seguranca_webapp.Usuario.Dominio.AutenticarUsuarioPadrao;
import seguranca_webapp.Usuario.Dominio.UsuarioRepositorio;
import seguranca_webapp.Usuario.Persistencia.UsuarioRepositorioJPA;

public class LocalizadorServico {
	
	public static EntityManager getEntityManager() {
        return Persistence
                .createEntityManagerFactory("SegurancaPU")
                .createEntityManager();
    }
    
    public static AutenticarUsuario autenticarUsuario() {
        return new AutenticarUsuarioPadrao(usuarioRepositorio());
    }
    
    public static UsuarioRepositorio usuarioRepositorio() {
        return new UsuarioRepositorioJPA(getEntityManager());
    }
    
}

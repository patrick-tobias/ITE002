package seguranca_webapp.Usuario.Dominio;

public interface AutenticarUsuario {
    
    boolean executar(String email, String senha);
    
}

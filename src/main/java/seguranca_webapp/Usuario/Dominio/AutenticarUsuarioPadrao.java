package seguranca_webapp.Usuario.Dominio;

import java.util.Optional;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class AutenticarUsuarioPadrao implements AutenticarUsuario {

    private final UsuarioRepositorio usuarioRepositorio;

    public AutenticarUsuarioPadrao(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }
    
    @Override
    public boolean executar(String email, String senha) {
    	Optional<Usuario> opUsuario = usuarioRepositorio.consultarPorEmail(email);
    	if(!opUsuario.isPresent()) {
    		return false;
    	}
      
    	Usuario usuario = opUsuario.get();
    	BCrypt.Result senhaVerificada = BCrypt.verifyer().verify(senha.toCharArray(), usuario.getSenha());
    	
    	return usuario.getEmail().equals(email) && senhaVerificada.verified;
    }
    
}

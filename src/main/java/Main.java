import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.persistence.EntityManager;
import seguranca_webapp.Comum.LocalizadorServico;
import seguranca_webapp.Usuario.Dominio.Usuario;

public class Main {
	
	public static void main(String[] args) {
		String email = "odileiafdias@fatec.sp.gov.br";
		String senha = "senha1234";
		
		String senhaCriptografada = BCrypt.withDefaults().hashToString(12, senha.toCharArray());
		
		Usuario usuario1 = new Usuario(email,senhaCriptografada);
		
		
		
		EntityManager entityManager = LocalizadorServico.getEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(usuario1);
		entityManager.getTransaction().commit();
	}
}

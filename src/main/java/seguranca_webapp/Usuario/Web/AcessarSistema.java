package seguranca_webapp.Usuario.Web;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import seguranca_webapp.Comum.LocalizadorServico;
import seguranca_webapp.Usuario.Dominio.AutenticarUsuario;

@WebServlet("/acessar")
public class AcessarSistema extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	if(!validarRecaptcha(request,response)) {
    		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index.jsp?recaptcha_invalido=true"));
    		return;
    	}
    	
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        AutenticarUsuario autenticarUsuario = LocalizadorServico.autenticarUsuario();
        if(autenticarUsuario.executar(email, senha)) {
            request.getSession().setAttribute("AUTENTICADO", email);
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/restrito/principal.jsp"));
        } else {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index.jsp?invalido=true"));
        }
    }
    
    private boolean validarRecaptcha(HttpServletRequest request, HttpServletResponse response) {
    	String recaptchaSecret = "6LeSugcpAAAAAGycJpMcj_JmU50_EL8gC-T1UDhj";
        String recaptchaResponse = request.getParameter("g-recaptcha-response");
        
        Map<String, String> parametros = new HashMap<>();
        parametros.put("response", recaptchaResponse);
        parametros.put("secret", recaptchaSecret);
    	
        String parametrosCodificados = parametros.entrySet()
        	    .stream()
        	    .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
        	    .collect(Collectors.joining("&"));
    	
    	HttpClient httpClient = HttpClient.newHttpClient();
    	HttpRequest httpRequest = HttpRequest.newBuilder()
    			.uri(URI.create("https://www.google.com/recaptcha/api/siteverify"))
    			.headers("Content-Type", "application/x-www-form-urlencoded")
    			.POST(BodyPublishers.ofString(parametrosCodificados))
    			.build();
    	try {
			HttpResponse<String> resposta = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			JSONObject json = new JSONObject(resposta.body().toString());
			return (boolean) json.get("success");
			
		} catch (Exception e) {
			return false;
		}
    }

}

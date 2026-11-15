package contratos;

import java.util.List;
import modelo.Usuario;

public interface Gerenciar {
    Usuario buscarRegistro (String parametro);
    List<Usuario> listarTudo(); // retorna todos os usuarios cadastrados


}

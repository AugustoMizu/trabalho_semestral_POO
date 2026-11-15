package modelo;

import contratos.Gerenciar;

import java.util.List;

public class Administrador extends Usuario implements Gerenciar {
    private List<Usuario> usuarios;

    public Administrador(int id, String nome, String cep, String endereco,
                         List<Usuario> usuarios) {
        super (id,nome,cep,endereco);
        this.usuarios = usuarios;
    }

    @Override
    public Usuario buscarRegistro(String parametro) {
        return null;
    }

    @Override
    public List<Usuario> listarTudo() {
        return usuarios;
    }

    @Override
    public void cadastrar() {

    }

    @Override
    public void excluirConta() {

    }
}

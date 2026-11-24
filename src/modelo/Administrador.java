package modelo;

import contratos.Gerenciar;

import java.util.List;

public class Administrador extends Usuario implements Gerenciar {
    private List<Usuario> usuarios;

    public Administrador(int id, String nome, String cep, String endereco,
                         List<Usuario> usuarios) {
        super(id, nome, cep, endereco);
        this.usuarios = usuarios;
    }

    @Override
    public Usuario buscarRegistro(String parametro) {
        for (Usuario u : usuarios) {
            if (String.valueOf(u.getId()).equals(parametro) || u.getNome().equalsIgnoreCase(parametro)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public List<Usuario> listarTudo() {
        return usuarios;
    }

    @Override
    public void cadastrar() {
        System.out.println("Conta do usuario cadastrada.");
    }

    @Override
    public void excluirConta() {
        System.out.println("Conta do usuário excluida.");
    }

    // inclui a conta de um usuário (Comprador ou Vendedor) na lista
    public void incluirUsuario(Usuario novo) {
        usuarios.add(novo);
        System.out.println("Conta cadastrada: " + novo.getNome());
    }

    // exclui por id ou nome; o admin não pode se excluir
    public boolean excluirUsuario(String parametro) {
        Usuario alvo = buscarRegistro(parametro);
        if (alvo == null || alvo == this) {
            return false;
        }
        usuarios.remove(alvo);
        return true;
    }
}
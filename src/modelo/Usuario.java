package modelo;

public abstract class Usuario {
    public Usuario(int id, String nome, String cep, String endereco) {
    }

    public abstract void cadastrar();
    public abstract void excluirConta();
}

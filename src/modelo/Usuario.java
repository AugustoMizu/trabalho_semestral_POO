package modelo;

public abstract class Usuario {

    private int id;
    private String nome;
    private Endereco endereco;

    public Usuario(int id, String nome, String cep, String endereco) {
    	this.id = id;
    	this.nome = nome;
    	this. endereco = new Endereco(cep, endereco);
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }
    
    public boolean alterarEndereco(Usuario user, Endereco endereco) {
    	if (user.getId() != this.id) {
			return false;
		}
    	this.endereco.setCep(endereco.getCep());
    	this.endereco.setEndereco(endereco.getEndereco());
    	return true;
    }

    public abstract void cadastrar();
    public abstract void excluirConta();
}

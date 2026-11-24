package modelo;

import java.util.ArrayList;
//ALTERADO: Vendedor não extendia usuario
public class Vendedor extends Usuario {
	private String cnpj;
	private ArrayList<Estoque> estoques = new ArrayList<>();
	private Boolean contaAtiva = true;

  //  ALTERADO: o construtor precisa chamar super(id, nome, cep, endereco) da Usuario
    public Vendedor(int id, String nome, String cep, String endereco, String cnpj) {
        super(id, nome, cep, endereco);
        this.cnpj = cnpj;
    }

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public ArrayList<Estoque> getEstoques() {
		return estoques;
	}

	public void setEstoques(ArrayList<Estoque> estoques) {
        // EM CASOS DE NULL
        this.estoques = (estoques != null) ? estoques : new ArrayList<>();
	}

    // ALTERADO: cadastrar() é abstrato na Usuario, então precisa ser implementado
    @Override
    public void cadastrar() {
        System.out.println("Conta cadastrada com sucesso!");

    }

    public void excluirConta() {
		contaAtiva = false;
		estoques.clear();
		System.out.println("Conta excluida com sucesso!");
	}
// ALTERADO: Já esxiste na usuario
	//public void alterarEndereco(String novoEndereco) {
	//	this.endereco = novoEndereco;
	//	System.out.println("Endereco alterado com sucesso!");
	//}

	public void adicionarEstoque(Estoque estoque) {
		estoques.add(estoque);
		System.out.println("Estoque adicionado com sucesso!");
	}

	public void excluirEstoque(Estoque estoque) {
		if (estoques.remove(estoque)) {
			System.out.println("Estoque removido com sucesso!");
		} else {
			System.out.println("Estoque não encontrado!");
		}
	}
}

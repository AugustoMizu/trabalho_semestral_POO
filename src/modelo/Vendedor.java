package modelo;

import java.util.ArrayList;

public class Vendedor {
	private int cnpj;
	private String endereco;
	private ArrayList<Estoque> estoques;
	private Boolean contaAtiva = true;

	public Vendedor(int cnpj, String endereco, ArrayList<Estoque> estoques, Boolean contaAtiva) {
		super();
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.estoques = estoques;
		this.contaAtiva = contaAtiva;
	}

	public int getCnpj() {
		return cnpj;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public ArrayList<Estoque> getEstoques() {
		return estoques;
	}

	public void setEstoques(ArrayList<Estoque> estoques) {
		this.estoques = estoques;
	}

	public void excluirConta() {
		contaAtiva = false;
		estoques.clear();
		System.out.println("Conta excluida com sucesso!");
	}

	public void alterarEndereco(String novoEndereco) {
		this.endereco = novoEndereco;
		System.out.println("Endereco alterado com sucesso!");
	}

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

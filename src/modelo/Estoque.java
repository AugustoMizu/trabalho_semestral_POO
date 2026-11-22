package modelo;

public class Estoque {
	private String nome;
	private String tipo;
	private Integer qtd;
	private Double valor;

	public Estoque(String nome, String tipo, Integer qtd, Double valor) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.qtd = qtd;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public void reduzirEstoque(int qtd) {
		if (qtd > 0 && qtd <= this.qtd) {
			this.qtd = this.qtd - qtd;
			System.out.println("Estoque reduzido com sucesso!");
		} else {
			System.out.println("Quantidade indisponível no estoque!");
		}
	}
}
package modelo;

import contratos.PagamentoOnline;

public class Comprador extends Usuario {

	private Integer cpf;
	private Double totalPagar;
	private PagamentoOnline pagamento;

	public Comprador(int id, String nome, String cep, String endereco, Integer cpf) {
		super(id, nome, cep, endereco);
		this.cpf = cpf;
		this.totalPagar = 0.0;
		this.pagamento = null;
	}

	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	public Double getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(Double totalPagar) {
		this.totalPagar = totalPagar;
	}	
	

	public PagamentoOnline getPagamento() {
		return pagamento;
	}

	public void setPagamento(PagamentoOnline pagamento) {
		this.pagamento = pagamento;
	}

	@Override
	public void cadastrar() {
		System.out.println("Conta cadastrada com sucesso!");
	}

	@Override
	public void excluirConta() {
		System.out.println("Conta excluida com sucesso!");

	}	
	
	// reduz o estoque e acumula o valor em totalPagar
	public boolean adicionarCarrinho(Estoque estoque, int qtd) {
	if (qtd <= estoque.getQuantidade()) {
		estoque.reduzirEstoque(qtd);
		totalPagar += estoque.getValor() * qtd;
		System.out.println("Adicionado ao carrinho " + qtd + " unidades.");
		return true;
	}
	System.out.println("Quantidade superior ao estoque do produto!");
	return false;	
	}
	
	public boolean pagarCarrinho() {
		if (this.totalPagar > 0) {
			System.out.println("Pagamento realizado!");
			return pagamento.pagar(totalPagar);
		}
		System.out.println("Não há valor à pagar! Adicione algo ao carrinho");
		return false;
	}

}

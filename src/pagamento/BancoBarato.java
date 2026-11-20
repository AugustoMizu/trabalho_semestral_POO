package pagamento;

import contratos.PagamentoOnline;

public class BancoBarato implements PagamentoOnline {
	private static final Double taxaServico = 0.2;

	@Override
	public boolean pagar(Double valor) {
		valor += (taxaServico * valor);
		return true;
	}

}

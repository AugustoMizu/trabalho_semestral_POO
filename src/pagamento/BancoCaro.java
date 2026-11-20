package pagamento;

import contratos.PagamentoOnline;

public class BancoCaro implements PagamentoOnline {
	private static final Double taxaServico = 0.5;
	
	@Override
	public boolean pagar(Double valor) {	
		valor += (taxaServico * valor);
		return true;
	}

}

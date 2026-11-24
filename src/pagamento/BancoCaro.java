package pagamento;

import contratos.PagamentoOnline;

public class BancoCaro implements PagamentoOnline {
	private static final Double taxaServico = 0.5;
	
	@Override
	public boolean pagar(Double valor) {
        double total = valor + (valor * taxaServico);
        System.out.println("Pagando via Banco Caro com taxa de " + (taxaServico * 100) + "%. Total: " + total);
		return true;
	}

}

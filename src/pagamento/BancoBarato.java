package pagamento;

import contratos.PagamentoOnline;

public class BancoBarato implements PagamentoOnline {
	private static final Double taxaServico = 0.2;

    // ALTERADO: Pelo diagrama, pagar() deve imprimir um texto com o valor + taxaServico
	@Override
	public boolean pagar(Double valor) {
		double total = valor +(valor*taxaServico);
        System.out.println("Pagando via Banco Barato com taxa de " + (taxaServico * 100) + "%. Total: " + total);
		return true;
	}

}

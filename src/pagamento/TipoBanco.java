package pagamento;

import java.util.function.Supplier;

import contratos.PagamentoOnline;

public enum TipoBanco implements PagamentoOnline {
    BARATO("Banco Barato", 0.2, BancoBarato::new), 
    CARO("Banco Caro", 0.5, BancoCaro::new); 	

    private final String nome;
    private final double taxaServico;
    private final Supplier<PagamentoOnline> fabrica;
    
    TipoBanco(String nome, double taxaServico, Supplier<PagamentoOnline> fabrica) {
        this.nome = nome;
        this.taxaServico = taxaServico;
        this.fabrica = fabrica;
    }

    @Override
    public boolean pagar(Double valor) {
        double total = valor + (valor * this.taxaServico);
        System.out.println("Pagando via '" + this.nome + "' com taxa de " + (this.taxaServico * 100) + "%. Total: " + total);
        return true;
    }
    
    public PagamentoOnline criarInstancia() {
        return fabrica.get();
    }
}
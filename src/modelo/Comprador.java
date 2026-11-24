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

    // ALTERADO - Victoria
    // Motivo: Estoque não tem getQuantidade(), o getter se chama getQtd()
    // Também passou a rejeitar qtd <= 0, que antes entrava e reduzia/cobrava valor errado.
    public boolean adicionarCarrinho(Estoque estoque, int qtd) {
        if (qtd > 0 && qtd <= estoque.getQtd()) {
            estoque.reduzirEstoque(qtd);
            totalPagar += estoque.getValor() * qtd;
            System.out.println("Adicionado ao carrinho " + qtd + " unidades.");
            return true;
        }
        System.out.println("Quantidade inválida ou superior ao estoque do produto!");
        return false;
    }


    // ALTERADO
    //pagamento == null dava NullPointerException; (2) "Pagamento realizado!"
    // era impresso antes de chamar pagar(), mesmo se falhasse; (3) totalPagar não era
    // zerado, então um segundo pagamento cobrava tudo de novo.
    public boolean pagarCarrinho() {
        if (totalPagar <= 0) {
            System.out.println("Não há valor à pagar! Adicione algo ao carrinho");
            return false;
        }
        if (pagamento == null) {
            System.out.println("Escolha uma forma de pagamento.");
            return false;
        }
        if (pagamento.pagar(totalPagar)) {
            System.out.println("Pagamento realizado!");
            totalPagar = 0.0;
            return true;
        }
        return false;
    }
}
package aplicacao;

import modelo.Administrador;
import modelo.Comprador;
import modelo.Endereco;
import modelo.Estoque;
import modelo.Usuario;
import modelo.Vendedor;
import pagamento.TipoBanco;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Usuario> usuarios = new ArrayList<>();
        // Login no menu do ADM
        Administrador admin = new Administrador(1, "Admin", "00000-0000", "Sede", usuarios);
        usuarios.add(admin);

        int opcao;
        do {
            exibeMenuInicial();
            opcao = lerInt("Escolha: ");

            switch (opcao) {
                case 1 -> cadastrarConta(null, usuarios);
                case 2 -> fazerLogin(usuarios);
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opcao inválida.");
            }
        } while (opcao != 0);
    }

    // MENU INICIAL
    private static void exibeMenuInicial() {
        System.out.println("\n⊹₊˚‧︵₊୨ᰔ୧₊˚₊⊹ SISTEMA DE REGISTROS ⊹₊˚‧︵₊୨ᰔ୧₊₊⊹");
        System.out.println("1 - Cadastrar conta");
        System.out.println("2 - Fazer login");
        System.out.println("0 - Sair");
    }

    private static void fazerLogin(List<Usuario> usuarios) {
        int id = lerInt("Digite seu id: ");

        Usuario logado = null;
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                logado = u;
                break;
            }
        }

        if (logado == null) {
            System.out.println("Usuario não encontrado.");
        } else if (logado instanceof Administrador adm) {
            menuAdministrador(adm, usuarios);
        } else if (logado instanceof Comprador c) {
            menuComprador(c, usuarios);
        } else if (logado instanceof Vendedor v) {
            menuVendedor(v, usuarios);
        }
    }

    // ================= CADASTRO =================
    private static void cadastrarConta(Administrador admin, List<Usuario> usuarios) {
        System.out.println("1 - Comprador");
        System.out.println("2 - Vendedor");
        int tipo = lerInt("Escolha: ");
        if (tipo != 1 && tipo != 2) {
            System.out.println("Opcao inválida.");
            return;
        }

        int id = lerInt("Id: ");
        if (id <= 0) {
            System.out.println("Id inválido.");
            return;
        }
        if (idExiste(usuarios, id)) {
            System.out.println("Já existe um usuário com esse id.");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CEP: ");
        String cep = scanner.nextLine();
        System.out.print("Endereço: ");
        String end = scanner.nextLine();

        Usuario novo;
        if (tipo == 1) {
            novo = new Comprador(id, nome, cep, end, lerInt("CPF: "));
        } else {
            novo = new Vendedor(id, nome, cep, end, lerString("CNPJ: "));
        }

        if (admin != null) {
            admin.incluirUsuario(novo);
        } else {
            usuarios.add(novo);
            System.out.println("Conta cadastrada: " + novo.getNome());
        }
    }

    private static String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();

    }


    private static boolean idExiste(List<Usuario> usuarios, int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) return true;
        }
        return false;
    }

    // ================= MENU ADMINISTRADOR =================
    private static void exibeMenuAdministrador() {
        System.out.println("\n===== MENU ADMINISTRADOR =====");
        System.out.println("1 - Incluir conta");
        System.out.println("2 - Excluir conta");
        System.out.println("3 - Listar tudo");
        System.out.println("4 - Buscar");
        System.out.println("5 - Alterar endereço");
        System.out.println("0 - Voltar ao menu");
    }

    private static void menuAdministrador(Administrador admin, List<Usuario> usuarios) {
        int opcao;
        do {
            exibeMenuAdministrador();
            opcao = lerInt("Escolha: ");

            switch (opcao) {
                case 1 -> cadastrarConta(admin, usuarios);
                case 2 -> {
                    System.out.print("Id ou nome da conta a excluir: ");
                    if (admin.excluirUsuario(scanner.nextLine().trim())) {
                        System.out.println("Conta excluída.");
                    } else {
                        System.out.println("Conta não encontrada (ou não pode ser excluída).");
                    }
                }
                case 3 -> {
                    List<Usuario> todos = admin.listarTudo();
                    if (todos.isEmpty()) {
                        System.out.println("Nenhum registro.");
                    } else {
                        for (Usuario u : todos) {
                            System.out.println(u.getId() + " - " + u.getNome());
                        }
                    }
                }
                case 4 -> {
                    System.out.print("Buscar por id ou nome: ");
                    Usuario achado = admin.buscarRegistro(scanner.nextLine().trim());
                    if (achado == null) {
                        System.out.println("Não encontrado.");
                    } else {
                        System.out.println(achado.getId() + " - " + achado.getNome());
                    }
                }
                case 5 -> alterarEndereco(admin);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opcao inválida.");
            }
        } while (opcao != 0);
    }

    // ================= MENU COMPRADOR =================
    private static void exibeMenuComprador() {
        System.out.println("\n===== MENU COMPRADOR =====");
        System.out.println("1 - Ver estoque com vendedor");
        System.out.println("2 - Pagar");
        System.out.println("3 - Alterar endereço");
        System.out.println("4 - Excluir conta");
        System.out.println("0 - Voltar ao menu");
    }

    private static void menuComprador(Comprador comprador, List<Usuario> usuarios) {
        int opcao;
        do {
            exibeMenuComprador();
            opcao = lerInt("Escolha: ");

            switch (opcao) {
                case 1 -> verEstoque(comprador, usuarios);
                case 2 -> {
                    if (comprador.getPagamento() == null) {
                        escolherPagamento(comprador);
                    }
                    if (comprador.getPagamento() != null) {
                        comprador.pagarCarrinho();
                    }
                }
                case 3 -> alterarEndereco(comprador);
                case 4 -> {
                    comprador.excluirConta();
                    usuarios.remove(comprador);
                    return;
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opcao inválida.");
            }
        } while (opcao != 0);
    }

    private static void verEstoque(Comprador comprador, List<Usuario> usuarios) {
        List<Vendedor> vendedores = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u instanceof Vendedor v) vendedores.add(v);
        }
        if (vendedores.isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado.");
            return;
        }

        for (int i = 0; i < vendedores.size(); i++) {
            System.out.println((i + 1) + " - " + vendedores.get(i).getNome());
        }
        int v = lerInt("Escolha o vendedor: ") - 1;
        if (v < 0 || v >= vendedores.size()) {
            System.out.println("Vendedor inválido.");
            return;
        }

        Vendedor vendedor = vendedores.get(v);
        listarEstoque(vendedor);
        if (vendedor.getEstoques().isEmpty()) return;

        int item = lerInt("Item para adicionar ao carrinho (0 para voltar): ") - 1;
        if (item < 0 || item >= vendedor.getEstoques().size()) return;

        int qtd = lerInt("Quantidade: ");
        if (qtd > 0) {
            comprador.adicionarCarrinho(vendedor.getEstoques().get(item), qtd);
        } else {
            System.out.println("Quantidade inválida.");
        }
    }

    private static void escolherPagamento(Comprador comprador) {
        TipoBanco[] bancos = TipoBanco.values();
        for (int i = 0; i < bancos.length; i++) {
            System.out.println((i + 1) + " - " + bancos[i].getNome()
                    + " (taxa " + (bancos[i].getTaxaServico() * 100) + "%)");
        }
        int op = lerInt("Escolha o banco: ") - 1;
        if (op >= 0 && op < bancos.length) {
            comprador.setPagamento(bancos[op].criarInstancia());
        } else {
            System.out.println("Banco inválido.");
        }
    }

    // ================= MENU VENDEDOR =================
    private static void exibeMenuVendedor() {
        System.out.println("\n===== MENU VENDEDOR =====");
        System.out.println("1 - Adicionar estoque");
        System.out.println("2 - Excluir do estoque");
        System.out.println("3 - Alterar endereço");
        System.out.println("4 - Excluir conta");
        System.out.println("0 - Voltar ao menu");
    }

    private static void menuVendedor(Vendedor vendedor, List<Usuario> usuarios) {
        int opcao;
        do {
            exibeMenuVendedor();
            opcao = lerInt("Escolha: ");

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do item: ");
                    String nome = scanner.nextLine();
                    System.out.print("Tipo: ");
                    String tipo = scanner.nextLine();
                    int qtd = lerInt("Quantidade: ");
                    double valor = lerDouble("Valor: ");
                    if (qtd <= 0 || valor < 0) {
                        System.out.println("Quantidade ou valor inválido.");
                    } else {
                        vendedor.adicionarEstoque(new Estoque(nome, tipo, qtd, valor));
                    }
                }
                case 2 -> {
                    listarEstoque(vendedor);
                    if (!vendedor.getEstoques().isEmpty()) {
                        int idx = lerInt("Número do item a excluir: ") - 1;
                        if (idx >= 0 && idx < vendedor.getEstoques().size()) {
                            vendedor.excluirEstoque(vendedor.getEstoques().get(idx));
                        } else {
                            System.out.println("Item inválido.");
                        }
                    }
                }
                case 3 -> alterarEndereco(vendedor);
                case 4 -> {
                    vendedor.excluirConta();
                    usuarios.remove(vendedor);
                    return;
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opcao inválida.");
            }
        } while (opcao != 0);
    }

    // ================= AUXILIARES =================
    private static void listarEstoque(Vendedor vendedor) {
        if (vendedor.getEstoques().isEmpty()) {
            System.out.println("Estoque vazio.");
            return;
        }
        int i = 1;
        for (Estoque e : vendedor.getEstoques()) {
            System.out.println(i++ + " - " + e.getNome() + " (" + e.getTipo() + ") | qtd: "
                    + e.getQtd() + " | R$ " + e.getValor());
        }
    }

    private static void alterarEndereco(Usuario usuario) {
        System.out.print("Novo CEP: ");
        String cep = scanner.nextLine();
        System.out.print("Novo endereço: ");
        String end = scanner.nextLine();

        if (usuario.alterarEndereco(usuario, new Endereco(cep, end))) {
            System.out.println("Endereço alterado.");
        } else {
            System.out.println("Não foi possível alterar.");
        }
    }

    private static int lerInt(String mensagem) {
        System.out.print(mensagem);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        try {
            return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
package aplicacao;

import modelo.Administrador;
import modelo.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Usuario> usuarios = new ArrayList<>();
        Administrador admin = new Administrador(1, "Admin", "00000-0000", "Sede", usuarios);
        usuarios.add(admin);

        int opcao;
        do {
            exibeMenu();
            System.out.println("Escolha: ");
            opcao = Integer.parseInt(scanner.nextLine()); // pra nao ter problema lendo dados dps tratar com try/catch

            switch (opcao) {
                case 1 -> System.out.println("Incluir cadastro");
                case 2 -> System.out.println("Alterar cadastro");
                case 3 -> System.out.println("Excluir cadastro");
                case 4 -> System.out.println("Listar cadastro");
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opcao inválida.");
            }
        } while (opcao != 0);
    }


    private static void exibeMenu() {
        System.out.println("\n⊹₊˚‧︵₊୨ᰔ୧₊˚₊⊹ MENU SISTEMAS DE REGISTROS ⊹₊˚‧︵₊୨ᰔ୧₊₊⊹");
        System.out.println("1 - Incluir");
        System.out.println("2 - Alterar");
        System.out.println("3 - Excluir");
        System.out.println("4 - Listar");
        System.out.println("0 - Sair");

    }


    // ---------- incluir usuario ----------
//    private static void incluir(Administrador admin, List<Usuario> usuarios) {
//        System.out.println("1 - Usuario (Comprador ou Vendedor)");
//        System.out.println("2 - Item no estoque de um Vendedor");
//        System.out.print("Escolha: ");
//        int escolha = Integer.parseInt(scanner.nextLine());
//
//        switch (escolha) {
//            case 1 -> incluirUsuario(usuarios);
//            case 2 -> incluirEstoque(admin);
//            default -> System.out.println("Opcao invalida.");
//        }
//    }

}


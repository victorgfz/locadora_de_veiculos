import java.util.Scanner;

public class Menu {

    public static void iniciar() {
        Scanner scanner = new Scanner(System.in);
        Sistema sistema = new Sistema();
        int opcao;
        do {
            System.out.println("Bem-vindo a locadora de veiculos, para prosseguir selecione uma opcao:");
            System.out.println("1 - Fazer login");
            System.out.println("2 - Fazer cadastro");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    sistema.login();
                    break;
                case 2:
                    sistema.cadastrar();
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    System.out.println("");
                    break;
            }
        } while (opcao < 1 || opcao > 2);
        System.out.println("");

        scanner.close();
    }

    public static void admin() {
        Scanner scanner = new Scanner(System.in);
        Locadora locadora = new Locadora();
        int opcao;
        do {
            System.out.println("---MENU ADMIN---");
            System.out.println("Selecione uma opcao:");
            System.out.println("1 - Cadastrar novo veiculo");
            System.out.println("2 - Listar todos os veiculos disponiveis");
            System.out.println("3 - Listar todos os alugueis em andamento");
            System.out.println("4 - Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    locadora.cadastrarVeiculo();
                    break;
                case 2:

                    locadora.listarVeiculosDisponiveis();
                    break;
                case 3:
                    System.out.println("mouta");
                    /* locadora.cadastrarMoto(); */
                    break;
                case 4:
                    Menu.iniciar();
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    System.out.println("");
                    break;

            }

        } while (opcao < 1 || opcao > 4);
        scanner.close();
    }
}

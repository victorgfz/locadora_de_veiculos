import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    static Sistema sistema = new Sistema();
    static Locadora locadora = new Locadora();

    public static void iniciar() {
        try {
            int opcao;
            do {
                System.out.println("Bem-vindo a locadora de veiculos, para prosseguir selecione uma opcao:");
                System.out.println("1 - Fazer login");
                System.out.println("2 - Fazer cadastro");
                System.out.println("3 - Encerrar");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        sistema.login();
                        break;
                    case 2:
                        sistema.cadastrar();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Opcao invalida!");
                        System.out.println("");
                        break;
                }
            } while (opcao < 1 || opcao > 3);
            System.out.println("");

        } catch (InputMismatchException e) {
            System.out.println("Opcao invalida!");
            System.out.println("");
            scanner.nextLine();
            Menu.iniciar();
        }

    }

    public static void admin() {
        try {
            int opcao;
            do {
                System.out.println("---MENU ADMIN---");
                System.out.println("Selecione uma opcao:");
                System.out.println("1 - Cadastrar novo veiculo");
                System.out.println("2 - Listar todos os veiculos");
                System.out.println("3 - Listar todos os alugueis");
                System.out.println("4 - Sair");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        locadora.cadastrarVeiculo();
                        break;
                    case 2:

                        locadora.listarTodosOsVeiculos();
                        break;
                    case 3:
                        locadora.listarTodosOsAlugueis();
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

        } catch (InputMismatchException e) {
            System.out.println("Opcao invalida!");
            System.out.println("");
            scanner.nextLine();
            Menu.admin();
        }

    }

    public static void usuario(String email) {
        try {

            int opcao;
            do {
                System.out.println("---MENU USUARIO---");
                System.out.println("Selecione uma opcao:");
                System.out.println("1 - Alugar um veiculo");
                System.out.println("2 - Listar meus alugueis atuais");
                System.out.println("3 - Sair");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        locadora.alugarUmVeiculo(email);
                        break;
                    case 2:
                        locadora.listarAlugueisPorUsuario(email);
                        break;
                    case 3:
                        Menu.iniciar();
                        break;
                    default:
                        System.out.println("Opcao invalida!");
                        System.out.println("");
                        break;
                }

            } while (opcao < 1 || opcao > 3);

        } catch (InputMismatchException e) {
            System.out.println("Opcao invalida!");
            System.out.println("");
            scanner.nextLine();
            Menu.usuario(email);
        }

    }

}

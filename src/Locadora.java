import java.util.ArrayList;
import java.util.Scanner;

public class Locadora {
    private ArrayList<Veiculo> veiculos = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    private boolean verificaSeExistePlaca(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa))
                return true;
        }
        return false;
    }

    public void cadastrarVeiculo() {
        System.out.println("---CADASTRAR NOVO VEICULO---");
        int tipo;
        do {
            System.out.println("Qual o tipo do veiculo? ");
            System.out.println("1 - Carro");
            System.out.println("2 - Caminhao");
            System.out.println("3 - Moto");
            tipo = scanner.nextInt();
            scanner.nextLine();
        } while (tipo < 1 || tipo > 3);

        System.out.println("Digite a placa: ");
        String placa = scanner.nextLine();

        if (this.verificaSeExistePlaca(placa)) {
            System.out.println("Veiculo já está cadastrado!");
            Menu.admin();
            return;
        }

        System.out.println("Digite o modelo: ");
        String modelo = scanner.nextLine();

        System.out.println("Digite o valor da diaria: ");
        int valorDiaria = scanner.nextInt();
        scanner.nextLine();

        switch (tipo) {
            case 1:
                Carro carro = new Carro(placa, modelo, valorDiaria);
                this.veiculos.add(carro);
                break;
            case 2:
                Caminhao caminhao = new Caminhao(placa, modelo, valorDiaria);
                this.veiculos.add(caminhao);
                break;
            case 3:
                Moto moto = new Moto(placa, modelo, valorDiaria);
                this.veiculos.add(moto);
                break;
            default:
                System.out.println("Opcao invalida! Tente novamente");
                this.cadastrarVeiculo();
                break;
        }

        System.out.println("O novo veiculo foi cadastrado com sucesso!");
        System.out.println("");
        Menu.admin();
        return;
    }

    public void listarVeiculosDisponiveis() {
        System.out.println("veiculos");
        System.out.println(veiculos);
        for (Veiculo veiculo : veiculos) {

            if (veiculo.isDisponivel()) {
                System.out.println("TIPO: " + veiculo.getTipo());
                System.out.println("MODELO: " + veiculo.getModelo());
                System.out.println("VALOR DA DIARIA: R$" + veiculo.getValorDiaria());
                System.out.println("------------");
            } else {
                System.out.println("it aint available");
            }

        }
        Menu.admin();

    }

}

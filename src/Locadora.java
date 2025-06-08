import java.util.ArrayList;
import java.util.InputMismatchException;
import java.time.temporal.ChronoUnit;

public class Locadora {
    private ArrayList<Veiculo> veiculos = new ArrayList<>();
    private ArrayList<Aluguel> alugueis = new ArrayList<>();
    private ServicoExtra[] servicos = {
            new ServicoExtra("Lavacao completa", 100),
            new ServicoExtra("Tanque cheio", 200),
            new ServicoExtra("Seguro adicional", 100)
    };

    private boolean verificaSeExistePlaca(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa))
                return true;
        }
        return false;
    }

    public void cadastrarVeiculo() {
        System.out.println("---CADASTRAR NOVO VEICULO---");
        int tipo = 0;

        try {
            do {
                System.out.println("Qual o tipo do veiculo? ");
                System.out.println("1 - Carro");
                System.out.println("2 - Caminhao");
                System.out.println("3 - Moto");
                tipo = Menu.scanner.nextInt();
                Menu.scanner.nextLine();
                if (tipo < 1 || tipo > 3)
                    System.out.println("Opcao invalida! Tente novamente");

            } while (tipo < 1 || tipo > 3);
        } catch (InputMismatchException e) {
            System.out.println("Opcao invalida! Tente novamente");
            tipo = 0;
            Menu.scanner.nextLine();
            this.cadastrarVeiculo();
        }

        System.out.println("Digite a placa: ");
        String placa = Menu.scanner.nextLine();

        if (this.verificaSeExistePlaca(placa)) {
            System.out.println("Veiculo ja esta cadastrado!");
            Menu.admin();
            return;
        }

        System.out.println("Digite o modelo: ");
        String modelo = Menu.scanner.nextLine();

        int valorDiaria = 0;
        while (valorDiaria == 0) {
            try {
                System.out.println("Digite o valor da diaria: ");
                valorDiaria = Menu.scanner.nextInt();
                Menu.scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opcao invalida! Tente novamente");
                valorDiaria = 0;
                Menu.scanner.nextLine();
            }
        }

        int id = this.veiculos.size() + 1;
        switch (tipo) {
            case 1:
                Carro carro = new Carro(placa, modelo, valorDiaria, id);
                this.veiculos.add(carro);
                break;
            case 2:
                Caminhao caminhao = new Caminhao(placa, modelo, valorDiaria, id);
                this.veiculos.add(caminhao);
                break;
            case 3:
                Moto moto = new Moto(placa, modelo, valorDiaria, id);
                this.veiculos.add(moto);
                break;
        }

        System.out.println("O novo veiculo foi cadastrado com sucesso!");
        System.out.println("");
        Menu.admin();
        return;
    }

    public void listarTodosOsVeiculos() {
        if (this.veiculos.size() == 0) {
            System.out.println("Nenhum veiculo cadastrado ate o momento!");
            System.out.println("");
            Menu.admin();
            return;
        }
        System.out.println("ESSES SAO TODOS OS VEICULOS CADASTRADOS:");
        System.out.println("------------");
        for (Veiculo veiculo : veiculos) {

            System.out.println("ID: " + veiculo.getId());
            System.out.println("TIPO: " + veiculo.getTipo());
            System.out.println("MODELO: " + veiculo.getModelo());
            System.out.println("STATUS: " + (veiculo.isDisponivel() ? "Disponivel" : "Indisponivel"));
            System.out.println("VALOR DA DIARIA: R$" + veiculo.getValorDiaria());
            System.out.println("------------");

        }

        Menu.admin();

    }

    private Veiculo encontraVeiculo(int id) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getId() == id) {
                veiculo.setDisponivel(false);
                return veiculo;
            }

        }
        return null;
    }

    private Usuario encontraUsuario(String email) {
        for (Usuario usuario : Menu.sistema.getUsuarios()) {
            if (email.equals(usuario.getEmail())) {
                return usuario;
            }
        }
        return null;
    }

    private Pagamento realizarPagamento(int valorTotal) {

        System.out.println("O valor do aluguel ficou em R$" + valorTotal);
        int escolhaPagamento = 0;
        TipoPagamento escolhaTipo = null;
        int escolhaVezes = 0;
        do {
            try {
                System.out.println("Qual sera o metodo de pagamento?");
                System.out.println("1 - Pix (a vista)");
                System.out.println("2 - Cartao debito (a vista)");
                System.out.println("3 - Cartao credito (parcelamento em ate 3x)");
                escolhaPagamento = Menu.scanner.nextInt();
                Menu.scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opcao invalida!");
                Menu.scanner.nextLine();
                escolhaPagamento = 0;
            }
            ;

            switch (escolhaPagamento) {
                case 1:
                    escolhaTipo = TipoPagamento.PIX;
                    escolhaVezes = 1;
                    break;
                case 2:
                    escolhaTipo = TipoPagamento.DEBITO;
                    escolhaVezes = 1;
                    break;
                case 3:
                    do {
                        try {
                            System.out.println("Quantas vezes?");
                            System.out.println("1 - 1x de R$ " + valorTotal);
                            System.out.println("2 - 2x de R$ " + valorTotal / 2);
                            System.out.println("3 - 3x de R$ " + valorTotal / 3);
                            escolhaTipo = TipoPagamento.CREDITO;
                            escolhaVezes = Menu.scanner.nextInt();
                            Menu.scanner.nextLine();
                            if (escolhaVezes < 1 || escolhaVezes > 3)
                                System.out.println("Opcao invalida!");
                        } catch (InputMismatchException e) {
                            System.out.println("Opcao invalida!");
                            Menu.scanner.nextLine();
                            escolhaVezes = 0;
                        }

                    } while (escolhaVezes < 1 || escolhaVezes > 3);

                    break;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        } while (escolhaPagamento < 1 || escolhaPagamento > 3);

        Pagamento pagamento = new Pagamento(escolhaTipo, escolhaVezes, valorTotal);
        return pagamento;
    }

    public void alugarUmVeiculo(String email) {

        System.out.println("---ALUGAR UM VEICULO---");

        if (this.veiculos.size() == 0) {
            System.out.println("Nenhum veiculo disponivel para locacao!");
            Menu.usuario(email);
            return;
        }

        System.out.println("Essa e a lista de veiculos disponiveis para locacao: ");
        System.out.println("------------");
        for (Veiculo veiculo : veiculos) {
            if (veiculo.isDisponivel()) {
                System.out.println("ID: " + veiculo.getId());
                System.out.println("TIPO: " + veiculo.getTipo());
                System.out.println("MODELO: " + veiculo.getModelo());
                System.out.println("VALOR DA DIARIA: R$" + veiculo.getValorDiaria());
                System.out.println("------------");
            }
        }
        int escolhaVeiculo = -1;
        while (escolhaVeiculo == -1) {
            try {
                System.out.println(
                        "Para realizar a locacao de um veiculo digite o ID do mesmo, para cancelar a locacao digite 0: ");
                escolhaVeiculo = Menu.scanner.nextInt();
                Menu.scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opcao invalida!");
                Menu.scanner.nextLine();
                escolhaVeiculo = -1;
            }
        }

        if (escolhaVeiculo == 0) {
            Menu.usuario(email);
            return;
        }

        Veiculo veiculo = encontraVeiculo(escolhaVeiculo);

        if (veiculo == null) {
            System.out.println("Opcao invalida!");
            System.out.println("");
            this.alugarUmVeiculo(email);
            return;
        }

        int dias = 0;
        while (dias == 0) {
            try {
                System.out.println("Quantos dias pretende ficar com o veiculo?");
                dias = Menu.scanner.nextInt();
                Menu.scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opcao invalida!");
                Menu.scanner.nextLine();
                dias = 0;
            }
        }

        System.out.println("Deseja algum servico extra?");
        int escolhaServico = 0;
        do {
            try {
                for (int i = 0; i < servicos.length; i++) {
                    System.out
                            .println((i + 1) + " - " + servicos[i].getDescricao() + " / R$ " + servicos[i].getValor());
                }
                System.out.println((servicos.length + 1) + " - " + " Nao");
                escolhaServico = Menu.scanner.nextInt();
                Menu.scanner.nextLine();
                if (escolhaServico < 1 || escolhaServico > (servicos.length + 1))
                    System.out.println("Opcao invalida!");
            } catch (InputMismatchException e) {
                System.out.println("Opcao invalida!");
                Menu.scanner.nextLine();
                escolhaServico = 0;
            }
        } while (escolhaServico < 1 || escolhaServico > (servicos.length + 1));

        ServicoExtra servicoEscolhido = escolhaServico == (servicos.length + 1) ? null : servicos[escolhaServico - 1];

        Usuario usuario = encontraUsuario(email);
        if (usuario == null) {
            System.out.println("Usuario nao encontrado!");
            System.out.println("");
            Menu.iniciar();
            return;
        }

        int id = this.alugueis.size() + 1;
        Aluguel aluguel = new Aluguel(id, veiculo, dias, usuario, servicoEscolhido);

        aluguel.setPagamento(this.realizarPagamento(aluguel.calcularValor(dias)));
        this.alugueis.add(aluguel);
        System.out.println("O aluguel foi realizado com sucesso!");
        System.out.println("");
        Menu.usuario(email);

    }

    public void listarTodosOsAlugueis() {
        if (this.alugueis.size() == 0) {
            System.out.println("Nenhum aluguel realizado ate o momento!");
            System.out.println("");
            Menu.admin();
            return;
        }
        System.out.println("ESSES SAO TODOS OS ALUGUEIS REALIZADOS:");
        System.out.println("------------");
        for (Aluguel aluguel : alugueis) {
            System.out.println("ID DO ALUGUEL: " + aluguel.getId());
            System.out.println("VEICULO: " + aluguel.getVeiculo().getModelo());
            System.out.println("CLIENTE: " + aluguel.getCliente().getNome());
            System.out.println("DATA DE INICIO: " + aluguel.getDataInicio());
            System.out.println("DATA DE FIM: " + aluguel.getDataFim());

            if (aluguel.getServico() != null)
                System.out.println("SERVICO EXTRA: " + aluguel.getServico().getDescricao());

            int dias = (int) ChronoUnit.DAYS.between(aluguel.getDataInicio(), aluguel.getDataFim());
            System.out.println("VALOR TOTAL: R$ " + aluguel.calcularValor(dias));
            System.out.println("METODO DE PAGAMENTO: " + aluguel.getPagamento().getTipo());
            System.out.println("------------");
        }
        Menu.admin();
    }

    public void listarAlugueisPorUsuario(String email) {
        System.out.println("ESSES SAO TODOS OS ALUGUEIS DO USUARIO " + email + ": ");
        System.out.println("------------");
        for (Aluguel aluguel : alugueis) {
            if (aluguel.getCliente().getEmail().equals(email)) {
                System.out.println("ID DO ALUGUEL: " + aluguel.getId());
                System.out.println("VEICULO: " + aluguel.getVeiculo().getModelo());
                System.out.println("DATA DE INICIO: " + aluguel.getDataInicio());
                System.out.println("DATA DE FIM: " + aluguel.getDataFim());
                if (aluguel.getServico() != null)
                    System.out.println("SERVICO EXTRA: " + aluguel.getServico().getDescricao());

                int dias = (int) ChronoUnit.DAYS.between(aluguel.getDataInicio(), aluguel.getDataFim());
                System.out.println("VALOR TOTAL: R$ " + aluguel.calcularValor(dias));
                System.out.println("METODO DE PAGAMENTO: " + aluguel.getPagamento().getTipo());
                System.out.println("------------");
            }
        }
        Menu.usuario(email);
    }

}

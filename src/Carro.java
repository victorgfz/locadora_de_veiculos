public class Carro extends Veiculo {

    public Carro(String placa, String modelo, int valorDiaria) {
        super(placa, modelo, valorDiaria);

    }

    public String getTipo() {
        return "Carro";
    }

}

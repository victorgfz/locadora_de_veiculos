public class Caminhao extends Veiculo {

    public Caminhao(String placa, String modelo, int valorDiaria) {
        super(placa, modelo, valorDiaria);
    }

    public String getTipo() {
        return "Caminhao";
    }
}

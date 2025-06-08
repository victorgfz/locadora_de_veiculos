public class Caminhao extends Veiculo {

    public Caminhao(String placa, String modelo, int valorDiaria, int id) {
        super(placa, modelo, valorDiaria, id);
    }

    public String getTipo() {
        return "Caminhao";
    }
}

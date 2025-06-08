public class Moto extends Veiculo {

    public Moto(String placa, String modelo, int valorDiaria, int id) {
        super(placa, modelo, valorDiaria, id);
    }

    public String getTipo() {
        return "Moto";
    }
}

public class Moto extends Veiculo {

    public Moto(String placa, String modelo, int valorDiaria) {
        super(placa, modelo, valorDiaria);
    }

    public String getTipo() {
        return "Moto";
    }
}

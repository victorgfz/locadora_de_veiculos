public abstract class Veiculo {
    private String placa;
    private String modelo;
    private boolean disponivel = true;
    private int valorDiaria;

    public Veiculo(String placa, String modelo, int valorDiaria) {
        this.placa = placa;
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public int getValorDiaria() {
        return valorDiaria;
    }

    public abstract String getTipo();
}

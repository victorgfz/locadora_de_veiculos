public abstract class Veiculo {
    private String placa;
    private String modelo;
    private boolean disponivel = true;
    private int valorDiaria;
    private int id;

    public Veiculo(String placa, String modelo, int valorDiaria, int id) {
        this.placa = placa;
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
        this.id = id;
    }

    public int getId() {
        return id;
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

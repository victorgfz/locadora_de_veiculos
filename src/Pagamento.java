public class Pagamento {

    private TipoPagamento tipo;
    private int parcelas;

    public Pagamento(TipoPagamento tipo, int vezes, int valorTotal) {
        this.tipo = tipo;
        this.parcelas = valorTotal / vezes;
    }

    public TipoPagamento getTipo() {
        return tipo;
    }

    public int getParcelas() {
        return parcelas;
    }

}

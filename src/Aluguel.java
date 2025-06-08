import java.time.LocalDate;

public class Aluguel {
    private int id;
    private Veiculo veiculo;
    private LocalDate dataInicio = LocalDate.now();
    private LocalDate dataFim;
    private Usuario cliente;
    private ServicoExtra servico;
    private Pagamento pagamento;

    public int getId() {
        return id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public ServicoExtra getServico() {
        return servico;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public Aluguel(int id, Veiculo veiculo, int dias, Usuario cliente, ServicoExtra servico) {
        this.id = id;
        this.veiculo = veiculo;
        this.dataFim = dataInicio.plusDays(dias);
        this.cliente = cliente;
        this.servico = servico;

    }

    public int calcularValor(int dias) {
        int valorAluguel = this.veiculo.getValorDiaria() * dias;
        int valorServico = (this.servico != null) ? this.servico.getValor() : 0;

        return valorAluguel + valorServico;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

}

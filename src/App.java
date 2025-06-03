public class App {
    public static void main(String[] args) throws Exception {

        /* Menu.iniciar(); */
        Locadora locadora = new Locadora();
        locadora.cadastrarVeiculo();
        locadora.listarVeiculosDisponiveis();
    }
}

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Sistema {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    private boolean verificaSeExisteEmail(String email) {
        for (Usuario usuario : this.usuarios) {
            if (usuario.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    };

    private boolean verificaSeExisteSenha(String senha) {
        for (Usuario usuario : this.usuarios) {
            if (usuario.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    };

    public void cadastrar() {
        System.out.println("---FAZER CADASTRO---");
        System.out.println("Digite o seu email: ");
        String email = scanner.nextLine();

        System.out.println("Digite a sua senha: ");
        String senha = scanner.nextLine();

        System.out.println("Digite o seu nome completo: ");
        String nome = scanner.nextLine();

        System.out.println("Digite o seu CPF (XXX.XXX.XXX-XX): ");
        String cpf = scanner.nextLine();

        System.out.println("Digite a sua data de nascimento (dd/MM/aaaa): ");
        String data = scanner.nextLine();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimento = LocalDate.parse(data, formatador);

        if (this.verificaSeExisteEmail(email)) {
            System.out.println("Usuario ja cadastrado!");
            System.out.println("");
            this.cadastrar();
            return;
        }
        Usuario usuario = new Usuario(email, senha, nome, cpf, dataNascimento);
        usuarios.add(usuario);
        System.out.println("Usuario cadastrado com sucesso!");
        System.out.println("");
        Menu.iniciar();
    }

    public void login() {
        System.out.println("---FAZER LOGIN---");
        System.out.println("Digite o seu email: ");
        String email = scanner.nextLine();

        System.out.println("Digite a sua senha: ");
        String senha = scanner.nextLine();

        if (email.trim().equals("admin") && senha.trim().equals("admin")) {
            System.out.println("Usuario logado como administrador!");
            System.out.println("");
            Menu.admin();
            return;
        }

        if (this.verificaSeExisteEmail(email) && this.verificaSeExisteSenha(senha)) {
            System.out.println("Usuario logado com sucesso!");
            System.out.println("");
            return;
        } else {
            System.out.println("Email e/ou senha incorretos! Tente novamente!");
            System.out.println("");
            this.login();
            return;
        }

    }

}

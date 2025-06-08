import java.time.LocalDate;

public class Usuario {
    private String email;
    private String senha;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

    public Usuario(String email, String senha, String nome, String cpf, LocalDate dataNascimento) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

}

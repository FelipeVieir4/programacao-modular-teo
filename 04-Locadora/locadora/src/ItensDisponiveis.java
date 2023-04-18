import java.security.InvalidParameterException;

public class ItensDisponiveis {
    private String nome;
    private double valor;

    public ItensDisponiveis(String nome, double valor) throws InvalidParameterException {

        if (nome.length() < 3) {
            throw new InvalidParameterException("Nome do item adicional inválido");
        }
        if (valor < 0) {
            throw new InvalidParameterException("Valor do item adicional inválido");
        }

        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
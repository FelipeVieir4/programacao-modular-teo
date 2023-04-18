import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class Imovel {

    // #region Atributos
    private static final float TAXA_COMISSAO_IMOBILIARIA;
    protected static final float TAXA_MAX_DEPRECIACAO;
    protected static final double TRIGGER_DEPRECICACAO;
    protected static int anoAtual;
    protected String anoConstrucao;
    protected double valorVenda;
    protected List<ItensDisponiveis> itensDisponiveis = new ArrayList<ItensDisponiveis>();
    private String endereco;

    // #endregion

    // #region Construtores

    static {
        TAXA_COMISSAO_IMOBILIARIA = 0.12f;
        TAXA_MAX_DEPRECIACAO = 0.30f;
        TRIGGER_DEPRECICACAO = 5;
        anoAtual = Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 
     * @param anoConstrucao string do ano de construção que seja maior que 1500 e
     *                      menor ou igual ao ano atual.
     * @param endereco      string com endereço completo do imóvel.
     * @param valorVenda    valor atual de venda.
     * @throws InvalidParameterException
     */
    public Imovel(String anoConstrucao, String endereco, double valorVenda) throws InvalidParameterException {

        int anoConstrucaoInt = Integer.parseInt(anoConstrucao);

        // Validações
        if (anoConstrucaoInt < 1500 || anoConstrucaoInt > anoAtual) {
            throw new InvalidParameterException("Ano de construção inválido");
        }

        if (endereco.length() < 5) {
            throw new InvalidParameterException("Endereço inválido");
        }

        if (valorVenda < 0) {
            throw new InvalidParameterException("Valor de venda inválido");
        }

        this.anoConstrucao = anoConstrucao;
        this.endereco = endereco;
        this.valorVenda = valorVenda;
    }
    // #endregion

    // #region Métodos

    /**
     * Método para calcular o valor do aluguel de um imóvel
     * 
     * @return valor final do aluguel
     */
    public abstract double calcAluguel();

    /**
     * Método para calcular quanto que o propietário vai receber
     * 
     * @return valor a receber para o propietário
     */
    public abstract double calcGanhoPropietario();

    /**
     * Método para retornar a comissão da imobiliária.
     * 
     * @return double com valor a ser repassado para imobiliária.
     */
    public double calcGanhoImobiliaria() {
        return calcAluguel() * TAXA_COMISSAO_IMOBILIARIA;
    }

    /**
     * Método para calcular a deprecialção do imóvel, ou seja, valor a ser
     * descontado do aluguel bruto.
     * 
     * @param aluguelBruto
     * @return valor do desconto
     */
    protected abstract double calcDepreciacao(double aluguelBruto);

    /**
     * Método para calcular o custo total dos itens adicionais.
     * 
     * @return double com a soma dos valores adicionais.
     */
    protected double calcAdicionaisItensDisponiveis() {
        double custoTotalAdicionais = 0;
        for (ItensDisponiveis item : itensDisponiveis) {
            custoTotalAdicionais += item.getValor();
        }
        return custoTotalAdicionais;
    }

    /**
     * Adiciona um novo Item disponível para o imóvel.
     * 
     * @param nome  String com descrição do adicional
     * @param valor valor do adicional
     * @throws InvalidParameterException
     */
    public void addItemAdicional(String nome, Double valor) throws InvalidParameterException {

        try {
            ItensDisponiveis item = new ItensDisponiveis(nome, valor);
            itensDisponiveis.add(item);
        } catch (Exception e) {
            throw new InvalidParameterException(e.getMessage());
        }

    }

    @Override
    public String toString() {
        BigDecimal aluguel = new BigDecimal(calcAluguel()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal precoVenda = new BigDecimal(valorVenda).setScale(2, RoundingMode.HALF_UP);
        BigDecimal ganhoImobiliaria = new BigDecimal(calcGanhoImobiliaria()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal ganhoPropietario = new BigDecimal(calcGanhoPropietario()).setScale(2, RoundingMode.HALF_UP);

        StringBuilder aux = new StringBuilder("Imóvel localizado na rua: " + endereco);
        aux.append("\n\tAno de construção: " + anoConstrucao);
        aux.append("\n\tValor de venda: " + precoVenda);
        aux.append("\n\tValor do aluguel: " + aluguel);

        if (itensDisponiveis.size() > 0) {
            aux.append("\n\n\titens disponíveis:");

            for (ItensDisponiveis item : itensDisponiveis) {
                aux.append("\n\t" + item.getNome());
            }
        }
        aux.append("\n\n\tDADOS COMERCIAIS DO IMÓVEL: ");
        aux.append("\n\tGanho da imobiliária: " + ganhoImobiliaria);
        aux.append("\n\tGanho propietário: " + ganhoPropietario);
        return aux.toString();

    }
    // #endregion

    public String getAnoConstrucao() {
        return anoConstrucao;
    }

}

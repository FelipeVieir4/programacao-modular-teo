import java.security.InvalidParameterException;

public class Casa extends Imovel {

    // #region Atributos
    private static final float ALIQUOTA_ALUGUEL = 0.05f;
    private static final float TAXA_DEPRECIACAO = 0.10f;
    private double seguroIncendio;
    // #endregion

    /**
     * Construtor para uma casa que extends de imóvel.
     * 
     * @param anoConstrucao  string do ano de construção que seja maior que 1500 e
     *                       menor ou igual ao ano atual.
     * @param endereco       string com endereço completo do imóvel.
     * @param valorVenda     valor atual de venda.
     * @param seguroIncendio valor total do seguro que será divido por 12 para ser
     *                       alocado no valor mensal do aluguel
     * @throws InvalidParameterException
     */
    public Casa(String anoConstrucao, String endereco, double valorVenda, double seguroIncendio)
            throws InvalidParameterException {
        super(anoConstrucao, endereco, valorVenda);

        if (seguroIncendio < 0) {
            throw new InvalidParameterException("Valor do seguro inválido");
        }

        this.seguroIncendio = seguroIncendio / 12;
    }


    @Override
    public double calcAluguel() {
        double aluguelBruto = this.valorVenda * ALIQUOTA_ALUGUEL;
        double descontoDepreciacao = calcDepreciacao(aluguelBruto);
        double adicionalItensDisponiveis = calcAdicionaisItensDisponiveis();
        double aluguel = aluguelBruto - descontoDepreciacao + adicionalItensDisponiveis + seguroIncendio;
        return aluguel;
    }


    @Override
    public double calcGanhoPropietario() {
        return calcAluguel() - seguroIncendio - calcGanhoImobiliaria();
    }

    /*
     * ALERTA!!!! CÓDGIGO DUPLCIADO - TENHO QUE ESTUDAR COMO MANTER SOMENTE UMA
     * VERSÃO DESSE CÓDIGO
     */
    @Override
    protected double calcDepreciacao(double aluguelBruto) {
        int cicloDepreciacao = (int) ((int) (anoAtual - Integer.parseInt(anoConstrucao)) / TRIGGER_DEPRECICACAO);
        double desconto = (aluguelBruto * (cicloDepreciacao * TAXA_DEPRECIACAO));
        double taxaEfetiva = desconto / aluguelBruto;

        if (taxaEfetiva > TAXA_MAX_DEPRECIACAO) {
            return aluguelBruto * TAXA_MAX_DEPRECIACAO;
        } else {
            return desconto;
        }

    }

}

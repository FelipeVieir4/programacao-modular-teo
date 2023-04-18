import java.security.InvalidParameterException;

public class Apartamento extends Imovel {

    // #region Atributos
    private static final float ALIQUOTA_ALUGUEL = 0.04f;
    private static final float TAXA_DEPRECIACAO = 0.05f;
    private double condominio;
    // #endregion

    /**
     * Construtor para uma casa que extends de imóvel.
     * 
     * @param anoConstrucao string do ano de construção que seja maior que 1500 e
     *                      menor ou igual ao ano atual.
     * @param endereco      string com endereço completo do imóvel.
     * @param valorVenda    valor atual de venda.
     * @param condominio    valor mensal do condominio
     * @throws InvalidParameterException
     */
    public Apartamento(String anoConstrucao, String endereco, double valorVenda, double condominio)
            throws InvalidParameterException {
        super(anoConstrucao, endereco, valorVenda);

        if (condominio < 0) {
            throw new InvalidParameterException("Valor do condominio inválido");
        }

        this.condominio = condominio;
    }


    @Override
    public double calcAluguel() {
        double aluguelBruto = this.valorVenda * ALIQUOTA_ALUGUEL;
        double descontoDepreciacao = calcDepreciacao(aluguelBruto);
        double adicionalItensDisponiveis = calcAdicionaisItensDisponiveis();
        double aluguel = aluguelBruto - descontoDepreciacao + adicionalItensDisponiveis + condominio;
        return aluguel;
    }

    @Override
    public double calcGanhoPropietario() {
        return calcAluguel() - condominio - calcGanhoImobiliaria();
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


public class Pizza {

    private static final double FIXED_PIZZA_PRICE;
    private static final double FIXED_ADDITIONAL_PRICE;
    private static final String DESCRIPTION;
    private int id;
    private int additional = 0;

    static {
        FIXED_PIZZA_PRICE = 25.00;
        FIXED_ADDITIONAL_PRICE = 4.00;
        DESCRIPTION = "Pizza básica com queijo e calabresa";
    }

    public Pizza() {
        
    }

    /**
     * Cria um objeto do tipo pizza com no máximo 8 adicionais
     * 
     * @param add atribui a quantidade de adicionais que o objeto pizza possui.
     *            Valor mínimo = 1 e máximo = 8.
     */
    public Pizza(int add) {
        if (add > 8 || add < 1) {
            throw new IllegalArgumentException("Quatidade de adicionais inválida");
        }
        setAdditional(add);
    }

    /**
     * Calcula o preço final da pizza
     * 
     * @return double com preço final
     */
    public double calcFinalPrice() {
        return (FIXED_PIZZA_PRICE + (FIXED_ADDITIONAL_PRICE * additional));
    }

    /**
     * Imprime uma NF simples
     * 
     * @return String com descrição do produto, quantidade de adicinais e o valor
     *         final a pagar
     */
    public String invoice() {

        double finalPrice = calcFinalPrice();

        String string = DESCRIPTION + " com " + additional + " adicionais. Valor final = " + finalPrice;

        return string;

    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdditional() {
        return additional;
    }

    public void setAdditional(int additional) {
        this.additional = additional;
    }

}

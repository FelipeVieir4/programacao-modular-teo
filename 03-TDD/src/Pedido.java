import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    // #region ATRIBUTOS
    private static final int limitePedido;
    private static int proximoId;
    private final int pedidoId;
    private List<Pizza> listaPizza = new ArrayList<Pizza>();
    private Date data;

    static {
        proximoId = 1000;
        limitePedido = 10;
    }

    // #endregion ATRIBUTOS

    // #region CONSTRUTOR

    /**
     * Cria um novo pedido com limite de 10 pizzas
     */
    public Pedido() {
        this.pedidoId = proximoId;
        proximoId++;
        this.data = new Date();
    }
    // #endregion

    // #region MÉTODOS

    /**
     * Adiciona uma pizza ao pedido.
     * 
     * @param pizza objeto do tipo pizza para ser adicionado ao pedido
     * @return em caso de sucesso retorna TRUE, caso não seja possível adicionar
     *         mais pizza false é retornado
     * 
     */
    public boolean adicionarPizza(Pizza pizza) {
        if (listaPizza.size() < limitePedido) {
            listaPizza.add(pizza);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove um item do tipo Pizza do pedido conforme posição indicada por
     * parametro.
     * 
     * @param indexPizza posição do item dentro do pedido.
     * @return true para sucesso e false para falha.
     * 
     */
    public boolean removerPizza(int indexPizza) {
        if (!listaPizza.isEmpty()) {
            listaPizza.remove(indexPizza);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para informar quantidade de itens do pedido.
     * 
     * @return void
     * 
     */

    public int quantidadeDeItensNoPedido() {
        if (listaPizza.isEmpty()) {
            return 0;
        } else {
            return listaPizza.size();
        }
    }

    /**
     * Método para informar o valor total do pedido.
     * 
     * @return int: total do pedido
     * 
     */

    public double valorTotalDoPedido() {
        double total = 0;
        for (Pizza pizza : listaPizza) {
            total += pizza.calcFinalPrice();
        }
        return total;
    }

    /**
     * Método para alterar um intem do pedido.
     * 
     * @param indexPizza indice do item no pedido
     * @param pizza      novo objeto do tipo Pizza para substituir o atual.
     * @return void
     * 
     */

    public void alterarItemDoPedido(int indexPizza, Pizza pizza) {
        listaPizza.set(indexPizza, pizza);
    }

    /**
     * Método para gerar Nota Final.
     * Este método retorna a lista de todos os itens do pedido e seus respectivos
     * valores.
     * Também retorna o número do pedido, data e valor final.
     * 
     * @return String
     * 
     */
    public String gerarNotaFinal() {
        StringBuilder str = new StringBuilder();
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

        str
                .append("Id: " + pedidoId + "\n")
                .append("Data: " + dataFormatada.format(data) + "\n\n");

        int index = 0;
        for (Pizza pizza : listaPizza) {
            str.append(index + " - " + pizza.invoice() + "\n");
            index++;
        }

        str.append("\n valor final: " + valorTotalDoPedido());
        return str.toString();
    }

    // #endregion

    // #region SETTERS & GETTERS
    public int getPedidoId() {
        return pedidoId;
    }
    // #endregion
}
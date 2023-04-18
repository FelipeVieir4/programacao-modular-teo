import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class PedidoTest {
    Pedido pedido;
    Pizza pizza;
    Pizza pizza2;
    Pedido pedido2;

    @BeforeEach
    public void prepare() {
        pedido = new Pedido();
        pedido2 = new Pedido();
        pizza = new Pizza();
        pizza2 = new Pizza(1);
    }

    @Test
    public void testarAdicionarUmaPizzaNoPedido() {
        assertTrue(pedido.adicionarPizza(pizza));
    }

    @Test
    public void testarRemoverUmaPizzaNoPedido() {
        for (int i = 0; i <= 3; i++) {
            pedido.adicionarPizza(pizza);
        }
        assertTrue(pedido.removerPizza(0));
    }

    @Test
    public void testarAlterarUmaPizzaNoPedido() {

        pedido.adicionarPizza(pizza);
        assertEquals(25, pedido.valorTotalDoPedido());
        assertEquals(1, pedido.quantidadeDeItensNoPedido());
        int posicaoDoItemParaEditar = 0;
        pedido.alterarItemDoPedido(posicaoDoItemParaEditar, pizza2);
        assertEquals(29, pedido.valorTotalDoPedido());
        assertEquals(1, pedido.quantidadeDeItensNoPedido());

    }

    @Test
    public void testarValorTotalDoPedido() {
        pedido.adicionarPizza(pizza);
        pedido.adicionarPizza(pizza2);
        assertEquals(54, pedido.valorTotalDoPedido());

    }

    @Test
    public void testarAdicionarPizzaAcimaDoLimite() {
        for (int i = 0; i <= 10; i++) {
            pedido.adicionarPizza(pizza);
        }
        assertFalse(pedido.adicionarPizza(pizza));
    }

    @Test
    public void retornarTotalDePizzasDoPedido() {
        for (int i = 0; i <= 3; i++) {
            pedido.adicionarPizza(pizza);
        }
        assertEquals(4, pedido.quantidadeDeItensNoPedido());
    }

    @Test
    public void imprimirNotaFinal() {
        for (int i = 0; i <= 3; i++) {
            pedido.adicionarPizza(pizza);
        }
        pedido.adicionarPizza(pizza2);
       assertTrue(pedido.gerarNotaFinal().contains("valor final: 129"));
    }

    @Test
    public void testarIdDiferenteDosPedidos() {
        assertFalse(pedido.getPedidoId() == pedido2.getPedidoId());
    }
}

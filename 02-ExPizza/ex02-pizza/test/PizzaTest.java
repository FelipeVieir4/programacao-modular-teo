import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;


public class PizzaTest {

    Pizza pizza = new Pizza();
    Pizza pizzaAdicional = new Pizza(5);

    @Test
    public void testarExceçãoConstrutorComAdicional() {
        assertThrows(IllegalArgumentException.class, () -> new Pizza(9));
    }

    @Test
    public void testarTotalAdicional() {
        assertEquals(5, pizzaAdicional.getAdditional());
    }

    @Test
    public void testarCalcPrecoFinal(){
        assertEquals(45, pizzaAdicional.calcFinalPrice());
    }

    @Test
    public void testarNFSimples(){
        assertEquals("Pizza básica com queijo e calabresa com 0 adicionais. Valor final = 25.0", pizza.invoice());
    }

}

public class Main {
    public static void main(String[] args) {
        Pizza pizza = new Pizza();
        Pedido pedido = new Pedido();
        Pizza pizza2 = new Pizza(8);


        for (int i= 0; i<6; i++){
            pedido.adicionarPizza(pizza);
        }
        pedido.adicionarPizza(pizza2);
        System.out.println(pedido.gerarNotaFinal());
    }
}

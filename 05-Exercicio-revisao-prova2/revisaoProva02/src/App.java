import java.util.LinkedList;
import java.util.Scanner;
/** 
 * MIT License
 *
 * Copyright(c) 2022-23  João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */


//TODO: documentação do app
//TODO: melhorar o menu
public class App {
    static Scanner teclado = new Scanner(System.in);
    // #region Utilidades e menus

    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Menu para o restaurante
     * 
     * @return Opção do usuário (int)
     */
    public static int menuPrincipal() {
        limparTela();
        System.out.println("XULAMBS PIZZA");
        System.out.println("==========================");
        System.out.println("1 - Abrir pedido");
        System.out.println("2 - Alterar pedido");
        System.out.println("3 - Mostrar pedido atual");
        System.out.println("4 - Fechar pedido");
        System.out.println("5 - Relatórios");
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());

        return opcao;
    }

    
    /**
     * Menu para o restaurante
     * 
     * @return Opção do usuário (int)
     */
    public static int subMenuAdicionais() {
        System.out.println("");
        System.out.println("==========================");
        System.out.println("1 - Inserir ingredientes");
        System.out.println("2 - Retirar ingredientes");
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }

    /**
     * Menu para o restaurante
     * 
     * @return Opção do usuário (int)
     */
    public static int subMenuPedido() {
        limparTela();
        System.out.println("");
        System.out.println("==========================");
        System.out.println("1 - Pedido local");
        System.out.println("2 - Pedido para entrega");
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }

/**
     * Menu para o restaurante
     * 
     * @return Opção do usuário (int)
     */
    public static int subMenuComidas() {
        System.out.println("");
        System.out.println("==========================");
        System.out.println("1 - Pizza");
        System.out.println("2 - Sanduíche");
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }

    /** Menu para o restaurante
    * 
    * @return Opção do usuário (int)
    */
   public static int subMenuSanduiches() {

        System.out.println("==========================");
        System.out.println("Comprando um novo sanduíche:");
        System.out.println("01 - Básico");
        System.out.println("02 - Combo com refrigerante");
        System.out.println("03 - Vegano");
        System.out.println("04 - Vegano + combo com refrigerante"); 
       System.out.print("\nSua opção: ");
       int opcao = Integer.parseInt(teclado.nextLine());
       return opcao;
   }
    /**
     * Menu para o restaurante
     * 
     * @return Opção do usuário (int)
     */
    public static int menuAlteracaoPedido(Pedido pedidoAtual) {
        limparTela();
        System.out.println("ALTERAR PEDIDO");
        System.out.println("O pedido atual tem " + pedidoAtual.quantidadeDeItens() + " itens.");
                    
        System.out.println("1 - Acrescentar item");
        System.out.println("2 - Modificar item");
        System.out.println("3 - Cancelar item");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }

    /**
     * Pausa para leitura de mensagens em console
     * 
     * @param teclado Scanner de leitura
     */
    static void pausa() {
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }
    // #endregion

    // #region métodos do app
    
    //#region Pizza

    public static Pizza comprarPizza() {
        return (Pizza)criarComida(1);
    }

    public static Comida comprarComida() {
        System.out.println("\n==========================");
        System.out.println("Comprando uma nova comida:");
        int opcao=-1;
        opcao = subMenuComidas();
        Comida novaComida = criarComida(opcao);
        return novaComida;
    }

    public static Comida criarComida(int opcao) {
        Comida nova = null;
        if(opcao==1){
            Pizza novaPizza = criarPizza();
            nova = novaPizza;
        }
        else{
            Sanduiche novoSand = criarSanduiche();
            nova = novoSand;
        }
        alterarIngredientes(nova);
        return nova;

    }

    public static  Pizza criarPizza(){
        String validos = "pmg";
        String tamanho;
        System.out.println("\n==========================");
        System.out.println("Comprando uma nova pizza:");
        System.out.print("Tamanho (p, m, g): ");
        tamanho = teclado.nextLine();
        if(!validos.contains(tamanho.toLowerCase()))
            tamanho = "m";
        Pizza pizza = new Pizza(tamanho);

        return pizza;
    }

    
    public static  Sanduiche criarSanduiche(){
        int opcao = subMenuSanduiches();
        Sanduiche novo = new Sanduiche();
        switch(opcao){
            
            case 2: novo = new Sanduiche(true);
                break;
            case 3: novo = new Sanduiche();
                    novo.opcaoVegana(true);
                break;
            case 4: novo = new Sanduiche(true);
                    novo.opcaoVegana(true);
                break;
        }
        return novo;
    }

    @Deprecated
    public static void alterarPizza(Pizza pizza) {
        alterarIngredientes(pizza);
    }

    public static void alterarIngredientes(Comida comida) {
        int operacao = subMenuAdicionais();
        int quantos = 0;
        if (operacao == 1 || operacao == 2) {
            System.out.print("Quantos? ");
            quantos = Integer.parseInt(teclado.nextLine());
            if (operacao == 1)
                comida.adicionarIngrediente(quantos);
            else
                comida.retirarIngrediente(quantos);
        }
    }

    public static void acrescentarPizza(Pedido pedido) {
        adicionarComidas(pedido);
        
    }

    


    /**
     * Encapsula a impressão de dados de uma pizza no sistema.
     * 
     * @param mensagem Mensagem adicional para exibição (opcional)
     * @param qual     A pizza que terá seus dados impressos.
     */
    public static void imprimirDadosPizza(String mensagem, Pizza qual) {
        if (qual != null) {
            System.out.println(mensagem);
            System.out.println(qual.toString());
        } else
            System.out.println("Pizza não está registrada. Favor verificar");
    }
    //#endregion
    
    //#region Pedido
    
    public static Comida selecionarItemPedido(Pedido pedido, String mensagem) {
        int qualItem = -1;
        System.out.print(mensagem + " ");
        qualItem = Integer.parseInt(teclado.nextLine());
        Comida comidaParaAlterar = pedido.getComida(qualItem - 1);
        return comidaParaAlterar;
    }
    
    public static void alterarPedido(Pedido pedido) {
        Comida comidaParaAlterar = selecionarItemPedido(pedido, "Qual item você deseja alterar?");
        if (comidaParaAlterar != null) {
            alterarIngredientes(comidaParaAlterar);
            System.out.println("Comida alterada no pedido.");
        } else
            System.out.println("Pedido sem alterações.");
    }

    public static void cancelarItemPedido(Pedido pedido) {
        int qualItem = -1;
        System.out.print("Qual item você deseja cancelar? ");
        qualItem = Integer.parseInt(teclado.nextLine());
        pedido.cancelaItem(qualItem - 1);
        System.out.println("Item cancelado.");
    }

    /**
     * Encapsula a impressão de dados de um pedido no sistema.
     * 
     * @param mensagem Mensagem adicional para exibição (opcional)
     * @param qual     Pedido que terá seus dados impressos.
     */
    public static void imprimirDadosPedido(String mensagem, Pedido qual) {
        if (qual != null) {
            System.out.println(mensagem);
            System.out.println(qual);
        } else
            System.out.println("Não há pedido em andamento.");
    }

    public static Pedido adicionarPizzas(Pedido pedido){
        Pizza novaPizza;
        System.out.print("Adicionar outra pizza (S/N)? ");
        String continuar = teclado.nextLine();
        while (continuar.toLowerCase().equals("s")) {
            novaPizza = comprarPizza();
            if (novaPizza != null)
                pedido.addItem(novaPizza);
            System.out.print("Adicionar outra pizza (S/N)? ");
            continuar = teclado.nextLine();
        }
        return pedido;
    }

    public static Pedido adicionarComidas(Pedido pedido){
        Comida novaComida = null;
        
        int opcao = subMenuComidas();
        while (opcao!=0) {
            limparTela();
            System.out.println(pedido);
            System.out.print("\nAdicionar mais itens? ");
            novaComida = criarComida(opcao);
            
            if (novaComida != null){
                pedido.addComida(novaComida);
                System.out.println("Comida acrescentada ao pedido.");
            }
            
            System.out.print("\nAdicionar mais itens? ");
            opcao = subMenuComidas();
        }
        return pedido;
    }

    public static Pedido criarNovoPedido(int modoPedido) {
        limparTela();
        System.out.println("NOVO PEDIDO:");
        Pedido novo = null;
        Comida novaComida = comprarComida();
        if(modoPedido==1)
            novo = new Pedido(novaComida);
        else{
            System.out.print("Pedido para entrega. Distância da entrega(km): ");
            double distancia = Double.parseDouble(teclado.nextLine());
            novo = new Pedido(novaComida, distancia);
        }
        adicionarComidas(novo);
        return novo;
    }
    //#endregion
    
    //#endregion

    public static void main(String[] args) throws Exception {
        int opcao = -1;
        Pedido pedidoAtual = null;
        LinkedList<Pedido> todosOsPedidos = new LinkedList<>();
       
        do {
            opcao = menuPrincipal();
            
            switch (opcao) {
                case 1:
                    if (pedidoAtual == null) {
                        int modoPedido = subMenuPedido();
                        pedidoAtual = criarNovoPedido(modoPedido);
                    } else
                        System.out.println("Ainda há pedido em aberto.");
                    break;
                case 2:
                    if (pedidoAtual != null) {
                        int alteracao = menuAlteracaoPedido(pedidoAtual);
                        
                        switch (alteracao) {
                            case 1:
                                adicionarComidas(pedidoAtual);
                                break;
                            case 2:
                                alterarPedido(pedidoAtual);
                                break;
                            case 3:
                                cancelarItemPedido(pedidoAtual);
                                break;
                            default:          
                                System.out.println("Opção inválida");
                                break;
                        }
                    } else
                        System.out.println("Não há pedido em andamento. Favor abrir pedido.");
                    break;
                case 3:
                    imprimirDadosPedido("Pedido em aberto:", pedidoAtual);
                    break;
                case 4:
                    imprimirDadosPedido("Finalizando o pedido:", pedidoAtual);
                    todosOsPedidos.add(pedidoAtual);
                    pedidoAtual = null;
                    break;
                case 5:
                    limparTela();
                    System.out.println("Pedidos por data: ");
                    Pedido[] pedidos = new Pedido[todosOsPedidos.size()];
                    todosOsPedidos.toArray(pedidos);
                    Util.quicksort(pedidos, 0, pedidos.length-1);
                    limparTela();
                    for (Pedido pedido : pedidos) {
                        System.out.println(pedido);
                        pausa();
                    }
                break;

            }
            pausa();
        } while (opcao != 0);
        System.out.println("Vlw flw glr. T prx.");
    }
}

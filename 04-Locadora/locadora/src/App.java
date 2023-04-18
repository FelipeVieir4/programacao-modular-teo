import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);
    public static List<Imovel> lista = new ArrayList<Imovel>();

    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa() {
        System.out.println("Enter para continuar.");
        sc.nextLine();
    }

    static void bufferClear() {
        sc.nextLine();
    }

    public static void cadastrarNovoImovel() {
        limparTela();
        String ano;
        String endereco;
        double valorVenda;
        int opcao = -1;

        System.out.println("CADASTRO DE IMÓVEL");
        System.out.println("==================");

        System.out.println("Informe o ano de construção do imóvel");
        ano = sc.nextLine();

        System.out.println("Informe o endereço");
        endereco = sc.nextLine();

        System.out.println("Informe o valor de venda");
        valorVenda = sc.nextDouble();

        do {
            System.out.println("=========================================");
            System.out.println("Selecione uma opção abaixo para continuar");
            System.out.println("1 - Casa");
            System.out.println("2 - Apartamento");
            System.out.println("0 - Cancelar");
            opcao = sc.nextInt();

        } while ((opcao != 1) && (opcao != 2) && (opcao != 0));

        switch (opcao) {
            case 0:
                return;
            case 1:
                System.out.println("Informe o valor anual do seguro contra incêndio");
                double seguro = sc.nextDouble();
                try {
                    Imovel casa = new Casa(ano, endereco, valorVenda, seguro);
                    lista.add(casa);
                    subMenuItensAdicionais(casa);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    pausa();
                }
                return;
            case 2:
                System.out.println("Informe o valor mensal do condominio");
                double condominio = sc.nextDouble();

                try {
                    Imovel apartamento = new Apartamento(ano, endereco, valorVenda, condominio);
                    lista.add(apartamento);
                    subMenuItensAdicionais(apartamento);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return;

            default:
                return;
        }

    }

    public static void subMenuItensAdicionais(Imovel imovel) {
        int opcao = -1;
        do {

            System.out.println("================================================");
            System.out.println("Deseja Cadastrar adicionais?");

            System.out.println("1 - Sim");
            System.out.println("2 - Não");

            opcao = sc.nextInt();
            bufferClear();

            if (opcao == 1) {

                System.out.println("=======================================================");
                System.out.println("Digite o nome do item adicional disponível neste imóvel");
                String nome = sc.nextLine();
                System.out.println("Digite o valor deste item adicionl");
                double valor = sc.nextDouble();
                try {
                    imovel.addItemAdicional(nome, valor);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    pausa();
                }

            }
        } while (opcao != 2);

    }

    public static void exibirImoveisCadastrados() {
        limparTela();
        System.out.println("LISTA DE IMÓVEIS CADASTRADOS");
        if (lista.size() > 0) {
            for (Imovel imovel : lista) {

                System.out.println(lista.indexOf(imovel) + " : " + imovel);
                System.out.println("--------------------------------------");

            }
        } else {
            System.out.println("Não há imóveis cadastrados");
        }

    }

    public static void removerImovelCadastrado() {
        limparTela();

        exibirImoveisCadastrados();
        System.out.println("EXCLUSÃO DE IMÓVEL!!!!!");
        System.out.println("=======================");
        System.out.print("Informe o número do imóvel que deseja excluir: ");

        int opcao = sc.nextInt();

        if (opcao > lista.size() - 1) {
            System.out.println("Opção inválida");
        } else {
            lista.remove(opcao);
        }
    }

    public static void relatorioComercial() {
        limparTela();
        System.out.println("RELATÓRIO COMERCIAL");
        System.out.println("===================");

        if (lista.size() > 0) {

            BigDecimal totalImobiliaria = BigDecimal.valueOf(0);
            BigDecimal totalPropietarios = BigDecimal.valueOf(0);
            BigDecimal totalAlugueis = BigDecimal.valueOf(0);
            StringBuilder aux = new StringBuilder("");

            for (Imovel imovel : lista) {
                totalImobiliaria = totalImobiliaria
                        .add(BigDecimal.valueOf(imovel.calcGanhoImobiliaria()).setScale(2, RoundingMode.HALF_UP));
                totalPropietarios = totalPropietarios
                        .add(BigDecimal.valueOf(imovel.calcGanhoPropietario()).setScale(2, RoundingMode.HALF_UP));
                totalAlugueis = totalAlugueis
                        .add(BigDecimal.valueOf(imovel.calcAluguel()).setScale(2, RoundingMode.HALF_UP));

            }

            aux.append("\nGanho Total da imobiliária: " + totalImobiliaria);
            aux.append("\nGanho Arrecadado com alugueis: " + totalAlugueis);
            aux.append("\nValor total a ser repassado para os propietários: " + totalPropietarios);
            aux.append("\n");

            aux.append("\nLista de imóveis: ");
            for (Imovel imovel : lista) {
                aux.append("\n");
                aux.append(lista.indexOf(imovel) + " : " + imovel);
                aux.append("\n");
                aux.append("\n");
            }

            System.out.println(aux);

        } else {
            System.out.println("Não há dados!");
        }

    }

    public static void buscarImovel() {
        System.out.println("BUSCAR IMÓVEL POR ANO DE CONSTRUÇÃO");
        System.out.println("============================");

        System.out.println("informe o ano");
        String ano = sc.nextLine();

        System.out.println("\nResultado:");
        for (Imovel imovel : lista) {
            if (imovel.getAnoConstrucao().equals(ano)) {
                System.out.println(imovel);
            }
        }

    }

    public static int menuPrincial() {
        limparTela();
        System.out.println("LOCADORA DE IMOVEIS MOVEFLIX");
        System.out.println("============================");
        System.out.println("1 - Cadastrar um novo imóvel");
        System.out.println("2 - Exibir lista de imóveis");
        // System.out.println("3 - Alterar um imóvel");
        System.out.println("4 - Remover um imóvel da lista");
        System.out.println("5 - Relatório Comercial");
        System.out.println("6 - Buscar imóveis por ano de construção");
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(sc.nextLine());

        return opcao;
    }

    public static void main(String[] args) throws Exception {
        int opcao = -1;
        do {
            opcao = menuPrincial();
            limparTela();
            switch (opcao) {
                case 1:
                    cadastrarNovoImovel();
                    break;
                case 2:
                    exibirImoveisCadastrados();
                    break;
                case 3:
                    break;
                case 4:
                    removerImovelCadastrado();
                    break;
                case 5:
                    relatorioComercial();
                    break;
                case 6:
                    buscarImovel();
                    break;
            }
            pausa();
        } while (opcao != 0);
    }

}

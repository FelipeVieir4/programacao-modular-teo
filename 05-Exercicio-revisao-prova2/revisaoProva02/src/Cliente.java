import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Cliente {
    private static int proximo_id;
    private int idCliente;
    private String nome;
    private String cpf;
    private List<Pedido> listaPedidos = new ArrayList<Pedido>();
    private ICliente tipoClente;

    static {
        proximo_id = 1;
    }

    public Cliente(String nome, String cpf) throws InvalidParameterException {
        if (nome.length() < 2) {
            throw new InvalidParameterException("Nome inválido");
        }
        if (cpf.length() < 11) {
            throw new InvalidParameterException("CPF inválido");
        }

        this.idCliente = proximo_id;
        proximo_id++;
        this.nome = nome;
        this.cpf = cpf;
        this.tipoClente = new ClienteRegular();
    }

    public void addPedido(Pedido novoPedido) {
        listaPedidos.add(novoPedido);
    }

    public void cancelarPedido(int posicao) {
        listaPedidos.remove(posicao);
    }

    public void atualizarTipoCliente() {
        Calendar calendario = Calendar.getInstance();
        Date dataAtual = calendario.getTime();

        double junior_valorTotalPedidos = 130;
        int junior_quantidadeTotalPedidos = 10;
        double senior_valorTotalPedidos = 350;
        int senior_quantidadeTotalPedidos = 30;
        double cliente_valorTotalPedidos = 0;
        int cliente_quantidadeTotalPedidos = 0;

        for (Pedido pedido : listaPedidos) {
            String dataPedido = pedido.getDate();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date data = null;
            try {
                data = formato.parse(dataPedido);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }

            Calendar dataPedidoFormatado = Calendar.getInstance();
            calendario.setTime(data);
            
            
        }

    }

    public void calcDesconto(double precoPedido) {
        tipoClente.calcDesconto(precoPedido);
    }

    public String relatorioPedido() {
        return "";
    }

    public String relatorioPedido(String dataInicial, String dataFinal) {
        return "";
    }

    // TODO - compareTodos pedidos.

}

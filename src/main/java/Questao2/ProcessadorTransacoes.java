// Target (Alvo) - A interface moderna esperada pelo nosso sistema

public interface ProcessadorTransacoes {
    RespostaTransacao autorizar(String cartao, double valor, String moeda);
}
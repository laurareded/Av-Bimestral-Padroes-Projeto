// Modelo de dados moderno para o retorno
public class RespostaTransacao {
    private boolean sucesso;
    private String codigoAutorizacao;
    private String mensagem;

    public RespostaTransacao(boolean sucesso, String codigoAutorizacao, String mensagem) {
        this.sucesso = sucesso;
        this.codigoAutorizacao = codigoAutorizacao;
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return "Processamento: " + (sucesso ? "SUCESSO" : "FALHA") + 
               " | Autorizacao: " + codigoAutorizacao + " | Mensagem: " + mensagem;
    }
}
import java.util.HashMap;
import java.util.Map;

// Adapter - Converte a interface moderna para a legada
public class LegadoAdapter implements ProcessadorTransacoes {
    private SistemaBancarioLegado legado;
    private static final Map<String, String> CODIFICACAO_MOEDA = Map.of(
        "USD", "1",
        "EUR", "2",
        "BRL", "3"
    );

    /**
     * Justificativa do Padrão: Implementa a interface moderna (Target) e encapsula
     * a classe legada (Adaptee). É responsável por:
     * 1. Converter a assinatura do método.
     * 2. Converter tipos de dados (moeda String -> código numérico).
     * 3. Adicionar campos obrigatórios do legado que não existem no Target (ex: TIPO_TRANSACAO).
     * 4. Conversão bidirecional: mapear a resposta complexa do legado para o modelo simples RespostaTransacao.
     */
    public LegadoAdapter(SistemaBancarioLegado legado) {
        this.legado = legado;
    }

    @Override
    public RespostaTransacao autorizar(String cartao, double valor, String moeda) {
        // --- 1. Conversão da Interface Moderna para o Legado (HashMap) ---
        HashMap<String, Object> parametrosLegado = new HashMap<>();

        // Mapeamento de campos
        parametrosLegado.put("NUM_CARTAO", cartao);
        parametrosLegado.put("VALOR", valor);
        
        // **Restrição/Conversão de Moeda (String -> Código numérico Legado)**
        String codMoeda = CODIFICACAO_MOEDA.getOrDefault(moeda.toUpperCase(), "99");
        parametrosLegado.put("COD_MOEDA", codMoeda); 

        // **Tratamento de Campos Obrigatórios do Legado que Faltam no Target**
        parametrosLegado.put("TIPO_TRANSACAO", "CREDITO"); // Exemplo de campo obrigatório
        
        // Chamada ao Legado
        Map<String, Object> respostaLegado = legado.processarTransacao(parametrosLegado);

        // --- 2. Conversão da Resposta do Legado (Bidirecional) ---
        String status = (String) respostaLegado.get("STATUS_COD");
        boolean sucesso = "00".equals(status);
        String auth = sucesso ? (String) respostaLegado.get("AUTORIZACAO") : "N/A";
        String mensagem = sucesso ? "Autorização OK" : (String) respostaLegado.get("MSG_ERRO");

        return new RespostaTransacao(sucesso, auth, mensagem);
    }
}
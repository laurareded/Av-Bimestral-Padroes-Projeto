import java.util.HashMap;
import java.util.Map;

public class SistemaBancarioLegado {
    
    public Map<String, Object> processarTransacao(HashMap<String, Object> parametros) {
        System.out.println("\n[LEGADO] Recebendo processamento de transação...");
        System.out.println("[LEGADO] Parâmetros recebidos: " + parametros);

        String moedaCodigo = (String) parametros.get("COD_MOEDA"); 

        Map<String, Object> respostaLegado = new HashMap<>();


        if ("99".equals(moedaCodigo)) { // Se a moeda foi mapeada para o código de fallback
             respostaLegado.put("STATUS_COD", "98"); 
             respostaLegado.put("MSG_ERRO", "Moeda inválida ou não codificada (Código 99) no Legado.");
             System.out.println("[LEGADO] Retorno Legado: " + respostaLegado);
             return respostaLegado;
        }
        
        if (moedaCodigo == null || parametros.get("VALOR") == null) {
             respostaLegado.put("STATUS_COD", "99");
             respostaLegado.put("MSG_ERRO", "Campos obrigatórios ausentes no Legado.");
             System.out.println("[LEGADO] Retorno Legado: " + respostaLegado);
             return respostaLegado;
        }

        // Simulação de processamento bem-sucedido
        respostaLegado.put("STATUS_COD", "00");
        respostaLegado.put("AUTORIZACAO", "AUTH-" + Math.abs(((String) parametros.get("NUM_CARTAO")).hashCode()));
        respostaLegado.put("TS_PROCESSAMENTO", System.currentTimeMillis());
        
        System.out.println("[LEGADO] Retorno Legado: " + respostaLegado);
        return respostaLegado;
    }
}
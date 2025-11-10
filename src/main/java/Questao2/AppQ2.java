public class AppQ2 {
    public static void main(String[] args) {
        // Instancia o legado e o adapter
        SistemaBancarioLegado legado = new SistemaBancarioLegado();
        ProcessadorTransacoes processador = new LegadoAdapter(legado);

        System.out.println("--- Processando Transação 1: Sucesso (BRL) ---");
        RespostaTransacao resposta1 = processador.autorizar("1234-5678-9012-3456", 150.75, "BRL");
        System.out.println("\n[MODERNO] Resposta Recebida: " + resposta1);

        System.out.println("\n===============================================");
        
        System.out.println("--- Processando Transação 2: Falha (Moeda Inválida) ---");
        // O Adapter tratará a moeda inválida e a converterá para "99" (ou fará o fallback)
        RespostaTransacao resposta2 = processador.autorizar("9876-5432-1098-7654", 500.00, "JPY");
        System.out.println("\n[MODERNO] Resposta Recebida: " + resposta2);
    }
}
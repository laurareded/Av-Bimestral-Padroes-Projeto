public class AppQ3 {
    public static void main(String[] args) {
        ContextoUsina usina = new ContextoUsina();

        // 1. OPERACAO_NORMAL -> ALERTA_AMARELO (se temperatura > 300°C)
        usina.acionarAlertaTemp(310.0); 

        // 2. ALERTA_AMARELO (Se temperatura > 400°C por 30s) - Simulado por outra chamada
        // (Assumindo que AlertaAmarelo implementa uma checagem de tempo)
        // Neste exemplo, vamos para VERMELHO
        usina.acionarAlertaTemp(410.0); // Transição simulada para Vermelho

        // 3. ALERTA_VERMELHO -> EMERGENCIA (se resfriamento falhar)
        usina.acionarFalha("RESFRIAMENTO"); 

        // Voltando para ALERTA VERMELHO para testar EMERGENCIA
        if (!usina.getEstadoAtualNome().equals("ALERTA_VERMELHO")) {
        // Transiciona rapidamente para ALERTA VERMELHO
        usina.mudarEstado(new OperacaoNormal());
        usina.acionarAlertaTemp(310.0); // -> AMARELO
        usina.mudarEstado(new AlertaVermelho()); // Simula o tempo
}

        System.out.println("\n--- Teste de Transição para EMERGENCIA ---");
        // 4. ALERTA_VERMELHO -> EMERGENCIA (se sistema de resfriamento falhar)
        usina.acionarFalha("RESFRIAMENTO"); 
        System.out.println("Estado após falha crítica: " + usina.getEstadoAtualNome());

        // O único reset possível após EMERGENCIA deve ser para DESLIGADA
        usina.acionarReset();
        System.out.println("Estado após Reset: " + usina.getEstadoAtualNome());
        System.out.println("----------------------------------------");
        
        // 4. Teste do modo Manutenção (Sobrescrita)
        System.out.println("\n--- Teste de Modo Manutenção ---");
        usina.mudarEstado(new OperacaoNormal()); // Volta para um estado inicial para testar
        
        usina.acionarManutencao();
        System.out.println("Estado atual após manutenção: " + usina.getEstadoAtualNome());
        
        usina.acionarAlertaTemp(500.0); // No modo manutenção, a transição é ignorada
        
        usina.acionarReset(); // Retorna ao estado salvo
        System.out.println("Estado atual após Reset da Manutenção: " + usina.getEstadoAtualNome());
    }
}
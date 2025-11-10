// Estado Concreto 4: Alerta Vermelho
public class AlertaVermelho implements EstadoUsina {
    @Override
    public String getNomeEstado() { return "ALERTA_VERMELHO"; }

    @Override
    public void aumentoTemperatura(ContextoUsina contexto, double temperatura) {
        System.out.println("!!! PERIGO: Temperatura alta em ALERTA VERMELHO. Manter observação.");
    }

    /**
     * Justificativa da Transição: Condição crítica e unidirecional.
     * EMERGENCIA só pode ser ativada a partir de ALERTA_VERMELHO (Regra de Negócio).
     */
    @Override
    public void falhaSistema(ContextoUsina contexto, String sistema) {
        if ("RESFRIAMENTO".equals(sistema.toUpperCase())) {
            System.out.println("!!! FALHA CRÍTICA NO RESFRIAMENTO. Transicionando para EMERGENCIA.");
            contexto.mudarEstado(new Emergencia());
        } else {
            System.out.println("-> Falha em outro sistema. Permanecendo em ALERTA VERMELHO.");
        }
    }

    @Override
    public void reset(ContextoUsina contexto) {
        System.out.println("-> Reset. Voltando para ALERTA AMARELO (Evitando transição circular perigosa para NORMAL).");
        contexto.mudarEstado(new AlertaAmarelo()); // Transição controlada para um estado menos crítico
    }

    @Override
    public void manutencao(ContextoUsina contexto) {
        System.out.println("!!! Manutenção não permitida a partir de ALERTA VERMELHO.");
    }
}
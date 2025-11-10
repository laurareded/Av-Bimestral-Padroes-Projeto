// Estado Concreto 2: Alerta Amarelo
public class AlertaAmarelo implements EstadoUsina {
    private long tempoAlertaInicio = System.currentTimeMillis();

    @Override
    public String getNomeEstado() { return "ALERTA_AMARELO"; }

    /**
     * Justificativa da Transição: Se a temperatura exceder 400°C por mais de 30 segundos,
     * transiciona para ALERTA_VERMELHO (Regra de Negócio).
     */
    @Override
    public void aumentoTemperatura(ContextoUsina contexto, double temperatura) {
        long tempoDecorrido = (System.currentTimeMillis() - tempoAlertaInicio) / 1000;
        
        if (temperatura > 400.0) {
            if (tempoDecorrido >= 30) {
                System.out.println("-> Condição CRÍTICA: Temp > 400°C por mais de 30s. Transicionando para ALERTA VERMELHO.");
                contexto.mudarEstado(new AlertaVermelho());
            } else {
                System.out.println("-> Temp alta, mas tempo de alerta ( " + tempoDecorrido + "s) não atingiu 30s. Permanecendo em AMARELO.");
            }
        } else if (temperatura <= 300.0) {
            System.out.println("-> Temperatura sob controle. Voltando para OPERACAO NORMAL.");
            contexto.mudarEstado(new OperacaoNormal()); // Transição bidirecional segura
        } else {
             System.out.println("-> Temp elevada, mas estável. Permanecendo em ALERTA AMARELO.");
        }
    }

    @Override
    public void falhaSistema(ContextoUsina contexto, String sistema) {
        System.out.println("-> Falha no sistema " + sistema + " em ALERTA AMARELO. Aumentando cautela.");
        // Em alerta, qualquer falha pode levar ao próximo nível.
        contexto.mudarEstado(new AlertaVermelho());
    }

    @Override
    public void reset(ContextoUsina contexto) {
        System.out.println("-> Reset. Voltando para OPERACAO NORMAL.");
        contexto.mudarEstado(new OperacaoNormal());
    }
    
    @Override
    public void manutencao(ContextoUsina contexto) {
        System.out.println("-> Solicitada entrada em MANUTENÇÃO. Salvando estado anterior...");
        contexto.salvarEstadoAnterior(this);
        contexto.mudarEstado(new Manutencao());
    }
}
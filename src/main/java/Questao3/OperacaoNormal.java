// Estado Concreto 1: Operacao Normal
public class OperacaoNormal implements EstadoUsina {
    @Override
    public String getNomeEstado() { return "OPERACAO_NORMAL"; }

    /**
     * Justificativa da Transição: Se a temperatura exceder 300°C,
     * a transição para ALERTA_AMARELO é validada aqui.
     */
    @Override
    public void aumentoTemperatura(ContextoUsina contexto, double temperatura) {
        if (temperatura > 300.0) {
            System.out.println("-> Condição atingida: Temperatura > 300°C. Transicionando para ALERTA AMARELO.");
            contexto.mudarEstado(new AlertaAmarelo());
        } else {
            System.out.println("-> Temperatura estável. Permanecendo em OPERACAO NORMAL.");
        }
    }

    @Override
    public void falhaSistema(ContextoUsina contexto, String sistema) {
        System.out.println("-> Falha no sistema " + sistema + " em operação normal. Alerta!");
        // Em um sistema real, poderia ir direto para AlertaAmarelo ou Vermelho.
        contexto.mudarEstado(new AlertaAmarelo());
    }

    @Override
    public void reset(ContextoUsina contexto) {
        // Já está no estado mais seguro de operação.
        System.out.println("-> Reset. Permanecendo em OPERACAO NORMAL.");
    }
    
    @Override
    public void manutencao(ContextoUsina contexto) {
        System.out.println("-> Solicitada entrada em MANUTENÇÃO. Salvando estado anterior...");
        contexto.salvarEstadoAnterior(this); // Salva o estado atual
        contexto.mudarEstado(new Manutencao());
    }
}
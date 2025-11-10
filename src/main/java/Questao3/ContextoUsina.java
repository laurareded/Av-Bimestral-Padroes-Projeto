// Context (Contexto) - Mantém e gerencia o estado atual
public class ContextoUsina {
    private EstadoUsina estadoAtual;
    private EstadoUsina estadoAnterior; // Para o modo Manutenção

    /**
     * Justificativa do Padrão: O Contexto Usina armazena a referência
     * ao Estado Usina. Todas as ações delegam o comportamento para o
     * objeto de Estado atual (Princípio OCP). O Contexto gerencia a troca.
     */
    public ContextoUsina() {
        // Estado inicial
        this.estadoAtual = new OperacaoNormal(); 
        System.out.println("Usina iniciada em: " + this.estadoAtual.getNomeEstado());
    }

    public void mudarEstado(EstadoUsina novoEstado) {
        System.out.println("   [Transição] De " + this.estadoAtual.getNomeEstado() + " para " + novoEstado.getNomeEstado());
        this.estadoAtual = novoEstado;
    }

    // Métodos para o modo Manutenção
    public void salvarEstadoAnterior(EstadoUsina estado) {
        this.estadoAnterior = estado;
    }

    public void restaurarEstadoAnterior() {
        if (this.estadoAnterior != null) {
            this.mudarEstado(this.estadoAnterior);
            this.estadoAnterior = null;
        } else {
            System.out.println("ERRO: Não há estado anterior para restaurar. Voltando para NORMAL.");
            this.mudarEstado(new OperacaoNormal());
        }
    }

    // Ações que acionam a lógica de transição
    public void acionarAlertaTemp(double temperatura) {
        System.out.println("\n[AÇÃO] Checagem de temperatura: " + temperatura + "°C");
        estadoAtual.aumentoTemperatura(this, temperatura);
    }

    public void acionarFalha(String sistema) {
        System.out.println("\n[AÇÃO] Falha reportada: " + sistema);
        estadoAtual.falhaSistema(this, sistema);
    }
    
    public void acionarManutencao() {
        estadoAtual.manutencao(this);
    }

    public void acionarReset() {
        System.out.println("\n[AÇÃO] Acionando reset.");
        estadoAtual.reset(this);
    }

    public String getEstadoAtualNome() {
        return estadoAtual.getNomeEstado();
    }
}

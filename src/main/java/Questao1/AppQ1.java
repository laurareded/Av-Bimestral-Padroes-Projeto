public class AppQ1 {
    public static void main(String[] args) {
        ContextoFinanceiro contexto = new ContextoFinanceiro("Fundo Alfa", 100000.00, 252);

        // 1. Inicia com a primeira estratégia (VaR)
        ProcessadorDeRisco processador = new ProcessadorDeRisco(new VaRStrategy());
        System.out.println("--- 1. Cálculo Inicial (VaR) ---");
        System.out.println(processador.executarCalculo(contexto));
        
        System.out.println("\n----------------------------------");

        // 2. Troca a estratégia em tempo de execução (Expected Shortfall)
        processador.setEstrategia(new ExpectedShortfallStrategy());
        System.out.println("--- 2. Cálculo após troca (ES) ---");
        System.out.println(processador.executarCalculo(contexto));

        System.out.println("\n----------------------------------");

        // 3. Troca novamente (Stress Testing)
        processador.setEstrategia(new StressTestingStrategy());
        System.out.println("--- 3. Cálculo Final (Stress) ---");
        System.out.println(processador.executarCalculo(contexto));
    }
}
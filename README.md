# 🎓 Avaliação Prática Bimestral de Padrões de Projeto 

## UNIVERSIDADE POSITIVO - Padrões de Projetos

---

## 💡 Sobre o Projeto

Este repositório contém a solução prática para a avaliação bimestral de Padrões de Projeto, demonstrando a aplicação dos princípios **SOLID** e padrões de *design* para resolver problemas de arquitetura de *software* complexos em diferentes contextos (financeiro, legado, industrial e fiscal).

## 🚀 Padrões Aplicados e Contextos

| Questão | Contexto do Problema | Padrão Aplicado | Objetivo Principal |
| :---: | :--- | :--- | :--- |
| **1** | Processamento de Risco Financeiro (VaR, ES) | **Strategy** | Permitir a troca dinâmica de algoritmos em tempo de execução. |
| **2** | Integração com Sistema Bancário Legado | **Adapter (Wrapper)** | Converter interfaces e tipos de dados incompatíveis (ex: moeda para código) e injetar campos obrigatórios do legado. |
| **3** | Controle de Usina Nuclear (Estados Complexos) | **State** | Isolar o comportamento e as regras de transição em classes de estado, prevenindo transições inválidas (ex: EMERGENCIA só após ALERTA_VERMELHO). |
| **4** | Validação de Documentos Fiscais (NF-e) | **Chain of Responsibility** | Encadear validadores, implementar *Circuit Breaker* (interrupção após 3 falhas) e garantir capacidade de *Rollback* em modificações de estado. |

---


O código está estruturado em pacotes (`Questao1`, `Questao2`, etc.) para manter o Princípio da Responsabilidade Única (SRP) e a organização.



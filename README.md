# 07-java-thread

# Mutex

Mutex (abreviação de Mutual Exclusion ou Exclusão Mútua) é um mecanismo de sincronização usado para evitar que múltiplas threads ou processos acessem o mesmo recurso compartilhado ao mesmo tempo. 

- Funciona como uma tranca ou cadeado digital.

---

# Problema:
Quando duas ou mais threads ou processos tentam modificar a mesma variável, arquivo ou banco de dados ao mesmo tempo, os dados podem ficar corrompidos.

---

# Seção Crítica

No contexto da computação paralela, uma `seção crítica` é um trecho de código que acessa um recurso compartilhado que não pode ser acessado simultaneamente por mais de uma linha de execução (thread ou processo).

Se duas ou mais threads executarem esse trecho ao mesmo tempo, pode ocorrer uma `condição de corrida`, corrompendo os dados.

## Características Principais

- Acesso Exclusivo: apenas uma thread pode executar a seção crítica por vez.

- Recursos Compartilhados: envolve variáveis globais, arquivos, conexões de banco de dados ou memória.

- Sincronização: exige mecanismos de controle (como Mutex, Semáforos ou travas) para barrar acessos simultâneos.

## Exemplo: 

**Saldo Bancário**:

Imagine duas threads tentando sacar R$ 70 da mesma conta corrente (saldo de R$ 100) ao mesmo tempo:

- Sem Seção Crítica Protegida: ambas leem o saldo como R$ 100, ambas aprovam o saque e o saldo final vira R$ -40 (um prejuízo para o banco, pois R$ 140 foram retirados).

- Com Seção Crítica Protegida: a primeira thread tranca a conta, lê o saldo, faz o saque e atualiza para R$ 30. Só então a segunda thread entra, vê o saldo como R$ 30 e o saque é negado.

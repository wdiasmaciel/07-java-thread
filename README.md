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

# Comandos

```bash
javac *.java
```

```bash
java Main
```

---

# Exercício

## Exercício 1: Modificação do Exemplo (Bloqueio sem Espera Infinita)

Objetivo: modificar o exemplo da ContaBancaria para que uma thread não fique travada para sempre esperando a outra liberar o saldo.

Instruções: no método sacar, substitua a chamada `mutex.lock()` pelo método `mutex.tryLock()`. Se o método retornar `true`, o saque ocorre normalmente. Se retornar `false`, a thread deve desistir imediatamente e exibir a mensagem: `"[NomeThread] desistiu do saque porque a conta estava ocupada por outra operação."`

## Exercício 2: O Sistema de Votação Online (Contador Global)

Objetivo: criar um contador de votos concorrente e seguro.

Cenário: uma eleição possui uma variável global `int totalVotos = 0`. Crie uma classe `UrnaEletronica` que implementa `Runnable` e que roda um loop adicionando 100 votos ao contador global. Instancie 3 threads rodando essa mesma urna ao mesmo tempo.

Desafio: use um Mutex para garantir que nenhuma thread sobrescreva o voto da outra. 

Ao final, a `main` deve exibir exatamente 300 votos.

## Exercício 3: Reserva de Assentos em Cinema (Verificação Dupla)

Objetivo: evitar que duas pessoas comprem o mesmo assento físico ao mesmo tempo.

Cenário: crie uma classe `Cinema` que gerencia um vetor booleano `boolean[] assentos = new boolean[10]` (onde false significa livre).

Implementação: crie uma thread de compras que tenta reservar o assento de número 5. Use um Mutex para proteger o vetor. Se duas threads tentarem comprar o assento 5 simultaneamente, a primeira tranca, compra (muda para true) e a segunda deve receber o aviso: `"Assento indisponível"`.

## Exercício 4: O Gerador de Identificadores Únicos (ID Generator)

Objetivo: garantir a geração incremental e sequencial de IDs em um ambiente multithread.

Cenário: um sistema de banco de dados precisa gerar IDs sequenciais (1, 2, 3, 4...). Crie uma classe com um método `obterProximoId()`.

Implementação: dispare 5 threads simultâneas, em que cada uma pede um ID e o exibe no console. Proteja o incremento da variável de ID com um Mutex para que nenhum ID seja duplicado ou pulado na tela.

## Exercício 5: Gravação em Arquivo de Log Compartilhado

Objetivo: simular múltiplas partes de um sistema escrevendo mensagens em uma mesma saída (simulando um arquivo).

Cenário: crie uma classe `GravadorLog` com um método `escreverMensagem(String msg)`. Esse método deve imprimir a abertura da mensagem, usar um `Thread.sleep(300)` para simular a lentidão da gravação e depois imprimir o fechamento da mensagem.

Desafio: sem o Mutex, as palavras das threads vão se misturar na tela. Use o Mutex para garantir que a mensagem de uma thread seja impressa por completo antes de a próxima começar.
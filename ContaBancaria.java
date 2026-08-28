import java.util.concurrent.locks.ReentrantLock;

public class ContaBancaria {
    private int saldo = 0;

    // Cria o Mutex (cadeado de exclusão mútua):
    private final ReentrantLock mutex = new ReentrantLock();

    public ContaBancaria(int saldo){
        this.saldo = saldo;
    }

    public void sacar(String nomeThread, int valor) {
        System.out.println(nomeThread + " está tentando sacar R$" + valor);

        // Tenta pegar a chave do Mutex. Se outra thread já trancou, esta espera aqui.
        mutex.lock(); 
        
        try {
            // --- INÍCIO DA REGIÃO CRÍTICA ---
            // Apenas UMA thread por vez consegue executar este bloco de código.
            if (saldo >= valor) {
                System.out.println(nomeThread + " verificou o saldo: R$" + saldo);
                
                // Simula uma pequena demora no processamento do saque:
                Thread.sleep(500); 
                
                saldo -= valor;
                System.out.println(nomeThread + " realizou o saque! Saldo atual: R$" + saldo);
            } else {
                System.out.println(nomeThread + " não pôde sacar. Saldo insuficiente: R$" + saldo);
            }
            // --- FIM DA REGIÃO CRÍTICA ---
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Obrigatoriamente destranca o Mutex no bloco 'finally'.
            // Isso garante que a chave seja liberada mesmo se ocorrer um erro no try.
            mutex.unlock(); 
            System.out.println(nomeThread + " liberou o Mutex.");
        }
    }
}


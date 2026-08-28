// Exemplo de Mutex:
public class Main {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria(100);

        // Criamos duas threads tentando sacar R$70 ao mesmo tempo do saldo de R$100:
        Thread t1 = new Thread(() -> conta.sacar("Thread_A", 70));
        Thread t2 = new Thread(() -> conta.sacar("Thread_B", 70));

        t1.start();
        t2.start();
    }
}

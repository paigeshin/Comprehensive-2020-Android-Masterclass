public class LearnSynchronizedKeyword {

    //2개의 쓰레드가 접근하고자 하는 데이터
    private int balance = 0;

    public static void main(String[] args) {

        LearnSynchronizedKeyword appEntry = new LearnSynchronizedKeyword();
        appEntry.goingThroughLife();

    }

    private void goingThroughLife(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    add();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    subtract();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join(); //thread가 끝날 때까지 기다림
            t2.join(); //thread가 끝날 때까지 기다림
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //여기서 의도하는 결과 값: 0.
        System.out.println("Balance: " + balance);
    }

    private void add(){
        synchronized (this) {
            balance++;
        }
    }

    private void subtract(){
        synchronized (this) {
            balance--;
        }
    }

    /*
    private void add(){
        balance++;
    }

    private void subtract(){
        balance--;
    }
     */


}

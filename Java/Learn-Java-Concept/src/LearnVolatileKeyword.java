import java.util.Scanner;

//Thread is basically depending upon OS. It's not just Java

class Counter extends Thread {

    private volatile boolean counting = true;
    private int counter = 1;

    @Override
    public void run() {
        super.run();

        while (counting){
            System.out.println(counter);
            counter++;
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    void stopCounting(){
        counting = false;
    }

}

public class LearnVolatileKeyword {

    public static void main(String[] args) {

        //아래의 기본적인 로직은 enter를 누르면 count를 멈추는 거임

        Counter c = new Counter();
        //Be aware that Main Thread is already running
        c.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        //Enter를 눌러도 아래 함수가 작동을 안함. => 이전에 실행한 thread에서 counting value(boolean값)가 cache가 되어있기 때문이다.
        c.stopCounting();
    }

}

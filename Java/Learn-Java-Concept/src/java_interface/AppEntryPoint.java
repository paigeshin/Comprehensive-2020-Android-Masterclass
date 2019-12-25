package java_interface;

interface ABC {

    void show();
    void showTwice();

}

public class AppEntryPoint {

    public static void main(String[] args) {
        ABC instance = new ABC() {
            @Override
            public void show() {

            }

            @Override
            public void showTwice() {

            }
        };

        instance.show();


    }

}

abstract class SingleTonClass {

    private static volatile SingleTonClass INSTANCE;

    public static SingleTonClass getInstance(){
        if(INSTANCE == null){
            synchronized (SingleTonClass.class){
                if(INSTANCE == null){
                    INSTANCE = new SingleTonClass() {
                        @Override
                        void inherit() {

                        }
                    };
                }
            }
        }
        return INSTANCE;
    }

    public void sayHi(){
        System.out.println("Hi");
    }

    public void sayBye(){
        System.out.println("Bye");
    }

    abstract void inherit();

}

class newClass extends SingleTonClass {

    @Override
    void inherit() {

    }
}


public class LearnAbstractClassSingleTon {

    public static void main(String[] args) {
        SingleTonClass singleTonClass = SingleTonClass.getInstance();
        singleTonClass.sayHi();
    }

}

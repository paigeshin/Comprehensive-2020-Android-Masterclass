abstract class Dog {

    private String breed;

    public Dog(String breed) {
        this.breed = breed;
    }

    public void bark(){
        System.out.println(breed + " Bark");
    }

    public abstract void poop();

}

abstract class SuperDog extends Dog{

    public SuperDog(String breed) {
        super(breed);
    }
}

class Chihuahua extends Dog {

    private String breed;

    public Chihuahua(String breed) {
        super(breed);
        this.breed = breed;
    }

    @Override
    public void poop() {
        System.out.println(breed + "Dog Pooped!");
    }

}

public class LearnAbstractClass {

    public static void main(String[] args) {

        //Anonymous class임
        Dog dog = new Dog("Dog") {
            @Override
            public void poop() {

            }
        };

        dog.bark();

        //상속받은 class임
        Chihuahua chihuahua = new Chihuahua("chihuahua");
        chihuahua.bark();
        chihuahua.poop();

        //abstract를 상속받은 abstract class instance화
        SuperDog superDog = new SuperDog("Super Dog") {
            @Override
            public void poop() {

            }
        };

    }

}

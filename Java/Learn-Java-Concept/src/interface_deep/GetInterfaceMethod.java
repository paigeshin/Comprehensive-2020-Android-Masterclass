package interface_deep;

public class GetInterfaceMethod {


    private SharedValue sharedValue;

    GetInterfaceMethod(SharedValue sharedValue) {
        this.sharedValue = sharedValue;
        setText();
    }

    private void setText(){
        sharedValue.print("Hello");
    }


}

package interface_deep;


public class EntryPoint implements SharedValue{

    public void main(String[] args) {
        GetInterfaceMethod getInterfaceMethod = new GetInterfaceMethod(this);
    }

    @Override
    public void print(String text) {
        System.out.println(text);
    }
}

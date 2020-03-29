package stringuri.stringbuffer;

public class Exemplu3 {
    public static void main(String[] args) {
//        String buffers are saafe for use by multiple threads
        StringBuffer stringBuff = new StringBuffer();
        stringBuff.append("Buna ziua");
        System.out.println(stringBuff);
        stringBuff.replace(2, 4, "sal");
        System.out.println(stringBuff);
    }
}

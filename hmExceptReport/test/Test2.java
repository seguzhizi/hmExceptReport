import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Test2
{
    public static void main(String[] args) throws NoSuchAlgorithmException
    {
//        String str = "zjj";
//        byte [] buf = str.getBytes();
//        MessageDigest md5 = MessageDigest.getInstance("MD5");
//        md5.update(buf);
//        byte [] tmp = md5.digest();
//        StringBuilder sb = new StringBuilder();
//        for (byte b:tmp) {
//            sb.append(Integer.toHexString(b&0xff));
//        }
//        System.out.println(sb);
        int m = 11;
        int n = 5;
        int k = 0;
        if(m % n == 0){
            k = m / n;
        } else {
            k = (m/n + 1);
        }
        System.out.println(k);
        
    }
}

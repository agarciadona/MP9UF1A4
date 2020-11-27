import javax.crypto.SecretKey;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class e2 {
    public static void main(String[] args) throws IOException {
        //Exercici 1.5 i 1.7

        String msg = "Patata";
        SecretKey sk = e1.keygenKeyGeneration(256);

        byte[] msgXifrat5 = e1.encryptData(sk, msg.getBytes());
        byte[] msgDesxifrat5 = e1.decryptData(sk, msgXifrat5);
        String Desxifrat5 = new String(msgDesxifrat5);

        System.out.println(Desxifrat5);
        System.out.println(Arrays.toString(sk.getEncoded()));
        System.out.println(sk.getFormat());


        //Exercici 1.6

        String msg2 = "Hola mundo";
        String passwd = "safepass";
        SecretKey sk2 = e1.passwordKeyGeneration(passwd, 256);

        byte[] msgXifrat6 = e1.encryptData(sk2, msg2.getBytes());
        byte[] msgDesxifrat6 = e1.decryptData(sk2, msgXifrat6);
        String Desxifrat6 = new String(msgDesxifrat6);
        System.out.println(Desxifrat6);


        //Exercici 1.8

        String passwd2 = "contrasegura";
        SecretKey sk3 = e1.passwordKeyGeneration(passwd2, 256);

        try {
            byte[] msgDesxifrat8 = e1.decryptData(sk3, msgXifrat6);
            String Desxifrat8 = new String(msgDesxifrat8);
            System.out.println(Desxifrat8);
        } catch (Exception e) {
            System.out.println(e);
        }


     //Exercici 2

        String home = System.getProperty("user.home");
        File file = new File(home + "/" + "clausA4.txt");
        FileReader Fr = new FileReader(file);
        BufferedReader Br = new BufferedReader(Fr);
        String linia = Br.readLine();
        Path path = Paths.get(home + "/" + "textamagat");

        byte[] TextBytes = Files.readAllBytes(path);
        boolean bucle =false;

        while (!bucle) {
            try {
                SecretKey password = e1.passwordKeyGeneration(linia, 128);
                byte[] textDescobert = e1.decryptData(password, TextBytes);
                String MsgDescobert = new String(textDescobert);
                System.out.println(MsgDescobert);
                System.out.println("La contraseña es: "+linia);
                bucle=true;
                break;

            } catch (Exception e) {
                System.out.println("La contraseña no es: " + linia + "  " + e);
                linia = Br.readLine();
            }


        }
    }
}
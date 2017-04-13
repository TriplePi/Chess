/**
 * Created by Useless on 30.03.2017.
 */
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.Objects;

class Net extends Thread
{
    Net(String ip, boolean logicColour)
    {
        System.out.println("kokoko");
        while (true){
            netter("localhost",8005,true);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void netter(String ip, int port, boolean logicColour){
        Socket s;
        try {
            s = new Socket(ip, port);
        }
        catch (ConnectException e){
            JOptionPane.showMessageDialog(null,"Smt with ip or port");
            e.printStackTrace();
            netter(ip,port,logicColour);
            return;
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null,"Smt with ip or port");
            return;
        }
        try
        {
            // открываем сокет и коннектимся к localhost:3128
            // получаем сокет сервера



            // берём поток вывода и выводим туда первый аргумент
            // заданный при вызове, адрес открытого сокета и его порт
            String colour;
            if(logicColour)
                colour = "white\r\n\r\n";
            else colour = "black\r\n\r\n";

            s.getOutputStream().write(colour.getBytes());

            // читаем ответ
            byte buf[] = new byte[64*1024];
            int r = s.getInputStream().read(buf);
            String data = new String(buf, 0, r);

            // выводим ответ в консоль
            //System.out.println(data);
            if (Objects.equals(data, "BAD\r\n\r\n")){
                s.close();
                netter(ip,port,logicColour);
            }
            data = data.substring(0,64);
            data = new StringBuilder(data).reverse().toString();
            //System.out.println(data);
            Collocation collocation = Collocation.convert(data);
            AI ai = new AI(collocation,logicColour);
            ai.colculate();
            collocation.updateCollocation();
            System.out.println("eto to");
            collocation.test();
            String pole = "";
            pole = collocation.reverseConvert();
            pole = new StringBuilder(pole).reverse().toString();
            pole+="\r\n\r\n";
            System.out.println(pole);
            s.getOutputStream().write(pole.getBytes());
            s.close();

        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений
    }
}

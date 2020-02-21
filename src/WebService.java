import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class WebService {
    public static void main(String[] args){
        int port = 7777;
        try {
            ServerSocket serverSocket = new ServerSocket(7777);

            while(true) {
                Socket socket = serverSocket.accept();
                Service service = new Service(socket);
                service.start();

            }



        }catch (IOException e){System.out.println("IOException serversocket");}



    }

    private static class Service extends Thread{
        private Socket socket;

        public Service(Socket socket){
            this.socket = socket;
        }

        public void run(){
            Scanner scanner;
            DataOutputStream dataOutputStream;

            try {
                scanner = new Scanner(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                MapMaker maker = new MapMaker();

                BufferedImage map = maker.getMapBuffer();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(map, "png", baos);
                baos.flush();
                byte[] imageArray = baos.toByteArray();
                baos.close();

                dataOutputStream.writeInt(imageArray.length);
                dataOutputStream.write(imageArray);

                socket.close();
            }catch (IOException e){System.out.println("IOException thread");}
        }



    }

}

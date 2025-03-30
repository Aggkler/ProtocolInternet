package org.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ScannerTcpUdp {
    public static void main(String[] args) {
        String name = "localhost";
        int under = 1;
        int top = 65535;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--name":
                    name = args[++i];
                    break;
                case "--under":
                    under = Integer.parseInt(args[++i]);
                    break;
                case "--top":
                    top = Integer.parseInt(args[++i]);
                    break;
            }
        }

        try {
            InetAddress inetAddress = InetAddress.getByName(name);
            String ipAddress = inetAddress.getHostAddress();

            for (int port = under; port <= top; port++) {
                try (Socket socket = new Socket()) {
                    socket.setSoTimeout(1000);
                    socket.connect(new InetSocketAddress(ipAddress, port), 1000);
                    System.out.println("Socket открыт:" + port);
                } catch (IOException e) {
                    // ignore
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("Неверный Ip-адрес или имя порта");
        }
    }
}

package utilities;

import java.util.Scanner;

public class Console {
    private static final Scanner in = new Scanner(System.in);

    public static int readPort() {
        String port = "";
        try {
            System.out.print("Порт:\n~ ");

            port = in.nextLine();
            if (port.replace(" ", "").equals("")) return readPort();
            return Integer.parseInt(port);
        } catch (Exception e) {
            return readPort();
        }
    }

    public static String readHost() {
        System.out.print("Хост:\n~ ");
        String host = in.nextLine();
        if (host.replace(" ", "").equals("")) return readHost();
        return host;
    }

}

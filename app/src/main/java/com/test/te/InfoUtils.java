package com.test.te;

import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class InfoUtils {
    static String host;
    static int port;
    Socket s;

    Socket getSocket()
    {
        return this.s;
    }
    String sendData(String data,Socket s) throws IOException {
        System.out.println("send:"+data);
        long timeout = 3000;
        long now = System.currentTimeMillis();
        this.s = s;
        String mess = null;
        if(this.s==null)
        {
            this.s = new Socket(host, port);
        }
        DataInputStream in = null;
        DataOutputStream out;
        if(!this.s.isClosed())
        {
            in = new DataInputStream(this.s.getInputStream());
            out = new DataOutputStream(this.s.getOutputStream());
            out.write(data.getBytes());
            //out.flush();
            // out.writeUTF("GT200-123TM321");
            //out.flush();
            byte[] a ;
            //  while (true) {
            while (System.currentTimeMillis()-now <timeout){
                {
                    if (in.available() > 0) {
                        a = new byte[in.available()];
                        in.read(a);
                        mess = new String(a);
                        break;
                    }
                }
            }
            //    in.close();
            //   out.close();
            System.out.println("rec:"+mess);
        }


        return mess;
    }
}

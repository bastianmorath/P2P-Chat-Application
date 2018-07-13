package ch.ethz.inf.vs.a1.rubfisch.p2pchat;

import android.util.Log;

import java.io.BufferedReader;

/**
 * Created by alexa on 11.12.2017.
 */

public class Listener extends Thread{
    BufferedReader input;
    boolean listening;

    public Listener(BufferedReader input){
        this.input=input;
    }

    public void run(){
        Log.d("Chat","Started listening to client");
        while(listening){
            try{
                String data;
                if((data=input.readLine())!=null){
                    Log.d("Chat","Listener calling receive");
                    ChatActivity.receive(data);

                }

            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }
}


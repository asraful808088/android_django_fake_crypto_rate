package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

import tech.gusavila92.websocketclient.WebSocketClient;

public class MainActivity extends AppCompatActivity {
    private  ArrayList<Item> list = new ArrayList<>(Arrays.asList(
            new Item("BTC","0"),
            new Item("ETH","0"),
            new Item("USDT","0"),
            new Item("BNB","0"),
            new Item("XRP","0")));
    private ViewAdapter viewAdapter;
    private WebSocketClient webSocketClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.mainView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (savedInstanceState!=null){
           list =savedInstanceState.getParcelableArrayList("savedList");
        }
        viewAdapter = new ViewAdapter(this,list);
        recyclerView.setAdapter(viewAdapter);
        createSocket();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webSocketClient.close();
    }

    private void createSocket(){
       URI uri;
       try {
           uri = new URI(Config.getWsURI());
       } catch (URISyntaxException e) {
           e.printStackTrace();
           return;
       }
       Gson gson= new Gson();

        webSocketClient = new WebSocketClient(uri) {


           @Override
           public void onOpen() {
               Log.d("active","websocket connected...");
               this.send("get rate");


           }

           @Override
           public void onTextReceived(String message) {
               CryptoType cryptoType = gson.fromJson(message,CryptoType.class);
               list =new ArrayList<>(Arrays.asList(
                       new Item("BTC",cryptoType.BTC),
                       new Item("ETH",cryptoType.ETH),
                       new Item("USDT",cryptoType.USDT),
                       new Item("BNB",cryptoType.BNB),
                       new Item("XRP",cryptoType.XRP)));
               runOnUiThread(() -> viewAdapter.setValue(list));
           }

           @Override
           public void onBinaryReceived(byte[] data) {

           }

           @Override
           public void onPingReceived(byte[] data) {

           }

           @Override
           public void onPongReceived(byte[] data) {

           }

           @Override
           public void onException(Exception e) {
               Log.d("error",e.getMessage());
           }

           @Override
           public void onCloseReceived() {

           }
       };

       webSocketClient.connect();
   }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("savedList", list);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString("cryptoList");
    }
}
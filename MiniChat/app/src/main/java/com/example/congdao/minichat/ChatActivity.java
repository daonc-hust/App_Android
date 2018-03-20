package com.example.congdao.minichat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    Firebase database;
    ListView lvChat;
    EditText edtChat;
    Button btnSend;

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mapping();

        Firebase.setAndroidContext(this);
        database = new Firebase("https://chat-82a4d.firebaseio.com/");

        arrayChat = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayChat);
        lvChat.setAdapter(adapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chatContent = MainActivity.edtUser.getText().toString() + ": " + edtChat.getText().toString();
                database.push().setValue(chatContent);
                edtChat.setText("");
                arrayChat.add(chatContent);
                adapter.notifyDataSetChanged();
            }
        });


//        FirebaseListAdapter<String> adapter = new FirebaseListAdapter<String>(database,String.class,android.R.layout.simple_list_item_1,this) {
//            @Override
//            protected void populateView(View v, String model, int position) {
//                TextView txt = (TextView) v.findViewById(android.R.id.text1);
//                txt.setText(model);
//            }
//        };
//
//        lvChat.setAdapter(adapter);
    }

    private void mapping() {
        lvChat = (ListView) findViewById(R.id.listViewChat);
        edtChat = (EditText) findViewById(R.id.editTextChat);
        btnSend = (Button) findViewById(R.id.buttonSend);
    }
}

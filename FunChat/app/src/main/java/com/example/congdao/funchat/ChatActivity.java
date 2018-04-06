package com.example.congdao.funchat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ChatActivity extends AppCompatActivity {

    EditText edtChat, edtReg;
    Button btnChat, btnReg;
    ListView lvChat, lvUser;
    ArrayAdapter adapterChat, adapterUser;
    ArrayList<String> arrayChat, arrayUser;

    private Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mapping();

        arrayChat = new ArrayList<>();
        adapterChat = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayChat);
        lvChat.setAdapter(adapterChat);

        arrayUser = new ArrayList<>();
        adapterUser = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayUser);
        lvUser.setAdapter(adapterUser);

        try {
            mSocket = IO.socket("http://192.168.0.102:8000");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mSocket.connect();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtReg.getText().toString().trim().length() > 0) {
                    String username = edtReg.getText().toString().trim();
                    mSocket.emit("client-send-user", username);
                }
                edtReg.setText("");
            }
        });

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtChat.getText().toString().trim().length() > 0) {
                    String chat = edtChat.getText().toString().trim();
                    mSocket.emit("client-send-chat", chat);
                }
                edtChat.setText("");
            }
        });

        mSocket.on("server-send-user", onListUser);
        mSocket.on("server-send-result", onListResult);
        mSocket.on("server-send-chat", onListChat);

    }

    private Emitter.Listener onListUser = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject object = (JSONObject) args[0];
                    try {
                        JSONArray array = object.getJSONArray("user");
                        arrayUser.clear();
                        for (int i = 0; i < array.length(); i++) {
                            arrayUser.add(array.getString(i));
                        }
                        adapterUser.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    private Emitter.Listener onListResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject object = (JSONObject) args[0];
                    boolean exist = true;
                    try {
                        exist = object.getBoolean("tonTai");

                        if (exist) {
                            Toast.makeText(ChatActivity.this, "Tài khoản này đã tồn tại", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ChatActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    private Emitter.Listener onListChat = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject object = (JSONObject) args[0];
                    try {
                        String chat = object.getString("chatContent");
                        arrayChat.add(chat);
                        adapterChat.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    private void mapping() {
        edtChat = (EditText) findViewById(R.id.editTextChat);
        edtReg = (EditText) findViewById(R.id.editTextReg);

        btnChat = (Button) findViewById(R.id.buttonChat);
        btnReg = (Button) findViewById(R.id.buttonRegister);

        lvChat = (ListView) findViewById(R.id.listViewChat);
        lvUser = (ListView) findViewById(R.id.listViewUser);
    }
}

package com.example.wallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btnlogin;
    TextView t1;
    EditText txtname, txtuname, txtph, txtdob, txtpswd, txtcpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnlogin = findViewById(R.id.button);
        txtname = findViewById(R.id.txt_name);
        txtuname = findViewById(R.id.txt_uname);
        txtdob = findViewById(R.id.txt_dob);
        txtph = findViewById(R.id.txt_ph);
        txtpswd = findViewById(R.id.txt_pwd);
        txtcpwd = findViewById(R.id.txt_cpwd);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtname.getText().toString().isEmpty() || txtuname.getText().toString().isEmpty() || txtdob.getText().toString().isEmpty()
                        || txtph.getText().toString().isEmpty() || txtcpwd.getText().toString().isEmpty() || txtcpwd.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fill all feilds", Toast.LENGTH_SHORT).show();
                }
                else if (!(txtcpwd.getText().toString()).equals(txtpswd.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Password Miss matched", Toast.LENGTH_SHORT).show();
                    txtcpwd.setError("Re-Enter");
                }
                else {
                    Insert();
                }

            }
        });

    }
    public void Insert()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://full-bottomed-cushi.000webhostapp.com/Wallet_Reg.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//If we are getting success from server
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();

                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject json_obj = jsonArray.getJSONObject(i);
                                // ram=json_obj.getString("ram");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                    }

                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
//Adding parameters t o request

         ;
                params.put("name",txtname.getText().toString());
                params.put("uname",txtuname.getText().toString());
                params.put("ph", txtph.getText().toString());
                params.put("dob",txtdob.getText().toString());
                params.put("pswd",txtpswd.getText().toString());

//returning parameter
                return params;
            }
        };

//Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}


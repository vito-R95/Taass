package com.example.projectasl2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView2;
    private RecyclerView.Adapter adapter2;
    private RecyclerView.LayoutManager layoutManager2;
    public ProgressDialog dialog;
    private static String urlGetDottori = "http://192.168.1.6:8080/api/dottori/getDottori";
    private static String urlGetAppuntamenti = "http://192.168.1.6:8080/api/appuntamenti/getAppuntamenti";
    private static String urlGetPazienti = "http://192.168.1.6:8080/api/pazienti/getPazienti";
    ArrayList<ExampleItemDottori> exampleItemsDottori;
    TextView benvenuto,benvenuto2;
    //public static Button invioPass;
    public static TextInputLayout password;
    public static ExtendedFloatingActionButton floatInvioPass;

    public static ArrayList<String> coloreDottori;
    public static ArrayList<String> appuntamenti;
    public static ArrayList<String> pazienti;
    String color="";
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coloreDottori = new ArrayList<>();

        exampleItemsDottori = new ArrayList<>();
        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        layoutManager2 = new LinearLayoutManager(getApplicationContext());
        adapter2 = new ExampleAdapterDottori(exampleItemsDottori);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(adapter2);

        benvenuto = findViewById(R.id.benvenuto);
        benvenuto.setText("Benvenuto in AppointMed");
        benvenuto2=findViewById(R.id.benvenuto2);
        benvenuto2.setText("Seleziona il tuo profilo");
        //invioPass = findViewById(R.id.invioPass);
        floatInvioPass=findViewById(R.id.FloatInvioPass);
        password = findViewById(R.id.password);
        context=recyclerView2.getContext();




        new FetchDataTask2().execute();


        floatInvioPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int p=ExampleAdapterDottori.pos;
                String pass= String.valueOf(password.getEditText().getText());
                for (int i=0; i<coloreDottori.size(); i++){
                    String pass2[]=coloreDottori.get(i).split("!");
                    String password=pass2[4];
                    String nome=pass2[2]+" "+pass2[3];
                    int position= Integer.parseInt(pass2[5]);
                        //Toast.makeText(view.getContext(), pass+" pass "+password, Toast.LENGTH_LONG).show();
                        if (i == p) {
                            if(i==position && pass.compareTo(password)==0) {
                            //Toast.makeText(view.getContext(), "password :"+pass+" "+position, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(view.getContext(), MainActivity2.class);
                            intent.putExtra("info", coloreDottori.get(i));
                            startActivity(intent);
                        }else{
                                Toast.makeText(view.getContext(), "password errata:"+pass, Toast.LENGTH_LONG).show();
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(getIntent());
                                overridePendingTransition(0, 0);
                            }
                    }
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }


    protected class FetchDataTask2 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String getDottori="";
            /*String getAppuntamenti= "";
            String getPazienti="";
            String all = "";*/

            URL url=null;
            HttpURLConnection con= null;
            try {
                url = new URL(urlGetDottori);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                getDottori = bf.readLine();


            } catch (IOException e) {
                e.printStackTrace();
                Log.i("ERRORE",e.getMessage());
            }finally {
                con.disconnect();
            }
            return getDottori;
        }

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            Toast.makeText(MainActivity.this, "Carico i dottori", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String s) {
           //parseJSON(s);
            //System.out.print("ALL"+ s);
            String id = "";
            String nome = "";
            String cognome = "";
            String colore = "";
            String pass="";
            String info="";
            int pos=0;
            try {
                JSONArray dottori = new JSONArray(s);
                //Log.e(TAG, "app: " + jsonMainNode);
                //int jsonArrLength = jsonMainNode.length();
                for (int i = 0; i < dottori.length(); i++) {
                    JSONObject object = dottori.getJSONObject(i);
                    id = object.getString("id");
                    nome = object.getString("nome");
                    cognome = object.getString("cognome");
                    colore = object.getString("colore");
                    pass = object.getString("password");
                    pos = i;
                    info = colore + "!" + id + "!" + nome + "!" + cognome + "!" + pass + "!" + pos;
                    coloreDottori.add(info);
                    //Log.i("INFO","INFO: "+coloreDottori.toString());
                    //color=colore;
                    //dottore.setText(nome+""+cognome);

                    exampleItemsDottori.add(new ExampleItemDottori(R.drawable.ic_baseline_account_circle_24, nome + " " + cognome, colore));

                }

                //coloreDottori.add(info);
                //Log.i("INFO","INFO: "+coloreDottori.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.i("ERRORE",e.getMessage());

            }










        }

        public void parseJSON(String s){
            /*String id = "";
            String nome = "";
            String cognome = "";
            String colore = "";
            String pass="";
            String info="";
            int pos=0;
            try {
                JSONArray dottori = new JSONArray(s);
                //Log.e(TAG, "app: " + jsonMainNode);
                //int jsonArrLength = jsonMainNode.length();
                for (int i = 0; i < dottori.length(); i++) {
                    JSONObject object = dottori.getJSONObject(i);
                    id = object.getString("id");
                    nome = object.getString("nome");
                    cognome = object.getString("cognome");
                    colore = object.getString("colore");
                    pass = object.getString("password");
                    pos = i;
                    info = colore + "!" + id + "!" + nome + "!" + cognome + "!" + pass + "!" + pos;
                    coloreDottori.add(info);
                    //Log.i("INFO","INFO: "+coloreDottori.toString());
                    //color=colore;
                    //dottore.setText(nome+""+cognome);

                    exampleItemsDottori.add(new ExampleItemDottori(R.drawable.ic_baseline_account_circle_24, nome + " " + cognome, colore));

                }

                //coloreDottori.add(info);
                //Log.i("INFO","INFO: "+coloreDottori.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.i("ERRORE",e.getMessage());

            }*/
        }

    }
}
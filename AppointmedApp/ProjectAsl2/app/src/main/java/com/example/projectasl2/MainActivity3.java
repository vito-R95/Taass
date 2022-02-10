package com.example.projectasl2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static com.example.projectasl2.MainActivity2.appuntamentiArr;
import static com.example.projectasl2.MainActivity2.invioDati;

public class MainActivity3 extends AppCompatActivity {
    public static String giorno;
    public static String info;
    public static RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<ExampleItemAppuntamenti> exampleItems;
    TextView today;
    private static String urlPostMessage="http://192.168.1.6:8080/api/messaggi/addMessaggio";
    private static String urlGetApp = "http://192.168.1.6:8080/api/appuntamenti/getAppuntamenti";
    private static String urlGetPaz = "http://192.168.1.6:8080/api/pazienti/getPazienti";

    Button conferma,confermaUpdate,close,closeUpdate;
    TextView domanda,domandaUpdate,attenzione,attenzioneUpdate;
    Dialog a,dialogUpdate;
    ArrayList<String> idPaz;
    ArrayList<String> oraArr;
    //ArrayList<String> invioDati;
    EditText time,date;
    View orizontalLine,verticalLine,orizontalLineDelete,verticalLineDelete;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        exampleItems = new ArrayList<>();
        idPaz= new ArrayList<>();
        oraArr= new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        adapter = new ExampleAdapterAppuntamenti(exampleItems);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        today = findViewById(R.id.textToday);

        giorno=getIntent().getStringExtra("giorno");
        //Log.i("giornoApp",giorno);
        today.setText("Appuntamenti del "+giorno);
       // Log.i("Appuntamenti",MainActivity2.appuntamentiArr.toString());

        getDate(MainActivity2.appuntamentiArr);
        relativeLayout=findViewById(R.id.relativeLayout);

       a=new Dialog(this);
       dialogUpdate=new Dialog(this);












        new FetchDataTask4().execute();
    }



    public void getDate(ArrayList<String> a){
        String gg="";
        String ora="";
        String idp="";
        String idd="";
        String tipoApp="";

        for (int i=0; i<a.size(); i++){
            String gg2[]=a.get(i).split("!");
            gg=gg2[0];
            ora=gg2[1];
            idp=gg2[2];
            tipoApp=gg2[4];

                if (gg.compareTo(giorno)==0){

                    Log.i("idp",idp);
                    idPaz.add(idp);
                    oraArr.add(getOra(ora));

                    exampleItems.add(new ExampleItemAppuntamenti(R.drawable.ic_baseline_access_time_24, getOra(ora), getP(info,idp), tipoApp));
                    slide(getOra(ora),idp);



                    Log.i("clickgiornocalendario",getOra(ora)+" ora "+oraArr.toString());
                    //Toast.makeText(getApplicationContext(), "gg uguale"+gg+" "+giorno+info, Toast.LENGTH_LONG).show();
                }else{
                    //Toast.makeText(getApplicationContext(), "gg diversi"+gg+" "+giorno, Toast.LENGTH_LONG).show();
                }
        }

    }



    protected class FetchDataTask4 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String getPazienti = "";
            try {
                URL url2 = new URL(urlGetPaz);
                HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
                con2.setRequestMethod("GET");
                con2.connect();
                BufferedReader bf2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
                getPazienti = bf2.readLine();
                //System.out.print("getPazienti"+ getPazienti);
                //all = getAppuntamenti + "!" + getPazienti;
                // System.out.print("ALL"+ all.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
            info=getPazienti;
            return getPazienti;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            parseJson(s);
        }
        public void parseJson(String s){
            Log.i("PAZ",s);
            /*String idP = "";
            String nome = "";
            String cognome="";
            String cf = "";
            try {
                JSONArray pazienti = new JSONArray(s);
                //Log.e(TAG, "app: " + jsonMainNode);
                //int jsonArrLength = jsonMainNode.length();
                for (int i = 0; i < pazienti.length(); i++) {
                    JSONObject object = pazienti.getJSONObject(i);
                    idP = object.getString("id");
                    nome = object.getString("nome");
                    cognome = object.getString("cognome");
                    cf = object.getString("cf");
                    //info=nome+"!"+cognome+"!"+idP+"!";
                    //Log.i("TAG",info);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        }
    }

    public String getOra(String ora) {
        String s = "";
        String ora2[] = ora.split(":");
        s = ora2[0] + ":" + ora2[1];
        return s;
    }

    public String getP(String paz, String idp) {
        //Log.i("TAG", arr.toString());
        String s = "";
        try {
            JSONArray jsonMainNode2 = new JSONArray(paz);
            for (int j = 0; j < jsonMainNode2.length(); j++) {
                JSONObject object2 = jsonMainNode2.getJSONObject(j);
                String id = object2.getString("id");
                String nome = object2.getString("nome");
                String cognome = object2.getString("cognome");

                if (id.compareTo(idp) == 0) {
                    s += nome + " " + cognome;
                    //Toast.makeText(MainActivity3.this, "nome e cognome" + s, Toast.LENGTH_LONG).show();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return s;
    }

    public void slide( String ora,String idp){

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Log.i("SLIDE",""+direction+" pos"+viewHolder.getLayoutPosition());
                if (direction==ItemTouchHelper.LEFT){
                    openDialog2(ora,idp,viewHolder);
                    Log.i("ORAARR",ora);

                }else{
                    openDialog(ora,idp,viewHolder);
                    Log.i("ORAARR",ora);
                }

            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

                        //.addSwipeLeftBackgroundColor(ContextCompat.getColor(MainActivity3.this,R.color.design_default_color_error))
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(MainActivity3.this,R.color.swipedelete))
                        .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(MainActivity3.this,R.color.swipeupdate))
                        .addSwipeRightActionIcon(R.drawable.ic_baseline_update_24)
                        .create()
                        .decorate();


                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(recyclerView);

    }

    private void openDialog2(String ora, String idp, RecyclerView.ViewHolder viewHolder) {
        a.setContentView(R.layout.dialog_delete);
        conferma = a.findViewById(R.id.okDelete);
        close = a.findViewById(R.id.closeDelete);
        domanda=a.findViewById(R.id.domandaDelete);
        attenzione=a.findViewById(R.id.textViewAttenzione);
        orizontalLineDelete=dialogUpdate.findViewById(R.id.orizontalLineDelete);
        verticalLineDelete=dialogUpdate.findViewById(R.id.verticalLineDelete);
        int pos=viewHolder.getLayoutPosition();
        for (int i=0; i<idPaz.size(); i++){
            if (pos==i){
                domanda.setText("Vuoi eliminare l appuntamento: "+"\n"+getNewOra(pos)+" "+getP(info,idPaz.get(i))+" ?");
                //Log.i("bo"," info:"+idPaz);
                invioDati.add(giorno);
                invioDati.add(idPaz.get(i));

            }

        }
        a.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        a.show();
    }

    public String getNewOra(int pos){
        String oraes="";
        for (int i=0;i<oraArr.size(); i++){
            if (pos==i){
                oraes=oraArr.get(i);
            }
        }
        return oraes;
    }

    private void openDialog(String ora, String idp, RecyclerView.ViewHolder viewHolder) {
        dialogUpdate.setContentView(R.layout.dialog_update);
        confermaUpdate = dialogUpdate.findViewById(R.id.okUpdate);
        closeUpdate = dialogUpdate.findViewById(R.id.closeUpdate);
        domandaUpdate=dialogUpdate.findViewById(R.id.domandaUpdate);
        time=dialogUpdate.findViewById(R.id.editTextTime);
        date=dialogUpdate.findViewById(R.id.editTextDate);
        orizontalLine=dialogUpdate.findViewById(R.id.orizontalLine);
        verticalLine=dialogUpdate.findViewById(R.id.verticallLine);
        attenzioneUpdate=dialogUpdate.findViewById(R.id.attenzioneUpdate);
        date.setHint(giorno);
        int pos=viewHolder.getLayoutPosition();
        for (int i=0; i<idPaz.size(); i++){
            if (pos==i){
                domandaUpdate.setText("Vuoi modificare l appuntamento: "+"\n"+getNewOra(pos)+" "+getP(info,idPaz.get(i))+" ?");
                time.setHint(getNewOra(pos));
                invioDati.add(giorno);
                invioDati.add(idPaz.get(i));
                invioDati.add(getNewOra(pos));
                //Log.i("bo",idPaz.toString()+" "+idPaz.get(i));
            }


        }
        dialogUpdate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogUpdate.show();

    }

    public void closeUpdate(View view) {
        Toast.makeText(MainActivity3.this, "close Update", Toast.LENGTH_LONG).show();
        dialogUpdate.dismiss();
        adapter.notifyDataSetChanged();
        //adapter.notifyItemChanged(ItemTouchHelper.RIGHT);

    }

    public void closeDelete(View view) {
        a.dismiss();
        adapter.notifyDataSetChanged();

        //adapter.notifyItemMoved(4,8);
    }

    public void okUpdate(View view) {
        String oraEsatta="";
        String dataEsatta="";
        //Toast.makeText(MainActivity3.this, "ok Update", Toast.LENGTH_LONG).show();
        for (int i=0; i<appuntamentiArr.size(); i++){
            String a[]=appuntamentiArr.get(i).split("!");
            String idDottore=a[3];
            String idApp=a[5];
            String idPaziente=a[2];
            String ora=a[1];
            String giornoApp=a[0];
            String oraNew= String.valueOf(time.getText());
            String dataNew= String.valueOf(date.getText());
            if (invioDati.get(1).compareTo(idPaziente)==0 && invioDati.get(0).compareTo(giornoApp)==0){
               /*if (oraNew.trim().length()<4 || dataNew.length()<6){
                   Toast.makeText(MainActivity3.this, "ATTENZIONE: Completa entrambi ii campi ", Toast.LENGTH_LONG).show();
               }else{*/
                   Toast.makeText(MainActivity3.this, "Modifica richiesta al segretario", Toast.LENGTH_LONG).show();
                   //Log.i("APPUNTAMENTOELIMINARE","il dottore: "+idDottore+" desidera modificare l appuntamento del giorno "+giornoApp+" alle ore "+ora+" del paziente "+idPaziente+" ("+idApp+")");
                   //String m="eliminare+appuntamento+"+idApp;
                   String message="il dottore "+idDottore+" desidera modificare l appuntamento del giorno "+giornoApp+" alle ore "+ora+" del paziente "+idPaziente+" ("+idApp+"): nuova ora oppure giorno: "+oraNew+" "+dataNew;
                   new FetchDataTaskPOST().execute(message);
                   dialogUpdate.dismiss();
                   onBackPressed();
               //}

            }
        }invioDati.clear();
    }


    public void okDelete(View view) {
        Toast.makeText(MainActivity3.this, "Eliminazione richiesta al segretario", Toast.LENGTH_LONG).show();
        //Log.i("OKDELETE",invioDati.toString());
        //Log.i("appuntamenti array",appuntamentiArr.toString());
        for (int i=0; i<appuntamentiArr.size(); i++){
            String app[]=appuntamentiArr.get(i).split("!");
            String idDottore=app[3];
            String idApp=app[5];
            String idPaziente=app[2];
            String ora=app[1];
            String giornoApp=app[0];
            if (invioDati.get(1).compareTo(idPaziente)==0 && invioDati.get(0).compareTo(giornoApp)==0){
                //Log.i("APPUNTAMENTOELIMINARE","il dottore: "+idDottore+" desidera eliminare l appuntamento del giorno "+giornoApp+" alle ore "+ora+" del paziente "+idPaziente+" ("+idApp+")");
                 //String m="eliminare+appuntamento+"+idApp;
                 String message="il dottore "+idDottore+" desidera eliminare l appuntamento del giorno "+giornoApp+" alle ore "+ora+" del paziente "+idPaziente+" ("+idApp+")";
                new FetchDataTaskPOST().execute(message);
                a.dismiss();
                onBackPressed();
            }
        }invioDati.clear();
    }

    /*public void post(){
        URL  url = null;
        try {
            url = new URL(urlPostMessage);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{'message': 'il dottore desidera eliminare'}";
           try(OutputStream os = con.getOutputStream()) {

                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }


    }*/


    protected class FetchDataTaskPOST extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            //return null;
            //String jsonInputString = "{message: il dottore desidera eliminare35}";
            String m=strings[0];
            //Log.i("cancel ",m);

            JSONObject object= new JSONObject();
            try {
                //object.put("message",jsonInputString);
                object.put("message",m);
                sendHTTPData(urlPostMessage,object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "aaaaa";

        }

        public String sendHTTPData(String urlpath, JSONObject json) {
            HttpURLConnection connection = null;
            try {
                URL url=new URL(urlpath);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
                streamWriter.write(json.toString());
                streamWriter.flush();
                StringBuilder stringBuilder = new StringBuilder();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(streamReader);
                    String response = null;
                    while ((response = bufferedReader.readLine()) != null) {
                        stringBuilder.append(response + "\n");
                    }
                    bufferedReader.close();

                    Log.d("test", stringBuilder.toString());
                    return stringBuilder.toString();
                } else {
                    Log.e("test", connection.getResponseMessage());
                    return null;
                }
            } catch (Exception exception){
                Log.e("test", exception.toString());
                return null;
            } finally {
                if (connection != null){
                    connection.disconnect();
                }
            }
        }

        /*public void post2(){
            URL url = null;
            try {
                url = new URL(urlPostMessage);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; utf-8");
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);
                String jsonInputString = "{'message':'Upendra'}";
                try(OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
                try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println(response.toString());
                }



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void post3(){
             URL  url = null;
            try {
                url = new URL(urlPostMessage);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestProperty("Content-Type", "application/json; utf-8");
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);
                con.setRequestMethod("POST");
                String jsonInputString = "{'message':'il dottore desidera eliminare222'}";
                try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
                }
                try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                        System.out.println(response.toString());
                    }

                    // return response.toString();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
                System.out.println(e);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e);
            }

        }*/

    }


}
  package com.example.projectasl2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.red;

  public class MainActivity2 extends AppCompatActivity {
    CalendarView calendarView;
    private static String urlGetAppuntamenti = "http://192.168.1.6:8080/api/appuntamenti/getAppuntamenti";
    static String idDottore,coloreDottore,nomeDottore;
    public static ArrayList<String> appuntamentiArr;
    public static ArrayList<String> giorni;
    ImageButton imageButtonDoct2;
    List<EventDay> events2;
    ExtendedFloatingActionButton fly,exOggi;
    Boolean isOpen;

    static ArrayList<String>invioDati;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ArrayList<String> b =MainActivity.appuntamenti;
        calendarView=findViewById(R.id.calendarView);
        fly=findViewById(R.id.floatingActionButton);
        exOggi=findViewById(R.id.floatOggi);


        //imageButtonDoct2=findViewById(R.id.imageButtonDott2);
        appuntamentiArr= new ArrayList<>();
        String info="";
        info=getIntent().getStringExtra("info");
        String info2[]=info.split("!");
        coloreDottore=info2[0];
        idDottore=info2[1];
        nomeDottore=info2[2]+" "+info2[3];

        //Log.i("INFO","INFO: "+coloreDottore+" id"+idDottore+"nome"+nomeDottore);

        //imageButtonDoct2.setBackgroundColor(R.drawable.button_shape);
        giorni=new ArrayList<>();

        //calendarView.setBackgroundColor(GREEN);
        //calendarView.setHeaderLabelColor(Color.GREEN);
        events2=new ArrayList<>();
        invioDati=new ArrayList<>();
        //invioDati.add(idDottore);

        isOpen=false;


        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                String date= String.valueOf(eventDay.getCalendar().getTime());
                Log.i("date","DATE: "+getDate(date));
                Intent intent=new Intent(getApplicationContext(),MainActivity3.class);
                intent.putExtra("giorno",getDate(date));
                startActivity(intent);
            }
        });


        fly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               fly.setText(nomeDottore);

               if (!isOpen){
                   fly.extend();
                   isOpen=true;
               }else{
                   fly.shrink();
                   isOpen=false;
               }
            }
        });
        exOggi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());
                String currentDate2[] = currentDate.split("/");
                String date = currentDate2[0] + "/" + currentDate2[1] + "/20" + currentDate2[2];
                Log.i("date","DATE: "+date);
                Intent intent=new Intent(getApplicationContext(),MainActivity3.class);
                intent.putExtra("giorno",date);
                startActivity(intent);
            }
        });


        new FetchDataTask3().execute();
    }

      @Override
      public void onBackPressed() {
          //super.onBackPressed();
          Intent i= new Intent(this,MainActivity.class);
          startActivity(i);
      }

      public void imageButtonDott2(View view) {
        Toast.makeText(this,"id dottore: "+idDottore,Toast.LENGTH_LONG).show();
    }


    protected class FetchDataTask3 extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            String getAppuntamenti= "";
            try {

                URL url3 = new URL(urlGetAppuntamenti);
                HttpURLConnection con3 = (HttpURLConnection) url3.openConnection();
                con3.setRequestMethod("GET");
                con3.connect();
                BufferedReader bf3 = new BufferedReader(new InputStreamReader(con3.getInputStream()));
                getAppuntamenti = bf3.readLine();
                //appuntamenti.add(getAppuntamenti);
                //System.out.print("getAppuntamenti"+ getAppuntamenti);
                Log.i("TAG",getAppuntamenti);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return getAppuntamenti;
        }

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            Toast.makeText(MainActivity2.this, "Caricamento degli appuntamenti", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(String s) {
            parseJSON(s);
            //pallino();
            //System.out.print("ALL"+ s);
        }

        public void parseJSON(String s){
            String id = "";
            String giorno = "";
            String ora="";
            String idP = "";
            String idD = "";
            String tipoApp="";
            try {
                JSONArray dottori = new JSONArray(s);
                //Log.e(TAG, "app: " + jsonMainNode);
                //int jsonArrLength = jsonMainNode.length();
                for (int i = 0; i < dottori.length(); i++) {
                    JSONObject object = dottori.getJSONObject(i);
                    id = object.getString("id");
                    giorno = object.getString("giorno");
                    ora = object.getString("ora");
                    idP = object.getString("idPaziente");
                    idD = object.getString("idDottore");
                    tipoApp=object.getString("tipoAppuntamento");
                    String info=giorno+"!"+ora+"!"+idP+"!"+idD+"!"+tipoApp+"!"+id;
                    //Log.i("TAG",info);
                    /*String date2[]=giorno.split("/");
                    int gg= Integer.parseInt(date2[0]);
                    int mm= Integer.parseInt(date2[1]);
                    int aaaa= Integer.parseInt(date2[2]);*/


                    if(idD.compareTo(idDottore)==0){
                        Log.i("TAG",info);
                        //inserire appuntamenti in array statico
                        appuntamentiArr.add(info);
                        //appuntamentiArr.add(id);
                        //giorni.add(giorno);
                        pallino(giorno);
                        /*Calendar calendar3 = Calendar.getInstance();
                        calendar3.set(aaaa,mm,gg);
                        Log.i("gg",aaaa+" "+mm+" "+gg);
                        events2.add(new EventDay(calendar3, R.drawable.date_shape, Color.parseColor("#228B22")));
                        calendarView.setEvents(events2);*/
                    }


                    //coloreDottori.add(info);
                    //color=colore;
                    //dottore.setText(nome+""+cognome);

                    //exampleItemsDottori.add(new ExampleItemDottori(R.drawable.ic_baseline_account_circle_24, nome+" "+cognome,colore));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
    public void pallino(String giorno){

        int aaaa=0;
        int mm=0;
        int gg=0;

        String gg3[] =giorno.split("/");
        aaaa= Integer.parseInt(gg3[2]);
        mm= Integer.parseInt(gg3[1]);
        gg= Integer.parseInt(gg3[0]);
        if(mm<10){
            String mm2="0"+mm;
            // mm= Integer.parseInt(mm2);
            String a=aaaa+" "+mm2+" "+gg;
            Log.i("TAG2",a);
            Calendar calendar3 = Calendar.getInstance();
            //calendar3.set(aaaa, Integer.parseInt(mm2),gg);
            //calendar3.set(aaaa, 4,gg);
            calendar3.set(aaaa,mm-1,gg);
            events2.add(new EventDay(calendar3, R.drawable.ic_baseline_push_pin_24, Color.parseColor("#228B22")));
            //events2.add(new EventDay(calendar3, R.drawable.date_shape, Color.parseColor("#228B22")));
            calendarView.setEvents(events2);
        }
    }

    /*public void oggi(View view) {
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());
        String currentDate2[] = currentDate.split("/");
        String date = currentDate2[0] + "/" + currentDate2[1] + "/20" + currentDate2[2];
        Log.i("date","DATE: "+date);
        Intent intent=new Intent(getApplicationContext(),MainActivity3.class);
        intent.putExtra("giorno",date);
        startActivity(intent);

        //va alla mainActivity3
    }*/

    public String getDate(String date){
        int c=1;
        String mese="";
        String date2[]=date.split(" ");
        String gg=date2[2];
        String mm=date2[1];
        String aaaa=date2[5];

        ArrayList<String> mesi= new ArrayList<>(12);
        mesi.add("Jan");
        mesi.add("Feb");
        mesi.add("Mar");
        mesi.add("Apr ");
        mesi.add("May");
        mesi.add("Jun");
        mesi.add("Jul");
        mesi.add("Aug");
        mesi.add("Sep");
        mesi.add("Oct");
        mesi.add("Nov");
        mesi.add("Dec");
        for (int i=0; i<mesi.size(); i++){
            //Toast.makeText(this, c+"mm "+mesi.get(i)+":mm2 "+mm, Toast.LENGTH_LONG).show();
            if(mesi.get(i).compareTo(mm)==0){
                mese="0"+c;
                //Toast.makeText(this, "mm "+mesi.get(i)+":mm2 "+mm, Toast.LENGTH_LONG).show();
            }
            c++;
        }
        //Log.i("TAG", "mesi: " + mesi);
        String sd=gg+"/"+mese+"/"+aaaa;
        return sd;
    }

}
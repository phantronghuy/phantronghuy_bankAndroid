package com.example.tronghuy_b18_bank;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

//    ListView lv;
//    static MyArrayAdapter myArrayAdapter;
//    ArrayList <TyGia> arl;
//    TextView txt_date;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        thamChieu();
//        getDate();
//        TyGiaAsyncTask task= new TyGiaAsyncTask();
//        task.execute();
//    }
//
//    private void getDate() {
//        Date currentDate= Calendar.getInstance().getTime();
//
//        SimpleDateFormat Sformat = new SimpleDateFormat("dd/MM/yyyy");
//
//        txt_date.setText("Hôm nay: "+ Sformat.format(currentDate));
//
//    }
//
//    private void thamChieu() {
//
//        lv=findViewById(R.id.lv);
//        txt_date=findViewById(R.id.txt_date);
//        arl=new ArrayList<>();
//        myArrayAdapter=new MyArrayAdapter(MainActivity.this,
//                R.layout.my_layout_custom,arl);
//        lv.setAdapter(myArrayAdapter);
//
//    }
//    class TyGiaAsyncTask extends AsyncTask<Void, Void, ArrayList<TyGia>> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            MainActivity.myArrayAdapter.clear();
//            Log.e("Day","vao");
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<TyGia> tyGias) {
//            super.onPostExecute(tyGias);
//            MainActivity.myArrayAdapter.clear();
//            MainActivity.myArrayAdapter.addAll(tyGias);
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected ArrayList<TyGia> doInBackground(Void... voids) {
//            ArrayList<TyGia> arl= new ArrayList<>();
//            try {
//                //Đây là link Server
//                URL urlServer= new URL("http://dongabank.com.vn/exchange/export");
//
//                //Mở Connection ra
//                HttpURLConnection connection= (HttpURLConnection) urlServer.openConnection();
//
//                // Thiết lập Method là Get dữ liệu
//                connection.setRequestMethod("GET");
//
//                //Thiết lập thuộc tính nó thuộc loại Json nào, để biết sử dụng công cụ HttpRequester trong FireFox
//                connection.setRequestProperty("Content-type", "application/json; charset=utf-8");
//                connection.setRequestProperty("User-Agent","Mozilla/5.0(compatible)");
//                connection.setRequestProperty("Accept","*/*");
//
//                //lấy dữ liệu mà server trả về
//                // Lây chuỗi dữ liệu InputStream trả về
//                InputStream inputStream = connection.getInputStream();
//
//                //Chuyển kiểu về kiểu UTF-8 và Đưa vào bộ đọc dữ liệu
//                InputStreamReader reader= new InputStreamReader(inputStream,"UTF-8");
//
//                //Lưu vào bộ đệm
//                BufferedReader bufferedReader= new BufferedReader(reader);
//
//                String line=bufferedReader.readLine();
//                StringBuilder builder=new StringBuilder();
//                while (line!=null) {
//                    builder.append(line);
//                    line=bufferedReader.readLine();
//                }
//                String json=builder.toString();
//                //Bỏ hai ngoặc tròn trong dữ liệu trả về
//                json=json.replace("(", "");
//                json=json.replace(")","");
//                JSONObject jsonObject= new JSONObject(json);
//                JSONArray jsonArray= jsonObject.getJSONArray("items");
//                for (int i=0;i<jsonArray.length();i++){
//                    JSONObject jsonObject1= jsonArray.getJSONObject(i);
//                    TyGia tyGia= new TyGia();
//                    tyGia.setType(jsonObject1.getString("type"));
//                    if (jsonObject1.has("imageurl")){
//                        tyGia.setImageUrl(jsonObject1.getString("imageurl"));
//
//                        urlServer= new URL(tyGia.getImageUrl());
//                        connection= (HttpURLConnection) urlServer.openConnection();
//                        connection.setRequestMethod("GET");
//                        connection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
//                        connection.setRequestProperty("Accept", "*/*");
//                        Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
//                        tyGia.setBitmap(bitmap);
//                    }
//
//                    if(jsonObject1.has("muatienmat")) {
//                        tyGia.setMuaTienMat(jsonObject1.getString("muatienmat"));
//                    }
//                    if(jsonObject1.has("muack")) {
//                        tyGia.setMuaCk(jsonObject1.getString("muack"));
//                    }
//                    if(jsonObject1.has("bantienmat")) {
//                        tyGia.setBanTienMat(jsonObject1.getString("bantienmat"));
//                    }
//                    if(jsonObject1.has("banck")) {
//                        tyGia.setBanCk(jsonObject1.getString("banck"));
//                    }
//                    arl.add(tyGia);
//                }
//                Log.d("JSON_DONGA",json);
//            }
//            catch (Exception e){
//                Log.e("Lỗi", e.toString());
//            }
//            return arl;
//        }
//    }


    ListView lvTigia;
    TextView txtdate;
    ArrayList<TyGia> dstygia;
    MyArrayAdapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvTigia = (ListView) findViewById(R.id.lv);
        txtdate = (TextView) findViewById(R.id.txt_date);
        getdate();
        dstygia = new ArrayList<TyGia>();
        myadapter = new MyArrayAdapter(MainActivity.this, R.layout.my_layout_custom, dstygia);
        lvTigia.setAdapter(myadapter);
        TyGiaTask task = new TyGiaTask();
        task.execute();
    }
    public void getdate() {
        //Lấy ngày giờ hệ thống
        Date currentDate = Calendar.getInstance().getTime();
        //Format theo định dạng dd/mm/yyyy
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
        //Hiển thị lên TextView
        txtdate.setText("Hôm Nay: "+simpleDate.format(currentDate));
    }

        class TyGiaTask extends AsyncTask<Void, Void, ArrayList<TyGia>>{
            @Override
            protected ArrayList<TyGia> doInBackground(Void... params) {
                // TODO Auto-generated method stub
                ArrayList<TyGia> ds = new ArrayList<TyGia>();
                try
                {
                    //Đây là link Server
                    URL url=new URL("https://dongabank.com.vn/exchange/export");
                    //Mở Connection ra
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    // Thiết lập Method là Get dữ liệu
                    connection.setRequestMethod("GET");
                    //Thiết lập thuộc tính nó thuộc loại Json nào, để biết sử dụng công cụ HttpRequester trong FireFox
                    connection.setRequestProperty("Content-type", " application/json; charset=utf-8");
                    connection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
                    connection.setRequestProperty("Accept", "*/*");
                    //lấy dữ liệu mà server trả về
                    // Lây chuỗi dữ liệu InputStream trả về
                    InputStream is= connection.getInputStream();

                    //Chuyển kiểu về kiểu UTF-8 và Đưa vào bộ đọc dữ liệu
                    InputStreamReader isr=new InputStreamReader(is,"UTF-8");
                    //Lưu vào bộ đệm
                    BufferedReader br=new BufferedReader(isr);
                    String line=br.readLine();
                    StringBuilder builder=new StringBuilder();
                    while (line!=null) {
                        builder.append(line);
                        line=br.readLine();
                    }

                    String json=builder.toString();

                    //Bỏ hai ngoặc tròn trong dữ liệu trả về
                    json=json.replace("(", "");
                    json=json.replace(")","");
                    Log.e("Chuỗi JSON",json);
                    Log.e("Loi","o day");
                    JSONObject jsonObject=new JSONObject(json);

                    JSONArray jsonArray= jsonObject.getJSONArray("items");

                    for (int i=0;i<jsonArray.length();i++) {
                        JSONObject item=jsonArray.getJSONObject(i);
                        TyGia tiGia=new TyGia();
                        tiGia.setType(item.getString("type"));
                        if(item.has("imageurl")) {
                            tiGia.setImageUrl(item.getString("imageurl"));
                            // thay thế
       //                     url=new URL(tiGia.getImageUrl());
                            url= new URL(item.getString("imageurl"));
                            connection= (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
                            connection.setRequestProperty("Accept", "*/*");
                            Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                            tiGia.setBitmap(bitmap);
                        } if(item.has("muatienmat")) {
                            tiGia.setMuaTienMat(item.getString("muatienmat"));
                        } if(item.has("muack")) {
                            tiGia.setMuaCk(item.getString("muack"));
                        } if(item.has("bantienmat")) {
                            tiGia.setBanTienMat(item.getString("bantienmat"));
                        } if(item.has("banck")) {
                            tiGia.setBanCk(item.getString("banck"));
                        }
                        ds.add(tiGia);
                    }
                    Log.d("JSON_DONGA",json);
                }
                catch (Exception ex) {
                    // TODO: handle exception
                    Log.e("Lỗi", ex.toString());
                }
                return ds;
            }
        protected void onPreExecute() {
          //   TODO Auto-generated method stub
            super.onPreExecute();
            myadapter.clear();
            Log.e("Vao:","onPreExecute");
        }
        @Override
        protected void onPostExecute(ArrayList<TyGia> result) {
            // TODO Auto-generated method stub
              super.onPostExecute(result);
              myadapter.clear();
              myadapter.addAll(result);
            Log.e("Vao:","onPostExecute");
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            // TODO Auto-generated method stub
              super.onProgressUpdate(values);}
        }

}
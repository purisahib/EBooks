package com.pradeep.new014.ui.contener;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pradeep.new014.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class BookActivity extends AppCompatActivity {
    public static String bookl;
    public static String URL;

    private TextView tvtitle,tvcategory,tvdescription,tvauthor;
    private ImageView img;
    private ImageButton open1Btn,download1Btn;
    private static String downloadFileName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        tvtitle=(TextView)findViewById(R.id.text_title);
        tvdescription=(TextView)findViewById(R.id.text_desc);
        tvcategory=(TextView)findViewById(R.id.text_Cat);
        tvauthor=(TextView)findViewById(R.id.text_Author);
        img=(ImageView)findViewById(R.id.book_thumnail);
        open1Btn=(ImageButton)findViewById(R.id.open1Btn);
        download1Btn=(ImageButton)findViewById(R.id.download1Btn);


        //Reciving data
        Intent intent= getIntent();
        String title=intent.getExtras().getString("Title");
        String category=intent.getExtras().getString("Category");
        String Description= intent.getExtras().getString("Description");
        URL= intent.getExtras().getString("Thumbnail");
        String author=intent.getExtras().getString("Author");
        bookl=intent.getExtras().getString("Bookl");
        //Clin space from url;
        downloadFileName = title+".pdf";//Create file name by picking download file name from URL

        //Setting Values

        tvtitle.setText(title);
        tvcategory.setText(category);
        tvdescription.setText(Description);
        //img.setImageURI(uri);
        tvauthor.setText(author);
        open1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentopen1Btn=new Intent(BookActivity.this, PdfReaderActivity.class);
                PdfReaderActivity pdfReaderActivity =new PdfReaderActivity();
                pdfReaderActivity.getUrlvalue(bookl);
                startActivity(intentopen1Btn);
            }
        });
        download1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=bookl.replace(" ", "%20");
                DownloadManager.Request request= new DownloadManager.Request(Uri.parse(url));
                request.setDescription("Downloading Book "+downloadFileName);
                request.setTitle("PS Books");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                }
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS+".PuriSahib",downloadFileName);
                DownloadManager manager=(DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                manager.enqueue(request);

            }
        });
        GetXMLTask task = new GetXMLTask();
        // Execute the task
        task.execute(new String[] { URL });
    }
    private class GetXMLTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            for (String url : urls) {
                map = downloadImage(url);
            }
            return map;
        }

        // Sets the Bitmap returned by doInBackground
        @Override
        protected void onPostExecute(Bitmap result) {
            img.setImageBitmap(result);
        }

        // Creates Bitmap from InputStream and returns it
        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }

        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            java.net.URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }
}


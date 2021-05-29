package com.pradeep.new014.ui.contener;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.pradeep.new014.R;

public class PdfReaderActivity extends AppCompatActivity {
public static String urrl,exteraUrl="https://docs.google.com/viewer?url=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_reader);
        final WebView webView;
        webView=findViewById(R.id.webView00100143);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(urrl);

        //webView.loadUrl("https://docs.google.com/viewer?url=http://www.adobe.com/devnet/acrobat/pdfs/pdf_open_parameters.pdf");
        //webView.loadUrl("https://docs.google.com/viewer?url=http://www.tutorialspoint.com/cplusplus/cpp_tutorial.pdf");
        //webView.loadUrl("https://docs.google.com/viewer?url=http://www.deccansoft.com/Documents/SyllabusDocs/0e97d961-f70a-4321-99bf-05f2c29c9dab_Syllabus_of_CPP.pdf");

    }
    public void getUrlvalue(String url)
    {
        urrl=exteraUrl+url;
    }

}

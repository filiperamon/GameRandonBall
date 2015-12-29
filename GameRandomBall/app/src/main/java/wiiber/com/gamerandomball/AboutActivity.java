package wiiber.com.gamerandomball;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends Activity {

    private TextView txtAbout, txvDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/postmaster.ttf");
        txtAbout = (TextView) findViewById(R.id.txtAbout);
        txvDescricao = (TextView) findViewById(R.id.textViewDescricao);
        txtAbout.setTypeface(font);
        txvDescricao.setTypeface(font);
    }
}

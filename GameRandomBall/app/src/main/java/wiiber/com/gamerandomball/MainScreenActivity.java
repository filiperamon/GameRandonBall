package wiiber.com.gamerandomball;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainScreenActivity extends Activity {

    private Button btnStart;
    private Button btnOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sreen);

        btnStart = (Button) findViewById(R.id.btn_iniciar);
        btnOption = (Button) findViewById(R.id.btn_opcoes);
        TextView txt = (TextView) findViewById(R.id.textView2);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/postmasterlaser.ttf");
        txt.setTypeface(font);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle parameter = new Bundle();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtras(parameter);
                startActivity(intent);
            }
        });

        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);*/
            }
        });
    }
}

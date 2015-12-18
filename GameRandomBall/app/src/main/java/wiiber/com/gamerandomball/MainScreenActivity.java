package wiiber.com.gamerandomball;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreenActivity extends Activity {

    private Button btnStart;
    private Button btnOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sreen);

        btnStart = (Button) findViewById(R.id.btn_iniciar);
        btnOption = (Button) findViewById(R.id.btn_opcoes);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/DSAccent.otf");
        btnStart.setTypeface(font);
        btnOption.setTypeface(font);


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
                Intent intent = new Intent(getApplicationContext(),AboutActivity.class);
                startActivity(intent);
            }
        });
    }
}

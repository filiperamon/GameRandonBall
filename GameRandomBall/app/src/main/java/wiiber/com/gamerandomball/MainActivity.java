package wiiber.com.gamerandomball;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends Activity {

    private Switch switchRest;
    private TextView txtNumRandom, tvSum;
    private Button tvNumOne, tvNumTwo, tvNumTree, tvNumFour, tvNumFive, tvNumSix ;
    private ImageButton btnVolume;
    public Random random;
    private int soma, volume, count, player = 0;
    private int[] results;
    private MediaPlayer audioRoleta;
    private int tempoRotacao = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaComponentes();
        inicializaVariaveis();

        switchRest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    resetNumbers();
                    switchRest.setChecked(false);
                }
            }
        });

        txtNumRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 0) {
                    if (player <= 6) {
                        refreshView();
                    } else {
                        player = 0;
                        soma = 0;
                    }
                }
            }
        });

        btnVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (volume == 1) {
                    btnVolume.setImageResource(R.drawable.volume_off);
                    volume = 0;
                } else {
                    btnVolume.setImageResource(R.drawable.volume_high);
                    volume = 1;
                }
            }
        });
   }

    private void inicializaVariaveis() {
        random = new Random();
        txtNumRandom.setText("?");
        results = new int[6];
        soma = 0;
        volume = 1;
    }

    private void inicializaComponentes() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/postmaster.ttf");
        btnVolume    = (ImageButton) findViewById(R.id.volume);
        txtNumRandom = (TextView) findViewById(R.id.txvNumeroRandomico);
        tvSum        = (TextView) findViewById(R.id.textViewSoma);
        tvNumOne     = (Button) findViewById(R.id.textViewNumOne);
        tvNumTwo     = (Button) findViewById(R.id.textViewNumTwo);
        tvNumTree    = (Button) findViewById(R.id.textViewNumTree);
        tvNumFour    = (Button) findViewById(R.id.textViewNumFour);
        tvNumFive    = (Button) findViewById(R.id.textViewNumFive);
        tvNumSix     = (Button) findViewById(R.id.textViewNumSix);
        switchRest   = (Switch) findViewById(R.id.switchGerar);

        txtNumRandom.setTypeface(font);
        tvNumOne    .setTypeface(font);
        tvNumTwo    .setTypeface(font);
        tvNumTree   .setTypeface(font);
        tvNumFour   .setTypeface(font);
        tvNumFive   .setTypeface(font);
        tvNumSix    .setTypeface(font);
        switchRest  .setTypeface(font);

        font = Typeface.createFromAsset(getAssets(), "fonts/DSAccent.otf");
        tvSum.setTypeface(font);
    }

    private void efeitoSonoroRoleta(String efeito){

        if(efeito.equals("R.raw.roleta")) {
            audioRoleta = MediaPlayer.create(this, R.raw.roleta);
        } else {
            audioRoleta = MediaPlayer.create(this, R.raw.reset);
        }

        if(volume==1)
            audioRoleta.start();
    }
 
    private void refreshView(){
        final int numberRandom = random.nextInt(60) + 1;
        txtNumRandom.setText(String.valueOf(numberRandom));
        count ++;

        Handler handler = new Handler(getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(count == 1)
                    efeitoSonoroRoleta("R.raw.roleta");

                if (count < 37) {
                    if(count < 31) {
                        animationRoleta(txtNumRandom);
                    }
                    else if (count < 36){
                        tempoRotacao = 180;
                        animationRoletaLenta(txtNumRandom, 10);
                    }
                    else {
                        tempoRotacao = 300;
                        animationRoletaLenta(txtNumRandom, 20);
                    }

                    refreshView();

                } else {
                    count = 0;
                    tempoRotacao = 100;
                    player++;
                    txtNumRandom.setText(String.valueOf(numberRandom));

                    if(player == 6){
                        txtNumRandom.setClickable(false);
                    }

                    switch (player) {
                        case 1:
                            animationResults(tvNumOne);
                            results[0] = Integer.valueOf(txtNumRandom.getText().toString());
                            animationButton(tvNumOne, txtNumRandom.getText().toString());
                            break;
                        case 2:
                            animationResults(tvNumTwo);
                            results[0] = Integer.valueOf(txtNumRandom.getText().toString());
                            animationButton(tvNumTwo, txtNumRandom.getText().toString());
                            break;
                        case 3:
                            animationResults(tvNumTree);
                            results[0] = Integer.valueOf(txtNumRandom.getText().toString());
                            animationButton(tvNumTree, txtNumRandom.getText().toString());
                            break;
                        case 4:
                            animationResults(tvNumFour);
                            results[0] = Integer.valueOf(txtNumRandom.getText().toString());
                            animationButton(tvNumFour, txtNumRandom.getText().toString());
                            break;
                        case 5:
                            animationResults(tvNumFive);
                            results[0] = Integer.valueOf(txtNumRandom.getText().toString());
                            animationButton(tvNumFive, txtNumRandom.getText().toString());
                            break;
                        case 6:
                            animationResults(tvNumSix);
                            results[0] = Integer.valueOf(txtNumRandom.getText().toString());
                            animationButton(tvNumSix, txtNumRandom.getText().toString());
                            player = 0;
                            break;
                    }

                    for(int i = 0; i < results.length; i++){
                        soma += results[i];
                    }
                    tvSum.setText(getString(R.string.soma) + ": " + soma + " ");
                }
            }
        }, tempoRotacao);
    }

    private void animationResults(View view) {
        Animation animator = AnimationUtils.loadAnimation(this, R.anim.from_middle);
        view.setAnimation(animator);
        view.startAnimation(animator);
    }

    private void animationRoleta(View view) {
        Animation animator = AnimationUtils.loadAnimation(this, R.anim.from_middle2);
        view.setAnimation(animator);
        view.startAnimation(animator);
    }

    private void animationRoletaLenta(View view, int tempo) {
        Animation animator;

        if(tempo < 11)
            animator = AnimationUtils.loadAnimation(this, R.anim.from_middle3);
        else
            animator = AnimationUtils.loadAnimation(this, R.anim.from_middle4);

        view.setAnimation(animator);
        view.startAnimation(animator);
    }

    public void animationButton(final TextView view, final String number){
        Handler handler = new Handler(getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setText(number);
            }
        }, 300);
    }

    private void resetNumbers(){

        efeitoSonoroRoleta("R.raw.reset");

        animationResults(tvNumOne);
        animationButton (tvNumOne, "");
        animationResults(tvNumTwo);
        animationButton (tvNumTwo, "");
        animationResults(tvNumTree);
        animationButton (tvNumTree, "");
        animationResults(tvNumFour);
        animationButton (tvNumFour, "");
        animationResults(tvNumFive);
        animationButton (tvNumFive, "");
        animationResults(tvNumSix);
        animationButton (tvNumSix, "");

        animationRoleta(txtNumRandom);
        animationButton(txtNumRandom, "?");
        txtNumRandom.setClickable(true);

        count = 0;
        soma = 0;
        tvSum.setText(getString(R.string.soma) + ": " + soma + " ");
        results = new int[6];
        player = 0;
    }
}

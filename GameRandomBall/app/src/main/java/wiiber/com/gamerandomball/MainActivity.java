package wiiber.com.gamerandomball;

import android.app.Activity;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {

    private Switch switchGerar;
    private TextView textViewNumberRandom, tvSoma;
    private Button tvNumOne, tvNumTwo, tvNumTree, tvNumFour, tvNumFive, tvNumSix ;
    public Random random;
    private int count = 0;
    private int jogada = 0;
    private int[] resultados;
    private int soma;
    private MediaPlayer mediaRoleta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewNumberRandom = (TextView) findViewById(R.id.txvNumeroRandomico);
        tvNumOne     = (Button) findViewById(R.id.textViewNumOne);
        tvNumTwo     = (Button) findViewById(R.id.textViewNumTwo);
        tvNumTree    = (Button) findViewById(R.id.textViewNumTree);
        tvNumFour    = (Button) findViewById(R.id.textViewNumFour);
        tvNumFive    = (Button) findViewById(R.id.textViewNumFive);
        tvNumSix     = (Button) findViewById(R.id.textViewNumSix);
        tvSoma       = (TextView) findViewById(R.id.textViewSoma);
        switchGerar  = (Switch) findViewById(R.id.switchGerar);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/postmaster.ttf");
        tvSoma.setTypeface(font);
        textViewNumberRandom.setTypeface(font);
        tvNumOne.setTypeface(font);
        tvNumTwo.setTypeface(font);
        tvNumTree.setTypeface(font);
        tvNumFour.setTypeface(font);
        tvNumFive.setTypeface(font);
        tvNumSix.setTypeface(font);
        switchGerar.setTypeface(font);

        mediaRoleta = MediaPlayer.create(this,R.raw.roleta);
        random = new Random();
        textViewNumberRandom.setText("?");
        resultados = new int[6];
        soma = 0;

        switchGerar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    resetarNumeros();
                    switchGerar.setChecked(false);
                }
            }
        });

        textViewNumberRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 0) {
                    if (jogada <= 6) {
                        atualizaView();
                    } else {
                        jogada = 0;
                        soma = 0;
                    }
                }
            }
        });
   }

    private void efeitoSonoroRoleta(){
        mediaRoleta.start();
    }

    private void atualizaView(){
        final int numeroGerado = random.nextInt(60) + 1;
        textViewNumberRandom.setText(String.valueOf(numeroGerado));
        count ++;

        Handler handler = new Handler(getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (count < 10) {
                    efeitoSonoroRoleta();
                    animation2(textViewNumberRandom);
                    atualizaView();
                } else {
                    count = 0;
                    jogada ++;
                    textViewNumberRandom.setText(String.valueOf(numeroGerado));

                    if(jogada == 6){
                        textViewNumberRandom.setClickable(false);
                    }

                    switch (jogada) {
                        case 1:
                            animation(tvNumOne);
                            resultados[0] = Integer.valueOf(textViewNumberRandom.getText().toString());
                            animationButton(tvNumOne, textViewNumberRandom.getText().toString());
                            break;
                        case 2:
                            animation(tvNumTwo);
                            resultados[0] = Integer.valueOf(textViewNumberRandom.getText().toString());
                            animationButton(tvNumTwo, textViewNumberRandom.getText().toString());
                            break;
                        case 3:
                            animation(tvNumTree);
                            resultados[0] = Integer.valueOf(textViewNumberRandom.getText().toString());
                            animationButton(tvNumTree, textViewNumberRandom.getText().toString());
                            break;
                        case 4:
                            animation(tvNumFour);
                            resultados[0] = Integer.valueOf(textViewNumberRandom.getText().toString());
                            animationButton(tvNumFour, textViewNumberRandom.getText().toString());
                            break;
                        case 5:
                            animation(tvNumFive);
                            resultados[0] = Integer.valueOf(textViewNumberRandom.getText().toString());
                            animationButton(tvNumFive, textViewNumberRandom.getText().toString());
                            break;
                        case 6:
                            animation(tvNumSix);
                            resultados[0] = Integer.valueOf(textViewNumberRandom.getText().toString());
                            animationButton(tvNumSix, textViewNumberRandom.getText().toString());
                            jogada = 0;
                            break;
                    }

                    for(int i = 0; i < resultados.length; i++){
                        soma += resultados[i];
                    }
                    tvSoma.setText(getString(R.string.soma) + ": " + soma);
                }
            }
        }, 60);
    }

    private void animation(View view) {
        Animation animator = AnimationUtils.loadAnimation(this, R.anim.from_middle);
        view.setAnimation(animator);
        view.startAnimation(animator);
    }

    private void animation2(View view) {
        Animation animator = AnimationUtils.loadAnimation(this, R.anim.from_middle2);
        view.setAnimation(animator);
        view.startAnimation(animator);
    }

    public void animationButton(final TextView view, final String numero){
        Handler handler = new Handler(getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setText(numero);
            }
        }, 300);
    }

    private void resetarNumeros(){

        animation(tvNumOne);
        animationButton(tvNumOne, "");
        animation(tvNumTwo);
        animationButton(tvNumTwo, "");
        animation(tvNumTree);
        animationButton(tvNumTree, "");
        animation(tvNumFour);
        animationButton(tvNumFour, "");
        animation(tvNumFive);
        animationButton(tvNumFive, "");
        animation(tvNumSix);
        animationButton(tvNumSix, "");

        animation2(textViewNumberRandom);
        animationButton(textViewNumberRandom, "?");
        textViewNumberRandom.setClickable(true);

        count = 0;
        soma = 0;
        tvSoma.setText(getString(R.string.soma) + ": " + soma);
        resultados = new int[6];
        jogada = 0;
    }
}

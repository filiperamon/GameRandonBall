package wiiber.com.gamerandomball;

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

public class MainActivity extends AppCompatActivity {

    private Switch switchGerar;
    private TextView textViewNumberRandom, tvSoma;
    private Button tvNumOne, tvNumTwo, tvNumTree, tvNumFour, tvNumFive, tvNumSix ;
    public Random random;
    private int count = 0;
    private int jogada = 0;
    private int[] resultados;
    private int soma;

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

        random = new Random();
        textViewNumberRandom.setText("?");
        resultados = new int[6];
        soma = 0;

        switchGerar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(count == 0) {
                        if(jogada <= 6) {
                            atualizaView();
                        } else {
                            jogada = 0;
                            soma = 0;
                        }
                        switchGerar.setChecked(false);
                    }
                }
            }
        });
   }

    private void atualizaView(){
        final int numeroGerado = random.nextInt(101);
        textViewNumberRandom.setText(String.valueOf(numeroGerado));
        count ++;

        Handler handler = new Handler(getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (count < 10) {
                    animation2(textViewNumberRandom);
                    atualizaView();
                } else {
                    count = 0;
                    jogada ++;
                    textViewNumberRandom.setText(String.valueOf(numeroGerado));

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
                            break;
                    }

                    for(int i = 0; i < resultados.length; i++){
                        soma += resultados[i];
                    }
                    tvSoma.setText("Soma: " + soma);
                }
            }
        }, 50);
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
}

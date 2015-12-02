package wiiber.com.gamerandomball;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button buttonRandom;
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

        buttonRandom = (Button) findViewById(R.id.gerar);
        textViewNumberRandom = (TextView) findViewById(R.id.txvNumeroRandomico);
        tvNumOne     = (Button) findViewById(R.id.textViewNumOne);
        tvNumTwo     = (Button) findViewById(R.id.textViewNumTwo);
        tvNumTree    = (Button) findViewById(R.id.textViewNumTree);
        tvNumFour    = (Button) findViewById(R.id.textViewNumFour);
        tvNumFive    = (Button) findViewById(R.id.textViewNumFive);
        tvNumSix     = (Button) findViewById(R.id.textViewNumSix);
        tvSoma       = (TextView) findViewById(R.id.textViewSoma);

        random = new Random();
        textViewNumberRandom.setText("?");
        resultados = new int[6];
        soma = 0;

        buttonRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count == 0) {
                    if(jogada <= 6) {
                        atualizaView();
                    } else {
                        jogada = 0;
                        soma = 0;
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
                    atualizaView();
                } else {

                    jogada ++;
                    textViewNumberRandom.setText(String.valueOf(numeroGerado));

                    switch (jogada) {
                        case 1:
                            resultados[0] = Integer.valueOf(textViewNumberRandom.getText().toString());
                            tvNumOne.setText(String.valueOf(textViewNumberRandom.getText().toString()));
                            break;
                        case 2:
                            resultados[0] = Integer.valueOf(textViewNumberRandom.getText().toString());
                            tvNumTwo.setText(String.valueOf(textViewNumberRandom.getText().toString()));
                            break;
                        case 3:
                            resultados[0] = Integer.valueOf(textViewNumberRandom.getText().toString());
                            tvNumTree.setText(String.valueOf(textViewNumberRandom.getText().toString()));
                            break;
                        case 4:
                            resultados[0] = Integer.valueOf(textViewNumberRandom.getText().toString());
                            tvNumFour.setText(String.valueOf(textViewNumberRandom.getText().toString()));
                            break;
                        case 5:
                            resultados[0] = Integer.valueOf(textViewNumberRandom.getText().toString());
                            tvNumFive.setText(String.valueOf(textViewNumberRandom.getText().toString()));
                            break;
                        case 6:
                            resultados[0] = Integer.valueOf(textViewNumberRandom.getText().toString());
                            tvNumSix.setText(String.valueOf(textViewNumberRandom.getText().toString()));
                            break;
                    }

                    for(int i = 0; i < resultados.length; i++){
                        soma += resultados[i];
                    }
                    tvSoma.setText("Soma: " +soma);

                    count = 0;
                }
            }
        }, 50);
    }
}

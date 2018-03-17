package br.com.nilsondurval.jokenpo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button jogar;

    private ImageView selecionado;
    private ImageView sorteado;

    private TextView resultado;
    private ImageView emoticon;

    private TextView vitorias;
    private TextView derrotas;

    private int contVitorias;
    private int contDerrotas;

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.jogar = (Button) findViewById(R.id.jogar);
        this.jogar.setTag("jogar");

        this.selecionado = (ImageView) findViewById(R.id.selecionado);
        this.sorteado = (ImageView) findViewById(R.id.sorteado);

        this.resultado = (TextView) findViewById(R.id.resultado);
        this.emoticon = (ImageView) findViewById(R.id.emoticon);

        this.vitorias = (TextView) findViewById(R.id.vitorias);
        this.derrotas = (TextView) findViewById(R.id.derrotas);

        this.contVitorias = 0;
        this.contDerrotas = 0;

        this.mp = MediaPlayer.create(this, R.raw.tech_live);
        this.mp.setLooping(true);
    }

    public void selecionarJogada(View view) {
        if (this.jogar.getTag().equals("jogar")) {
            ImageView viewClicada = (ImageView) view;
            this.selecionado.setImageDrawable(viewClicada.getDrawable());
            this.selecionado.setTag(viewClicada.getId());
        }
    }

    public void jogar(View view) {
        if (this.selecionado.getTag() != null && this.jogar.getTag().equals("jogar")) {

            int indice = (new Random()).nextInt(3);

            switch (indice) {
                case 0:
                    this.sorteado.setImageResource(R.drawable.pedra);
                    this.sorteado.setTag(R.id.pedra);
                    break;
                case 1:
                    this.sorteado.setImageResource(R.drawable.papel);
                    this.sorteado.setTag(R.id.papel);
                    break;
                case 2:
                    this.sorteado.setImageResource(R.drawable.tesoura);
                    this.sorteado.setTag(R.id.tesoura);
                    break;
            }

            if (this.selecionado.getTag().equals(this.sorteado.getTag())) {

                this.resultado.setText("Empatou!");
                this.resultado.setTextColor(getResources().getColor(R.color.preto));
                this.emoticon.setImageResource(R.drawable.triste);

            } else {

                if (this.selecionado.getTag().equals(R.id.pedra)) {

                    if (this.sorteado.getTag().equals(R.id.tesoura)) {

                        this.contVitorias++;

                        this.resultado.setText("Você ganhou!");
                        this.resultado.setTextColor(getResources().getColor(R.color.verde));
                        this.emoticon.setImageResource(R.drawable.sorrindo);
                        this.vitorias.setText("Vitórias: " + contVitorias);

                    } else {

                        this.contDerrotas++;

                        this.resultado.setText("Você perdeu!");
                        this.resultado.setTextColor(getResources().getColor(R.color.vermelho));
                        this.emoticon.setImageResource(R.drawable.chorando);
                        this.derrotas.setText("Derrotas: " + contDerrotas);

                    }

                } else if (this.selecionado.getTag().equals(R.id.papel)) {

                    if (this.sorteado.getTag().equals(R.id.pedra)) {

                        this.contVitorias++;

                        this.resultado.setText("Você ganhou!");
                        this.resultado.setTextColor(getResources().getColor(R.color.verde));
                        this.emoticon.setImageResource(R.drawable.sorrindo);
                        this.vitorias.setText("Vitórias: " + contVitorias);

                    } else {

                        this.contDerrotas++;

                        this.resultado.setText("Você perdeu!");
                        this.resultado.setTextColor(getResources().getColor(R.color.vermelho));
                        this.emoticon.setImageResource(R.drawable.chorando);
                        this.derrotas.setText("Derrotas: " + contDerrotas);

                    }

                } else if (this.selecionado.getTag().equals(R.id.tesoura)) {

                    if (this.sorteado.getTag().equals(R.id.papel)) {

                        this.contVitorias++;

                        this.resultado.setText("Você ganhou!");
                        this.resultado.setTextColor(getResources().getColor(R.color.verde));
                        this.emoticon.setImageResource(R.drawable.sorrindo);
                        this.vitorias.setText("Vitórias: " + contVitorias);

                    } else {

                        this.contDerrotas++;

                        this.resultado.setText("Você perdeu!");
                        this.resultado.setTextColor(getResources().getColor(R.color.vermelho));
                        this.emoticon.setImageResource(R.drawable.chorando);
                        this.derrotas.setText("Derrotas: " + contDerrotas);

                    }

                }

            }

            this.jogar.setText("Reiniciar");
            this.jogar.setTag("reiniciar");
            this.jogar.setBackgroundResource(R.color.laranja);

        } else if (this.selecionado.getTag() != null && this.jogar.getTag().equals("reiniciar")) {

            this.selecionado.setImageDrawable(null);
            this.selecionado.setTag(null);

            this.sorteado.setImageDrawable(null);
            this.sorteado.setTag(null);

            this.resultado.setText("");
            this.emoticon.setImageDrawable(null);

            this.jogar.setText("Jogar");
            this.jogar.setTag("jogar");
            this.jogar.setBackgroundResource(R.color.verde);

        } else {
            Toast.makeText(this, "Selecione uma jogada primeiro!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mp.start();
    }
}

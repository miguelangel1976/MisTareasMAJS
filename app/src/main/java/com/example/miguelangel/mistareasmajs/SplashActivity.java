package com.example.miguelangel.mistareasmajs;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Ocultar la Barra en la Pantalla
        getSupportActionBar().hide();

        //Crear tipo de Fuente
        Typeface miFuente = Typeface.createFromAsset(getAssets(),"fuente.ttf");

        //Identificar el Texto
        TextView texto = (TextView)findViewById(R.id.titulo); //Texto con id: "titulo"
        //findViewById devuelve un componente general: View. Por eso se hace el casting.
        //Asignar la Fuente al Texto
        texto.setTypeface(miFuente);



        //ANIMACIÓN: Pasos
        //1. Crear la Animacion: Cargando la Animación
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.animacion);

        //2. Asignar la Animación al Texto
        texto.startAnimation(anim);



        //Cuando termine la animación, ir a la pantalla del LOGIN
        //Se implementa en la Clase el Escuchador de Eventos

        //Se pone la Animación a la escucha de los eventos:
        //se pone This porque el objeto que está implementando la interfaz
        //es ella misma

        anim.setAnimationListener(this);













    }//Fin del Método onCreate

    //Métodos de la Interface de Eventos

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        //Método cuando termine la animacion pasar a LoginActivity
        //Indicar la Ruta
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);

        //Para impedir el regreso a la pantalla Splash desde Login.
        finish();



    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}

package com.example.miguelangel.mistareasmajs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Para Cargar la Barra
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Añadir elementos a la barra, y cargar todos los elementos del archivo menu_principal
        getMenuInflater().inflate(R.menu.menu_principal,menu);

        return super.onCreateOptionsMenu(menu);

    }//Fin del Método onCreateOption


    //Para dar funcionalidad el Icono añadido a la Barra.
    //Qué pasa cuando se pulsa en dicho Icono: Se realiza con el
    //Método: onOptionsItemSelected

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

      switch (item.getItemId()){

          case R.id.nuevaTarea:
              //Acciones: Visualizar Mensaje de Toast en la pantalla
              Toast.makeText(this, "Añadir Tarea", Toast.LENGTH_SHORT).show();
              return true;

          default:
              return super.onOptionsItemSelected(item);

      }//Fin sel switch-case


    } //Fin del Método onOptionsItemSelected

}//Fin de la Clase MainActivity

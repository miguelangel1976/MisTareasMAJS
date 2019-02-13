package com.example.miguelangel.mistareasmajs;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Ocultar la Barra en la Pantalla
        getSupportActionBar().hide();

    }//Fin del Método onCreate

    //Método asociado al  TextView android:id="@+id/cuentaNueva"
    //Condiciones para tener una función aquí en el contexto:
    //1.Debe ser pública: public
    //2. No debe devolver nada: void
    //3. Debe llevar como parámetro un objeto de clase View.

    public void crearCuenta(View view){

        //Realizar un Toast:
        Toast mensaje = Toast.makeText(this,"Funcionalidad no implementada.",Toast.LENGTH_LONG);

        //Para visualizar el Toast en Pantalla:
        mensaje.show();

    }//Fin del Método crearCuenta


    public void login(View view){

        //Variables asociadas a las referencias de los Textos mediante su identificador "id"
        TextInputEditText usuario = findViewById(R.id.cajaUsuario);
        TextInputEditText contrasenya = findViewById(R.id.cajaContrasenya);


        //Comprobar que se ha introducido el "Usuario" y "Contraseña" de forma correcta.
        //EqualsIgnoreCase: no distinguen las letras mayúsculas y minúsculas.
        //Equals: Distingue las letras mayúsculas y minúsculas.

        if((usuario.getText().toString().equalsIgnoreCase("miguel"))&&
                (contrasenya.getText().toString().equalsIgnoreCase("1234"))){

            //Definir la Actividad a ejecutar:
            Intent actividadMain = new Intent(this,MainActivity.class);

            //Ejecutar la Actividad:
            startActivity(actividadMain);


        }//Fin del If

        else{
            //Realizar un Toast:
            Toast aviso = Toast.makeText(this,"Usuario y/o Contraseña Errónea.",Toast.LENGTH_LONG);

            //Para visualizar el Toast en Pantalla:
            aviso.show();
        }//Fin del Else

    }//Fin del Método login























}//Fin de la Clase

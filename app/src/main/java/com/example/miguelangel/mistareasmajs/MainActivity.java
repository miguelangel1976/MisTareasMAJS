package com.example.miguelangel.mistareasmajs;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miguelangel.mistareasmajs.db.ControladorDB;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    //Para comunicar dos Clases "MainActivity.java" y "ControladorDB":
    //Vamos a pasar una referencia al Controlador.
    //Se crea el objeto de Forma Global, y se da la referencia al crear "onCreate" MainActivity
    ControladorDB controladorDB;

    //Crear un objeto "Adapter" que contenga un Array de Cadenas.
//El ListView va a recibir el Array de Cadenas, con  que va a rellenar la Lista.

    //Declaración del ArrayAdapter.
    private ArrayAdapter<String> miAdaptadorCadenas;


    //Tener una referencia al ListView

    ListView listViewTareas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se pone this: este contexto: MainActivity
        controladorDB = new ControladorDB(this);

        //Hay que dar la Referencia al ListView
        //Se hace un Casting porque devuelve un "View" y se necesita "ListView)
        listViewTareas = (ListView)findViewById(R.id.listaTareas);

        actualizarUI(); //Llamar al Método de "ACTUALIZAR INTERFAZ".


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

        //AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        //Se crea un EditText dentro del Cuadro de Diálogo
        //EditText necesita el contexto que es this (que esté dentro del MainActivity.java).

        //Debido a ello, la definición del EditText se realiza fuera del "AlertDialog",
        // porque desde éste no se puede acceder al contexto.

        final EditText cajaTexto = new EditText(this);



      switch (item.getItemId()){

          case R.id.nuevaTarea:
              //Acciones: Visualizar Mensaje de Toast en la pantalla
              //Toast.makeText(this, "Añadir Tarea", Toast.LENGTH_SHORT).show();


              //Se ha sustituido del TOAST por el siguiente CUADRO DE DIÁLOGO

              /*   */
              //PRIMERA FORMA DE HACER EL "CUADRO DE DIÁLOGO":
              //1. Instanciar el cuadro de diálogo con su constructor.

              //Crear el "Cuadro de Diálogo"
              AlertDialog cuadroDialogo = new AlertDialog.Builder(this)

                //2. Se configuran los campos del "Cuadro de Diálogo"
                .setTitle("Nueva Tarea.")
                .setMessage("Introduce una Tarea que desees realizar.")
                .setView(cajaTexto)//Visualizar Caja de Texto en el "Cuadro de Diálogo
                .setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String tarea = cajaTexto.getText().toString();
                        controladorDB.addTarea(tarea);



                        actualizarUI(); //Llamar al Método de "ACTUALIZAR INTERFAZ".



                        //Mensaje de Prueba Toast
                        Toast.makeText(MainActivity.this, "Se ha añadido una nueva tarea.", Toast.LENGTH_SHORT).show();
                    }//Fin del onClick
                })//Fin del setPositiveButton

                //No se va hacer nada al pulsar el botón cancelar
                .setNegativeButton("Cancelar", null)

                //3.Crear Cuadro de Diálogo.
                .create();

                //4. Mostar el Cuadro de Diálogo en Pantalla
                cuadroDialogo.show();

                //Fin de la PRIMERA FORMA DE HACER EL "CUADRO DE DIÁLOGO"
                /*   */



              /*
              //SEGUNDA FORMA DE HACER EL "CUADRO DE DIÁLOGO":
              //1. Instanciar el cuadro de diálogo con su constructor.

              //Crear el "Cuadro de Diálogo"
              AlertDialog.Builder cuadroDialogo = new AlertDialog.Builder(this);

              //2. Se configuran los campos del "Cuadro de Diálogo"
              cuadroDialogo.setTitle("Nueva Tarea.");
              cuadroDialogo.setMessage("Introduce una Tarea que desees realizar.");
              cuadroDialogo.setView(cajaTexto);//Visualizar Caja de Texto en el "Cuadro de Diálogo
              cuadroDialogo.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      //Mensaje de Prueba Toast
                      Toast.makeText(MainActivity.this, "Se ha añadido una nueva tarea.", Toast.LENGTH_SHORT).show();

                   }//Fin del onClick
              });//Fin del setPositiveButton

              //No se va hacer nada al pulsar el botón cancelar
              cuadroDialogo.setNegativeButton("Cancelar", null);

              //3.Crear Cuadro de Diálogo.
              cuadroDialogo.create();

              //4. Mostar el Cuadro de Diálogo en Pantalla
              cuadroDialogo.show();

              //Fin de la SEGUNDA FORMA DE HACER EL "CUADRO DE DIÁLOGO"
              */



              return true;

          default:
              return super.onOptionsItemSelected(item);

      }//Fin sel switch-case


    } //Fin del Método onOptionsItemSelected



    //Método que permite actualizar el interfaz usuario con la Base
    //de Datos.
    //Va a ser un Método que unicamente va a ser llamado desde esta
    //clase, por lo que puede ser "Privado (private)".

    //Se va a llamar a este Método "actualizarUI" en:
    // Al crear "onCreate" de MainActivity: "LA PRIMERA VEZ QUE SE CREA EL ACTIVITY".
    // Al agregar una nueva tarea, se debe también actualizar: "onClick" de MainActivity.
    // En el Método "borrarTarea" de la clase "MainActivity": cuando se borra un dato de la Base de Datos.

    private void actualizarUI(){
        //Para actualizar el ListView, para que coja elementos
        // se necesita un objeto "Adapter" que contenga un array de caracteres.

        // Poniendo una cadena en cada uno de los elementos del ListView,
        //cada vez que se utilize un ListView se necesita un ArrayAdapter.

        //Crear objeto de ArrayAdapter
        //This: este contexto; R.layout.item_tarea:para indicar con qué elementos vamos a rellenar el ListView.
        //R.id.titulo_tarea: Para indicar lo que necesitamos dentro del "item_tarea.
        //Para indicar con qué lo vamos a rellenar: Con los registros de la Base de Datos:
        //Por lo que hay que llamar al ControladorDB que tiene el método "obtenerTareas".



        //SI ES LA PRIMERA VEZ QUE ENTRAMOS VAMOS A OBTENER UN NULO, y en el método obtenerTareas VAMOS A TENER PROBLEMAS.
        //ANTES DE ACTUALIZAR EL LIST VIEW ES NECESARIO CONOCER CUANTOS REGISTROS TENEMOS EN LA BASE DE DATOS.


        //SI NO HAY ELEMENTOS HAY QUE PASAR AL setAdapter un NULL.
        //listViewTareas.setAdapter(null);

        //PARA CONOCER EL NÚMERO DE REGISTROS QUE TIENE LA TABLA SE CREA UN Método "numeroRegistros"
        // en la Clase "ControladorDB".

        int valor_numero_registros = controladorDB.numeroRegistros();

        if(valor_numero_registros == 0){
            //Si no hay registros en la Base de Datos: se da al ListView un "setAdapter" nulo.
            listViewTareas.setAdapter(null);

        }//Fin del If

        else{

            //SI HAY REGISTROS SE HACE LO SIGUIENTE:
            miAdaptadorCadenas=new ArrayAdapter<>(this,R.layout.item_tarea,R.id.titulo_tarea,controladorDB.obtenerTareas());

            ;

            //Asignar al "ListView" el "Adaptador De cadenas".
            listViewTareas.setAdapter(miAdaptadorCadenas);

        }//Fin del Else


    }//Fin del Método "actualizarUI"





    //Método asociado al Botón "Borrar Tarea".
    //El Botón "Borrar Tarea" llama a este método.

    public void borrarTarea(View view){
    //Se va a llamar a un método del Controlador que se encargue de dar de baja.
    //Se va a dar el texto de la tarea que se desea dar de baja en la Base de Datos.


         //En el "item_tarea.xml":
        //Hay que coger el texto del TextView asociado al Botón "Borrar Tarea" que se ha pulsado (CLICK).
        // Para ello, coger el Botón.  Del Botón se obtiene el Padre que en nuestro caso es "RelativeLayout"
        //y desde el Padre se busca un elemento con el identificador del TextView android:id="@+id/titulo_tarea".
        //De esta forma nos va a dar el TextView que acompaña a ese botón, y desde aquí obtener el String del TextView.

        //Si no se hace lo anterior, al pulsar un determinado botón "Borrar Tarea" me empieza a borrar los primeros elementos
        //de la lista o ListView.


        //1. Obtener el Padre del Botón.
        //View es el Botón, debido a que el Botón es el que llama a este Método Actual:"borrarTarea"
        //View es el componente que ha llamado al Método: El Botón.

        View parent = (View)view.getParent(); //getParent retorna un ViewParent, hay que hacer el casting a View.
            //El "parent" es el "RelativeLayout", y desde aquí se busca el TextView con id=titulo_tarea.

        //2. Dentro del Padre buscamos el identificador del TextView que está asociado al Botón.

        //Devuelve un View, por lo que se necesita hacer casting a TextView
        TextView tareaTextView =(TextView)parent.findViewById(R.id.titulo_tarea);

        //tareaTextView es la Caja de Texto.


        //3.Obtener el contenido del TextView
            String nombreTarea = tareaTextView.getText().toString();


       //El texto del TextView asociado al Botón que se ha pulsado, se envia al Método "BorrarTarea"
       //de la clase "controladorDB".
        controladorDB.borrarTarea(nombreTarea);


        //4. Actualizar la interfaz una vez eliminado los datos en la Base de Datos.

        actualizarUI(); //Llamar al Método de "ACTUALIZAR INTERFAZ".

        //Mensaje de Prueba Toast
        Toast.makeText(MainActivity.this, "Se ha Eliminado la tarea indicada.", Toast.LENGTH_SHORT).show();


    }//Fin del Método "borrarTarea"



}//Fin de la Clase MainActivity

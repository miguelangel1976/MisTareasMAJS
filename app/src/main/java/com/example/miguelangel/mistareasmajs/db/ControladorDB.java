package com.example.miguelangel.mistareasmajs.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*public class ControladorDB extends SQLiteOpenHelper {
    public ControladorDB(Context context,  String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
*/
/*Hay que pasarle:
- El nombre de la Base de Datos: que es el nombre del paquete.



 */
public class ControladorDB extends SQLiteOpenHelper {


    //CONDICIONES Y PASOS A SEGUIR:
    //1.El controlador extienda sea hija de SQLiteOpenHelper (ControladorDB extends SQLiteOpenHelper).
    //2. Necesita un Controlador para indicar donde se va a crear la Base de Datos y la Versión.
    //3. Con el Método onCreate se crea la Tabla.

    //Constructor.
    public ControladorDB(Context context) {
        super(context, "com.example.miguelangel.mistareasmajs.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase baseDatos) {
        //Es donde se va a crear la Tabla de la Base de Datos
        //y un método "Constructor" que también es obligatorio

        //En este método se pueden ejecutar instrucciones SQL
        //Para ejecutar una instrucción SQL se utiliza el método: execSQL


        //Primer Parámetro: ID entero autoincremental y Primary Key (PK).
        //Segundo Parámetro: Nombre que va a ser un Texto no Nulo (Dato Obligatorio).

        //PRIMERA FORMA
       /* String consulta = "CREATE TABLE TAREAS (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT NOT NULL)";
        baseDatos.execSQL(consulta);
        */

       //SEGUNDA FORMA
        baseDatos.execSQL("CREATE TABLE TAREAS (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT NOT NULL)");

    }

    //Este Método sirve para migración de datos cuando se cambia la
    //Versión de la Base de Datos.

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }//Fin del Método onUpgrade


    //El Método "addTarea" va a ser pública, y tiene como parámetro el nombre de la tarea.
    public void addTarea(String tarea){
    /*Todos los métodos que vamos a poner aquí: Insertar, Consultar, Actualizar y Borrar
    se definen en tres pasos:
         1)Abrir la Base de Datos.
         2)Sentencias: Ejecutar la Acción correspondiente.
         3)Cerrar la Base de Datos.
    */

    /*Para abrir la Base de Datos necesitamos el Objeto del Controlador
    y obtener la Base de Datos: Para Insertar : Se abre la Base de Datos para Lectura y Escritura.
    que devuelve una referencia a la base de datos, almacenandose en una variable "db"
    de Clase SQLiteDatabase.
     */

    //1. Se está abriendo la Base de Datos para Lectura y Escritura.
    SQLiteDatabase db=this.getWritableDatabase();

    //2. Lo siguiente es realizar la operación de INSERTAR.
    // El registro que vamos a insertar debe ser del tipo ContentValues.
    //El ContentValues es para poner la clave (Nombre del Campo) y Valor asignado

        ContentValues registro = new ContentValues();
        registro.put("NOMBRE",tarea); //Nombre del Campo: "NOMBRE", y valor asignado: "TAREA".

        //Nombre de la Tabla: "TAREAS", Se introduce el registro.

        //Primera Forma de hacerlo:
        db.insert("TAREAS",null,registro);

        //Segunda Forma de hacerlo:
       // db.execSQL("INSERT INTO TAREAS VALUES(null, ' + tarea + ');");

     //3. Cerrar la Base de Datos.
     db.close();


    }//Fin del Método addTarea


    //Método Público para acceder desde otra clase, y devuelve un array de cadenas: String[].
    public String[] obtenerTareas(){

// 1)Abrir la Base de Datos.Para consulta se puede hacer "Solo Lectura" con el método getReadableDatabase().
        SQLiteDatabase db=this.getReadableDatabase();

        // 2)Sentencias: Ejecutar la Acción correspondiente.
        //Para realizar consultas se realiza con el método: rawQuery(), en el que se le pasa la consulta en SQL.
        //También es necesario indicar los parámetros que van con la sentencia SQL mediante ?:
        //debido a que no estamos utilizando ningún parámetro "?"en la sentencia SQL: se pone NULL.

        //Se obtiene campo 0 al Id y campo 1 al nombre.
        Cursor cursor=db.rawQuery("SELECT * FROM TAREAS", null);


        //Se obtiene campo 0 al nombre.
        //Cursor cursor=db.rawQuery("SELECT NOMBRE FROM TAREAS", null);


        //Al hacer esta consulta, se obtiene un conjunto de Registros: denominado "Cursor",
        //consiste a una referencia de los registros que he sacado con el SELECT.


        //Hay que recorrer el "cursor" que es un conjunto de Registros.

        //El número de registros del "cursor" se obtiene mediante:getCount().

        int numero_registros = cursor.getCount();

        //Si el "numero_registros" es igual a 0, significa que no hay elementos en la Tabla.

        if(numero_registros == 0){ //Tabla Vacia
            db.close();//3)Cerrar la Base de Datos.
            return null; //No hay nada que retornar.

        }//Fin del If


        else{ //Hay Elementos en la Tabla, por lo que hay registros en el Cursor.
            //Se procede a recorrer los registros del cursor, con el propósito de
            //almacenarlos en un array String y retornar dichor array.

            //Mover al Principio del Cursor.
            cursor.moveToFirst(); //Ir al Primer registro del cursor.

            //Declarar array de "tareas" con tamaño igual al de numero_registros.
            String[]tareas = new String[numero_registros];


            //Recorrer el Cursor
            for(int i=0; i<numero_registros;i++){
                //El campo 0 de la Tabla corresponde al "ID"
                //El campo 1 de la Tabla corresponde al "Nombre"
                //Nos interesa el Campo 1, debido a que el campo 0 es autonumérico.

                tareas[i]=cursor.getString(1);
                //Se coge el Primer Campo debido a que se ha hecho un "SELECT * FROM TAREAS
                //por lo que se obtiene el campo 0 correspondiente al "ID" y el campo 1 al "Nombre".

                //Si se hubiera hecho la sentencia "SELECT NOMBRE FROM TAREAS
                //La tabla solo obtiene un único campo que es nombre, y habría que haber
                //escogido el campo "0"
                //tareas[i]=cursor.getString(0);


                //Mover el cursor al siguiente Registro
                cursor.moveToNext();

            }//Fin del for

            //3)Una vez almacenados los registros en el array, cerrar la base de datos.
            db.close();

            return tareas; //Retornar el Array

        }//Fin del Else


    }//Fin del Método obtenerTareas




    //Método que devuelve el Número de Registros que Tiene la Tabla
    //Para conocer si la TABLA TIENE REGISTROS para el Método "ActualizarUI" de la Clase "MainActivity"
    public int numeroRegistros(){

        //1)Abrir la base de Lecturas "En Modo Lectura" para Consultas.
        SQLiteDatabase db=this.getReadableDatabase();
        //Se obtiene campo 0 al Id y campo 1 al nombre.
        Cursor cursor=db.rawQuery("SELECT * FROM TAREAS", null);

        //2)Obtener número Registros de la Tabla.
        int numero_registros = cursor.getCount();

        //3)Cerrar La Base de Datos.
        db.close();

        //Retornar valor del número registros de la Tabla.
        return numero_registros;
    }//Fin del Método numeroRegistros





    //Método para dar de baja de la Base de Datos una determinada Tarea

    //Debe ser pública porque se va acceder desde otras clases.
    //No retorna ningún valor.
    //Recibe como parámetro el nombre de la tarea a Borrar.

    //Este métood es llamado desde el Método "borrarTarea" del MainActivity
    
    public void borrarTarea(String tarea){
       // 1)Abrir la Base de Datos en modo Lectura y Escritura, debido a que se van a Borrar Datos.
        SQLiteDatabase db=this.getWritableDatabase();


       //2)Sentencias: Ejecutar la Acción correspondiente.
        //Al poner el símbolo de interrogación se dice que es un parámetro del DELETE,
        // que va en el siguiente argumento.
        //Los simbolos de interrogación es un array de parámetros, ya que va a ser
        //una Consulta Parametrizada.

        //En nuestro caso el parámetro es el nombre de la Tarea que se desea Borrar.
        //Requiere que pasemos un Array de String y no un únimo String que es "tarea".

        //Por lo que hay que convertir el String "tarea" en un Array String de un único elemento.

        // Pasar: String tarea;  -->  nes String[]{tarea};
        db.delete("TAREAS","NOMBRE=?", new String[]{tarea});


       // 3)Cerrar la Base de Datos.
        db.close();
    }//Fin del Método borrarTarea







}//Fin de la Clase

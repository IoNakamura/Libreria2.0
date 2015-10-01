package com.example.bdlibreria20;


import android.support.v7.app.ActionBarActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.view.MenuItem;


public class MainActivity extends Activity {
	
	private EditText txtDocumento, txtNombre, txtNLibro, txtVolumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        txtDocumento = (EditText) findViewById(R.id.txtDocumento);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtNLibro = (EditText) findViewById(R.id.txtNLibro);
        txtVolumen = (EditText) findViewById(R.id.txtVolumen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void guardardor(View v)
    {
    	AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, 
    			"administracion", null, 1);
    	SQLiteDatabase bd = admin.getWritableDatabase();
    	String doc = txtDocumento.getText().toString();
    	String nom = txtNombre.getText().toString();
    	String nlibro = txtNLibro.getText().toString();
    	String vol = txtVolumen.getText().toString();
    	
    	ContentValues almacenar = new ContentValues();
    	almacenar.put("Documento", doc);
    	almacenar.put("Nombre", nom);
    	almacenar.put("NombreLibro", nlibro);
    	almacenar.put("Volumen", vol);
    	bd.insert("usuarios", null, almacenar);
    	bd.close();
    	txtDocumento.setText("");
    	txtNombre.setText("");
    	txtNLibro.setText("");
    	txtVolumen.setText("");
    	
    	Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show();
    }
  
public void buscador(View v)
   {
	
	   AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, 
			   "administracion", null, 1);
	   SQLiteDatabase bd = admin.getWritableDatabase();
	   String doc = txtDocumento.getText().toString();
	  Cursor fila = bd.rawQuery(
			  "select Nombre,NombreLibro,Volumen  from usuarios where Documento=" + doc, null);
	  if (fila.moveToFirst())
	  {
		  txtNombre.setText(fila.getString(0));
		  txtNLibro.setText(fila.getString(1));
		  txtVolumen.setText(fila.getString(2));
	  }else
		  Toast.makeText(this, "La persona que don señor decea buscar no existe", 
				  Toast.LENGTH_SHORT).show();
	  
	  bd.close();
	
   }
   public void eliminador(View v)
   {
	   AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, 
			   "administracion", null, 1);
	   SQLiteDatabase bd = admin.getWritableDatabase();
	   String doc = txtDocumento.getText().toString();
	   int cant = bd.delete("usuarios", "Documento = "+doc, null);
	   bd.close();
	   txtDocumento.setText("");
	   txtNombre.setText("");
	   txtNLibro.setText("");
	   txtVolumen.setText("");
	   if (cant == 1)
	   {
		   Toast.makeText(this, "Lastima ya se elimino esa persona", 
				   Toast.LENGTH_SHORT).show();
	   }else
		   Toast.makeText(this, "Lo sentimos: El usuario no existe u.u ",
				   Toast.LENGTH_SHORT).show();
   }
   public void modificador (View v)
   {
	   AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
			   "administracion", null, 1);
	   SQLiteDatabase bd = admin.getWritableDatabase();
	   String doc = txtDocumento.getText().toString();
	   String nom = txtNombre.getText().toString();
	   String nlibro = txtNLibro.getText().toString();
	   String vol = txtVolumen.getText().toString();
	   
	   ContentValues Registro = new ContentValues();
	   Registro.put("Nombre", nom);
	   Registro.put("NombreLibro", nlibro);
	   Registro.put("Volumen", vol);
	   
	   int cant = bd.update("usuarios", Registro, "Documento = "+ doc, null);
	   bd.close();
	   if (cant == 1) //Es una vandera. Marca uno o cero.
	   {
		   Toast.makeText(this, "Listo!! Ya se modificaron los datos! {^-^}", 
				   Toast.LENGTH_SHORT).show();
	   }else
		   Toast.makeText(this, "Lo sentimos Pero la persona con ese documento no existe", 
				   Toast.LENGTH_SHORT).show();
	   
   }
   
}
/* 

@Override
public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
        return true;
    }
    return super.onOptionsItemSelected(item);
} */
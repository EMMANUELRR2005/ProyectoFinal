package gt.edu.umg.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import gt.edu.umg.proyecto.DBThings.DbContactos;

public class HomeActivity extends AppCompatActivity {
    EditText editTextUsuarioNombre, editTextTelefono, editTextCorreo;
    Button buttonFoto, buttonGuardar;
    DbContactos dbContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Para iniciar textos y botones
        editTextUsuarioNombre = findViewById(R.id.editTextUsuarioNombre);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        buttonFoto = findViewById(R.id.buttonFoto);
        buttonGuardar = findViewById(R.id.buttonGuardar);

        //Para iniciar la BD
        dbContactos = new DbContactos(this);

        //Proceso para que el boton guardar, realice el guardado
        buttonGuardar.setOnClickListener(v -> {
            //En este proceso obtendra los valores asignados
            String Nombre = editTextUsuarioNombre.getText().toString();
            String Telefono = editTextTelefono.getText().toString();
            String Correo = editTextCorreo.getText().toString();

            //Para que se logren insertar los datos a la BD
            long id = dbContactos.insertaContacto(Nombre, Telefono, Correo);
            if (id > 0) {
                Toast.makeText(this, "Guardado exitoso", Toast.LENGTH_SHORT).show();
                //Para limpiar campos
                editTextUsuarioNombre.setText("");
                editTextTelefono.setText("");
                editTextCorreo.setText("");
            } else {
                Toast.makeText(this, "Ha ocurrido un error :(", Toast.LENGTH_SHORT).show();
            }
        });

        //Listener para el boton foto
        buttonFoto.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, Camara.class);
            startActivity(intent);});

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}

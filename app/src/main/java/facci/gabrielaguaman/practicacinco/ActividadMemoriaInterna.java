package facci.gabrielaguaman.practicacinco;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ActividadMemoriaInterna extends AppCompatActivity implements View.OnClickListener{

    EditText edt1, edt2, edt3;
    Button btn1, btn2;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_memoria_interna);
        edt1 = (EditText)findViewById(R.id.edt1);
        edt2 = (EditText)findViewById(R.id.edt2);
        edt3 = (EditText)findViewById(R.id.edt3);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        txt = (TextView)findViewById(R.id.txt);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                try {
                    OutputStreamWriter writer = new OutputStreamWriter(openFileOutput("archivo.txt", Context.MODE_APPEND));
                    writer.write(edt1.getText().toString()+
                            ","+edt2.getText().toString()+
                            ","+edt3.getText().toString());
                    writer.close();
                }catch (Exception ex){
                    Log.e("Archivo MI","Error en el archivo de escritura");
                }
                break;
            case R.id.btn2:
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("archivo.txt")));
                    String datos = reader.readLine();
                    String [] listaPersonas = datos.split(";");
                    for (int i = 0; i < listaPersonas.length; i++){
                        txt.append(listaPersonas[i].split(",")[0]+
                                ""+listaPersonas[i].split(",")[1]+
                                ""+listaPersonas[i].split(",")[2]);
                    }
                    reader.close();
                }catch (Exception ex){
                    Log.e("Archivo MI","Error en la lectura del archivo"+ex.getMessage());
                }
                break;
        }

    }
}

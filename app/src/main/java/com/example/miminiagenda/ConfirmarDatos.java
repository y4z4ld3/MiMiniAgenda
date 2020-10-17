package com.example.miminiagenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class ConfirmarDatos extends AppCompatActivity {

    String nombre,fecha_nac,telefono,email,descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);

        Bundle parametros = this.getIntent().getExtras();

        nombre       = parametros.getString(getResources().getString(R.string.pNombre));
        fecha_nac    = parametros.getString(getResources().getString(R.string.pFechaNac));
        telefono     = parametros.getString(getResources().getString(R.string.pTelefono));
        email        = parametros.getString(getResources().getString(R.string.pEmail));
        descripcion  = parametros.getString(getResources().getString(R.string.pDescripcion));


        TextView tvNombre       = (TextView)findViewById(R.id.tv_nombre);
        TextView tvFecha        = (TextView)findViewById(R.id.tv_fecha_nac);
        TextView tvTelef        = (TextView)findViewById(R.id.tv_telefono);
        TextView tvEmail        = (TextView)findViewById(R.id.tv_email);
        TextView tvDescripcion  = (TextView)findViewById(R.id.tv_descripcion);

        tvNombre.setText(nombre);
        tvFecha.setText(fecha_nac);
        tvTelef.setText(telefono);
        tvEmail.setText(email);
        tvDescripcion.setText(descripcion);

        AppCompatButton mBtnEditarAtras= (AppCompatButton)findViewById(R.id.btn_Editar);
        AppCompatImageButton mImgBtnAtras = (AppCompatImageButton)findViewById(R.id.flecha_atras);

        mBtnEditarAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Regresar();
            }
        });

        mImgBtnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Regresar();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Regresar();
        return super.onKeyDown(keyCode, event);
    }

    public void Regresar(){
        Intent intent = new Intent(ConfirmarDatos.this,MainActivity.class);

        Bundle extras = new Bundle();

        extras.putString(getResources().getString(R.string.pNombre), nombre);
        extras.putString(getResources().getString(R.string.pFechaNac), fecha_nac);
        extras.putString(getResources().getString(R.string.pTelefono), telefono);
        extras.putString(getResources().getString(R.string.pEmail), email);
        extras.putString(getResources().getString(R.string.pDescripcion), descripcion);

        intent.putExtras(extras);
        startActivity(intent);
        finish();
    };

}
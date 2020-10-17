package com.example.miminiagenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    EditText mNombre,mTvFecha,mTelef,mEmail,mDescripcion;
    AppCompatImageButton mIBtnCalendario;
    Calendar mCalendario;
    DatePickerDialog.OnDateSetListener mDPDL;
    AppCompatButton mBtnSiguiente;
    int dia, mes, anno;
    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCalendario = Calendar.getInstance();
        dia = mCalendario.get(Calendar.DAY_OF_MONTH);
        mes = mCalendario.get(Calendar.MONTH);
        anno = mCalendario.get(Calendar.YEAR);

        mTvFecha = (EditText)findViewById(R.id.editTextDate);
        mIBtnCalendario =(AppCompatImageButton)findViewById(R.id.img_btn_calendario);
        mBtnSiguiente = (AppCompatButton)findViewById(R.id.btn_Siguiente);
        mNombre = (EditText)findViewById(R.id.edtxt_nombre);
        mTelef = (EditText)findViewById(R.id.edtxt_telefono);
        mEmail = (EditText)findViewById(R.id.edtxt_email);
        mDescripcion = (EditText)findViewById(R.id.edtxt_descripcion);


        Bundle parametros = this.getIntent().getExtras();


        if (parametros != null) {
            String nombre       = parametros.getString(getResources().getString(R.string.pNombre));
            String fecha_nac    = parametros.getString(getResources().getString(R.string.pFechaNac));
            String telefono     = parametros.getString(getResources().getString(R.string.pTelefono));
            String email        = parametros.getString(getResources().getString(R.string.pEmail));
            String descripcion  = parametros.getString(getResources().getString(R.string.pDescripcion));

            mNombre.setText(nombre);
            mTvFecha.setText(fecha_nac);
            mTelef.setText(telefono);
            mEmail.setText(email);
            mDescripcion.setText(descripcion);
        }

        mIBtnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogo();
            }
        });

        mTvFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogo();
            }
        });


        mDPDL = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int mAnno, int mMes, int mDia) {
                mTvFecha.setText(mDia + "/" + (mMes+1) + "/" + mAnno );
            }
        };

        mBtnSiguiente.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StringFormatInvalid")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ConfirmarDatos.class);
                /*
                Log.i(TAG,"Valor N:" +  mNombregetText());
                Log.i(TAG,"Valor F:" +  mTvFecha.getText());
                Log.i(TAG,"Valor T:" +  mTelef.getText());
                Log.i(TAG,"Valor E:" +  mEmail.getText());
                Log.i(TAG,"Valor D:" +  mDescripcion.getText()); */

                if (
                        String.valueOf(mNombre.getText()).isEmpty() ||
                        String.valueOf(mNombre.getText()).equals("") ||
                        String.valueOf(mTvFecha.getText()).isEmpty() ||
                        String.valueOf(mTvFecha.getText()).equals("") ||
                        String.valueOf(mTelef.getText()).isEmpty() ||
                        String.valueOf(mTelef.getText()).equals("") ||
                        String.valueOf(mEmail.getText()).isEmpty() ||
                        String.valueOf(mEmail.getText()).equals("") ||
                        String.valueOf(mDescripcion.getText()).isEmpty() ||
                        String.valueOf(mDescripcion.getText()).equals("")
                )
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.msgDatosFaltantes), Toast.LENGTH_SHORT).show();
                else {
                    Bundle extras = new Bundle();
                    extras.putString(getResources().getString(R.string.pNombre), String.valueOf(mNombre.getText()));
                    extras.putString(getResources().getString(R.string.pFechaNac), String.valueOf(mTvFecha.getText()));
                    extras.putString(getResources().getString(R.string.pTelefono), String.valueOf(mTelef.getText()));
                    extras.putString(getResources().getString(R.string.pEmail), String.valueOf(mEmail.getText()));
                    extras.putString(getResources().getString(R.string.pDescripcion), String.valueOf(mDescripcion.getText()));
                    intent.putExtras(extras);
                    startActivity(intent);
                    finish();

                }
            }
        });


    }

    public void mostrarDialogo() {
        //Log.i(TAG,"Dia:" + dia);
        DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,  mDPDL, anno , mes, dia  );
        dialog.getWindow().setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
        dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        mCalendario.set(anno-120,1,1); //Fecha Minima 120 años menos
        dialog.getDatePicker().setMinDate(mCalendario.getTimeInMillis());
        dialog.getWindow().setTitle(getResources().getString(R.string.hintFechaNac));
        dialog.show();
    }


}
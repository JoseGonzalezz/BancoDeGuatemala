package com.example.bancogt;

import androidx.appcompat.app.AppCompatActivity;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpResponseException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.Proxy;

import android.content.Intent;


public class TipoCambioActivity extends AppCompatActivity {

    private EditText txt1;
    private TextView txt2;
    private Button btn;
    String valor, msj;
    String resultado="";
    SoapPrimitive resultString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_cambio);

        txt1 =  findViewById(R.id.txtValor);
        txt2 =  findViewById(R.id.txtTotal);
        btn =   findViewById(R.id.btnAceptar);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                segundoPlano tarea = new segundoPlano();
                tarea.execute();
            }
        });
    }

        private class segundoPlano extends AsyncTask<String,Void,String>{

            @Override
            protected String doInBackground(String... params) {
                return consumesWsTipsCambodia();
            }

            @Override
            protected void onPreExecute(){
            }


        }

    public String consumesWsTipsCambodia(){
        String SOAP_ACTION = "http://www.banguat.gob.gt/variables/ws/TipoCambioDia";
        String METHOD_NAME = "TipoCambioDia";
        String NAMESPACE = "http://www.banguat.gob.gt/variables/ws";
        String URL = "https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx";

        try{
            SoapObject request =  new SoapObject(NAMESPACE, METHOD_NAME);

            //request.addProperty("Fro");
            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.implicitTypes = true;
            soapEnvelope.setOutputSoapObject(request);
            HttpTransportSE httpTransport =  getHttpTransportSE(URL);

            try {

                httpTransport.call(SOAP_ACTION, soapEnvelope);
                //resultString = (SoapPrimitive) soapEnvelope.getResponse();

                //txt2.setText(resultString.toString());
            } catch (HttpResponseException e) {
                // TODO Auto-generated catch block
                Log.e("HTTPLOG", e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.e("IOLOG", e.getMessage());
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
                Log.e("XMLLOG", e.getMessage());
                e.printStackTrace();
            } //send request


            try {
                SoapObject soapObject = (SoapObject)soapEnvelope.getResponse();
                soapObject = (SoapObject) soapObject.getProperty("CambioDolar");
                soapObject = (SoapObject) soapObject.getProperty("VarDolar");
                String fecha = soapObject.getProperty("fecha").toString();
                String referencia = soapObject.getProperty("referencia").toString();
                double tipoCambio = Double.parseDouble(referencia);
                double total = Double.parseDouble(txt1.getText().toString()) * tipoCambio;

                resultado = String.valueOf(total);
                txt2.setText(resultado);

                Log.i("RESPONSE",String.valueOf(soapObject)); // see output in the console
            } catch (SoapFault e) {
                // TODO Auto-generated catch block
                Log.e("SOAPLOG", e.getMessage());
                e.printStackTrace();
            }

        }catch(Exception ex){
            msj = "ERROR: " + ex.getMessage();
            Toast.makeText(getApplicationContext(),
                    "Fracaso", Toast.LENGTH_SHORT);
        }

        return resultado;

    }


        private final HttpTransportSE getHttpTransportSE(String MAIN_REQUEST_URL) {
            HttpTransportSE ht = new HttpTransportSE(Proxy.NO_PROXY,MAIN_REQUEST_URL,60000);
            ht.debug = true;
            ht.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
            return ht;
        }

    public void Cerrar(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    public void Cuentas(View view){
        Intent i = new Intent(this, CuentasActivity.class);
        startActivity(i);
    }
    public void Home(View view){
        Intent i = new Intent(this, PrincipalActivity.class);
        startActivity(i);
    }
}
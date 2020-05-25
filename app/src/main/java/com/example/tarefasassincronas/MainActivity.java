package com.example.tarefasassincronas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //References

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);


    }

    public void iniciarAsyncTask(View view){

        MyAsyncTask task = new MyAsyncTask();
        task.execute(10); // passar os 3 parametros da classe

    }

    /**
     * 1->Parametro a ser passado para a classe - Ex: URL (baixar conteudos da web) ou imagem, NÚMERO, OU VOID (FAZIO)
     * 2->Tipo de valor que será utilizado para o progresso da tarefa
     * 3-> Retorno após tarefa finalizada . Ex.: Pode ser uma lista, inteiro, string ou outros valores
     * */
    class MyAsyncTask extends AsyncTask<Integer,Integer,String>{
        // Acionar botão direito e generate....overide methods...
        //Ajustar ordem de execução dos métodos


        @Override
        protected void onPreExecute() {
            //configurações inicias

            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) { // Integer... -> varios números inteiros
            //tarefas pesadas a serem executadas , rodar paralelo com a UI Principal

            int numero = integers[0]; //busca o primeiro item do array
            //int numero2 = integers[1]; //busca o segundo item do array

            for (int i=0;i<numero;i++){

                publishProgress(i); // enviar valor para progressupdate

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


            return "Finalizado";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //Tarefas para serem enviar para UI Principal

            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }


        @Override
        protected void onPostExecute(String s) {
            //Executado no contexto da UI Principal

            super.onPostExecute(s);

            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

            progressBar.setProgress(0);
            progressBar.setVisibility(View.INVISIBLE);
        }



    }



}

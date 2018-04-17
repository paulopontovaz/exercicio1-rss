package br.ufpe.cin.if1001.rss;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import br.ufpe.cin.if1001.rss.Utilities.Constants;

public class MainActivity extends Activity {
    //Modificando o TextView para ListView
    private ListView conteudoRSS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conteudoRSS = (ListView) findViewById(R.id.conteudoRSS);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Busca entre as preferências o valor da chave "rssfeed" para popular a ListView com o retorno do processamento.
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String rssfeed = sharedPreferences.getString(Constants.RSS_FEED, getString(R.string.rss_feed_default));
        new CarregaRSStask().execute(rssfeed);
    }

    //Função necessária para exibir o botão estilo dropdown menu no canto superior direito da activity.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Função utilizada quando um item do dropdown menu (criado pela função acima) é acionado.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.preferences_button)
            //Redirecionando para a activity de preferências se o item selecionado no menu tiver a ID do "preferences_button"
            startActivity(new Intent(getApplicationContext(), PreferenciasActivity.class));
        return super.onOptionsItemSelected(item);
    }

    private class CarregaRSStask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(), "iniciando...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String conteudo = "provavelmente deu erro...";
            try {
                conteudo = getRssFeed(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(), "terminando...", Toast.LENGTH_SHORT).show();

            //Obtendo os itens através do parser da string recebida na função.
            List<ItemRSS> itens = null;
            try {
                itens = ParserRSS.parse(s);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Populando o adapter criado com os itens da lista obtida pelo parser.
            ItemRSSAdapter itemRSSArrayAdapter = new ItemRSSAdapter(getApplicationContext(), R.layout.itemlista, itens);
            conteudoRSS.setAdapter(itemRSSArrayAdapter);
            //Cria-se um intent para acionar o navegador do android toda vez que uma opção da ListView for acionada.
            //O link do item selecionado na lista será enviado junto com o intent (setData).
            conteudoRSS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(((ItemRSS)adapterView.getItemAtPosition(i)).getLink()));
                    startActivity(browserIntent);
                }
            });
        }
    }

    //Opcional - pesquise outros meios de obter arquivos da internet
    private String getRssFeed(String feed) throws IOException {
        InputStream in = null;
        String rssFeed = "";
        try {
            URL url = new URL(feed);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            in = conn.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            for (int count; (count = in.read(buffer)) != -1; ) {
                out.write(buffer, 0, count);
            }
            byte[] response = out.toByteArray();
            rssFeed = new String(response, "UTF-8");
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return rssFeed;
    }
}

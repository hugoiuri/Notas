package br.com.hugo.notas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import br.com.hugo.notas.R;
import br.com.hugo.notas.adapter.NotaAdapter;
import br.com.hugo.notas.model.Nota;
import br.com.hugo.notas.model.NotasDatabase;

public class MainActivity extends AppCompatActivity {

    private MaterialSearchView searchView;
    private RecyclerView recyclerView;
    private NotasDatabase db;
    private NotaAdapter notaAdapter;
    private List<Nota> listaNotas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddId);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        db = NotasDatabase.getDATABASE(MainActivity.this);

        recyclerView = findViewById(R.id.recyclerViewId);
        searchView = findViewById(R.id.searchViewId);
    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarNotas();
    }

    public void AddNotaTeste(){
        Nota nota = new Nota();
        nota.setTitulo("Nota " + db.notaDAO().count());
        nota.setTexto("Texto " + db.notaDAO().count());

        db.notaDAO().insert(nota);
    }

    public void carregarNotas(){ 
        AddNotaTeste();
        listaNotas = db.notaDAO().getAll();

        notaAdapter = new NotaAdapter(listaNotas);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(notaAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.mnPesquisarId);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mnPesquisarId) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

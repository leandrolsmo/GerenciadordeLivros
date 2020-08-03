package br.imd.fic.gerenciadordelivros.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import br.imd.fic.gerenciadordelivros.R;
import br.imd.fic.gerenciadordelivros.adapter.LivroAdapter;
import br.imd.fic.gerenciadordelivros.dominio.Livro;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Livro> listaLivros = new ArrayList<>();

        listaLivros.add(new Livro(1L,"Android para Leigos", "Michel Burton", "Alta Books",true));
        listaLivros.add(new Livro(1L,"Android para Leigos", "Michel Burton", "Alta Books",true));
        listaLivros.add(new Livro(1L,"Android para Leigos", "Michel Burton", "Alta Books",false));
        listaLivros.add(new Livro(1L,"Android para Leigos", "Michel Burton", "Alta Books",true));
        listaLivros.add(new Livro(1L,"Android para Leigos", "Michel Burton", "Alta Books",true));
        listaLivros.add(new Livro(1L,"Android para Leigos", "Michel Burton", "Alta Books",false));
        listaLivros.add(new Livro(1L,"Android para Leigos", "Michel Burton", "Alta Books",true));
        listaLivros.add(new Livro(1L,"Android para Leigos", "Michel Burton", "Alta Books",false));

        LivroAdapter livroAdapter = new LivroAdapter(listaLivros, this);

        recyclerView.setAdapter(livroAdapter);
    }
}
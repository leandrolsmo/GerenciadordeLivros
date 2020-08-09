package br.imd.fic.gerenciadordelivros.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.imd.fic.gerenciadordelivros.R;
import br.imd.fic.gerenciadordelivros.adapter.LivroAdapter;
import br.imd.fic.gerenciadordelivros.data.LivroDAO;
import br.imd.fic.gerenciadordelivros.dialogs.DeleteDialog;
import br.imd.fic.gerenciadordelivros.dominio.Livro;

public class MainActivity extends AppCompatActivity implements LivroAdapter.OnLivroListener, DeleteDialog.OnDeleteListener {

    private LivroDAO livroDAO;
    LivroAdapter livroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        /*listaLivros.add(new Livro(1L,"Android para Leigos", "Michel Burton", "Alta Books",0));
        listaLivros.add(new Livro(1L,"Android para Leigos", "Michel Burton", "Alta Books",1));
        listaLivros.add(new Livro(1L,"Android para Leigos", "Michel Burton", "Alta Books",0));
        listaLivros.add(new Livro(1L,"Android para Leigos", "Michel Burton", "Alta Books",1));
        listaLivros.add(new Livro(1L,"Android para Leigos", "Michel Burton", "Alta Books",1));
        listaLivros.add(new Livro(1L,"Android para Leigos", "Michel Burton", "Alta Books",0));
        listaLivros.add(new Livro(1L,"Android para Leigos", "Michel Burton", "Alta Books",1));
        listaLivros.add(new Livro(1L,"Android para Leigos", "Michel Burton", "Alta Books",1));*/

        livroDAO = LivroDAO.getInstance(this);

        List<Livro> listaLivros = livroDAO.list();

        livroAdapter = new LivroAdapter(listaLivros, this, this);

        recyclerView.setAdapter(livroAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_adicionar:
                Intent intent = new Intent(getApplicationContext(), EditarLivroActivity.class);
                startActivityForResult(intent, 100);
                return true;
            case R.id.action_sair:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == RESULT_OK){
            atualizaListaLivros();
        }

        if(requestCode == 101 && resultCode == RESULT_OK){
            atualizaListaLivros();
        }
    }

    public void atualizaListaLivros(){
        List<Livro> livros = livroDAO.list();
        livroAdapter.setItems(livros);
        livroAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLivroClick(int posicao) {

        Intent intent = new Intent(getApplicationContext(), EditarLivroActivity.class);
        intent.putExtra("livro",livroAdapter.getItem(posicao));
        startActivityForResult(intent,101);

    }

    @Override
    public void onLivroLongClick(int posicao) {
        Livro livro = livroAdapter.getItem(posicao);

        DeleteDialog dialog = new DeleteDialog();
        dialog.setLivro(livro);
        dialog.show(getSupportFragmentManager(),"deleteDialog");
    }

    @Override
    public void onDelete(Livro livro) {
        livroDAO.delete(livro);
        atualizaListaLivros();

        Toast.makeText(this, "Livro excluído com sucesso!", Toast.LENGTH_SHORT).show();
    }
}
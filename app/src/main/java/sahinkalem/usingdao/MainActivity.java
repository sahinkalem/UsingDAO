package sahinkalem.usingdao;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    SearchView searchView;
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        appDatabase = AppDatabase.getAppDatabase(this);
        int userSize = appDatabase.userDAO().getCount();
        if (userSize == 0) {
            AddUser addUser = new AddUser(this);
            addUser.addUser();
        }

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        getUsers();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            getUsers();
            swipeRefreshLayout.setRefreshing(false);
        });

        searchView=findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!newText.isEmpty()) {
                    List<UserModel> users = appDatabase.userDAO().getUser(newText);
                    UserAdapter userAdapter = new UserAdapter(MainActivity.this, users);
                    recyclerView.setAdapter(userAdapter);
                }else {
                    List<UserModel> users = appDatabase.userDAO().getAllUsers();
                    UserAdapter userAdapter = new UserAdapter(MainActivity.this, users);
                    recyclerView.setAdapter(userAdapter);
                }
                return true;

            }
        });
    }

    private void getUsers() {

        List<UserModel> users = appDatabase.userDAO().getAllUsers();
        UserAdapter userAdapter = new UserAdapter(this, users);
        recyclerView.setAdapter(userAdapter);

    }
}
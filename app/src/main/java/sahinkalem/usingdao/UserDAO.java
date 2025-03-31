package sahinkalem.usingdao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM UserModel")
    List<UserModel> getAllUsers();

    @Query("SELECT * FROM UserModel WHERE first_name LIKE '%' || (:firstName) || '%' ")
    List<UserModel> getUser (String firstName);

    @Query("SELECT COUNT(*) FROM UserModel")
    int getCount();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(UserModel... users);

    @Update()
    void update(UserModel user);

    @Delete
    void delete(UserModel user);
}

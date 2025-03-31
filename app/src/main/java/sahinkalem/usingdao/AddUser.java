package sahinkalem.usingdao;

import android.content.Context;

public class AddUser {

    Context mContext;
    UserModel mUser1, mUser2, mUser3;

    public AddUser(Context context) {
        this.mContext = context;
    }

    public void addUser() {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(mContext);
        mUser1 = new UserModel();
        mUser1.setFirstName("John");
        mUser1.setLastName("Doe");
        mUser1.setCountry("USA");
        mUser1.setGraduationYear(2023);
        mUser1.setGraduation("Bachelor");
        mUser1.setImagePath("man.png");
        mUser1.setSex("Male");
        appDatabase.userDAO().insert(mUser1);

        mUser2 = new UserModel();
        mUser2.setFirstName("Jane");
        mUser2.setLastName("Doe");
        mUser2.setCountry("Canada");
        mUser2.setGraduationYear(2022);
        mUser2.setGraduation("Master");
        mUser2.setImagePath("woman.png");
        mUser2.setSex("Female");
        appDatabase.userDAO().insert(mUser2);

        mUser3 = new UserModel();
        mUser3.setFirstName("Bob");
        mUser3.setLastName("Smith");
        mUser3.setCountry("UK");
        mUser3.setGraduationYear(2021);
        mUser3.setGraduation("PhD");
        mUser3.setImagePath("man.png");
        mUser3.setSex("Male");
        appDatabase.userDAO().insert(mUser3);

    }
}

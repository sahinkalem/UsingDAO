package sahinkalem.usingdao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;
import java.util.Objects;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    View mView;
    List<UserModel> mUsers;
    Context mContext;

    public UserAdapter(Context context, List<UserModel> users) {
        mContext = context;
        mUsers = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserModel user = mUsers.get(position);
        holder.txtUserID.setText(String.valueOf(user.getUid()));
        holder.txtName.setText(user.getFirstName());
        holder.txtLastName.setText(user.getLastName());
        holder.txtCountry.setText(user.getCountry());
        holder.txtGraduation.setText(user.getGraduation());
        holder.txtGraduationYear.setText(String.valueOf(user.getGraduationYear()));

        if (Objects.equals(user.getSex(), "Male")) {
            Glide.with(mContext)
                    .load(R.drawable.man)
                    .into(holder.imageView);
        } else if (Objects.equals(user.getSex(), "Female")) {
            Glide.with(mContext)
                    .load(R.drawable.woman)
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ShapeableImageView imageView;
        private final MaterialTextView txtUserID;
        private final MaterialTextView txtName;
        private final MaterialTextView txtLastName;
        private final MaterialTextView txtCountry;
        private final MaterialTextView txtGraduation;
        private final MaterialTextView txtGraduationYear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            txtUserID = itemView.findViewById(R.id.txtUserID);
            txtName = itemView.findViewById(R.id.txtName);
            txtLastName = itemView.findViewById(R.id.txtLastName);
            txtCountry = itemView.findViewById(R.id.txtCountry);
            txtGraduation = itemView.findViewById(R.id.txtGraduation);
            txtGraduationYear = itemView.findViewById(R.id.txtGraduationYear);
        }
    }
}

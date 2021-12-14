package ru.geekbrains.notes.ui;


import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;

import ru.geekbrains.socialnetwork.R;
import ru.geekbrains.notes.data.Notes;
import ru.geekbrains.notes.data.NotesSource;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private final static String TAG = "SocialNetworkAdapter";
    private NotesSource dataSource;
    private final Fragment fragment;
    private OnItemClickListener itemClickListener;
    private int menuPosition;

    public NotesAdapter(NotesSource dataSource, Fragment fragment){
        this.dataSource = dataSource;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        Log.d(TAG, "onCreateViewHolder");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        holder.bind(dataSource.getCardData(position));
        Log.d(TAG, "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public int getMenuPosition() {
        return menuPosition;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView description;
        private AppCompatImageView image;
        private CheckBox like;
        private TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);

            registerContextMenu(itemView);

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null){
                        itemClickListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });

            title.setOnLongClickListener(new View.OnLongClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public boolean onLongClick(View view) {
                    itemView.showContextMenu(10, 10);
                    menuPosition = getLayoutPosition();
                    return true;
                }
            });
        }

        private void registerContextMenu(@NonNull View itemView) {
            if (fragment != null) {
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        menuPosition = getLayoutPosition();
                        return false;
                    }
                });
                fragment.registerForContextMenu(itemView);
            }
        }

        public void bind(Notes notes){
            title.setText(notes.getTitle());
            description.setText(notes.getDescription());
            date.setText(new SimpleDateFormat("dd-MM-yy").format(notes.getDate()));
        }
    }
}

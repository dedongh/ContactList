package com.engineerskasa.contactlist.Adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineerskasa.contactlist.Model.Contact;
import com.engineerskasa.contactlist.R;

import java.util.List;

public class ContactRecyclerAdapter extends RecyclerView.Adapter<ContactRecyclerAdapter.ViewHolder> {
    //Interface for callbacks
    public interface ActionCallback {
        void onLongClickListener(Contact contact);
    }
    private Context context;
    private List<Contact> contactList;
    private int[] colors;
    private ActionCallback mActionCallbacks;

    public ContactRecyclerAdapter(Context context, List<Contact> contactList, int[] colors) {
        this.context = context;
        this.contactList = contactList;
        this.colors = colors;
    }

    @NonNull
    @Override
    public ContactRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactRecyclerAdapter.ViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void updateData(List<Contact> contacts) {
        this.contactList = contacts;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        private TextView mNameTextView;
        private TextView mInitialsTextView;
        private GradientDrawable mInitialsBackground;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnLongClickListener(this);

            mInitialsTextView = itemView.findViewById(R.id.initialsTextView);
            mNameTextView = itemView.findViewById(R.id.nameTextView);
            mInitialsBackground = (GradientDrawable) mInitialsTextView.getBackground();
        }

        @Override
        public boolean onLongClick(View v) {
            if (mActionCallbacks != null) {
                mActionCallbacks.onLongClickListener(contactList.get(getAdapterPosition()));
            }
            return true;
        }

        public void bindData(int position) {
            Contact contact = contactList.get(position);

            String fullName = contact.getFirstName() + " " + contact.getLastName();
            mNameTextView.setText(fullName);

            String initial = contact.getFirstName().toUpperCase().substring(0, 1);
            mInitialsTextView.setText(initial);

            mInitialsBackground.setColor(colors[position % colors.length]);
        }
    }
    public void addActionCallback(ActionCallback actionCallbacks) {
        mActionCallbacks = actionCallbacks;
    }

}

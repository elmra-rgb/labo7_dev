package com.example.lab7_dev.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab7_dev.R;
import com.example.lab7_dev.beans.Celebrity;
import com.example.lab7_dev.service.CelebrityManager;
import java.util.ArrayList;
import java.util.List;

public class CelebrityAdapter extends RecyclerView.Adapter<CelebrityAdapter.CelebrityViewHolder> implements Filterable {

    private List<Celebrity> originalCelebrityList;
    private List<Celebrity> filteredCelebrityList;
    private Context appContext;
    private CustomFilter searchFilter;

    public CelebrityAdapter(Context context, List<Celebrity> celebrities) {
        this.appContext = context;
        this.originalCelebrityList = celebrities;
        this.filteredCelebrityList = new ArrayList<>(celebrities);
        this.searchFilter = new CustomFilter();
    }

    @NonNull
    @Override
    public CelebrityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(appContext).inflate(R.layout.celebrity_item, parent, false);
        CelebrityViewHolder holder = new CelebrityViewHolder(itemView);

        // Gestion du clic pour modifier la note
        holder.itemView.setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Celebrity currentCelebrity = filteredCelebrityList.get(position);

                View dialogView = LayoutInflater.from(appContext).inflate(R.layout.rating_edit_dialog, null, false);

                ImageView dialogImg = dialogView.findViewById(R.id.dialogCelebrityImage);
                RatingBar dialogRating = dialogView.findViewById(R.id.dialogRatingBar);
                TextView dialogId = dialogView.findViewById(R.id.dialogCelebrityId);
                TextView dialogName = dialogView.findViewById(R.id.dialogCelebrityName);

                // Charger l'image locale
                int imageResId = getImageResource(currentCelebrity.getImageUrl());
                dialogImg.setImageResource(imageResId);

                dialogName.setText(currentCelebrity.getFullName());
                dialogRating.setRating(currentCelebrity.getAverageRating());
                dialogId.setText(String.valueOf(currentCelebrity.getUniqueId()));

                new AlertDialog.Builder(appContext)
                        .setTitle("Modifier la note")
                        .setMessage("Ajustez la note de " + currentCelebrity.getFullName())
                        .setView(dialogView)
                        .setPositiveButton("Enregistrer", (dialog, which) -> {
                            float newRating = dialogRating.getRating();
                            int celebrityId = Integer.parseInt(dialogId.getText().toString());
                            Celebrity targetCelebrity = CelebrityManager.getUniqueInstance().searchById(celebrityId);
                            if (targetCelebrity != null) {
                                targetCelebrity.setAverageRating(newRating);
                                CelebrityManager.getUniqueInstance().modify(targetCelebrity);
                                notifyItemChanged(position);
                            }
                        })
                        .setNegativeButton("Annuler", null)
                        .show();
            }
        });

        return holder;
    }

    // Méthode pour obtenir l'ID de l'image locale
    private int getImageResource(String imageName) {
        switch (imageName) {
            case "emma_watson":
                return R.drawable.emma_watson;
            case "tom_cruise":
                return R.drawable.tom_cruise;
            case "elle_fanning":
                return R.drawable.elle_fanning;
            case "leo":
                return R.drawable.leo;
            case "zendaya":
                return R.drawable.zendaya;
            case "keanu":
                return R.drawable.keanu;
            case "meryl":
                return R.drawable.meryl;
            case "brad":
                return R.drawable.brad;
            case "angelina":
                return R.drawable.angelina;
            case "monica_belluci":
                return R.drawable.monica_belluci;
            default:
                return R.drawable.ic_launcher_foreground;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CelebrityViewHolder holder, int position) {
        Celebrity currentCelebrity = filteredCelebrityList.get(position);

        holder.celebrityName.setText(currentCelebrity.getFullName().toUpperCase());
        holder.ratingBar.setRating(currentCelebrity.getAverageRating());
        holder.hiddenId.setText(String.valueOf(currentCelebrity.getUniqueId()));

        // Charger l'image locale
        int imageResId = getImageResource(currentCelebrity.getImageUrl());
        holder.celebrityImage.setImageResource(imageResId);
    }

    @Override
    public int getItemCount() {
        return filteredCelebrityList.size();
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }

    public static class CelebrityViewHolder extends RecyclerView.ViewHolder {
        ImageView celebrityImage;
        TextView celebrityName;
        RatingBar ratingBar;
        TextView hiddenId;

        public CelebrityViewHolder(@NonNull View itemView) {
            super(itemView);
            celebrityImage = itemView.findViewById(R.id.celebrityImage);
            celebrityName = itemView.findViewById(R.id.celebrityName);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            hiddenId = itemView.findViewById(R.id.celebrityId);
        }
    }

    private class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Celebrity> filteredResults = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredResults.addAll(originalCelebrityList);
            } else {
                String searchPattern = constraint.toString().toLowerCase().trim();
                for (Celebrity celebrity : originalCelebrityList) {
                    if (celebrity.getFullName().toLowerCase().startsWith(searchPattern)) {
                        filteredResults.add(celebrity);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredResults;
            results.count = filteredResults.size();
            return results;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredCelebrityList = (List<Celebrity>) results.values;
            notifyDataSetChanged();
        }
    }
}
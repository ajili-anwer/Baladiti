package com.example.baladiti;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {
    private List<SliderIteam> sliderIteams;
    private ViewPager2 viewPager2;

    SliderAdapter(List<SliderIteam> sliderIteams, ViewPager2 viewPager2) {
        this.sliderIteams = sliderIteams;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slide_item_container,
                        parent,
                        false

                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(sliderIteams.get(position));
        if(position == sliderIteams.size()-2){
            viewPager2.post(runnable);
        }

    }

    @Override
    public int getItemCount() {
        return sliderIteams.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{
        private RoundedImageView imageView;

        SliderViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageSlide);
        }



        void setImage(SliderIteam sliderIteam)
        {
            imageView.setImageResource(sliderIteam.getImage());

        }
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderIteams.addAll(sliderIteams);
            notifyDataSetChanged();

        }
    };
}

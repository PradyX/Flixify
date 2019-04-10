package prady.flixify;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DBViewHolder extends RecyclerView.ViewHolder{

    public TextView name_Text,year_Text, rating_Text, season_text, plot_Text, genre_Text, cast_Text, yt_Text;
    public ImageView show_IMG;
    public TextView url_Text;
    public LinearLayout linearLayout;
    public DBViewHolder(@NonNull View itemView) {
        super(itemView);

          name_Text = (TextView) itemView.findViewById(R.id.grid_title);
          year_Text = (TextView) itemView.findViewById(R.id.grid_year);
          show_IMG  = (ImageView) itemView.findViewById(R.id.grid_image);
          url_Text = (TextView) itemView.findViewById(R.id.grid_url);
          linearLayout = (LinearLayout) itemView.findViewById(R.id.grid_container);
          rating_Text = (TextView) itemView.findViewById(R.id.grid_rating);
          season_text = (TextView) itemView.findViewById(R.id.grid_seasons);

        plot_Text = (TextView) itemView.findViewById(R.id.grid_plot);
        genre_Text = (TextView) itemView.findViewById(R.id.grid_genre);
        cast_Text = (TextView) itemView.findViewById(R.id.grid_cast);
        yt_Text = (TextView) itemView.findViewById(R.id.grid_yt);
    }
}

package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mentorship.student.com.studentmentorship.R;
import model.Details;

/**
 * Created by HP on 17-10-2017.
 */

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>
{
    private List<Details> detailsList;

    @Override
    public DetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_details,parent,false);
        DetailsViewHolder detailsViewHolder=new DetailsViewHolder(view);
        return detailsViewHolder;
    }

    @Override
    public void onBindViewHolder(DetailsViewHolder holder, int position)
    {
        Details details= detailsList.get(position);
        holder.ivDetailsIcon.setImageResource(details.getDetailsIcon());
        holder.tvDetailsTitle.setText(details.getDetailsTitle());
    }

    @Override
    public int getItemCount()
    {
        return detailsList.size();
    }

    public DetailsAdapter(List<Details> detailsList)
    {
        this.detailsList=detailsList;
    }
    public class DetailsViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView ivDetailsIcon;
        private TextView tvDetailsTitle;

        public DetailsViewHolder(View itemView)
        {
            super(itemView);
            ivDetailsIcon=(ImageView)itemView.findViewById(R.id.ivDetailsIcon);
            tvDetailsTitle=(TextView)itemView.findViewById(R.id.tvDetailsTitle);
        }
    }

}

package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.biubiubiu.justifytext.library.JustifyTextView;
import mentorship.student.com.studentmentorship.R;
import model.PublicQus;

/**
 * Created by HP on 17-10-2017.
 */

public class PublicQuesAdapter extends RecyclerView.Adapter<PublicQuesAdapter.PublicQuesViewHolder>
{
    private List<PublicQus> publicQusList;
    @Override
    public PublicQuesViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_public,parent,false);
        PublicQuesViewHolder publicQuesViewHolder=new PublicQuesViewHolder(view);
        return publicQuesViewHolder;
    }

    @Override
    public void onBindViewHolder(PublicQuesViewHolder holder, int position)
    {
        PublicQus publicQus= publicQusList.get(position);
        holder.tvPublicQus.setText(publicQus.getPublicQuesTitle());
        holder.tvAskBy.setText(publicQus.getPublicAskBy());
        holder.tvUserId.setText(publicQus.getPublicUserId());
        holder.tvPublicDescrioprion.setText(publicQus.getPublicQuesDescription());
    }

    @Override
    public int getItemCount()
    {
        return publicQusList.size();
    }
    public PublicQuesAdapter(List<PublicQus> publicQusList)
    {
        this.publicQusList=publicQusList;
    }

    public  class  PublicQuesViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tvPublicQus;
        private JustifyTextView tvPublicDescrioprion;
        private TextView tvAskBy;
        private TextView tvUserId;
        public PublicQuesViewHolder(View itemView)
        {
            super(itemView);
            tvPublicQus=(TextView)itemView.findViewById(R.id.tvPublicQus);
            tvAskBy=(TextView)itemView.findViewById(R.id.tvAskBy);
            tvUserId=(TextView)itemView.findViewById(R.id.tvUserId);
            tvPublicDescrioprion=(JustifyTextView)itemView.findViewById(R.id.tvPublicDescrioprion);

        }
    }


}

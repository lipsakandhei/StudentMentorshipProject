package adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import me.biubiubiu.justifytext.library.JustifyTextView;
import mentorship.student.com.studentmentorship.R;
import model.PublicQuesAns;

/**
 * Created by HP on 18-10-2017.
 */

public class PublicQuesAnsAdapter extends RecyclerView.Adapter<PublicQuesAnsAdapter.PublicQuesAnsViewHolder>
{
    private List<PublicQuesAns> publicQuesAnsList;

    @Override
    public PublicQuesAnsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_public_ans,parent,false);
        PublicQuesAnsViewHolder publicQuesAnsViewHolder=new PublicQuesAnsViewHolder(view);
        return publicQuesAnsViewHolder;
    }

    @Override
    public void onBindViewHolder(PublicQuesAnsViewHolder holder, int position)
    {
        PublicQuesAns publicQuesAns= publicQuesAnsList.get(position);
        holder.tvPublicDescrioprionAns.setText(publicQuesAns.getQuesAns());
        holder.tvPublicQusans.setText(publicQuesAns.getQuesAnsMentor());

    }

    @Override
    public int getItemCount()
    {
        return publicQuesAnsList.size();
    }

    public PublicQuesAnsAdapter(List<PublicQuesAns> publicQuesAnsList)
    {
        this.publicQuesAnsList=publicQuesAnsList;
    }

    public class PublicQuesAnsViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tvPublicQusans;
        private JustifyTextView tvPublicDescrioprionAns;


        public PublicQuesAnsViewHolder(View itemView)
        {
            super(itemView);
            tvPublicQusans=(TextView)itemView.findViewById(R.id.tvPublicQusans);
            tvPublicDescrioprionAns=(JustifyTextView)itemView.findViewById(R.id.tvPublicDescrioprionAns);


        }
    }
}

package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mentorship.student.com.studentmentorship.R;
import model.PrivateAnswer;
import model.PublicQus;

/**
 * Created by HP on 18-10-2017.
 */

public class PrivateAnswerAdapter extends RecyclerView.Adapter<PrivateAnswerAdapter.PrivateAnswerAdapterViewHolder>
{

    private List<PrivateAnswer> privateanselist;

    @Override
    public PrivateAnswerAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_private_answer,parent,false);
        PrivateAnswerAdapter.PrivateAnswerAdapterViewHolder privateanswerViewHolder=new PrivateAnswerAdapterViewHolder(view);
        return privateanswerViewHolder;
    }

    @Override
    public void onBindViewHolder(PrivateAnswerAdapterViewHolder holder, int position)
    {

        PrivateAnswer privateAnswer= privateanselist.get(position);
        holder.privateanswers.setText(privateAnswer.getQuestion());

    }

    @Override
    public int getItemCount()
    {
        return privateanselist.size();
    }

    public PrivateAnswerAdapter(List<PrivateAnswer> privateanselist)
    {
        this.privateanselist=privateanselist;
    }
    public class PrivateAnswerAdapterViewHolder extends RecyclerView.ViewHolder
    {
        private TextView privateanswers;
        public PrivateAnswerAdapterViewHolder(View itemView)
        {
            super(itemView);
            privateanswers=(TextView)itemView.findViewById(R.id.tvprivateAns);
        }
    }
}

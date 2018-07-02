package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mentorship.student.com.studentmentorship.R;
import model.Answer;

/**
 * Created by HP on 18-10-2017.
 */

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>
{
    private List<Answer> answerList;
    @Override
    public AnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_answer,parent,false);
        AnswerViewHolder answerViewHolder=new AnswerViewHolder(view);
        return answerViewHolder;
    }

    @Override
    public void onBindViewHolder(AnswerViewHolder holder, int position)
    {
        Answer answer= answerList.get(position);
        holder.tvAnswer.setText(answer.getQuesAnswer());
        holder.tvanswerdate.setText(answer.getAnswerdate());
    }

    @Override
    public int getItemCount()
    {
        return answerList.size();
    }
    public AnswerAdapter(List<Answer> answerList)
    {
        this.answerList=answerList;
    }

    public class AnswerViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tvAnswer;
        private TextView tvanswerdate;
        public AnswerViewHolder(View itemView)
        {
            super(itemView);
            tvAnswer=(TextView)itemView.findViewById(R.id.tvAnswer);
            tvanswerdate=(TextView)itemView.findViewById(R.id.tvanswerdate);
        }
    }
}

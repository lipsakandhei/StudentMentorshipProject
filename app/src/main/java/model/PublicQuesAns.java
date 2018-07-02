package model;

import android.content.SharedPreferences;

/**
 * Created by HP on 18-10-2017.
 */

public class PublicQuesAns
{
    private String quesAns;
    private String quesAnsMentor;
    private String quesAnsId;
    ;
    public String getQuesAnsId() {
        return quesAnsId;
    }

    public void setQuesAnsId(String quesAnsId) {
        this.quesAnsId = quesAnsId;
    }

    public String getQuesAns() {
        return quesAns;
    }

    public void setQuesAns(String quesAns) {
        this.quesAns = quesAns;
    }

    public String getQuesAnsMentor() {
        return quesAnsMentor;
    }

    public void setQuesAnsMentor(String quesAnsMentor) {
        this.quesAnsMentor = quesAnsMentor;
    }
}

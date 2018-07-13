package ch.ethz.inf.vs.a1.rubfisch.p2pchat;

/**
 * Created by ruben on 04.12.17.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class ChatMessageAdapter extends ArrayAdapter<ChatMessage> {

    private List<ChatMessage> chatmessages;
    private Activity act;

    public ChatMessageAdapter(Activity context, int res, List<ChatMessage> messagesList) {
        super(context, res, messagesList);
        act = context;
        chatmessages = messagesList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) act.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        //Your own bubble is type zero
        int layoutRes = R.layout.own_bubble;

        //Get right message and text
        ChatMessage chatMessage = getItem(position);
        String text = chatMessage.getText();
        String sender = chatMessage.getSender();
        String time = chatMessage.getTime();

        //Use foreign_bubble for chat partner's messages
        if (!chatMessage.isOwned())
            layoutRes = R.layout.foreign_bubble;

        if (convertView == null) {
            //Create new view and set its texview as a tag
            convertView = layoutInflater.inflate(layoutRes, parent, false);
            TextView textView= (TextView) convertView.findViewById(R.id.msg);

            //Set the text
            textView.setText(text);

            //Set the time
            TextView timeView= (TextView) convertView.findViewById(R.id.time_stamp);
            timeView.setText(time);

            //Set sender if foreign message
            if(!chatMessage.isOwned()) {
                TextView senderView= (TextView) convertView.findViewById(R.id.sender);
                senderView.setText(sender);
            }
        } else {

            TextView textView= (TextView) convertView.findViewById(R.id.msg);
            textView.setText(text);

            //Set the time
            TextView timeView= (TextView) convertView.findViewById(R.id.time_stamp);
            timeView.setText(time);

            if(!chatMessage.isOwned()) {
                TextView senderView= (TextView) convertView.findViewById(R.id.sender);
                senderView.setText(sender);
            }
        }

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).isOwned())
            return 0;
        else
            return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}

package com.jonalmeida.project420;

import android.content.Context;

import com.klinker.android.send_message.Message;
import com.klinker.android.send_message.Settings;
import com.klinker.android.send_message.Transaction;

public class SmsSender {

    protected Settings smsSettings;
    private Context context;
    private int threadId;

    public SmsSender(Context context, int threadId) {
        smsSettings = new Settings();
        this.context = context;
        this.threadId = threadId;
    }

    public void sendSmsMessage(String textMessage, String address) {
        Message message = new Message(textMessage, address);
        message.setType(Message.TYPE_SMSMMS);
        Transaction sendTransaction = new Transaction(context, smsSettings);
        sendTransaction.sendNewMessage(message, threadId);
    }
}

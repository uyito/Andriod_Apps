package com.example.textify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.UserInfo
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_conversation.*
import kotlinx.android.synthetic.main.user_row_new_message.*
import kotlinx.android.synthetic.main.user_row_new_message.view.*

class ConversationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation)

        supportActionBar?.title = "Messages"

        val adapter = GroupAdapter<GroupieViewHolder>()

        adapter.add(ChatItem())

        message_logs_view.adapter = adapter
    }
}


class ChatItem(): Item<GroupieViewHolder>() {

    override fun getLayout(): Int {

        return R.layout.reciever_message_row_log

    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        TODO()
    }
}
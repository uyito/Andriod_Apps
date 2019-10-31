package com.example.textify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_start_message.*
import java.text.FieldPosition

class StartMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_message)

        supportActionBar?.title = "Select User"

        val adapter = GroupAdapter<GroupieViewHolder>()

        adapter.add(UserItem())
        adapter.add(UserItem())
        adapter.add(UserItem())


        contact_list.adapter = adapter
//        contact_list.layoutManager = LinearLayoutManager(this)



    }
}

class UserItem: Item<GroupieViewHolder>(){

    override fun getLayout(): Int{

        return R.layout.user_row_new_message

    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int){
    }
}
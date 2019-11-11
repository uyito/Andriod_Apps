package com.example.textify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_start_message.*
import kotlinx.android.synthetic.main.user_row_new_message.view.*
import java.text.FieldPosition

class StartMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_message)

        supportActionBar?.title = "Select User"

//        val adapter = GroupAdapter<GroupieViewHolder>()
//
////        adapter.add(UserItem())
////        adapter.add(UserItem())
////        adapter.add(UserItem())
////        adapter.add(UserItem())
//
//
//        contact_list.adapter = adapter


        getUsers()


    }

    private fun getUsers(){
        val ref = FirebaseDatabase.getInstance().getReference("/users")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onDataChange(p0: DataSnapshot){

                val adapter = GroupAdapter<GroupieViewHolder>()


                p0.children.forEach{
                    Log.d("Message", it.toString())
                    val user = it.getValue(User::class.java)
                    if (user != null) {
                        adapter.add(UserItem(user))
                    }
                }

                adapter.setOnItemClickListener { item, view ->
                    val intent = Intent(view.context, ConversationActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                contact_list.adapter = adapter

            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }
}

class UserItem(val user: User): Item<GroupieViewHolder>(){

    override fun getLayout(): Int{

        return R.layout.user_row_new_message

    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int){
        viewHolder.itemView.username_display.text = user.username
        val pic = viewHolder.itemView.user_photo

        Picasso.get().load(user.profileImageUrl).into(pic)
    }
}
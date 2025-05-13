package softserve.academy.groupproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter(var items: List<Item>, var context: Context) : RecyclerView.Adapter<ItemsAdapter.MyViewHolder>() {

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.item_list_image)
        val title: TextView = view.findViewById(R.id.item_list_title)
        val price: TextView = view.findViewById(R.id.item_list_price)
        val btn: Button = view.findViewById(R.id.item_list_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_in_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = items[position].title
        holder.price.text = items[position].price.toString() + "грн/год"

        var imageId = context.resources.getIdentifier(
            items[position].image,
            "drawable",
            context.packageName
        )

        holder.image.setImageResource(imageId)

        holder.btn.setOnClickListener {
            val intent = Intent(context, ItemActivity::class.java)
            intent.putExtra("itemTitle",items[position].title)
            intent.putExtra("itemPrice", items[position].price.toString() + "грн/год")
            intent.putExtra("itemDescription", items[position].description)
            intent.putExtra("itemImage", items[position].image)
            context.startActivity(intent)
        }
    }
}
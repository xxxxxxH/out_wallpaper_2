package net.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_hot_item.view.*
import net.basicmodel.R
import net.entity.DataEntity
import net.utils.Constanst
import net.utils.OnItemClickListener
import net.utils.ScreenUtils

class ImgAdapter(
    val data: List<DataEntity>,
    val context: Context,
    val activity: Activity,
    val itemClickListener: OnItemClickListener,
    val type: String
) :
    RecyclerView.Adapter<ImgAdapter.ViewHolder>() {
    var view: View? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when (type) {
            Constanst.TYPE_HOT -> view =
                View.inflate(parent.context, R.layout.layout_hot_item, null) as View

        }
        return ViewHolder(view!!)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (type) {
            Constanst.TYPE_HOT -> {
                with(holder.itemView) {
                    val params: ViewGroup.LayoutParams = img.layoutParams
                    params.height = ScreenUtils.getScreenSize(activity)[0] / 3
                    params.width = ScreenUtils.getScreenSize(activity)[1]
                    img.layoutParams = params
                    Glide.with(context).load(data[position].img_thumb_url).into(img)
                    img_name.text = data[position].img_title
                    img_like.text = "${data[position].img_like} Likes"
                    img.setOnClickListener {
                        itemClickListener.onItemClick(holder.layoutPosition, Constanst.TYPE_HOT)
                    }
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @JvmName("getData1")
    fun getData():List<DataEntity>{
        return data
    }
}
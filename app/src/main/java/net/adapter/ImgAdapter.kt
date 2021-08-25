package net.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_hot_item.view.*
import kotlinx.android.synthetic.main.layout_item_details.view.*
import kotlinx.android.synthetic.main.layout_item_type.view.*
import net.basicmodel.R
import net.entity.DataEntity
import net.utils.Constanst
import net.utils.OnItemClickListener
import net.utils.ScreenUtils
import net.utils.TypeDataManager

class ImgAdapter(
    val data: List<DataEntity>?,
    val typeDta: HashMap<String, String>?,
    val context: Context,
    val activity: Activity,
    val itemClickListener: OnItemClickListener,
    val type: String
) :
    RecyclerView.Adapter<ImgAdapter.ViewHolder>() {
    var view: View? = null
    var keyList: ArrayList<String>? = null

    var valuesList: ArrayList<String>? = null


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setKeyValues() {
        keyList = TypeDataManager.getInstance().getKeys(typeDta)
        valuesList = TypeDataManager.getInstance().getValues(typeDta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when (type) {
            Constanst.TYPE_HOT -> view =
                View.inflate(parent.context, R.layout.layout_hot_item, null) as View
            Constanst.TYPE_TYPE -> view =
                View.inflate(parent.context, R.layout.layout_item_type, null) as View
            Constanst.TYPE_TYPE_DETAILS -> view =
                View.inflate(parent.context, R.layout.layout_item_details, null) as View

        }
        return ViewHolder(view!!)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (type) {
            Constanst.TYPE_HOT -> {
                with(holder.itemView) {
                    val params = img!!.layoutParams
                    params.height = ScreenUtils.getScreenSize(activity)[0] / 3
                    params.width = ScreenUtils.getScreenSize(activity)[1]
                    img.layoutParams = params
                    Glide.with(context).load(data!![position].img_thumb_url).into(img)
                    img_name.text = data[position].img_title
                    img_like.text = "${data[position].img_like} Likes"
                    img.setOnClickListener {
                        itemClickListener.onItemClick(holder.layoutPosition, Constanst.TYPE_HOT, "")
                    }
                }
            }
            Constanst.TYPE_TYPE -> {
                with(holder.itemView) {
                    val params = type_root.layoutParams
                    params.height = ScreenUtils.getScreenSize(activity)[1] / 3
                    params.width = ScreenUtils.getScreenSize(activity)[1] / 3
                    type_root.layoutParams = params
                    val params1 = type_img.layoutParams
                    params1.height = (ScreenUtils.getScreenSize(activity)[1] / 3) - 10
                    params1.width = (ScreenUtils.getScreenSize(activity)[1] / 3) - 10
                    type_img.layoutParams = params1
                    type_name.text = keyList!![position]
                    Glide.with(context).load(valuesList!![position]).into(type_img)
                    type_img.setOnClickListener {
                        itemClickListener.onItemClick(
                            holder.layoutPosition, Constanst.TYPE_TYPE,
                            keyList!![position]
                        )
                    }
                }
            }
            Constanst.TYPE_TYPE_DETAILS -> {
                with(holder.itemView) {
                    val params = details_img.layoutParams
                    params.height = ScreenUtils.getScreenSize(activity)[0] / 3
                    params.width = ScreenUtils.getScreenSize(activity)[1]
                    details_img.layoutParams = params
                    Glide.with(context).load(data!![position].img_thumb_url)
                        .placeholder(R.mipmap.ic_launcher_round).into(details_img)
                    details_img.setOnClickListener {
                        itemClickListener.onItemClick(
                            holder.layoutPosition,
                            Constanst.TYPE_TYPE_DETAILS,
                            ""
                        )
                    }
                }
            }

        }
    }

    override fun getItemCount(): Int {
        var size = 0
        if (data != null) {
            size = data.size
        }
        if (typeDta != null) {
            size = typeDta.size
        }
        return size
    }

}
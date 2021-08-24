package net.http

import net.entity.DataEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestService {

    @GET("appcar/getCategoryThumb.php?page=0&ca_pic_limit=100&ca_limit=100")
    fun getHotData():Call<List<DataEntity>>

    @GET("app{type}/getCategoryThumb.php?page=0&ca_pic_limit=100&ca_limit=100")
    fun getTypeDetailsData(@Path("type")type:String ):Call<List<DataEntity>>
}
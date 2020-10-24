package com.example.tvshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.tvshop.models.TV
import org.json.JSONException

class TvListActivity : AppCompatActivity() {
    private lateinit var tvs: ArrayList<TV>
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: TVAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_list)

        requestQueue = Volley.newRequestQueue(this)

        tvs = ArrayList<TV>()

        viewManager = LinearLayoutManager(this)

        viewAdapter = TVAdapter(tvs, this)


        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTVs)
        // use a linear layout manager
        recyclerView.layoutManager = viewManager

        // specify an viewAdapter (see also next example)
        recyclerView.adapter = viewAdapter

        getAllTVs()

    }

    private fun getAllTVs() {

        val url = "http://192.168.0.177:8080/api/tvs"
        val request =
            JsonArrayRequest(Request.Method.GET, url, null, { response ->
                try {

                    for (i in 0 until response.length()) {
                        val tv = response.getJSONObject(i)
                        val id = tv.getInt("id")
                        val model = tv.getString("model")
                        val brand = tv.getString("brand")
                        val price = tv.getString("price")
                        this.tvs.add(TV(id, brand, model, price))

                    }
                    viewAdapter.tvList = tvs
                    viewAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }
}
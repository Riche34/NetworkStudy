package kanghan.example.networkstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import kanghan.example.networkstudy.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  var randomUserResponse: RandomUserResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        //binding.userData = randomUserResponse
        binding.btnGetData.setOnClickListener{
            getUser()
            showProfile()
            //binding.userData = randomUserResponse
        }
    }
    override fun onResume() {
        super.onResume()
    }

    private fun getUser() {
        RectrofitManager.apiService.getUser().enqueue(object: Callback<RandomUserResponse>{
            //통신 성공 시
            override fun onResponse(
                call: Call<RandomUserResponse>,
                response: Response<RandomUserResponse>
            ) {
                if (response.isSuccessful) {
                    randomUserResponse = response.body()
                }
            }
            // 통신 실패 했을 떄,
            override fun onFailure(call: Call<RandomUserResponse>, t: Throwable) {
               Log.d("test301", "실패")
            }
        })
    }

    private fun showProfile() {
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        val imageURL = randomUserResponse?.results?.get(0)?.picture?.large
        Glide.with(this)
                .load(imageURL)
                .placeholder(circularProgressDrawable)
                .into(binding.ivFacePicture)

    }
}
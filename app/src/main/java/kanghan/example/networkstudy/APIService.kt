package kanghan.example.networkstudy


import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    //API Service
    @GET("/")
    fun getUser(): Call<RandomUserResponse>


}
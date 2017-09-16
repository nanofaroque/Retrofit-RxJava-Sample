package network;

import java.util.Map;

import models.SignUpResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by omarfaroque on 9/15/17.
 */

public interface IRespayNetworkService {
    @Headers({
            "Content-Type", "application/x-www-form-urlencoded",
            "accessToken","OMAR_FERDOUS_MASUD@!#"
    })
    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Observable<Response> getAnswers();

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Observable<Response> getAnswers(@Query("tagged") String tags);

    @FormUrlEncoded
    @POST("/merchants/signup")
    Observable<Response<SignUpResponse>> signUp(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> fields);
}

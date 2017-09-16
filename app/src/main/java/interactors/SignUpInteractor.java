package interactors;

import android.util.Log;

import java.util.HashMap;

import models.SignUpResponse;
import network.IRespayNetworkService;
import network.SuperInteractor;
import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by omarfaroque on 9/15/17.
 */

public class SignUpInteractor extends SuperInteractor {
    public SignUpInteractor(){
        super();
    }

    public void apiCall(){
        String email= "abc@gmail.com";
        String password = "asdfjkl";
        HashMap<String,String> params = new HashMap<>();
        params.put("email",email);
        params.put("password",password);

        HashMap headers = new HashMap<String,String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("accessToken","OMAR_FERDOUS_MASUD@!#");
        NetworkService.signUp(headers,params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Response<SignUpResponse>>() {
            @Override
            public void onCompleted() {
                Log.d("Complete","Completed Successfully");

            }

            @Override
            public void onError(Throwable e) {
                Log.d("Error",e.getMessage().toString());
            }

            @Override
            public void onNext(Response<SignUpResponse> response) {
                Log.d("Result: ", String.valueOf(response.code()));

            }
        });
    }
}

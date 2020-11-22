package com.example.cout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import com.example.cout.Api.ApiHandler;
import com.example.cout.Api.ApiService;
import com.example.cout.Api.PostData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CodeActivity extends AppCompatActivity {
    TextView tvResult;
    EditText etInput,header,answer;
    String answer_1,answer_2,publicTestCase,privateTestCase;

    Button btnSubmit,btnRun;
    question myQuestion = new question();
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final String TAG = "TAG";
    public void getDataFromFirestore(){
        final DocumentReference docRef = db.collection("questions").document("026RZ33AdFeOD1tcHaY3");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    Log.w("TAG",error);
                    return;
                }
                if(value!=null && value.exists()){
                    Log.d("TAG","Current Data " + value.getData());
                    List listHeader,listAnswer_1,listAnswer_2,listTestCases;
                    listHeader = (List) value.getData().get("1_header");
                    listAnswer_1 = (List) value.getData().get("3_answer_1");
                    listAnswer_2 = (List) value.getData().get("5_answer_2");
                    listTestCases = (List) value.getData().get("4_testcases");
                    myQuestion.addHeader(listHeader);
                    myQuestion.addAnswer(listAnswer_1,listTestCases,listAnswer_2);
                    header.setText(myQuestion.header);
                    answer.setText(myQuestion.publicAnswer);
                }
                else{
                    Log.d("TAG","Curent Data: NULL");
                }
            }
        });

     }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        getDataFromFirestore();
        tvResult  = findViewById(R.id.tv_result);
        header = findViewById(R.id.header);
        answer = findViewById(R.id.answer);
        etInput   = findViewById(R.id.et_input);
        btnSubmit = findViewById(R.id.btn_submit);
        btnRun = findViewById(R.id.btn_run);
        answer_1 = "} \n int main() \n { \n";
        answer_2 = "\n return 0; \n }  ";
        publicTestCase = "  cout<<\"The sum of two numbers (5,15) is\"<<add(5,15)<<endl;";
        privateTestCase = " cout<<\"The sum of two numbers (25,25) is\"<<add(25,25)<<endl;";
        String questionHeader = myQuestion.header;
        Log.d("TAG1",questionHeader);
        header.setText(questionHeader);
        etInput.setText("");
        answer.setText("");
        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiService apiService = ApiHandler.getRetrofitInstance();
                Call<String> execute = apiService.execute(new PostData(header.getText().toString() + etInput.getText().toString() + myQuestion.publicAnswer));

                tvResult.setText("Loading...");

                execute.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        tvResult.setText("");

                        try {

                            if(response.isSuccessful()){


                                JSONObject responseJson = new JSONObject(response.body().toString());
                                String output = responseJson.getString("output");
                                tvResult.setText(output);

                            }else{

                                Toast.makeText(CodeActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            Toast.makeText(CodeActivity.this, "Gagal Parsing JSON : " + e.getMessage(), Toast.LENGTH_SHORT).show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                        tvResult.setText("Failed");
                        Toast.makeText(CodeActivity.this, "Gagal : " + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiService apiService = ApiHandler.getRetrofitInstance();
                Call<String> execute = apiService.execute(new PostData(header.getText().toString() + etInput.getText().toString() + myQuestion.privateAnswer));

                tvResult.setText("Loading...");

                execute.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        tvResult.setText("");

                        try {

                            if(response.isSuccessful()){


                                JSONObject responseJson = new JSONObject(response.body().toString());
                                String output = responseJson.getString("output");
                                tvResult.setText(output);

                            }else{

                                Toast.makeText(CodeActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            Toast.makeText(CodeActivity.this, "Gagal Parsing JSON : " + e.getMessage(), Toast.LENGTH_SHORT).show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                        tvResult.setText("Failed");
                        Toast.makeText(CodeActivity.this, "Gagal : " + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }
}
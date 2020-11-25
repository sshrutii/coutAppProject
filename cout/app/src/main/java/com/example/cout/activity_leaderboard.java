package com.example.cout;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.TreeMap;
import java.util.Vector;

public class activity_leaderboard extends AppCompatActivity {

    final Vector userScore = new Vector();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        getScore();

    }

    public void getScore()
    {
        final FirebaseFirestore db= FirebaseFirestore.getInstance();

        /**to get a single document
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        CollectionReference colRef= db.collection("UsersInfo");
        colRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    CoSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists())
                    {
                        Log.d("data",documentSnapshot.getData().toString());
                        System.out.println("Helloooo");
                        System.out.println(documentSnapshot.getData());
                    }
                    else
                    {
                        Log.d("TAG","No such document");
                    }
                }
            }
        });**/



//        db.collection("UsersInfo")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                        if (task.isSuccessful() ) {
//                            System.out.println(task.isSuccessful()+" task check");
//                            for (int i=0;i<2;i++) {
//                                QueryDocumentSnapshot document= (QueryDocumentSnapshot)task.getResult();
//
//                                System.out.println("Helloooo");
//                                System.out.println(document.getData());
//                                userScore.add(document.getData());
//                                Log.d("TAG", document.getId() + " => " + document.getData());
//
//                            }
//                        }
//                    }
//                });

        db.collection("UsersInfo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //List list = new ArrayList<>();
                    TreeMap list = new TreeMap();

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        list.put(document.get("Score"), document.getData());
                    }
                    //list.descendingMap();
                    Log.d("TAG", list.descendingMap().toString());
                    TextView textView = (TextView) findViewById(R.id.leaderboardview);
                    textView.setText(list.descendingMap().toString());

                } else {
                    Log.d("TAG", "Error getting document");
                }
            }
            });

        //iterating through vector



    }
}
package com.example.user.mvp_dagger.view.github;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.mvp_dagger.R;
import com.example.user.mvp_dagger.di.DaggerGithubComponent;

import javax.inject.Inject;

public class GithubActivity extends AppCompatActivity implements GithubContract.View {
    EditText etMain;
    private TextView tvName;

    @Inject
    GithubPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMain = findViewById(R.id.etMain);
        tvName = findViewById(R.id.tvName);

//        presenter = new GithubPresenter();

    }

    @Override
    protected void onStart() {
        super.onStart();

        DaggerGithubComponent.create().inject(this);
        presenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }

    public void onDisplayName(View view) {
        presenter.validateName(etMain.getText().toString());
    }

    @Override
    public void onValidationResults(String validName) {
        tvName.setText(validName);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}

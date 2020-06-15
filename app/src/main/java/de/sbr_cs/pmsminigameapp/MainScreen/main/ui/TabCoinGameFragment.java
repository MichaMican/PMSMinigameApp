package de.sbr_cs.pmsminigameapp.MainScreen.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import de.sbr_cs.pmsminigameapp.CoinGame.CoinGameActivity;
import de.sbr_cs.pmsminigameapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class TabCoinGameFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private Button startButton;

    public static TabCoinGameFragment newInstance(int index) {
        TabCoinGameFragment fragment = new TabCoinGameFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startButton = (Button) view.findViewById(R.id.startCoinGameButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CoinGameActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tab_coingame, container, false);
        return root;
    }
}
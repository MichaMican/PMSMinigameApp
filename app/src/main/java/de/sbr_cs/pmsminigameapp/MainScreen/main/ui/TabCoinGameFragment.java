package de.sbr_cs.pmsminigameapp.MainScreen.main.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import de.sbr_cs.pmsminigameapp.CoinGame.CoinGameActivity;
import de.sbr_cs.pmsminigameapp.R;

/**
 * Coin game fragment
 */
public class TabCoinGameFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private ImageButton startButton;
    private ImageButton helpButton;


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
        startButton = view.findViewById(R.id.startCoinGameButton);
        helpButton = view.findViewById(R.id.helpCoinGame);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog =
                        new AlertDialog.Builder(
                                Objects.requireNonNull(TabCoinGameFragment.this.getContext())
                        ).create();
                alertDialog.setTitle(R.string.help);
                alertDialog.setMessage(getString(R.string.helpCoin));
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });

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
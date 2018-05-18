package trabalho.aluno.ufg.br.minhacidade.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import trabalho.aluno.ufg.br.minhacidade.R;

public class MainFragment extends Fragment {

    private int idXML = R.layout.fragments_envios;
    private String nomeFragment;

    public MainFragment() {
    }

    public static MainFragment newInstance(int idXML, String nomeFragment) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt("idXML", idXML);
        args.putString("nomeFragment", nomeFragment);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idXML = getArguments().getInt("idXML");
            nomeFragment = getArguments().getString("nomeFragment");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(idXML, container, false);
    }

}

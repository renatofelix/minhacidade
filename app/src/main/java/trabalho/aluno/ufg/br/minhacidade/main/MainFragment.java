package trabalho.aluno.ufg.br.minhacidade.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import trabalho.aluno.ufg.br.minhacidade.R;
import trabalho.aluno.ufg.br.minhacidade.modelos.Problema;

public class MainFragment extends Fragment {

    private int idXML = R.layout.fragments_envios;
    private String nomeFragment;

    private List<Problema> problemas = new ArrayList<>();
    @BindView(R.id.rv_problemas)
    private RecyclerView recyclerView;

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
        View view = inflater.inflate(idXML, container, false);

//        recyclerView.setAdapter();

        // Inflate the layout for this fragment
        return view;
    }

}

package ssamba.ept.sn.bankingApp.views.utils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ssamba.ept.sn.bankingApp.MainActivity;

public class NestedScreenFragment extends Fragment {

    // BOTTOM BAR
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MainActivity) context).hideBottomNavigation();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ((MainActivity) getContext()).showBottomNavigation();
    }
}

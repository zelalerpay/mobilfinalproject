package msku.ceng.madlab.uniguide;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class QrCodeFragment extends Fragment {

    public QrCodeFragment() {
        // Required empty public constructor
    }

    public static QrCodeFragment newInstance() {
        return new QrCodeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qr_code, container, false);

        ImageView imgQr = view.findViewById(R.id.imgQr);
        // Burada bir QR kütüphanesi (Zxing vb.) kullanarak gerçek QR oluşturabilir
        // veya placeholders olarak bir örnek resim gösterebilirsiniz.
        imgQr.setImageResource(R.drawable.ic_placeholder_qr);

        Toast.makeText(getContext(), "Scan the QR for 10% OFF with Uni Guide!", Toast.LENGTH_LONG).show();

        return view;
    }
}
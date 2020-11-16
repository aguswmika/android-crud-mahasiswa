package id.aguswmika.mahasiswadrawer.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.List;

import id.aguswmika.mahasiswadrawer.R;
import id.aguswmika.mahasiswadrawer.model.Mahasiswa;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> {
    private List<Mahasiswa> mahasiswaList;

    public MahasiswaAdapter(List<Mahasiswa> mahasiswas) {
        mahasiswaList = mahasiswas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nama, nim, prodi;
        public ImageView foto;


        public ViewHolder(View itemView) {
            super(itemView);

            nama = (TextView) itemView.findViewById(R.id.mahasiswa_item_nama);
            nim = (TextView) itemView.findViewById(R.id.mahasiswa_item_nim);
            prodi = (TextView) itemView.findViewById(R.id.mahasiswa_item_program_studi);
            foto = (ImageView) itemView.findViewById(R.id.foto_image);
        }
    }

    @Override
    public MahasiswaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View mahasiswaView = inflater.inflate(R.layout.component_mahasiswa_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(mahasiswaView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MahasiswaAdapter.ViewHolder holder, int position) {
        Mahasiswa mahasiswa = mahasiswaList.get(position);

        TextView nama = holder.nama;
        nama.setText(mahasiswa.getNama());
        TextView nim = holder.nim;
        nim.setText(mahasiswa.getNim());
        TextView prodi = holder.prodi;
        prodi.setText(mahasiswa.getProgramStudi());
        ImageView foto = holder.foto;

        new DownloadImageTask(foto).execute("https://www.tutorialspoint.com/android/images/choose_device.jpg");
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

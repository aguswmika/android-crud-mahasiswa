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

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nama, nim, prodi;
        public ImageView foto;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nama = (TextView) itemView.findViewById(R.id.mahasiswa_item_nama);
            nim = (TextView) itemView.findViewById(R.id.mahasiswa_item_nim);
            prodi = (TextView) itemView.findViewById(R.id.mahasiswa_item_program_studi);
            foto = (ImageView) itemView.findViewById(R.id.foto_image);
        }
    }

    // Store a member variable for the mahasiswas
    private List<Mahasiswa> mahasiswaList;

    // Pass in the mahasiswa array into the constructor
    public MahasiswaAdapter(List<Mahasiswa> mahasiswas) {
        mahasiswaList = mahasiswas;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public MahasiswaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View mahasiswaView = inflater.inflate(R.layout.component_mahasiswa_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(mahasiswaView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(MahasiswaAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Mahasiswa mahasiswa = mahasiswaList.get(position);

        // Set item views based on your views and data model
        TextView nama = holder.nama;
        nama.setText(mahasiswa.getNama());
        TextView nim = holder.nim;
        nim.setText(mahasiswa.getNim());
        TextView prodi = holder.prodi;
        prodi.setText(mahasiswa.getProgramStudi());
        ImageView foto = holder.foto;

        new DownloadImageTask(foto).execute("https://www.tutorialspoint.com/android/images/choose_device.jpg");
    }

    // Returns the total count of items in the list
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

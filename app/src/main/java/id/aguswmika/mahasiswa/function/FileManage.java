package id.aguswmika.mahasiswa.function;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileManage {
    private Context context;
    private File file;
    private String FILE_NAME = "mahasiswa.txt";

    public FileManage(Context context){
        this.context = context;

        file = new File(context.getFilesDir(), FILE_NAME);
        if(!file.exists()){
            try{
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    public FileManage(Context context, String filename){
//        this.context = context;
//        this.filename = filename;
//
//        file = new File(context.getFilesDir(), filename);
//        if(!file.exists()){
//            try{
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void write(String value){
//        try {
//            outputStream = context.openFileOutput(this.filename, Context.MODE_PRIVATE);
//            outputStream.write(value.getBytes());
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try{
            FileWriter writer = new FileWriter(file);
            writer.append(value);
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String read() throws FileNotFoundException {
        String contents  = "";
        FileInputStream fis = this.context.openFileInput(this.FILE_NAME);
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        } finally {
            contents = stringBuilder.toString();
        }

        return contents;
    }


}

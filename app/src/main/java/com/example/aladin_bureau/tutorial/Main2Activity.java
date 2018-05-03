package com.example.aladin_bureau.tutorial;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xiaopo.flying.sticker.BitmapStickerIcon;
import com.xiaopo.flying.sticker.DrawableSticker;
import com.xiaopo.flying.sticker.StickerView;
import com.xiaopo.flying.sticker.DeleteIconEvent;
import com.xiaopo.flying.sticker.TextSticker;
import com.xiaopo.flying.sticker.ZoomIconEvent;
import com.xiaopo.flying.sticker.FlipHorizontallyEvent;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.aladin_bureau.tutorial.HelloIconEvent;

import org.lucasr.twowayview.TwoWayView;

public class Main2Activity extends AppCompatActivity {
StickerView stickerView;
Bitmap bmp;
ImageView imageView;
    Toolbar toolbar;
    FloatingActionButton fab;
    TwoWayView twoWayView;
    mystickers stickers= new mystickers();
    mystickers stickers2= new mystickers();
    mystickers stickers3= new mystickers();
    mystickers stickers4= new mystickers();
    mystickers stickers5= new mystickers();
    mystickers stickers6= new mystickers();
    mystickers stickers7= new mystickers();
    mystickers stickers8= new mystickers();


    List<mystickers> list= new ArrayList<>();
   imageAdapter adapter;
RelativeLayout relativeLayout;
    Integer REQUEST_CAMERA=1 ,SELECT_FILE=0,SELECTED_item=2;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setVisibility(View.GONE);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        relativeLayout=(RelativeLayout)findViewById(R.id.relative);
        relativeLayout.setBackgroundResource(R.drawable.bgsticker);
         stickerView=(StickerView)findViewById(R.id.sticker);
         twoWayView=(TwoWayView)findViewById(R.id.listorg);
         twoWayView.setVisibility(View.GONE);
         stickers.setImg(R.drawable.sticker2);
         stickers2.setImg(R.drawable.sticker3);
         stickers3.setImg(R.drawable.sticker4);
         stickers4.setImg(R.drawable.sticker5);
         stickers5.setImg(R.drawable.sticker1);
         stickers6.setImg(R.drawable.sticker6);
         stickers7.setImg(R.drawable.sticker7);
         stickers8.setImg(R.drawable.sticker8);
         list.add(stickers);
         list.add(stickers2);
         list.add(stickers3);
         list.add(stickers4);
         list.add(stickers5);
         list.add(stickers6);
         list.add(stickers7);
         list.add(stickers8);
         adapter=new imageAdapter(getApplicationContext(),list);
         adapter.notifyDataSetChanged();
         twoWayView.setAdapter(adapter);
         twoWayView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             if(twoWayView.getItemAtPosition(i)!=null){
                 DrawableSticker drawableSticker=new DrawableSticker(getDrawable(list.get(i).getImg()));
                 drawableSticker.setFlippedHorizontally(true);
                 drawableSticker.setFlippedVertically(true);
                 stickerView.addSticker(drawableSticker);
             }

         }
         });
        imageView=(ImageView)findViewById(R.id.myimage);
  /*    TextSticker sticker=new TextSticker(this);
        sticker.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sticker_transparent_background));
        sticker.setTextColor(Color.WHITE);
        sticker.setText("Hello world");
        sticker.setTextAlign(Layout.Alignment.ALIGN_CENTER);
        sticker.resizeText();*
        stickerView.addSticker(sticker);*/
        BitmapStickerIcon heartIcon =
                new BitmapStickerIcon(ContextCompat.getDrawable(this, R.drawable.sticker_transparent_background),
                        BitmapStickerIcon.LEFT_BOTTOM);
        heartIcon.setIconEvent(new HelloIconEvent());
        BitmapStickerIcon flipIcon =
                new BitmapStickerIcon(ContextCompat.getDrawable(this, R.drawable.sticker_ic_flip_white_18dp),
                        BitmapStickerIcon.LEFT_BOTTOM);
        flipIcon.setIconEvent(new FlipHorizontallyEvent());
        BitmapStickerIcon zoomIcon =
                new BitmapStickerIcon(ContextCompat.getDrawable(this, R.drawable.sticker_ic_scale_white_18dp),
                        BitmapStickerIcon.LEFT_BOTTOM);
        zoomIcon.setIconEvent(new ZoomIconEvent());
        BitmapStickerIcon deleteIcon =
                new BitmapStickerIcon(ContextCompat.getDrawable(this, R.drawable.sticker_ic_close_white_18dp),
                        BitmapStickerIcon.LEFT_BOTTOM);
        deleteIcon.setIconEvent(new DeleteIconEvent());
        stickerView.setIcons(Arrays.asList(deleteIcon, zoomIcon, flipIcon, heartIcon));
stickerView.configDefaultIcons();

        stickerView.setVisibility(View.GONE);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
             menu.findItem(R.id.save).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                 @Override
                 public boolean onMenuItemClick(MenuItem menuItem) {
                     getMenuInflater().inflate(R.menu.menushare,menu);
                     menu.findItem(R.id.save).setVisible(false);
                     menu.findItem(R.id.share).setVisible(false);
                     menu.findItem(R.id.add).setVisible(false);
                     menu.findItem(R.id.refresh).setVisible(false);
                     menu.findItem(R.id.quit).setVisible(false);
                     stickerView.setConstrained(false);
                     twoWayView.setVisibility(View.GONE);
                      imageView.getLayoutParams().height=imageView.getHeight()+180;
                     return false;
                 }
             });

             return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.save){
            onsave(imageView);
            Toast.makeText(getApplicationContext(),"Image saved on your gallery folder",Toast.LENGTH_LONG).show();


        }
        else if(id==R.id.add){
            addstickers();
        }
       else if(id==R.id.refresh){
            Intent inten=new Intent(Main2Activity.this,Main2Activity.class);
            startActivity(inten);
            finish();

        }
        else if(id==R.id.quit){
            this.finish();

        }
        else if (id==R.id.share2){
              share(imageView);
        }

        else if(id==R.id.refresh2){
            Intent inten2=new Intent(Main2Activity.this,Main2Activity.class);
            startActivity(inten2);
            finish();
        } else if (id==R.id.quit2){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public Bitmap getBitmap(){
        return bmp;
    }
 public void setBitmap(Bitmap bmp){
this.bmp=bmp; }
    private  void selectImage(){
        final CharSequence[] items={"Camera","Gallery","Cancel"};
        AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
        builder.setTitle("Add image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(items[i]=="Camera"){
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CAMERA);
                }
                else if(items[i]=="Gallery"){
                    Intent intent2=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent2.setType("image/*");
                    startActivityForResult(intent2,SELECT_FILE);
                } else if (items[i]=="Cancel"){
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }
    private void share(ImageView imageView){
        this.imageView=imageView;
        BitmapDrawable draw=(BitmapDrawable)imageView.getDrawable();
        Bitmap bitmap=draw.getBitmap();
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpeg");
        try{
            File cache = getApplicationContext().getExternalCacheDir();
            File shareFile = new File(cache,"example.png");
            FileOutputStream out = new FileOutputStream(shareFile);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,out);
            out.flush();
            out.close();
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+shareFile));
            startActivity(Intent.createChooser(shareIntent,"Share"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void opendialogtext(){
        LayoutInflater inflater=LayoutInflater.from(Main2Activity.this);
        View subview=inflater.inflate(R.layout.alertdialog_layout,null);
        final EditText editText=(EditText)subview.findViewById(R.id.dialogEditText);
        AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
        builder.setTitle("Add Texte sticker");
        builder.setMessage("Text Sticker ");
        builder.setView(subview);
        AlertDialog alertDialog=builder.create();
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TextSticker sticker=new TextSticker(Main2Activity.this);
                sticker.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sticker_transparent_background));
                sticker.setTextColor(Color.WHITE);
                sticker.setText(editText.getText().toString());
                sticker.setTextAlign(Layout.Alignment.ALIGN_CENTER);
                sticker.resizeText();
                stickerView.addSticker(sticker);
            }
        });
        builder.show();
    }

    public void addstickers(){
        final CharSequence[] lignes={"Add_Text_Stickers","Add_image_stickers","cancel"};
        AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
        builder.setTitle("Add Sticker  by yourself");
        builder.setItems(lignes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               if(lignes[i]=="Add_Text_Stickers"){
                   opendialogtext();
               } else if(lignes[i]=="Add_image_stickers"){
                         Intent intent3=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                         intent3.setType("image/*");
                         startActivityForResult(intent3,SELECTED_item);
               }else if(lignes[i]=="cancel"){
                   dialogInterface.dismiss();
               }
            }
        });
        builder.show();
    }
    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);

        if(resultCode== Activity.RESULT_OK){

            if(requestCode==REQUEST_CAMERA){

                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                imageView.setImageBitmap(bmp);
                relativeLayout.setBackgroundResource(0);
                toolbar.setVisibility(View.VISIBLE);
                stickerView.setVisibility(View.VISIBLE);
                twoWayView.setVisibility(View.VISIBLE);
                fab.setVisibility(View.GONE);



            }
            else if(requestCode==SELECT_FILE){

                Uri selectedImageUri = data.getData();
                imageView.setImageURI(selectedImageUri);
                relativeLayout.setBackgroundResource(0);
                toolbar.setVisibility(View.VISIBLE);
                stickerView.setVisibility(View.VISIBLE);
                twoWayView.setVisibility(View.VISIBLE);
                fab.setVisibility(View.GONE);

            }
            else if(requestCode==SELECTED_item){
                Uri selectedstickeruri = data.getData();
                ImageView newimg=new ImageView(this);
                newimg.setImageURI(selectedstickeruri);
                newimg.setVisibility(View.GONE);
                DrawableSticker dsticker=new DrawableSticker(newimg.getDrawable());
                dsticker.setFlippedVertically(true);
                dsticker.setFlippedHorizontally(true);
                stickerView.addSticker(dsticker);
                toolbar.setVisibility(View.VISIBLE);
                stickerView.setVisibility(View.VISIBLE);
                twoWayView.setVisibility(View.VISIBLE);
                fab.setVisibility(View.GONE);

            }


        }
    }
    public void  onsave(ImageView imageView){
          this.imageView=imageView;
        BitmapDrawable draw=(BitmapDrawable)imageView.getDrawable();
        Bitmap bitmap=draw.getBitmap();
        FileOutputStream outputStream=null;
        File sdcard= Environment.getExternalStorageDirectory();
        File file=new File(sdcard.getAbsolutePath()+"/StickersimgApp");
        file.mkdir();
        String filname=String.format("%d.jpg",System.currentTimeMillis());
        File outputfile=new File(file,filname);
        try {
            outputStream=new FileOutputStream(outputfile);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            try {
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        stickerView.save(outputfile);

    }
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public Bitmap setdrawposition(){
        Bitmap bmp = null;
        bmp.setWidth(20);
        bmp.setHeight(20);
        return bmp ;
}

}

# stickerapp-basedon-Stickview
sticker app
in this app i resolved many thing for stickview android plugin like save and share the image : 
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
    2-also for add the sticker from the gallery : 
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
    3- added text sticker by your keyboard : 
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
4- you must add this on you gradle : ! 
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:design:26.1.0'
    androidTestImplementation('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    implementation 'com.android.support:appcompat-v7:26.+'
    implementation 'org.lucasr.twowayview:twowayview:0.1.4'

    implementation 'com.flying.xiaopo:sticker:1.6.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    androidTestImplementation 'junit:junit:4.12'
}

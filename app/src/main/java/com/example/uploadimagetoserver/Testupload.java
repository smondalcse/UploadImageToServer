package com.example.uploadimagetoserver;

public class Testupload {


    /****


//
//
//     package com.nitolmotorsltd.amaarherocustomer.activity;
//
//     import android.Manifest;
//     import android.app.Activity;
//     import android.app.AlertDialog;
//     import android.app.ProgressDialog;
//     import android.content.Context;
//     import android.content.DialogInterface;
//     import android.content.Intent;
//     import android.content.pm.PackageManager;
//     import android.database.Cursor;
//     import android.graphics.Bitmap;
//     import android.graphics.BitmapFactory;
//     import android.net.Uri;
//     import android.os.AsyncTask;
//     import android.os.Build;
//     import android.os.Environment;
//     import android.os.StrictMode;
//     import android.provider.MediaStore;
//     import android.support.annotation.NonNull;
//     import android.support.design.widget.Snackbar;
//     import android.support.v4.app.ActivityCompat;
//     import android.support.v4.content.ContextCompat;
//     import android.support.v4.content.CursorLoader;
//     import android.support.v4.content.FileProvider;
//     import android.support.v7.app.AppCompatActivity;
//     import android.os.Bundle;
//     import android.support.v7.widget.LinearLayoutManager;
//     import android.support.v7.widget.RecyclerView;
//     import android.util.Base64;
//     import android.util.Log;
//     import android.view.View;
//     import android.view.Window;
//     import android.view.inputmethod.InputMethodManager;
//     import android.widget.AdapterView;
//     import android.widget.Button;
//     import android.widget.EditText;
//     import android.widget.ImageButton;
//     import android.widget.ImageView;
//     import android.widget.Spinner;
//     import android.widget.TextView;
//     import android.widget.Toast;
//
//     import com.nitolmotorsltd.amaarherocustomer.BuildConfig;
//     import com.nitolmotorsltd.amaarherocustomer.R;
//     import com.nitolmotorsltd.amaarherocustomer.adapter.CountryAdapter;
//     import com.nitolmotorsltd.amaarherocustomer.adapter.FileSpinnerAdapter;
//     import com.nitolmotorsltd.amaarherocustomer.adapter.RecyclerViewUploadAdapter;
//     import com.nitolmotorsltd.amaarherocustomer.model.DocumentFileModel;
//     import com.nitolmotorsltd.amaarherocustomer.others.CheckPermission;
//     import com.theartofdev.edmodo.cropper.CropImage;
//     import com.theartofdev.edmodo.cropper.CropImageView;
//
//     import org.ksoap2.SoapEnvelope;
//     import org.ksoap2.SoapFault;
//     import org.ksoap2.serialization.MarshalBase64;
//     import org.ksoap2.serialization.SoapObject;
//     import org.ksoap2.serialization.SoapSerializationEnvelope;
//     import org.ksoap2.transport.HttpTransportSE;
//
//     import java.io.ByteArrayOutputStream;
//     import java.io.File;
//     import java.io.FileInputStream;
//     import java.io.FileNotFoundException;
//     import java.io.FileOutputStream;
//     import java.io.IOException;
//     import java.io.InputStream;
//     import java.io.OutputStream;
//     import java.text.SimpleDateFormat;
//     import java.util.ArrayList;
//     import java.util.Date;
//     import java.util.List;
//
//     public class DocumentsUploadActivity extends AppCompatActivity {
//     private static final String TAG = "DocumentsUploadActivity";
//
//     private int PICK_IMAGE_REQUEST = 1;
//     private int PICK_PDF_REQUEST = 2;
//     private Bitmap bitmap;
//     private Uri filePath;
//     private TextView txt_remarks;
//     String remarks;
//
//     private int CAMERA_PERMISSION_CODE = 1324;
//     private static final int CAMERA_REQUEST = 10;
//
//     private ImageButton btnBack;
//     Spinner spinner_appForm;
//     Button btn_upload, btn_file_search,btn_documents_save;
//     ImageView img_file;
//
//     // For Image Resize
//     int intOldWidth, intOldHeight, intNewWidth, intNewHeight, intMaxSide;
//     int MaxSideSize = 600;
//     String ImageUri, encodedImage;
//     byte[] b;
//     boolean is_spinner_load = false;
//
//     DocumentFileModel documentFileModel;
//
//     // for rv
//     private ArrayList<DocumentFileModel> DocumentFileModel = new ArrayList<DocumentFileModel>();
//     private ArrayList<String> mFileID = new ArrayList<>();
//     private ArrayList<String> mFileName = new ArrayList<>();
//     private ArrayList<String> mRemarks = new ArrayList<>();
//     private ArrayList<String> mUploaded = new ArrayList<>();
//     private ArrayList<String> mDelete = new ArrayList<>();
//     RecyclerView recyclerView;
//
//     private FileSpinnerAdapter mAdapter;
//     CustomAlertDialog customAlertDialog;
//
//     boolean pic_uploaded = false;
//     boolean pdf_uploaded = false;
//     private EditText et_buyer_coder;
//     private String buyer_code = "";
//     String  OfficeID = "", sFullName, sFoID, sUserLabel;
//
//     String imageNewname;
//     private int imageFileSize;
//     public  static final int PERMISSIONS_MULTIPLE_REQUEST = 123;
//
//     String current_image_path = null;
//     Uri imageUri = null;
//
//     @Override
//     protected void onCreate(Bundle savedInstanceState) {
//     Log.d(TAG, "onCreate: ");
//     super.onCreate(savedInstanceState);
//     setContentView(R.layout.activity_documents_upload);
//
//     getParams();
//     initWidget();
//
//     }
//
//     private void getParams() {
//     Log.d(TAG, "getParams: ");
//
//     Bundle extras = getIntent().getExtras();
//     if (extras != null) {
//     sFullName = extras.getString("FullName");
//     OfficeID = extras.getString("FoID");
//     sUserLabel = extras.getString("UserLabel");
//     }
//     }
//
//     private void initWidget() {
//     Log.d(TAG, "initWidget: ");
//     btnBack = (ImageButton) findViewById(R.id.btnBack);
//     btnBack.setOnClickListener(new View.OnClickListener() {
//     @Override
//     public void onClick(View view) {
//     finish();
//     }
//     });
//
//     customAlertDialog = new CustomAlertDialog();
//
//     et_buyer_coder = findViewById(R.id.et_buyer_coder);
//
//     img_file = findViewById(R.id.img_file);
//     img_file.setOnClickListener(new View.OnClickListener() {
//     @Override
//     public void onClick(View v) {
//     checkAndroidVersion();
//     }
//     });
//
//
//     txt_remarks = findViewById(R.id.txt_remarks);
//
//     btn_upload = findViewById(R.id.btn_upload);
//     btn_upload.setOnClickListener(new View.OnClickListener() {
//     @Override
//     public void onClick(View v) {
//
//     checkAndroidVersion();
//
//     /*
//     if (ContextCompat.checkSelfPermission(DocumentsUploadActivity.this,
//     android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//     callCameraApp();
//     } else {
//     requestCameraPermission();
//     }*/
//
//}
//        });
//
////        btn_pdfupload = findViewById(R.id.btn_pdfupload);
////        btn_pdfupload.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                SavePDFToServer savePDFToServer = new SavePDFToServer();
////                savePDFToServer.execute();
////            }
////        });
//
//                btn_file_search = findViewById(R.id.btn_file_search);
//                btn_file_search.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        hideKeyboard(DocumentsUploadActivity.this);
//
//        buyer_code = et_buyer_coder.getText().toString();
//
//        GetDocFileInfo getDocFileInfo = new GetDocFileInfo();
//        getDocFileInfo.execute();
//        }
//        });
//
//        btn_documents_save = findViewById(R.id.btn_documents_save);
//        btn_documents_save.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//
//        if(!is_spinner_load){
//        showToast("Load File titles.");
//        // Toast.makeText(DocumentsUploadActivity.this, "Load File titles.", Toast.LENGTH_SHORT).show();
//        return;
//        }
//
//        remarks = txt_remarks.getText().toString().trim();
//        String fID = documentFileModel.getFileID().trim();
//
//        if(fID.equalsIgnoreCase("")){
//        showToast("Load File Titles.");
//        // Toast.makeText(DocumentsUploadActivity.this, "Load File titles.", Toast.LENGTH_SHORT).show();
//        return;
//        }
//
//        if(fID.equalsIgnoreCase("-1")){
//        showToast("Select File Name.");
//        return;
//        }
//
//        if(remarks.equalsIgnoreCase("")){
//        showToast("Please fill Remarks.");
//        return;
//        }
//
//        if(!pic_uploaded && !pdf_uploaded){
//        showToast("Please upload the Image or PDF.");
//        return;
//        } else {
//
//        hideKeyboard(DocumentsUploadActivity.this);
//
//        SaveUploadDocument saveUploadDocument = new SaveUploadDocument();
//        saveUploadDocument.execute();
//        }
//
//        }
//        });
//
//        recyclerView = findViewById(R.id.rv_upload);
//        Spinner_AppForm();
//
//        }
//
//private void checkAndroidVersion() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//        //checkPermission();
//        requestCameraStoragePermission();
//        } else {
//        //Toast.makeText(getApplicationContext(), "--------GO------------", Toast.LENGTH_SHORT).show();
//        callCameraApp();
//        }
//        }
//
//private void checkPermission() {
//        if (ContextCompat.checkSelfPermission(DocumentsUploadActivity.this,
//        Manifest.permission.CAMERA) + ContextCompat
//        .checkSelfPermission(DocumentsUploadActivity.this,
//        Manifest.permission.READ_EXTERNAL_STORAGE)
//        != PackageManager.PERMISSION_GRANTED) {
//
//        if (ActivityCompat.shouldShowRequestPermissionRationale
//        (DocumentsUploadActivity.this, Manifest.permission.CAMERA) ||
//        ActivityCompat.shouldShowRequestPermissionRationale
//        (DocumentsUploadActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//
//        Snackbar.make(DocumentsUploadActivity.this.findViewById(android.R.id.content),
//        "To use this feature you need to grant these permissions.",
//        Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
//        new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//        requestPermissions(
//        new String[]{Manifest.permission
//        .CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE},
//        PERMISSIONS_MULTIPLE_REQUEST);
//        }
//        }
//        }).show();
//        } else {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//        requestPermissions(
//        new String[]{Manifest.permission
//        .CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE},
//        PERMISSIONS_MULTIPLE_REQUEST);
//        }
//        }
//        } else {
//        callCameraApp();
//        // Toast.makeText(getApplicationContext(), "--------GO------------", Toast.LENGTH_SHORT).show();
//        }
//        }
//
//private void requestCameraStoragePermission() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale
//        (DocumentsUploadActivity.this, Manifest.permission.CAMERA) ||
//        ActivityCompat.shouldShowRequestPermissionRationale
//        (DocumentsUploadActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
//
//        new AlertDialog.Builder(this)
//        .setTitle("Permission needed")
//        .setMessage("To use this feature you need to grant these permissions. \n\n1. Camera permission for taking the new photo. \n2. Storage permission for PDF and photo upload")
//        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialog, int which) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//        requestPermissions(
//        new String[]{Manifest.permission
//        .CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE},
//        PERMISSIONS_MULTIPLE_REQUEST);
//        }
//        }
//        })
//        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialog, int which) {
//        dialog.dismiss();
//        }
//        })
//        .create().show();
//
//        } else {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//        requestPermissions(
//        new String[]{Manifest.permission
//        .CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE},
//        PERMISSIONS_MULTIPLE_REQUEST);
//        }
//        }
//        }
//
//@Override
//public void onRequestPermissionsResult(int requestCode,
//@NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        switch (requestCode) {
//        case PERMISSIONS_MULTIPLE_REQUEST:
//        if (grantResults.length > 0) {
//        boolean cameraPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
//        boolean readExternalFile = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//
//        if(cameraPermission && readExternalFile)
//        {
//        callCameraApp();
//        //Toast.makeText(getApplicationContext(), "--------GO------------", Toast.LENGTH_SHORT).show();
//        } else {
//
//        requestCameraStoragePermission();
// /*
//                        Snackbar.make(DocumentsUploadActivity.this.findViewById(android.R.id.content),
//                                "To use this feature you need to grant these permissions.",
//                                Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
//                                new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        requestPermissions(
//                                                new String[]{Manifest.permission
//                                                        .READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
//                                                PERMISSIONS_MULTIPLE_REQUEST);
//                                    }
//                                }).show();
//                        */
//
//        }
//        }
//        break;
//        }
//        }
//
///*******************************///////////////////////
//
//private void callCameraApp() {
//        selectImage();
//        }
//
//private void requestCameraPermission() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//        android.Manifest.permission.CAMERA)) {
//
//        new AlertDialog.Builder(this)
//        .setTitle("Permission needed")
//        .setMessage("To use this feature you need to grant these permissions.")
//        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialog, int which) {
//        ActivityCompat.requestPermissions(DocumentsUploadActivity.this,
//        new String[] {Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
//        }
//        })
//        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialog, int which) {
//        dialog.dismiss();
//        }
//        })
//        .create().show();
//
//        } else {
//        ActivityCompat.requestPermissions(this,
//        new String[] {android.Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
//        }
//        }
////
////    @Override
////    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
////        if (requestCode == PERMISSION_ALL)  {
////            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////                Log.d(TAG, "onRequestPermissionsResult: " + "Permission GRANTED");
////            } else {
////                Log.d(TAG, "onRequestPermissionsResult: " + "Permission GRANTED");
////            }
////        }
////    }
//
//
//    /*
//        @Override
//        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//            if (requestCode == CAMERA_PERMISSION_CODE)  {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                   // Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "onRequestPermissionsResult: " + "Permission GRANTED");
//                    callCameraApp();
//                } else {
//                    Log.d(TAG, "onRequestPermissionsResult: " + "Permission GRANTED");
//                }
//            }
//        }
//
//    */
//private void Spinner_AppForm() {
//
//        spinner_appForm = findViewById(R.id.spinner_appForm);
//        spinner_appForm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//@Override
//public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Log.d(TAG, "Spinner_AppForm: ");
//
//        documentFileModel = (DocumentFileModel) parent.getItemAtPosition(position);
//        //Log.d(TAG, "onItemSelected: " + documentFileModel.getFileName());
//        //Log.d(TAG, "onItemSelected: " + documentFileModel.getRemarks());
//
//        }
//
//@Override
//public void onNothingSelected(AdapterView<?> parent) {
//
//        }
//        });
//
//        }
//
//private class GetDocFileInfo extends AsyncTask<Void, Void, String> {
//
//    private String sReturn;
//    ProgressDialog Dialog;
//
//    @Override
//    protected void onPreExecute() {
//        //Log.d(TAG, "WSFileShowreader: onPreExecute");
//
//        Dialog = new ProgressDialog(DocumentsUploadActivity.this);
//        Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //Dialog.setTitle("Waiting for sync");
//        Dialog.setMessage("Loading...");
//        Dialog.setCancelable(false);
//        Dialog.show();
//    }
//
//    @Override
//    protected String doInBackground(Void... x) {
//
//        SoapObject request = new SoapObject(getApplicationContext().getResources().getString(R.string.NAMESPACE), getApplicationContext().getResources().getString(R.string.METHOD_NAME_AndroidDocFileTitleForDropdown));
//
//        request.addPropertyIfValue("customerCode", buyer_code);
//        request.addPropertyIfValue("loginID", OfficeID);
//
//        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//        new MarshalBase64().register(envelope);
//        envelope.dotNet = true;
//        envelope.setOutputSoapObject(request);
//        // HttpTransportSE httpTransport = new HttpTransportSE(URL);
//        HttpTransportSE httpTransport = new HttpTransportSE(getApplicationContext().getResources().getString(R.string.URL));
//        try {
//            httpTransport.call(getApplicationContext().getResources().getString(R.string.SOAP_ACTION_AndroidDocFileTitleForDropdown), envelope);
//
////                SoapFault soapFault = (SoapFault) envelope.bodyIn;
////                System.out.println("Soap Fault: " + soapFault.getMessage());
//
//            SoapObject newob = (SoapObject) envelope.bodyIn;
//            int count = newob.getPropertyCount();
//            if (count == 0) {
//                return "0";
//            }
//            SoapObject getmemo_listresult = (SoapObject) newob.getProperty(0);
//            SoapObject diffgram = (SoapObject) getmemo_listresult
//                    .getProperty("diffgram");
//            SoapObject datasetmemo = (SoapObject) diffgram
//                    .getProperty("NewDataSet");
//            int nrows = datasetmemo.getPropertyCount();
//
//            initArray();
//
//            for (int i = 0; i < nrows; i++) {
//                SoapObject Table = (SoapObject) datasetmemo.getProperty(i);
//
//                if (i == 0) {
//                    DocumentFileModel.add(new DocumentFileModel("-1", "-Select FileName-",
//                            "", "", "0", "0"));
//                }
//
//                String ID = Table.getProperty("ID").toString();
//                String FileName = Table.getProperty("Name").toString();
//                String DownloadLink = Table.getProperty("DownloadLink").toString();
//                String Uploaded = Table.getProperty("IsUpload").toString();
//                String IsDelete = Table.getProperty("IsDelete").toString();
//                String Remarks = Table.getProperty("Remarks").toString();
//
//                if (Uploaded.equalsIgnoreCase("true")) {
//                    mFileID.add(ID);
//                    mFileName.add(FileName);
//                    mRemarks.add(Remarks);
//                    mUploaded.add(Uploaded);
//                    mDelete.add(IsDelete);
//                }
//
//                if(Uploaded.equalsIgnoreCase("false")) {
//                    DocumentFileModel.add(new DocumentFileModel(ID, FileName,
//                            DownloadLink, Remarks, IsDelete, Uploaded));
//                }
//            }
//
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//
//                    mAdapter = new FileSpinnerAdapter(DocumentsUploadActivity.this, DocumentFileModel);
//                    spinner_appForm.setAdapter(mAdapter);
//
//                    RecyclerViewUploadAdapter adapter = new RecyclerViewUploadAdapter(DocumentsUploadActivity.this, mFileName, mRemarks, mUploaded, mDelete);
//                    recyclerView.setAdapter(adapter);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(DocumentsUploadActivity.this));
//                }
//            });
//
//            return "1";
//        } catch (Exception e) {
//            //EasyToast.error(MainActivity.this, "Data Save Failed!", Toast.LENGTH_LONG);
//            return "0";
//        }
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//        Dialog.dismiss();
//        Dialog = null;
//
//        if(result.equalsIgnoreCase("1")){
//            is_spinner_load = true;
//        } else if (result.equalsIgnoreCase("0")){
//            customAlertDialog.showDialog(DocumentsUploadActivity.this, "Customer not found for documents upload.", "onlyshow");
//        }
//    }
//}
//
//    private void initArray(){
//        DocumentFileModel = new ArrayList<>();
//        mFileID = new ArrayList<>();
//        mFileName = new ArrayList<>();
//        mRemarks = new ArrayList<>();
//        mUploaded  = new ArrayList<>();
//        mDelete = new ArrayList<>();
//    }
//
//private class SaveUploadDocument extends AsyncTask<Void, Void, String> {
//
//    private String sReturn;
//    ProgressDialog Dialog;
//
//    @Override
//    protected void onPreExecute() {
//        //Log.d(TAG, "WSFileShowreader: onPreExecute");
//
//        Dialog = new ProgressDialog(DocumentsUploadActivity.this);
//        Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //Dialog.setTitle("Waiting for sync");
//        Dialog.setMessage("Loading...");
//        Dialog.setCancelable(false);
//        Dialog.show();
//    }
//
//    @Override
//    protected String doInBackground(Void... x) {
//
//        SoapObject request = new SoapObject(getApplicationContext().getResources().getString(R.string.NAMESPACE), getApplicationContext().getResources().getString(R.string.METHOD_NAME_SaveUploadDocument));
//
//        String ImageUri11;
//        if (ImageUri == null) {
//            ImageUri11 = "";
//        } else {
//            ImageUri11 = ImageUri.substring(ImageUri.lastIndexOf("/") + 1);
//        }
//
//        String fname = documentFileModel.getFileName();
//        String fID = documentFileModel.getFileID();
//
//        request.addPropertyIfValue("customerCode", buyer_code);
//        if(pdf_uploaded)
//            request.addPropertyIfValue("FileName", fname+".pdf");
//        else
//            request.addPropertyIfValue("FileName", fname+".jpg");
//        request.addPropertyIfValue("arryAttachment", encodedImage);
//        request.addPropertyIfValue("FileSize", "800");
//        request.addPropertyIfValue("Remarks", remarks);
//        request.addPropertyIfValue("FileID", fID);
//
//        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//        new MarshalBase64().register(envelope);
//        envelope.dotNet = true;
//        envelope.setOutputSoapObject(request);
//        // HttpTransportSE httpTransport = new HttpTransportSE(URL);
//        HttpTransportSE httpTransport = new HttpTransportSE(getApplicationContext().getResources().getString(R.string.URL));
//        try {
//            httpTransport.call(getApplicationContext().getResources().getString(R.string.SOAP_ACTION_SaveUploadDocument), envelope);
//
//            Object response = (Object) envelope.getResponse();
//            String gg = response.toString();
//
//            return "1";
//        } catch (Exception e) {
//            Log.d(TAG, "doInBackground: Error");
//            return "0";
//        }
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//        Dialog.dismiss();
//        Dialog = null;
//
//        if(result.equalsIgnoreCase("1")){
//            Toast.makeText(DocumentsUploadActivity.this, "Data Save successful.", Toast.LENGTH_SHORT).show();
//            pic_uploaded = false;
//            pdf_uploaded = false;
//            //img_file.setBackgroundResource(R.drawable.noimage);
//            img_file.setImageDrawable(getResources().getDrawable(R.drawable.noimage));
////
//            GetDocFileInfo getDocFileInfo = new GetDocFileInfo();
//            getDocFileInfo.execute();
//        } else {
//            Toast.makeText(DocumentsUploadActivity.this, "Data Save failed.", Toast.LENGTH_SHORT).show();
//        }
//    }
//}
//
//    private void selectImage() {
//        final CharSequence[] options = {
//                "Take Photo",
//                "Choose from Gallery or SD card",
//                "Choose PDF",
//                "Cancel"
//        };
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(
//                DocumentsUploadActivity.this);
//        builder.setTitle("Please select photo.");
//        builder.setItems(options, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int item) {
//                if (options[item].equals("Take Photo")) {
//                    openCamera();
//                } else if (options[item].equals("Choose from Gallery or SD card")) {
//                    showFileChooser();
//                }
//
//                else if (options[item].equals("Choose PDF")) {
//                    showPDFChooser();
//                }
//
//                else if (options[item].equals("Cancel")) {
//                    dialog.dismiss();
//                }
//            }
//        });
//
//        builder.show();
//
//    }
///*
//    private void openCamera(){
//        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if(pictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(pictureIntent,
//                    CAMERA_REQUEST);
//        }
//    }
//*/
//
//    /*
//        private void openCamera(){
//
//            Intent oCameraAppIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            imageNewname = String.valueOf(System.currentTimeMillis()) + ".jpg";
//            File f = new File(Environment.getExternalStorageDirectory() + "/Download/", imageNewname);
//
//            Uri photoURI = FileProvider.getUriForFile(DocumentsUploadActivity.this,
//                    BuildConfig.APPLICATION_ID + ".provider",
//                    f);
//            oCameraAppIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoURI);
//            startActivityForResult(oCameraAppIntent, CAMERA_REQUEST);
//        }
//      */
//    private void openCamera(){
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if(cameraIntent.resolveActivity(getPackageManager()) != null){
//            File imageFile = null;
//            try{
//                imageFile = getImageFile();
//            } catch (Exception ex){
//                ex.printStackTrace();
//            }
//
//            if(imageFile != null){
//                imageUri = FileProvider.getUriForFile(this, "com.example.android.fileprovider", imageFile);
//                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                startActivityForResult(cameraIntent, CAMERA_REQUEST);
//            }
//        }
//    }
//
//    private File getImageFile() throws Exception {
//        Log.i(TAG, "getImageFile: ");
//        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageName = "jpg_" + timestamp + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File imageFile = File.createTempFile(imageName, ".jpg", storageDir);
//        current_image_path = imageFile.getAbsolutePath();
//        return imageFile;
//    }
//
//    /*
//        private void openCamera(){
//            Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            if(pictureIntent.resolveActivity(getPackageManager()) != null) {
//                startActivityForResult(pictureIntent,
//                        CAMERA_REQUEST);
//            }
//        }
//    */
//    private void showFileChooser() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
//    }
//
//    private void showPDFChooser() {
//        Intent intent = new Intent();
//        intent.setType("application/pdf*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select PDF File"), PICK_PDF_REQUEST);
//    }
//
//    //handling the image chooser activity result
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            // Galary section
//            filePath = data.getData();
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                intOldWidth = bitmap.getWidth();
//                intOldHeight = bitmap.getHeight();
//
//                if (intOldWidth >= intOldHeight) {
//                    intMaxSide = intOldWidth;
//                } else {
//                    intMaxSide = intOldHeight;
//                }
//
//                if (intMaxSide > MaxSideSize) {
//
//                    // set new width and height
//                    double dblCoef = MaxSideSize / (double) intMaxSide;
//                    double doubleHeight = dblCoef * (double) intOldHeight;
//                    double doubleWidth = dblCoef * (double) intOldWidth;
//                    intNewHeight = (int) doubleHeight;
//                    intNewWidth = (int) doubleWidth;
//                    // intNewWidth = (int) dblCoef * intOldWidth;
//                    // intNewHeight = (int) dblCoef * intOldHeight;
//                } else {
//                    intNewWidth = intOldWidth;
//                    intNewHeight = intOldHeight;
//                }
//
//                Log.i("tag", "Height New>>>" + bitmap.getHeight());
//                Log.i("tag", "Height Width  New>>>" + bitmap.getWidth());
//
//                Bitmap resizeBitmap = Bitmap.createScaledBitmap(bitmap,
//                        intNewWidth, intNewHeight, false);
//
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                resizeBitmap
//                        .compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                b = baos.toByteArray();
//                long testSize = b.length;
//                Log.i("tag", "Size  New>>>" + testSize);
//                encodedImage =  org.kobjects.base64.Base64.encode(b);
//
//                img_file.setImageBitmap(bitmap);
//                pic_uploaded = true;
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Toast.makeText(getApplicationContext(), "PDF select successful.", Toast.LENGTH_SHORT).show();
//
//            img_file.setImageDrawable(getResources().getDrawable(R.drawable.pdficon));
//
//            filePath = data.getData();
//            getRealPathFromURIForPDF(filePath);
//        } else {
//            // camera section
//            try {
//                //    launchImageCrop(imageUri);
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
//                BackgroundImageResize backgroundImageResize = new BackgroundImageResize(bitmap);
//                backgroundImageResize.execute();
//
//                img_file.setImageBitmap(bitmap);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
//
//    private void launchImageCrop(Uri uri){
//        CropImage.activity(uri)
//                .setGuidelines(CropImageView.Guidelines.ON)
//                .setAspectRatio(1920, 1080)
//                .setCropShape(CropImageView.CropShape.RECTANGLE) // default is rectangle
//                .start(this);
//    }
//
//    private void imageProcessing() {
//        Log.i(TAG, "imageProcessing: ");
//
//
//    }
//
//    private String getRealPathFromURIForPDF(Uri contentUri) {
//        String[] proj = { MediaStore.Images.Media.DATA };
//        CursorLoader loader = new CursorLoader(DocumentsUploadActivity.this, contentUri, proj, null, null, null);
//        Cursor cursor = loader.loadInBackground();
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String result = cursor.getString(column_index);
//        File originalFile = new File(result);
//
//        String encodedBase64 = null;
//        try {
//            FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
//            byte[] bytes = new byte[(int) originalFile.length()];
//            fileInputStreamReader.read(bytes);
//            encodedBase64=Base64.encodeToString(bytes,Base64.NO_WRAP);
//            encodedImage = encodedBase64.toString();
//            pdf_uploaded = true;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        cursor.close();
//        return result;
//    }
//
//    public static void hideKeyboard(Activity activity) {
//        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
//        //Find the currently focused view, so we can grab the correct window token from it.
//        View view = activity.getCurrentFocus();
//        //If no view currently has focus, create a new one, just so we can grab a window token from it
//        if (view == null) {
//            view = new View(activity);
//        }
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }
//
//    private void showToast(String msg){
//        customAlertDialog.showDialog(DocumentsUploadActivity.this, msg, "onlyshow");
//        // Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//    }
//
//
///*******************************************************************/
//
//public class BackgroundImageResize extends AsyncTask<Uri, Integer, byte[]>{
//
//    Bitmap mBitmap;
//
//    public BackgroundImageResize(Bitmap bitmap) {
//        if(bitmap != null){
//            this.mBitmap = bitmap;
//        }
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        Toast.makeText(getApplicationContext(), "compressing image", Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    protected byte[] doInBackground(Uri... params) {
//
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        Log.i(TAG, "doInBackground: mBitmap Height" + mBitmap.getHeight());
//        Log.i(TAG, "doInBackground: mBitmap Width" + mBitmap.getWidth());
//        mBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
//        byte[] byteArray = stream.toByteArray();
//        Log.i(TAG, "doInBackground: mBitmap Height" + mBitmap.getHeight());
//        Log.i(TAG, "doInBackground: mBitmap Width" + mBitmap.getWidth());
//        encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
//        pic_uploaded = true;
//
//        return byteArray;
////
////            Log.d(TAG, "doInBackground: started.");
////            img_file.setImageBitmap(mBitmap);
////            ByteArrayOutputStream stream = new ByteArrayOutputStream();
////            byte[] byteArray = stream.toByteArray();
////            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
////
////            //byte[] byteArray = getBytesFromBitmap(bitmap, 100);
////
////            encodedImage = org.kobjects.base64.Base64.encode(byteArray);
////            pic_uploaded = true;
////            return byteArray;
//    }
//
//    @Override
//    protected void onPostExecute(byte[] bytes) {
//        super.onPostExecute(bytes);
//        Toast.makeText(getApplicationContext(), "complete", Toast.LENGTH_SHORT).show();
//    }
//}
//
//    public static byte[] getBytesFromBitmap(Bitmap bitmap, int quality){
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, quality,stream);
//        return stream.toByteArray();
//    }
//}
//
//
//
//
//
//     */
}

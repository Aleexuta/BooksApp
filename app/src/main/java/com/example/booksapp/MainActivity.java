package com.example.booksapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.booksapp.Books.Book;
import com.example.booksapp.Books.BookType;
import com.example.booksapp.Books.CoverType;
import com.example.booksapp.Books.IBook;
import com.example.booksapp.Books.Language;
import com.example.booksapp.Books.ReadFrom;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    DBManager dbManager;
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager=DBManager.getInstance();
        dbManager.setMain(this);
        FragmentManager manager = getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.fragmentLayout, FragmentList.newInstance(this),"LISTA").commit();
        //transaction.replace(R.id.fragmentLayout,new ListFragment(this)," ");
        //transaction.commit();


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK) {
                    assert data != null;
                    Uri path = data.getData();
                    try {
                        OpenExcelFile(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            break;
            case 20:
                if(resultCode==RESULT_OK)
                {
                    try {
                        assert data != null;
                        Uri uri=data.getData();

                        CreateExcelFile(uri);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    private void OpenExcelFile(Uri path) throws IOException {
        try {

            InputStream myInput;
            AssetManager assetManager = getAssets();
            myInput = getContentResolver().openInputStream(path);
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);

            Iterator<Row> rowIter = mySheet.rowIterator();
            if(rowIter.hasNext())
                rowIter.next();
            while (rowIter.hasNext()) {
                HSSFRow myRow = (HSSFRow) rowIter.next();
                createBook(myRow);
            }
        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private void createBook(HSSFRow myRow) {
        String title="", author="", genre="", language="",
                readfrom="", readdate="", cover="",
                publisher="", year="", purchasedate="", obs="";
        int type=0,totalpages=0, bookmark=0;
        float rat=0;
        boolean tr=false, tb=false, r=false, o=false, p=false;
        Iterator<Cell> cellIter = myRow.cellIterator();
        HSSFCell cell;
        for (int colno = 0; colno < DatabaseHelper.NO_COLUMNS; colno++) {
            cell = (HSSFCell) cellIter.next();
            if (colno == 0)
                type = Integer.parseInt(cell.toString());
            if (colno == 1)
                title = cell.toString();
            if (colno == 2)
                author = cell.toString();
            if (colno == 3)
                genre = cell.toString();
            if (colno == 4)
                obs = cell.toString();
            if (colno == 5)
                language = cell.toString();
            if (colno == 6)
                tr = cell.toString().equals("1");
            if (colno == 7)
                tb = cell.toString().equals("1");
            if (colno == 8)
                p = cell.toString().equals("1");
            if (colno == 9)
                r = cell.toString().equals("1");
            if (colno == 10)
                o = cell.toString().equals("1");
            if (colno == 11)
                cover = cell.toString();
            if (colno == 12)
                publisher = cell.toString();
            if (colno == 13)
                year = cell.toString();
            if (colno == 14)
                purchasedate = cell.toString();
            if (colno == 15)
                totalpages = Integer.parseInt(cell.toString());
            if (colno == 16)
                bookmark = Integer.parseInt(cell.toString());
            if (colno == 17)
                rat = Float.parseFloat(cell.toString());
            if (colno == 18)
                readfrom = cell.toString();
            if (colno == 19)
                readdate = cell.toString();
        }

            IBook book=null;
            if(type==0)
                book=Book.getSimpleBook(title,author,BookType.valueOf(genre),
                        obs,Language.valueOf(language),tr,tb);
            if(type==1)
                book=Book.getProgressBook(title,author,BookType.valueOf(genre),
                        obs,Language.valueOf(language),tr,tb,totalpages,bookmark);
            if(type==2)
                book=Book.getProgressReadBook(title,author,BookType.valueOf(genre),
                        obs,Language.valueOf(language),tr,tb,totalpages,bookmark,rat,ReadFrom.valueOf(readfrom),
                        readdate);
            if(type==3)
                book=Book.getReadBook(title,author,BookType.valueOf(genre),
                        obs,Language.valueOf(language),tr,tb,totalpages,rat,ReadFrom.valueOf(readfrom),readdate);
            if(type==4)
                book=Book.getOwnedBook(title,author,BookType.valueOf(genre),
                        obs,Language.valueOf(language),tr,tb,CoverType.valueOf(cover),publisher,
                        year,purchasedate);
            if(type==5)
                book=Book.getOwnedProgressBook(title,author,BookType.valueOf(genre),
                        obs,Language.valueOf(language),tr,tb,CoverType.valueOf(cover),publisher,
                        year,purchasedate,totalpages,bookmark);
            if(type==6)
                book=Book.getOwnedProgressReadBook(title,author,BookType.valueOf(genre),
                        obs,Language.valueOf(language),tr,tb,CoverType.valueOf(cover),publisher,
                        year,purchasedate,totalpages,bookmark,rat,ReadFrom.valueOf(readfrom),readdate);
            if(type==7)
                book=Book.getOwnedReadBook(title,author,BookType.valueOf(genre),
                        obs,Language.valueOf(language),tr,tb,CoverType.valueOf(cover),publisher,
                        year,purchasedate,totalpages,rat,ReadFrom.valueOf(readfrom),readdate);
            DBManager.getInstance().insert(book);

    }
    private void CreateExcelFile(Uri path){
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Java Books");

            int rowCount = 0;
            Cell cell;
            Row row;

            row=sheet.createRow(rowCount++);
            for (int i= 0; i < 20; i++)
            {
                cell = row.createCell(i);
                cell.setCellValue(DatabaseHelper._COLUMNS_NAME[i]);
            }
            Cursor cursor = DBManager.getInstance().loadAllBooks();
            if (cursor.moveToFirst()) {
                do {
                    row = sheet.createRow(rowCount++);
                    int columnCount;
                    for (columnCount = 0; columnCount < 20; columnCount++) {
                        cell = row.createCell(columnCount);
                        if(cursor.getString(columnCount)!=null)
                            cell.setCellValue((String) cursor.getString(columnCount));
                        else
                            cell.setCellValue("0");
                    }
                } while (cursor.moveToNext());
            }
            OutputStream outputStream = getContentResolver().openOutputStream(path);
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


}
package com.example.expensetracker_adityatrivedi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class EmployeeDatabase extends SQLiteOpenHelper {

    public EmployeeDatabase(Context context) {
        super(context, "Emp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table EmpDetails(empname TEXT, exptype TEXT, expamt REAL, expdesc TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists EmpDetails");
    }

    public Boolean addExpData(String empname, String exptype, double expamt, String expdesc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("empname", empname);
        contentValues.put("exptype", exptype);
        contentValues.put("expamt", expamt);
        contentValues.put("expdesc", expdesc);
        long result=db.insert("EmpDetails",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Boolean updateExpData(String empname, String exptype, double expamt, String expdesc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("expdesc", expdesc);

        String whereClause = "empname=? AND exptype=? AND expamt=?";
        String[] whereArgs = {empname, exptype, String.valueOf(expamt)};

        Cursor cursor = db.rawQuery("SELECT * FROM EmpDetails WHERE empname = ? AND exptype = ? AND expamt = ?", whereArgs);

        if (cursor.getCount() > 0) {
            long result = db.update("EmpDetails", contentValues, whereClause, whereArgs);
            return result != -1;
        } else {
            return false;
        }
    }



    public Cursor SumExpAmtAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT empname, SUM(expamt) AS totalExpAmt FROM EmpDetails GROUP BY empname";
        return db.rawQuery(query, null);
    }
    public Cursor SumExpAmtEmp(String empname) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT empname, SUM(expamt) AS totalExpAmt FROM EmpDetails WHERE empname = ?";
        String[] whereArgs = {empname};
        return db.rawQuery(query, whereArgs);
    }
    public Boolean removeExpenseForEmployee(String empname, String exptype, double expamt) {
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = "empname=? AND exptype=? AND expamt=?";
        String[] whereArgs = {empname, exptype, String.valueOf(expamt)};

        long result = db.delete("EmpDetails", whereClause, whereArgs);

        return result != -1;
    }
    public Cursor getLatestExpenseForEmployee(String empname) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM EmpDetails WHERE empname = ? ORDER BY ROWID DESC LIMIT 1";
        String[] whereArgs = {empname};

        return db.rawQuery(query, whereArgs);
    }

    public Cursor getEmployeeExpenses(String empname) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM EmpDetails WHERE empname = ?";
        String[] whereArgs = {empname};

        return db.rawQuery(query, whereArgs);
    }

    public Cursor getAllExpensesForEmployee(String empname) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM EmpDetails WHERE empname = ?";
        String[] whereArgs = {empname};

        return db.rawQuery(query, whereArgs);
    }
    public double getTotalExpenseAmountForEmployee(String empname) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT SUM(expamt) AS totalExpAmt FROM EmpDetails WHERE empname = ?";
        String[] whereArgs = {empname};

        Cursor cursor = db.rawQuery(query, whereArgs);

        double totalExpAmt = 0;

        if (cursor.moveToFirst()) {
            totalExpAmt = cursor.getDouble(cursor.getColumnIndex("totalExpAmt"));
        }

        cursor.close();
        return totalExpAmt;
    }
    public double getTotalExpenseAmountForAllEmployees() {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT SUM(expamt) AS totalExpAmt FROM EmpDetails";

        Cursor cursor = db.rawQuery(query, null);

        double totalExpAmt = 0;

        if (cursor.moveToFirst()) {
            totalExpAmt = cursor.getDouble(cursor.getColumnIndex("totalExpAmt"));
        }

        cursor.close();
        return totalExpAmt;
    }

}

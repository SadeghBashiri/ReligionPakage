package co.religionpakage;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import co.religionpakage.utils.Gen;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final String queryCreateTable_Login =
            "CREATE TABLE TaLogIn (Id_User INTEGER PRIMARY KEY AUTOINCREMENT,Post TEXT,Username TEXT ,Password TEXT,LastLogin INTEGER)";

    private static final String queryCreateTable_Tanzimat =
            "CREATE TABLE TaTanzimat(Id_Tanzimat INTEGER PRIMARY KEY AUTOINCREMENT,Model TEXT ,Time_Last_Pause TEXT,Timer_Lock TEXT,Password TEXT)";

    private static final String queryCreateTable_Others =
            "CREATE TABLE TaOthers(Id_Others INTEGER PRIMARY KEY AUTOINCREMENT,Year_Summery TEXT)";

    public DataBaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL (queryCreateTable_Login);
        db.execSQL (queryCreateTable_Tanzimat);
        db.execSQL (queryCreateTable_Others);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //baraye taghir karbar
    public void updateLastLogin() {
        String queryUpdateLastLogin = "UPDATE TaLogIn SET LastLogin = 0 ";
        SQLiteDatabase dbWrite = this.getWritableDatabase (); // For Write In DB
        dbWrite.execSQL (queryUpdateLastLogin); // Last login All User = 0
        dbWrite.close ();
    }

    public void insertUser_TaLogIn(String username, String password) {
        String queryUpdateLastLogin = "UPDATE TaLogIn SET LastLogin = 0 ";
        //Check Exist User
        if (CheckIsUserNotAlreadyIn_TaLogIn (username, password)) {
            String queryInsertUserDB = "INSERT INTO TaLogIn(Username,Password,LastLogin) VALUES('" + username + "','" + password + "',1)";
            SQLiteDatabase dbWrite = this.getWritableDatabase (); // For Write In DB
            dbWrite.execSQL (queryUpdateLastLogin); // Last login All User = 0
            dbWrite.execSQL (queryInsertUserDB); // Inset In DB this User And Last Login = 1
            dbWrite.close ();
        } else {
            String updateLastLogin = "UPDATE TaLogIn SET LastLogin = 1 WHERE Username = '" + username + "' AND Password ='" + password + "' ";
            SQLiteDatabase dbWrite = this.getWritableDatabase ();
            dbWrite.execSQL (queryUpdateLastLogin); // Last login All User = 0
            dbWrite.execSQL (updateLastLogin);  //Update Last login Now User = 1
            dbWrite.close ();
        }
    }

    // check mikone user vared shode dar database hast ya na, baraye function 'insertUser' estefadeh mishe
    public boolean CheckIsUserNotAlreadyIn_TaLogIn(String username, String password) {
        SQLiteDatabase dbRead = this.getReadableDatabase (); // For Write In DB
        Cursor cursor = dbRead.rawQuery ("SELECT * FROM  TaLogIn WHERE Username = '" + username + "' AND Password ='" + password + "'", null);
        if (cursor.getCount () <= 0) {
            cursor.close ();
            dbRead.close ();
            return true;
        }
        cursor.close ();
        dbRead.close ();
        return false;
    }

    // password ro bar asas username va connection peyda mikone va barmigardone ,vase 'loginActivity' estefade mishe
    public String getPassword(String username) {
        String password = "";
        SQLiteDatabase dbRead = this.getReadableDatabase ();
        Cursor cursor = dbRead.rawQuery ("SELECT Password FROM  TaLogIn WHERE Username = 'username'", null);
        if (cursor.getCount () > 0 && cursor.moveToFirst ()) {
            // if (cursor.moveToFirst ()) {
            password = cursor.getString (0);
            cursor.close ();
            dbRead.close ();
            return password;
            // }
        }
        cursor = dbRead.rawQuery ("SELECT Password FROM  TaLogIn WHERE Username = '" + username + "' ", null);
        if (cursor.getCount () > 0 && cursor.moveToFirst ()) {
            // if (cursor.moveToFirst ()) {
            password = cursor.getString (0);
            cursor.close ();
            dbRead.close ();
            return password;
            // }
        }
        cursor.close ();
        dbRead.close ();
        return password;
    }

    public int getId_User() {
        int idUser = 0;
        SQLiteDatabase dbRead = this.getReadableDatabase ();
        Cursor cursor = dbRead.rawQuery ("SELECT Id_User FROM  TaLogIn WHERE LastLogin = 1 ", null);
        if (cursor.getCount () > 0 && cursor.moveToFirst ()) {
            idUser = cursor.getInt (0);
            cursor.close ();
            dbRead.close ();
            return idUser;
        }

        cursor.close ();
        dbRead.close ();
        return idUser;
    }

    // Select All Username In Table TaLogIn In DB ,vase 'loginActivity' estefade mishe
    public String[] SelectAllDataUser() {
        String arrData[] = {""};
        SQLiteDatabase dbRead = this.getReadableDatabase ();
        Cursor cursor = dbRead.rawQuery ("SELECT Username FROM TaLogIn", null);
        if (cursor.getCount () > 0 && cursor.moveToFirst ()) {
            //  if (cursor.moveToFirst ()) {
            arrData = new String[cursor.getCount ()];
            int i = 0;
            do {
                arrData[i] = cursor.getString (0);
                i++;
            } while (cursor.moveToNext ());
            //  }
        }
        cursor.close ();
        dbRead.close ();
        return arrData;
    }

    // Select All Connection In Table TaLogIn In DB ,vase 'loginActivity' estefade mishe
    public String[] SelectAllDataConnection() {
        String arrData[] = {""};
        SQLiteDatabase dbRead = this.getReadableDatabase ();
        Cursor cursor = dbRead.rawQuery ("SELECT Connection FROM TaLogIn", null);
        if (cursor.getCount () > 0 && cursor.moveToFirst ()) {
            //  if (cursor.moveToFirst ()) {
            arrData = new String[cursor.getCount ()];
            int i = 0;
            do {
                arrData[i] = cursor.getString (0);
                i++;
            } while (cursor.moveToNext ());
            //  }
        }
        cursor.close ();
        dbRead.close ();
        return arrData;
    }

//    public ModelInformationUser getUserPassConn() {
//        String username = "", password = "", connection = "";
//        //  { "username","password","connection"};
//        SQLiteDatabase dbRead = this.getReadableDatabase ();
//        Cursor cursor = dbRead.rawQuery ("SELECT Username,Password,Connection FROM  TaLogIn WHERE LastLogin = 1 ", null);
//        if (cursor.getCount () > 0 && cursor.moveToFirst ()) {
//            // if (cursor.moveToFirst ()) {
//            username = cursor.getString (0);
//            password = cursor.getString (1);
//            connection = cursor.getString (2);
//            cursor.close ();
//            dbRead.close ();
//            return new ModelInformationUser (username, password, connection);
//            // }
//        }
//        return new ModelInformationUser (username, password, connection);
//    }

    public void deleteTanzimat() {
        String queryDeleteRow = "DELETE FROM TaTanzimat";
        SQLiteDatabase dbWrite = this.getWritableDatabase (); // For Write In DB
        dbWrite.execSQL (queryDeleteRow);
        dbWrite.close ();
    }

    public void insertPasswordApps(String model, String time_Lock) {
        deleteTanzimat ();
        String queryInsertDB = "INSERT INTO TaTanzimat(Model,Timer_Lock,Password) " +
                "VALUES('" + model + "','" + time_Lock + "','0')";
        SQLiteDatabase dbWrite = this.getWritableDatabase (); // For Write In DB
        dbWrite.execSQL (queryInsertDB);
        dbWrite.close ();
        Gen.log("دیتابیس");
    }


    public void updatePasswordApps(String password) {
        String queryUpdate = "UPDATE TaTanzimat SET Password = '" + password + "' ";
        SQLiteDatabase dbWrite = this.getWritableDatabase (); // For Write In DB
        dbWrite.execSQL (queryUpdate); // Last login All User = 0
        dbWrite.close ();
    }

    public String getPasswordApps() {
        String password = "0";
        SQLiteDatabase dbRead = this.getReadableDatabase ();
        Cursor cursor = dbRead.rawQuery ("SELECT Password FROM  TaTanzimat ", null);
        if (cursor.getCount () > 0 && cursor.moveToFirst ()) {
            password = cursor.getString (0);
            cursor.close ();
            dbRead.close ();
            return password;
        }

        cursor.close ();
        dbRead.close ();
        return password;
    }

    public void updateTimerPauseApps() {
        String queryUpdate = "UPDATE TaTanzimat SET time_Last_Pause = '" + System.currentTimeMillis () + "' ";
        SQLiteDatabase dbWrite = this.getWritableDatabase (); // For Write In DB
        dbWrite.execSQL (queryUpdate); // Last login All User = 0
        dbWrite.close ();
    }

    public void updateTimerLockApps(String timer) {
        String queryUpdate = "UPDATE TaTanzimat SET Timer_Lock = '" + timer + "' ";
        SQLiteDatabase dbWrite = this.getWritableDatabase (); // For Write In DB
        dbWrite.execSQL (queryUpdate); // Last login All User = 0
        dbWrite.close ();
    }

    public boolean checkLockApps() {
        String timePause = "0";
        long diff = 0;
        float Second = 0;
        SQLiteDatabase dbRead = this.getReadableDatabase ();
        Cursor cursor = dbRead.rawQuery ("SELECT time_Last_Pause,Timer_Lock FROM  TaTanzimat ", null);
        if (cursor.getCount () > 0 && cursor.moveToFirst ()) {
            if ((cursor.getString (0)) != null && !(cursor.getString (0)).isEmpty ()) {
                timePause = cursor.getString (0);
            }
            diff = (System.currentTimeMillis () - Long.parseLong (timePause));
            Second = (float) (diff / (1000));
            if (Second > (Float.valueOf (cursor.getString (1)) * 60)) {
                return false;
            }
            cursor.close ();
            dbRead.close ();
            return true;
        }
        cursor.close ();
        dbRead.close ();
        return true;
    }

    public boolean checkLockAppsComplete() {
        String password = "0";
        SQLiteDatabase dbRead = this.getReadableDatabase ();
        Cursor cursor = dbRead.rawQuery ("SELECT Password FROM  TaTanzimat ", null);
        if (cursor.getCount () > 0 && cursor.moveToFirst ()) {
            password = cursor.getString (0);
            if (password.equals ("0")) {
                deleteTanzimat ();
                return false;
            }
            cursor.close ();
            dbRead.close ();
            return true;
        }
        cursor.close ();
        dbRead.close ();
        return false;
    }

    public String getModelLockerApps() {
        String model = "0";
        SQLiteDatabase dbRead = this.getReadableDatabase ();
        Cursor cursor = dbRead.rawQuery ("SELECT Model FROM  TaTanzimat ", null);
        if (cursor.getCount () > 0 && cursor.moveToFirst ()) {
            model = cursor.getString (0);
            cursor.close ();
            dbRead.close ();
            return model;
        }

        cursor.close ();
        dbRead.close ();
        return model;
    }

    public int getTimerLockApps() {
        String timer = "0";
        int timerPosatin = 1;
        SQLiteDatabase dbRead = this.getReadableDatabase ();
        Cursor cursor = dbRead.rawQuery ("SELECT Timer_Lock FROM  TaTanzimat ", null);
        if (cursor.getCount () > 0 && cursor.moveToFirst () && (cursor.getString (0)) != null && !(cursor.getString (0)).isEmpty ()) {
            timer = cursor.getString (0);
            //   {"0.5", "1", "1.5", "2", "3","4","5"};
            switch (timer) {
                case "0.5":
                    timerPosatin = 0;
                    break;
                case "1":
                    timerPosatin = 1;
                    break;
                case "1.5":
                    timerPosatin = 2;
                    break;
                case "02":
                    timerPosatin = 3;
                    break;
                case "3":
                    timerPosatin = 4;
                    break;
                case "4":
                    timerPosatin = 5;
                    break;
                case "5":
                    timerPosatin = 6;
                    break;
            }
            cursor.close ();
            dbRead.close ();
            return timerPosatin;
        }

        cursor.close ();
        dbRead.close ();
        return timerPosatin;
    }
}

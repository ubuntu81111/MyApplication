package com.example.ali.myapplication;

import android.content.Context;

import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

/**
 * Created by ali on 24-May-17.
 */

public class Trancatedb {

    public static void closedb(Context context){
        SugarContext.terminate();
        SchemaGenerator schemaGenerator = new SchemaGenerator(context);
        schemaGenerator.deleteTables(new SugarDb(context).getDB());
        SugarContext.init(context);
        schemaGenerator.createDatabase(new SugarDb(context).getDB());

    }



}

package fr.toulouse.iadata.datafetcher.services;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class JsonArraySplitterPrepareDatasService implements IPrepareDatasService
{
    @Override
    public List<String> prepare(String strDatas)
    {
        List<String> listDatas = new ArrayList<>();
        JSONArray jArray = new JSONArray( strDatas );
        if ( jArray != null ) {
            for (int i=0;i<jArray.length();i++){
                listDatas.add(jArray.get( i ).toString());
            }
        }
        return listDatas;
    }
}

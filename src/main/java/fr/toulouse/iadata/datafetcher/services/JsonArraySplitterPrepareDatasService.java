package fr.toulouse.iadata.datafetcher.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

import java.io.IOException;
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

    @Override
    public List<String> getListId(String strData) {

        List<String> identifierList = new ArrayList<>();
        List<String> DataList = prepare(strData);
        for (String json : DataList) {

            try {
                JsonNode jsonNode = new ObjectMapper().readTree(json);
                identifierList.add(jsonNode.get("id").textValue());

            } catch(IOException e){
                e.printStackTrace();
            }
        }
        return identifierList;
    }
}

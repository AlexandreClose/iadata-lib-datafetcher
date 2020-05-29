package fr.toulouse.iadata.datafetcher.services;

import java.util.List;

public interface IPrepareDatasService
{
    List<String> prepare(String strDatas);
    List<String> getListId(String strData) ;

}

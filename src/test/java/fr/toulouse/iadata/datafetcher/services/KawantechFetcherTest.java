package fr.toulouse.iadata.datafetcher.services;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KawantechFetcherTest
{
    @Autowired
    private FetcherService _fetcherService;

    @Test
    public void testFetch( )
    {
        List<String> listDatas = _fetcherService.fetch();

        Assertions.assertThat( listDatas ).isNotEmpty();

    }
}
package com.xu.search.service;


import com.xu.common.TO.es.WorksEsModel;
import com.xu.search.vo.SearchParam;
import com.xu.search.vo.SearchResult;

import java.io.IOException;

public interface MallSearchService {


    Boolean WorksUp(WorksEsModel worksEsModels) throws IOException;

    SearchResult search(SearchParam searchParam);

}

package com.neoye.rms.domain.infrastructure.vo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import rms.cmpnt.libs.commons.util.StringUtil;

import com.neoye.rms.domain.infrastructure.constant.SQLConstant;

public class ConditionVO {

    private Map<String, Object> keywordsMap;

    private Map<String, Object> filtersMap;

    private Map<String, Object> filtersInMap;

    private Map<String, Object> fuzzyMap;

    private Map<String, Object> sortsMap;

    private Map<String, Object> compareGraEquMap;

    private Map<String, Object> compareGraMap;

    private Map<String, Object> compareLesEquMap;

    private Map<String, Object> compareLesMap;
    
    private String whereSql;

    public String getWhereSql() {
        return whereSql;
    }

    public void setWhereSql(String whereSql) {
        this.whereSql = whereSql;
    }

    public Map<String, Object> getSortsMap() {
        return sortsMap;
    }

    public void setSortsMap(Map<String, Object> sortsMap) {
        this.sortsMap = sortsMap;
        this.assembleSortsSql();
    }

    public Map<String, Object> getCompareGraEquMap() {
        return compareGraEquMap;
    }

    public void setCompareGraEquMap(Map<String, Object> compareGraEquMap) {
        this.compareGraEquMap = compareGraEquMap;
        this.assembleCompareGreEquSql();
    }

    public Map<String, Object> getCompareGraMap() {
        return compareGraMap;
    }

    public void setCompareGraMap(Map<String, Object> compareGraMap) {
        this.compareGraMap = compareGraMap;
        this.assembleCompareGreSql();
    }

    public Map<String, Object> getCompareLesEquMap() {
        return compareLesEquMap;
    }

    public void setCompareLesEquMap(Map<String, Object> compareLesEquMap) {
        this.compareLesEquMap = compareLesEquMap;
        this.assembleCompareLessEquSql();
    }

    public Map<String, Object> getCompareLesMap() {
        return compareLesMap;
    }

    public void setCompareLesMap(Map<String, Object> compareLesMap) {
        this.compareLesMap = compareLesMap;
        this.assembleCompareLessSql();
    }

    public Map<String, Object> getKeywordsMap() {
        return keywordsMap;
    }

    public void setKeywordsMap(Map<String, Object> KeywordsMap) {
        this.keywordsMap = KeywordsMap;
        this.assembleKeywordSql();
    }

    public Map<String, Object> getFiltersMap() {
        return filtersMap;
    }

    public void setFiltersMap(Map<String, Object> filterMap) {
        this.filtersMap = filterMap;
        this.assembleFiltersSql();
    }

    public Map<String, Object> getFiltersInMap() {
        return filtersInMap;
    }

    public void setFiltersInMap(Map<String, Object> inMap) {
        this.filtersInMap = inMap;
        this.assembleFilters4KeywordInSql();
    }

    public Map<String, Object> getFuzzyMap() {
        return fuzzyMap;
    }

    public void setFuzzyMap(Map<String, Object> fuzzyMap) {
        this.fuzzyMap = fuzzyMap;
        // TODO
    }

    private String KeywordsSql = null;

    private String filtersSql = null;

    private String sortSql = null;

    private String fuzzySql = null;

    private String filterInSql = null;

    private String compareGreEquSql = null;

    private String compareGreSql = null;

    private String compareLesEquSql = null;

    private String compareLesSql = null;

    private int page = 0;

    private int record4Page = 50;

    private int rn = 0;

    private String defNo = null;

    public String getCompareGreEquSql() {
        return compareGreEquSql;
    }

    public void setCompareGreEquSql(String compareGreEquSql) {
        this.compareGreEquSql = compareGreEquSql;
    }

    public String getCompareGreSql() {
        return compareGreSql;
    }

    public void setCompareGreSql(String compareGreSql) {
        this.compareGreSql = compareGreSql;
    }

    public String getCompareLesEquSql() {
        return compareLesEquSql;
    }

    public void setCompareLesEquSql(String compareLesEquSql) {
        this.compareLesEquSql = compareLesEquSql;
    }

    public String getCompareLesSql() {
        return compareLesSql;
    }

    public void setCompareLesSql(String compareLesSql) {
        this.compareLesSql = compareLesSql;
    }

    /**
     * 在字典表中，当前表单支持的全模糊查询字段，对应的字典TYPE
     */
    private String fuzzyDicTypeKey;

    public String getFuzzyDicTypeKey() {
        return fuzzyDicTypeKey;
    }

    public void setFuzzyDicTypeKey(String fuzzyDicTypeKey) {
        this.fuzzyDicTypeKey = fuzzyDicTypeKey;
    }

    public String getKeywordsSql() {
        return KeywordsSql;
    }

    public void setKeywordsSql(String KeywordsSql) {
        this.KeywordsSql = KeywordsSql;
    }

    public String getFiltersql() {
        return filtersSql;
    }

    public void setFiltersSql(String FiltersSql) {
        this.filtersSql = FiltersSql;
    }

    public String getSortsSql() {
        return sortSql;
    }

    public void setSortsSql(String sortsSql) {
        this.sortSql = sortsSql;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        if (page == -1) {// 不分页
            this.record4Page = SQLConstant.RECORD_4_PAGE_LIMIIT_NO_PAGE_KEY;
            this.rn = 0;
        }
        else if (page == 0) 
        {
            this.rn = 0;
        }
        else {
            this.rn = (page - 1) * this.record4Page;
        }
    }

    public int getRecord4Page() {
        return record4Page;
    }

    public void setRecord4Page(int record4Page) {
        this.record4Page = record4Page;
    }

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

    public String getDefNo() {
        return defNo;
    }

    public void setDefNo(String defNo) {
        this.defNo = defNo;
    }

    public String getFuzzySql() {
        return fuzzySql;
    }

    public void setFuzzySql(String fuzzySql) {
        this.fuzzySql = fuzzySql;
    }

    public String getInSql() {
        return filterInSql;
    }

    public void setInSql(String inSql) {
        this.filterInSql = inSql;
    }

    public void buildConditionSql() {
        this.assembleSortsSql();
        // 处理关键字搜索条件
        this.assembleKeywordSql();

        // 处理过滤条件
        this.assembleFiltersSql();

        // 处理过滤条件中存在In的条件

        this.assembleFilters4KeywordInSql();

        // 处理模糊检索条件
        // this.assembleFuzzySql(condition);

        // 处理比较条件 Key >、<、>=、<=
        this.assembleCompareGreEquSql();

        this.assembleCompareGreSql();
        this.assembleCompareLessEquSql();
        this.assembleCompareLessSql();

        // 处理每页显示的条数
        // this.assembleRecord4Page();
    }

    public ConditionVO assembleCondition(Map condition) {
        // ConditionVO cVO = new ConditionVO();

        // assemble
        this.assembleDefNo(condition);

        this.assembleSorts(condition);

        // 处理关键字搜索条件
        this.assembleKeyword(condition);

        // 处理过滤条件
        this.assembleFilters(condition);

        // 处理过滤条件中存在In的条件
        this.assembleFilters4KeywordIn(condition);

        // 处理模糊检索条件
        this.assembleFuzzy(condition);

        // 处理比较条件 Key >、<、>=、<=
        this.assembleCompareGreEquMap(condition);
        this.assembleCompareGreMap(condition);
        this.assembleCompareLessEquMap(condition);
        this.assembleCompareLessMap(condition);

        // 处理每页显示的条数
        this.assembleRecord4Page(condition);

        // 处理页码，第几页 page,rn
        this.assembleRecordPage(condition);
        return this;
    }

    private void assembleRecordPage(Map condition) {
        try {
            page = (Integer) condition.get(SQLConstant.PAGE_KEY);
        }
        catch (Exception e2) {
            page = -1;
        }
        this.setPage(page);
    }

    private void assembleRecord4Page(Map condition) {
        Object objRecord4Page = condition.get(SQLConstant.RECORD_4_PAGE_KEY);
        int record4Page = 0;
        if (objRecord4Page != null && objRecord4Page instanceof Integer) {
            record4Page = (Integer) objRecord4Page;
        }
        else {
            record4Page = 50;
        }
        this.setRecord4Page(record4Page);
    }

    private void assembleDefNo(Map condition) {
        String defNo = (String) condition.get(SQLConstant.DEF_NO_KEY);
        if (defNo != null && !defNo.equalsIgnoreCase("null") && !defNo.equalsIgnoreCase("")) {
            this.setDefNo(defNo);
        }
        else {
            this.setDefNo("0");
        }
    }

    private List getList4Flex(Object obj) {
        Object[] objArr = null;

        List<String> result = new ArrayList<String>();
        if (obj != null) {
            objArr = (Object[]) obj;
            for (int i = 0; i < objArr.length; i++) {
                result.add(objArr[i].toString());
            }
        }
        return result;
    }

    private String assembleFuzzy(Map condition) {
        String result = "";
        String fuzzy = (String) condition.get(SQLConstant.FUZZY_KEY);
        if (fuzzy == null || fuzzy.trim().equalsIgnoreCase("")) {
            return "";
        }
        /*
         * TODO marked by liuxy 2014-11-28 待处理完DIC类再开放此部分代码 StringBuffer
         * fuzzySql = new StringBuffer(); DicDataDTO dicDataVOParam = new
         * DicDataDTO(); dicDataVOParam
         * .setDicType(SQLConstant.FUZZY_SEARCH_FIELDS_DIC_TYPE_KEY);
         * List<DicDataDTO> dicDataVOs = dicDataFacade
         * .getDicData4List(dicDataVOParam);
         * fuzzySql.append(SQLConstant.SQL_KEYWORD_AND_KEY);
         * fuzzySql.append(SQLConstant.SPACE_KEY);
         * fuzzySql.append(SQLConstant.SYMBOL_LEFT_PARENTHESIS_KEY); for
         * (DicDataDTO dicDataVO : dicDataVOs) {
         * fuzzySql.append(dicDataVO.getItemValue());
         * fuzzySql.append(SQLConstant.SPACE_KEY);
         * fuzzySql.append(SQLConstant.SQL_KEYWORD_Keywords_KEY);
         * fuzzySql.append(SQLConstant.SPACE_KEY);
         * fuzzySql.append(SQLConstant.SYMBOL_SINGLE_QUOTATION_MARKS_KEY);
         * fuzzySql.append(SQLConstant.SQL_SYMBOL_PERCENTAGE_KEY);
         * fuzzySql.append(fuzzy);
         * fuzzySql.append(SQLConstant.SQL_SYMBOL_PERCENTAGE_KEY);
         * fuzzySql.append(SQLConstant.SYMBOL_SINGLE_QUOTATION_MARKS_KEY);
         * fuzzySql.append(SQLConstant.SPACE_KEY);
         * fuzzySql.append(SQLConstant.SQL_KEYWORD_OR_KEY);
         * fuzzySql.append(SQLConstant.SPACE_KEY); } result =
         * fuzzySql.toString(); result = result.substring(0, result.length() -
         * 3); result = result + SQLConstant.SYMBOL_RIGHT_PARENTHESIS_KEY; //
         * fuzzySql.append(SQLConstant.SYMBOL_RIGHT_PARENTHESIS_KEY);
         */
        return result;
    }

    private void assembleKeyword(Map condition) {
        this.setKeywordsMap((Map<String, Object>) condition.get(SQLConstant.KEYWORDS_KEY));
    }

    private String assembleKeywordSql() {
        if (keywordsMap == null || keywordsMap.size() == 0)
            return null;
        String result = "";
        StringBuffer KeywordsSql = new StringBuffer();
        Iterator it = this.keywordsMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (entry.getValue() != null && !entry.getValue().toString().equals("")
                    && !entry.getValue().toString().equals("null")) {
                KeywordsSql.append(SQLConstant.SPACE_KEY);
                KeywordsSql.append(SQLConstant.SQL_KEYWORD_AND_KEY);
                KeywordsSql.append(SQLConstant.SPACE_KEY);
                KeywordsSql.append(SQLConstant.SQL_KEYWORD_LOWER_KEY);
                KeywordsSql.append(SQLConstant.SYMBOL_LEFT_PARENTHESIS_KEY);
                KeywordsSql.append(StringUtil.camel2Underline((String) entry.getKey()));
                KeywordsSql.append(SQLConstant.SYMBOL_RIGHT_PARENTHESIS_KEY);
                KeywordsSql.append(SQLConstant.SPACE_KEY);
                KeywordsSql.append(SQLConstant.SQL_KEYWORD_LIKE_KEY);
                KeywordsSql.append(SQLConstant.SPACE_KEY);
                KeywordsSql.append(SQLConstant.SYMBOL_SINGLE_QUOTATION_MARKS_KEY);
                KeywordsSql.append(SQLConstant.SQL_SYMBOL_PERCENTAGE_KEY);
                KeywordsSql.append(entry.getValue().toString().toLowerCase());
                KeywordsSql.append(SQLConstant.SQL_SYMBOL_PERCENTAGE_KEY);
                KeywordsSql.append(SQLConstant.SYMBOL_SINGLE_QUOTATION_MARKS_KEY);
            }
        }
        result = KeywordsSql.toString();
        this.KeywordsSql = result;
        return result;
    }

    private void assembleSorts(Map condition) {
        this.setSortsMap((Map) condition.get(SQLConstant.SORTS_KEY));
    }

    private void assembleSortsSql() {
        if (sortsMap == null || sortsMap.size() == 0) {
            this.sortSql = null;
        }
        else {
            String result = "";
            StringBuffer sortsSqlSb = new StringBuffer();
            Iterator it = sortsMap.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (entry.getValue() == null) {
                    continue;
                }
                if (!entry.getValue().toString().equals("-1")) {
                    sortsSqlSb.append(StringUtil.camel2Underline((String) entry.getKey()));
                    sortsSqlSb.append(SQLConstant.SPACE_KEY);
                    sortsSqlSb.append(entry.getValue());
                    sortsSqlSb.append(SQLConstant.SYMBOL_COMMA_KEY);
                }
            }
            result = sortsSqlSb.toString();
            result = result.substring(0, result.length() - 1);
            this.sortSql = result;
        }
    }

    /*
     * private String assembleSorts4Summary(Map condition) { String result = "";
     * StringBuffer orderSql = new StringBuffer(); // 处理前台传入的排序条件 Map sorts =
     * (Map) condition.get(SQLConstant.SORTS_KEY); if (sorts == null ||
     * sorts.size() == 0) { return orderSql.toString(); } Iterator it =
     * sorts.entrySet().iterator(); while (it.hasNext()) { Entry entry = (Entry)
     * it.next(); if (!entry.getValue().toString().equals("-1")) {
     * orderSql.append(SQLConstant.SYMBOL_COMMA_KEY);
     * orderSql.append(StringUtil.camel2Underline((String) entry .getKey()));
     * orderSql.append(SQLConstant.SPACE_KEY);
     * orderSql.append(entry.getValue()); } } result = orderSql.toString();
     * return result; }
     */

    private void assembleFilters(Map condition) {
        this.setFiltersMap((Map) condition.get(SQLConstant.FILTERS_KEY));
    }

    private void assembleFiltersSql() {
        if (filtersMap == null || filtersMap.size() == 0)
            this.filtersSql = null;
        else {
            String result = "";
            StringBuffer equalSql = new StringBuffer();
            Iterator it = filtersMap.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (entry.getValue() != null && !entry.getValue().toString().equals("")
                        && !entry.getValue().toString().equals("null")) {
                    equalSql.append(SQLConstant.SPACE_KEY);
                    equalSql.append(SQLConstant.SQL_KEYWORD_AND_KEY);
                    equalSql.append(SQLConstant.SPACE_KEY);
                    equalSql.append(StringUtil.camel2Underline((String) entry.getKey()));
                    equalSql.append(SQLConstant.SQL_KEYWORD_EQUAL_KEY);
                    equalSql.append(SQLConstant.SYMBOL_SINGLE_QUOTATION_MARKS_KEY);
                    equalSql.append(entry.getValue());
                    equalSql.append(SQLConstant.SYMBOL_SINGLE_QUOTATION_MARKS_KEY);
                }
            }

            result = equalSql.toString();
            this.filtersSql = result;
        }
    }

    private void assembleFilters4KeywordIn(Map condition) {
        this.setFiltersInMap((Map) condition.get(SQLConstant.FILTERS_IN_KEY));
    }

    private void assembleFilters4KeywordInSql() {
        if (filtersInMap == null || filtersInMap.size() == 0) {
            this.filterInSql = null;
        }
        else {
            // and column1 in ('5','6') and column2 in ('9','10')
            String result = "";
            StringBuffer inSql = new StringBuffer();
            Iterator it = filtersInMap.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (entry.getValue() != null && !entry.getValue().toString().equals("")
                        && !entry.getValue().toString().equals("null")) {

                    inSql.append(SQLConstant.SPACE_KEY);
                    inSql.append(SQLConstant.SQL_KEYWORD_AND_KEY);
                    inSql.append(SQLConstant.SPACE_KEY);
                    inSql.append(StringUtil.camel2Underline((String) entry.getKey()));
                    inSql.append(SQLConstant.SPACE_KEY);
                    inSql.append(SQLConstant.SQL_KEYWORD_IN_KEY);
                    inSql.append(SQLConstant.SYMBOL_LEFT_PARENTHESIS_KEY);
                    List<String> inItemList = null;
                    if (!(entry.getValue() instanceof List))
                        inItemList = getList4Flex(entry.getValue());
                    else
                        inItemList = (List<String>) entry.getValue();
                    for (int i = 0; i < inItemList.size(); i++) {
                        // ,'6'
                        if (i != 0)
                            inSql.append(SQLConstant.SYMBOL_COMMA_KEY);
                        inSql.append(SQLConstant.SYMBOL_SINGLE_QUOTATION_MARKS_KEY);
                        inSql.append(inItemList.get(i));
                        inSql.append(SQLConstant.SYMBOL_SINGLE_QUOTATION_MARKS_KEY);
                    }
                    inSql.append(SQLConstant.SYMBOL_RIGHT_PARENTHESIS_KEY);
                }
            }

            result = inSql.toString();
            this.filterInSql = result;
        }
    }

    private void assembleCompareGreEquMap(Map condition) {
        this.setCompareGraEquMap((Map) condition.get((SQLConstant.COMPARES_GREATER_EQUAL_KEY)));
    }

    private void assembleCompareGreEquSql() {
        this.setCompareGreEquSql(this.assembleCompareSql(this.compareGraEquMap,
                SQLConstant.COMPARES_GREATER_EQUAL_STR_KEY));
    }

    private void assembleCompareGreMap(Map condition) {
        this.setCompareGraMap((Map) condition.get((SQLConstant.COMPARES_GREATER_KEY)));
    }

    private void assembleCompareGreSql() {
        this.setCompareGreSql(this.assembleCompareSql(this.compareGraMap, SQLConstant.COMPARES_GREATER_STR_KEY));
    }

    private void assembleCompareLessEquMap(Map condition) {
        this.setCompareLesEquMap((Map) condition.get((SQLConstant.COMPARES_LESS_EQUAL_KEY)));
    }

    private void assembleCompareLessEquSql() {
        this.setCompareLesEquSql(this
                .assembleCompareSql(this.compareLesEquMap, SQLConstant.COMPARES_LESS_EQUAL_STR_KEY));
    }

    private void assembleCompareLessMap(Map condition) {
        this.setCompareLesMap((Map) condition.get((SQLConstant.COMPARES_LESS_KEY)));
    }

    private void assembleCompareLessSql() {
        this.setCompareLesSql(this.assembleCompareSql(this.compareLesMap, SQLConstant.COMPARES_LESS_STR_KEY));
    }

    private String assembleCompareSql(Map comparesMap, String typeCode) {
        String result = "";
        StringBuffer comparesSql = new StringBuffer();
        if (comparesMap != null && comparesMap.size() > 0) {
            Iterator it = comparesMap.entrySet().iterator();
            String entKey = null;
            String entValue = null;
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (entry.getValue() != null && !entry.getValue().toString().equals("")
                        && !entry.getValue().toString().equals("null")) {
                    entKey = (String) entry.getKey();
                    entValue = (String) entry.getValue();
                    if (entValue != null && entValue.length() > 0) {

                        comparesSql.append(SQLConstant.SPACE_KEY);
                        comparesSql.append(SQLConstant.SQL_KEYWORD_AND_KEY);
                        comparesSql.append(SQLConstant.SPACE_KEY);
                        comparesSql.append(StringUtil.camel2Underline(entKey));
                        comparesSql.append(SQLConstant.SPACE_KEY);
                        comparesSql.append(typeCode);
                        comparesSql.append(SQLConstant.SPACE_KEY);
                        comparesSql.append(SQLConstant.SYMBOL_SINGLE_QUOTATION_MARKS_KEY);
                        comparesSql.append(entValue);
                        comparesSql.append(SQLConstant.SYMBOL_SINGLE_QUOTATION_MARKS_KEY);
                        comparesSql.append(SQLConstant.SPACE_KEY);
                    }
                }
            }
        }
        result = comparesSql.toString();
        return result;
    }
}

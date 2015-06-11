package com.neoye.rms.domain.infrastructure.constant;

public class SQLConstant {
	/**
	 * map-key keywords : "like" 
	 */
	public final static String KEYWORDS_KEY = "keywords";

	/**
	 * map-key filters : "="
	 */
	public final static String FILTERS_KEY = "filters";

	/**
	 * map-key filtersIn :in
	 */
	public final static String FILTERS_IN_KEY = "filtersIn";

	/**
     * map-key compare :比较 >=
     */
    public final static String COMPARES_GREATER_EQUAL_KEY = "comp_greate_equ";
    
    /**
     * map-key compare :比较>
     */
    public final static String COMPARES_GREATER_KEY = "comp_greate";
    
    /**
     * map-key compare :比较<=
     */
    public final static String COMPARES_LESS_EQUAL_KEY = "comp_less_equ";
    
    /**
     * map-key compare :比较<
     */
    public final static String COMPARES_LESS_KEY = "comp_less";
    
    /**
     * map-key compare :比较 >=
     */
    public final static String COMPARES_GREATER_EQUAL_STR_KEY = ">=";
    
    /**
     * map-key compare :比较>
     */
    public final static String COMPARES_GREATER_STR_KEY = ">";
    
    /**
     * map-key compare :比较<=
     */
    public final static String COMPARES_LESS_EQUAL_STR_KEY = "<=";
    
    /**
     * map-key compare :比较<
     */
    public final static String COMPARES_LESS_STR_KEY = "<";
    
    
	/**
	 * map-key field
	 */
	public final static String FIELD_KEY = "field";

	/**
	 * sql-keyword like
	 */
	public final static String SQL_KEYWORD_LIKE_KEY = "like";

	/**
	 * sql-keyword or
	 */
	public final static String SQL_KEYWORD_OR_KEY = "or";

	/**
	 * sql-keyword asc
	 */
	public final static String SQL_KEYWORD_ASC_KEY = "ASC";

	/**
	 * sql-keyword desc
	 */
	public final static String SQL_KEYWORD_DESC_KEY = "DESC";

	/**
	 * sql-keyword in
	 */
	public final static String SQL_KEYWORD_IN_KEY = "IN";

	/**
	 * map-key space
	 */
	public final static String SPACE_KEY = " ";

	/**
	 * map-key %
	 */
	public final static String SQL_SYMBOL_PERCENTAGE_KEY = "%";

	/**
	 * map-key '
	 */
	public final static String SYMBOL_SINGLE_QUOTATION_MARKS_KEY = "'";

	/**
	 * map-key (
	 */
	public final static String SYMBOL_LEFT_PARENTHESIS_KEY = "(";

	/**
	 * map-key )
	 */
	public final static String SYMBOL_RIGHT_PARENTHESIS_KEY = ")";

	/**
	 * map-key value
	 */
	public final static String VALUE_KEY = "value";

	/**
	 * map-key and
	 */
	public final static String SQL_KEYWORD_AND_KEY = "and";

	/**
	 * map-key lower
	 */
	public final static String SQL_KEYWORD_LOWER_KEY = "lower";

	/**
	 * map-key whereSql
	 */
	public final static String WHERESQL_KEY = "whereSql";

	/**
	 * map-key inSql
	 */
	public final static String IN_SQL_KEY = "inSql";

	/**
	 * map-key sorts
	 */
	public final static String SORTS_KEY = "sorts";

	/**
	 * map-key ,
	 */
	public final static String SYMBOL_COMMA_KEY = ",";

	/**
	 * map-key =
	 */
	public final static String SQL_KEYWORD_EQUAL_KEY = "=";

	/**
	 * map-key order
	 */
	public final static String ORDER_KEY = "order";

	/**
	 * map-key orderSql
	 */
	public final static String ORDERSQL_KEY = "orderSql";

	/**
	 * map-key page
	 */
	public final static String PAGE_KEY = "page";

	/**
	 * map-key record4Page
	 */
	public final static String RECORD_4_PAGE_KEY = "record4Page";

	
	/**
     * 不分页时，使用的默认数量
     */
    public final static int RECORD_4_PAGE_LIMIIT_NO_PAGE_KEY = 10000;

    
	/**
	 * map-key tmplId
	 */
	public final static String TMPL_ID_KEY = "tmplId";

	/**
	 * map-key tmplKey
	 */
	public final static String TMPL_KEY_KEY = "tmplKey";

	/**
	 * map-key is_top
	 */
	public final static String SQL_COLUMN_IS_TOP_KEY = "is_top";

	/**
	 * map-key conditionForm
	 */
	public final static String CONDITION_FORM_KEY = "conditionForm";

	/**
	 * map-key parameterForm
	 */
	public final static String PARAMETER_FORM_KEY = "parameterForm";

	/**
	 * map-key tap filter id 1
	 */
	public final static int FILTER_ID_TAP_KEY = 1;

	/**
	 * map-key transient filter id 2
	 */
	public final static int FILTER_ID_TRANSIENT_KEY = 2;

	/**
	 * map-key similar filter id 10
	 */
	public final static int FILTER_ID_SIMI_KEY = 10;

	/**
	 * map-key threshold filter id 4
	 */
	public final static int FILTER_ID_THRESHOLD_KEY = 4;
	//
	// /**
	// * map-key insId
	// */
	// public final static String INS_ID_KEY = "insId";
	//
	// public final static String KG_ID_KEY = "kgId";

	/**
	 * map-key defNo
	 */
	public final static String DEF_NO_KEY = "defNo";

	/**
	 * map-key loadedItems
	 */
	public final static String LOADED_ITEMS_KEY = "loadedItems";

	/**
	 * map-key fuzzy
	 */
	public final static String FUZZY_KEY = "fuzzy";

	public final static int FUZZY_SEARCH_FIELDS_DIC_TYPE_KEY = 122;

	/**
	 * map-key groupBySql
	 */
	public final static String GROUP_BY_SQL_KEY = "groupBySql";
	
	public final static String DEF_RECORD_4_PAGE_SWITCH_TYPE_KEY = "infrastructure.recorde4Page";
	/**
     * 码率
     */
	public final static String DEF_CODE_KEY = "evaluation.code";
}

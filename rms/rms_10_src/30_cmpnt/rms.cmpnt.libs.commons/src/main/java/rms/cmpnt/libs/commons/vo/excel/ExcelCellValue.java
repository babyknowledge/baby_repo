package rms.cmpnt.libs.commons.vo.excel;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

public class ExcelCellValue {
	
	public final double ONE_SECOND_IN_EXCEL  = 0.000011574075; // ÿ��֮��ļ��
	
	public final long START_DATE_IN_EXCEL = -2209190400000L; // ��ʼ����1899-12-30
	
	private Number num;
	
	private String str;
	
	private Boolean bool;
	
	public ExcelCellValue(){
		
	}
	
	public ExcelCellValue(double value) {
		setValue(value);
	}
	
	public ExcelCellValue(boolean value) {
		setValue(value);
	}
	
	public ExcelCellValue(String value) {
		setValue(value);
	}

	/**
	 * @param bool ���� num��str
	 */
	public void setValue(boolean bool) {
		// ���ò���ֵ
		setBool(new Boolean(bool));
		
		// �����ַ�ֵ
		String tempStr = String.valueOf(getBool());
		setStr(tempStr);
		
		// ������ֵ
		double tempNum = bool ? 0 : 1;
		setNum(new Double(tempNum));
	}

	/**
	 * @param num ���� num��str
	 */
	public void setValue(double num) {
		// ������ֵ
		setNum(new Double(num));
		
		// �����ַ�ֵ
        DecimalFormat format = new DecimalFormat("#");   
		String tempStr = format.format(getNum());
		setStr(new String(tempStr));
		
		// ���ò���ֵ
		Boolean tempBool = new Boolean(getStr());
		setBool(tempBool);
	}

	/**
	 * @param str ���� num��str
	 */
	public void setValue(String str) {
		// �����ַ�ֵ
		setStr(str);
		
		// ������ֵ
		double tempNum = 0;
		try {
			tempNum = Double.parseDouble(str);
		} catch (NumberFormatException e) {
			tempNum = 0.0;
		}
		setNum(new Double(tempNum));
		
		// ���ò���ֵ
		Boolean tempBool = new Boolean(getStr());
		setBool(tempBool);
	}
	
	public long getLongValue() {
		long longValue = getNum().longValue();
		return longValue;
	}

	public int getIntValue() {
		int intValue = getNum().intValue();
		return intValue;
	}
	
	public double getDoubleValue() {
		double doubleValue = getNum().doubleValue();
		return doubleValue;
	}
	
	public byte getByteValue() {
		byte byteValue = getNum().byteValue();
		return byteValue;
	}
	
	public short getShortValue() {
		short shortValue = getNum().shortValue();
		return shortValue;
	}
	
	public float getFloatValue() {
		float floatValue = getNum().floatValue();
		return floatValue;
	}
	
	public String getStringValue() {
		String stringValue = getStr();
		return stringValue.trim();
	}
	
	public boolean getBoolValue() {
		boolean booleanValue = getBool().booleanValue();
		return booleanValue;
	}
	
	public Date getDateValue() {
		Date dateValue = null;
		try {
			int date = getNum().intValue();
			int second = (int) ((getNum().doubleValue() - date) / ONE_SECOND_IN_EXCEL);
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date(START_DATE_IN_EXCEL));
			cal.add(Calendar.DATE, date);
			cal.add(Calendar.SECOND, second);
			dateValue = new Date(cal.getTimeInMillis());
		} catch (Exception e) {
			dateValue = new Date(0);
		}
		return dateValue;
	}

	/**
	 * @return ��ȡ num
	 */
	private Number getNum() {
		return num;
	}

	/**
	 * @return ��ȡ str
	 */
	private String getStr() {
		return str;
	}

	/**
	 * @param num ���� num
	 */
	private void setNum(Number num) {
		this.num = num;
	}

	/**
	 * @param str ���� str
	 */
	private void setStr(String str) {
		this.str = str;
	}

	/**
	 * @return ��ȡ bool
	 */
	private Boolean getBool() {
		return bool;
	}

	/**
	 * @param bool ���� bool
	 */
	private void setBool(Boolean bool) {
		this.bool = bool;
	}
}

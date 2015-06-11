package rms.cmpnt.libs.commons.exception;

import org.springframework.dao.DataAccessException;

public class BaseDataAccessException extends DataAccessException {

	public BaseDataAccessException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
